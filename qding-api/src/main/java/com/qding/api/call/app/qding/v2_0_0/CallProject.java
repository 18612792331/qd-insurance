package com.qding.api.call.app.qding.v2_0_0;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_2_0.struct.brick.ProjectService;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.Goods;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.Notice;
import com.qding.api.call.app.qding.v2_0_0.struct.project.ActivitySale;
import com.qding.api.call.app.qding.v2_0_0.struct.project.AuctionActivity;
import com.qding.api.call.app.qding.v2_0_0.struct.project.GrouponActivity;
import com.qding.api.call.app.qding.v1_4_1.struct.project.ProductSalePolicyBean;
import com.qding.api.call.app.qding.v2_0_0.struct.project.*;
import com.qding.api.call.app.qding.v2_0_0.struct.project.request.*;
import com.qding.api.call.app.qding.v2_0_0.struct.project.response.data.*;
import com.qding.api.call.app.qding.v2_8_0.struct.project.LifeServicesBoard;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.*;
import com.qding.auction.rpc.request.GetRecentAuctionRequest;
import com.qding.auction.rpc.response.GetRecentAuctionResponse;
import com.qding.auction.rpc.service.IAuctionRpcService;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.ServiceItem;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.ware.KeywordConfRemote;
import com.qding.brick.struts.request.ProjectAroundRequest;
import com.qding.brick.struts.response.ProjectAroundResponse;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.api.struct.AppDevice;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.groupon.remote.ProductRemoteService;
import com.qding.groupon.struct.bean.ProductBean;
import com.qding.groupon.struct.request.GetProductsRequest;
import com.qding.groupon.struct.response.GetProductsResponse;
import com.qding.hk.domain.ResNotice;
import com.qding.hk.rpc.request.notice.GetAppNoticeRequest;
import com.qding.hk.rpc.response.notice.GetAppNoticeResponse;
import com.qding.hk.rpc.service.INoticeRpcService;
import com.qding.hotcycle.dto.FeedDto;
import com.qding.hotcycle.service.IHotCycleRemoteService;
import com.qding.hotcycle.struct.request.GetCityIdleAvtivityFeedsRequest;
import com.qding.hotcycle.struct.response.GetCityIdleAvtivityFeedsResponse;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;
import com.qding.sysconfig.domain.IndexRecommendContentGoods;
import com.qding.sysconfig.dto.IndexRecommendDTO;
import com.qding.sysconfig.dto.PersonRecommendModel;
import com.qding.sysconfig.dto.SpecialWare;
import com.qding.sysconfig.params.PersonRecommendParams;
import com.qding.sysconfig.rpc.response.PersonRecommendResponse;
import com.qding.sysconfig.rpc.service.IndexRecommendRpcService;
import com.qding.sysconfig.rpc.service.PersonRecommendRpcService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.*;

/**
 * Created by qd on 2015/10/23.
 */
public class CallProject extends com.qding.api.call.app.qding.v1_4_1.CallProject {

    @Autowired
    private IHotCycleRemoteService hotCycleService;

    @Autowired
    private KeywordConfRemote keywordConfRemote;

    @Autowired
    private IndexRecommendRpcService indexRecommendRpcService;

    @Autowired
    private IAuctionRpcService auctionRpcService;

    @Autowired
    private ProductRemoteService grouponRpcService;

    @Autowired
    private PersonRecommendRpcService personRecommendRpcService;

    @Autowired
    private ISolrSkuService solrSku;

    @Autowired
    private AppConfigRemote appConfigRemote;

    @Autowired
    private INoticeRpcService noticeService;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private ProjectReadRemote projectReadService;

    private static final Logger logger = Logger.getLogger("CallProject");


    /**
     * 社区首页
     *
     * @param request
     * @return
     */
    @HTTP(alias = "index")
    @ExplainAnnotation(explain = "社区首页")
    @Deprecated
    public Response<GetProjectIndexResponseData> index(GetProjectIndexRequest request) {

        Response<GetProjectIndexResponseData> response = new Response<GetProjectIndexResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        String keyWord = "";

        try {
            String version = request.getAppDevice().getQdVersion();
            Map<String, String> skipConfigMap = null;
            if (QDStringUtil.isNotEmpty(version)) {
                version = version.trim();
                skipConfigMap = skipMode.selSkipTemplateMap(version);
            } else {
                logger.info("project index can't get version");
            }

            //获取搜索框关键字
            try {
                keyWord = keywordConfRemote.getByProjectId(Long.parseLong(request.getProjectId()));
                if (QDStringUtil.isEmpty(keyWord)) {
                    keyWord = "搜索商品或服务";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            ProjectIndex entity =fittingIndex(request.getProjectId(),request.getMemberId(),request.getAppDevice(),skipConfigMap);

            //常用工具组装
            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());
            if (QDStringUtil.isNotNull(brickSourceType)) {
                List<ServiceItem> serviceItems = null;
                try {
                    Long startTime = System.currentTimeMillis();
                    serviceItems = appConfigRemote.getIndexPageServiceItem(Long.parseLong(request.getProjectId()), brickSourceType,version);
                    List<ProjectService> services = transforList(ProjectService.class, serviceItems);
                    entity.setProjectServices(services);
                    Long endTime = System.currentTimeMillis();
                    logger.info("==========> get indexPageService total time:" + (endTime - startTime));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            GetProjectIndexResponseData data = new GetProjectIndexResponseData();
            entity.setKeyWord(keyWord);
            data.setEntity(entity);
            response.setData(data);

        } catch (Exception e) {
            return handleException(GetProjectIndexResponseData.class, e);
        }/* finally {
            boardMap = null;
            indexMap = null;
        }*/
        return response;
    }


    public ProjectIndex fittingIndex(String projectId,String memberId,AppDevice appDevice, Map<String, String> skipConfigMap){

        List<BoardImg> liftServiceBoardList = Lists.newArrayList();
        LifeServicesBoard lifeServicesBoard = new LifeServicesBoard();
        ProjectIndex entity = new ProjectIndex();
        String version = appDevice.getQdVersion();


        Map<String, BoardTmplBean> boardMap = new HashMap<String, BoardTmplBean>();
        Map<String, Integer> indexMap = new HashMap<String, Integer>();
        String recommendId = "";
        boolean isExist = false;
        boolean isOpenAppTest = false;

        Project project = projectReadService.get(Long.parseLong(projectId));
        try {

        //管家电话
        try {
            Long startTime = System.currentTimeMillis();
            fittingHouseKeeperPhone(project, entity);
            Long endTime = System.currentTimeMillis();
            logger.info("==========> get houseKeeperPhone total time:" + (endTime - startTime));
        } catch (Exception e) {
            logger.error("fittingHouseKeeperPhone fail ", e);
        }



        //公告
        try {
            Long startTime = System.currentTimeMillis();
            NoticeBoard noticeBoard = fittingNoticeBoard(skipConfigMap, project,memberId, version);
            entity.setNoticeBoard(noticeBoard);
            Long endTime = System.currentTimeMillis();
            logger.info("==========> get notice total time:" + (endTime - startTime));
        } catch (Exception e) {
            logger.error("fittingNoticeBoard fail ", e);
        }

        //乐购AB测试用板块
        try {
            LgRevealCategoryBoard lgRevealCategoryBoard = new LgRevealCategoryBoard();
            entity.setLgRevealCategoryBoard(lgRevealCategoryBoard);
            //// TODO: 2016/4/25 如果后期强升级为2.2及以上版本则取消该判断赋值
            String initVersion = skipMode.initVersion(version);
            if (Integer.parseInt(initVersion) >= 220000) {
                lgRevealCategoryBoard = fittingLgRevealCategoryBoard(skipConfigMap, projectId);
                if (QDStringUtil.isNotNull(lgRevealCategoryBoard)) {
                    entity.setLgRevealCategoryBoard(lgRevealCategoryBoard);
                    isOpenAppTest = true;
                }
            }

        } catch (Exception e) {
            logger.error("fittingLgRevealCategoryBoard fail ", e);

        }

        Long startTime = System.currentTimeMillis();
        Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, appDevice);
        List<IndexRecommendDTO> indexRecommendDTOList = indexRecommendRpcService.getIndexRecommend(projectId, salePlatform);
        List<BoardTmplBean> boardList = Lists.newArrayList();
        BoardTmplBean activityBean = new BoardTmplBean();
        Long endTime = System.currentTimeMillis();
        logger.info("==========> get  indexRecommend total time:" + (endTime - startTime));

        Long startTime1 = System.currentTimeMillis();
        for (IndexRecommendDTO indexRecommend : indexRecommendDTOList) {
            Integer sectionId = indexRecommend.getSectionId();
            BoardTmplBean boardTmplBean = null;
            switch (sectionId) {
                //亲子板块
                case 1:
                    try {
                        boardTmplBean = fittingBoard(skipConfigMap, indexRecommend,false);
                        boardList.add(boardTmplBean);
                    } catch (Exception e) {
                        logger.error("fittingFamily child fail ", e);
                    }
                    break;
                //旅游板块
                case 2:
                    recommendId = indexRecommend.getRecommendId();
                    isExist = boardMap.containsKey(recommendId);
                    //如果之前没有装在过同样recommendId的旅游信息，则新生成一个boardTmplBean
                    if (!isExist) {
                        try {
                            boardTmplBean = fittingBoard(skipConfigMap, indexRecommend,false);
                            int index = boardList.size();
                            boardList.add(boardTmplBean);
                            indexMap.put(recommendId, index);
                        } catch (Exception e) {
                            logger.error("fitting travel board is  fail ", e);
                        }

                    } else {//如果之前已经生成过相同recommendId的旅游信息
                        try {
                            BoardImg travelBoardImg = getBoardImg(skipConfigMap, indexRecommend,false);
                            boardTmplBean = boardMap.get(recommendId);
                            List<BoardImg> travelBoardImgLists = boardTmplBean.getImgList();
                            travelBoardImgLists.add(travelBoardImg);
                            boardTmplBean.setImgList(travelBoardImgLists);
                            int index = indexMap.get(recommendId);
                            boardList.set(index, boardTmplBean);
                        } catch (Exception e) {
                            logger.error("fitting travel board is  fail ", e);
                        }

                    }
                    boardMap.put(recommendId, boardTmplBean);

                    break;
                //生鲜板块
                case 3:
                    try {
                        boardTmplBean = fittingBoard(skipConfigMap, indexRecommend,false);
                        boardList.add(boardTmplBean);
                    } catch (Exception e) {
                        logger.error("fitting fresh board is  fail ", e);
                    }
                    break;

                //特色商品板块
                case 4:
                    try {
                        boardTmplBean = fittingSpecialWare(skipConfigMap, indexRecommend);
                        boardList.add(boardTmplBean);
                    } catch (Exception e) {
                        logger.error("fitting special ware board is  fail ", e);
                    }
                    break;

                //活动板块
                case 5:
                    try {
                        boardTmplBean = fittingActivity(indexRecommend, activityBean, projectId, salePlatform);
                        boardTmplBean.setUiTempType(indexRecommend.getUiTempType());
                        if (!indexMap.containsKey("activityBoardIndex")) {
                            int index = boardList.size();
                            indexMap.put("activityBoardIndex", index);
                            boardList.add(boardTmplBean);
                        } else {
                            int index = indexMap.get("activityBoardIndex");
                            boardList.set(index, boardTmplBean);
                        }
                    } catch (Exception e) {
                        logger.error("fitting activity  board is  fail ", e);
                    }
                    break;
                //运营楼层板块
                case 6:
                    recommendId = indexRecommend.getRecommendId();
                    isExist = boardMap.containsKey(recommendId);
                    //如果之前没有装在过同样recommendId的旅游信息，则新生成一个boardTmplBean
                    if (!isExist) {
                        try {
                            boardTmplBean = fittingBoard(skipConfigMap, indexRecommend,false);
                            int index = boardList.size();
                            boardList.add(boardTmplBean);
                            indexMap.put(recommendId, index);
                        } catch (Exception e) {
                            logger.error("fitting floor  board is  fail ", e);
                        }

                    } else {//如果之前已经生成过相同recommendId的旅游信息
                        try {
                            BoardImg floorBoardImg = getBoardImg(skipConfigMap, indexRecommend,false);
                            boardTmplBean = boardMap.get(recommendId);
                            List<BoardImg> floorBoardImgLists = boardTmplBean.getImgList();
                            floorBoardImgLists.add(floorBoardImg);
                            boardTmplBean.setImgList(floorBoardImgLists);
                            int index = indexMap.get(recommendId);
                            boardList.set(index, boardTmplBean);
                        } catch (Exception e) {
                            logger.error("fitting floor  board is  fail ", e);
                        }

                    }
                    boardMap.put(recommendId, boardTmplBean);
                    break;

                case 9: //乐购板块
                    if (isOpenAppTest) break;
                    try {
                        String initVersion = skipMode.initVersion(version);
                        boolean compatibleFlag_28 = false;
                        if (Integer.parseInt(initVersion) == 280000 && appDevice.getQdPlatform().toLowerCase().equals("ios")) {
                            compatibleFlag_28 = true;
                        }
                        boardTmplBean = fittingBoard(skipConfigMap, indexRecommend,compatibleFlag_28);
                        boardList.add(boardTmplBean);
                    } catch (Exception e) {
                        logger.error("fittingFamily child fail ", e);
                    }
                    break;

                case 10://生活服务板块 2.8
                    BoardImg boardImg = new BoardImg();
                    lifeServicesBoard.setName(indexRecommend.getSectionName());
                    lifeServicesBoard.setUiTempType(indexRecommend.getUiTempType());
                    boardImg.setId(indexRecommend.getRecommendId());
                    boardImg.setImageUrl(indexRecommend.getImageUrl());
                    boardImg.setImgTitle(indexRecommend.getTitle());
                    if  (indexRecommend.getExtendStatus()!= null && indexRecommend.getExtendStatus() ==1){
                        boardImg.setImgDesc(indexRecommend.getButtonTitle());
                    }
                    boardImg.setSkipModel(skipMode.fittingSkipUrl(version, indexRecommend.getUrl(),1,indexRecommend.getRecommendId(),1));
                    if( 1==  indexRecommend.getExtendStatus()){
                        boardImg.setBtnName(indexRecommend.getButtonTitle());
                    }
                    liftServiceBoardList.add(boardImg);

                default:
                    break;
            }

        }
        Long endTime1 = System.currentTimeMillis();

        //生活服务板块 2.8
        if(CollectionUtils.isNotEmpty(liftServiceBoardList)){
            lifeServicesBoard.setLifeServicesList(liftServiceBoardList);
            entity.setLifeServicesBoard(lifeServicesBoard);
        }

        //其他板块
        entity.setBoardList(boardList);
        logger.info("==========> get  fittingIndexRecommend total time:" + (endTime1 - startTime1));

      /*  try {
            Long startTime2 = System.currentTimeMillis();
            // 组装猜你你喜欢板块
            RecommendBoard recommendBoard = fittingRecommendGoods(skipConfigMap, projectId, memberId, 1, 10);
            entity.setRecommendBoard(recommendBoard);
            Long endTime2 = System.currentTimeMillis();
            logger.info("==========> get  fittingRecommendGoods total time:" + (endTime2 - startTime2));
        } catch (Exception e) {
            logger.error("fitting other  board is  fail ", e);
        }*/

         /* String initVersion = skipMode.initVersion(version);

            if (Integer.parseInt(initVersion) < 260000) {
            //组装同城闲置
            if (QDStringUtil.isNotNull(project) && 1 == project.getIsNeighbour()) {
                try {
                    Long startTime2 = System.currentTimeMillis();
                    ReplacementBoard replacementBoard = fittingFeedBoard(skipConfigMap, projectId);
                    entity.setReplacementBoard(replacementBoard);
                    Long endTime2 = System.currentTimeMillis();
                    logger.info("==========> get  fittingFeedBoard total time:" + (endTime2 - startTime2));
                } catch (Exception e) {
                    logger.error("fitting feed  board is  fail ", e);
                }
            }
         }*/
        }catch (Exception e) {
            logger.error("get project index fail ", e);
        }
        return entity;
    }

    /**
     * 管家电话
     *
     * @param
     * @param entity
     * @throws ServiceException
     */
    private void fittingHouseKeeperPhone(Project project, ProjectIndex entity) throws ServiceException {
        if (project == null) {
            throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "项目不存在");
        }
        com.qding.api.call.app.qding.v1_3_0.struct.brick.Project p =
                transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Project.class, project);
        Set<String> phoneSet = new HashSet<String>();

        List<ProjectConcat> projectConcats = p.getConcats();
        for (ProjectConcat projectConcat : projectConcats) {
            if ("1".equals(projectConcat.getType())) {
                String[] phoneStrings = projectConcat.getPhones();
                for (String phone : phoneStrings) {
                    phoneSet.add(phone);
                }
            }
        }
        List<String> phoneList = new ArrayList<String>();
        if (phoneSet.size() > 0) {
            phoneList.addAll(phoneSet);
            entity.setPhones(phoneList);
        }
    }

    /**
     * 首页社区公告板块
     *
     * @return
     * @throws Exception
     */
    public NoticeBoard fittingNoticeBoard(Map<String, String> skipConfigMap, Project project,String memberId, String version) throws Exception {
   
    	GetAppNoticeRequest appNoticeRequest = new GetAppNoticeRequest();
        appNoticeRequest.setProjectId(project.getId());
        appNoticeRequest.setMid(memberId);
        String initVersion = skipMode.initVersion(version);
        if(Integer.parseInt(initVersion)>=280000){ //如果是2.8版本
            //获取社区属性
            appNoticeRequest.setPageSize(project.getHomepageNoticeCount());
            appNoticeRequest.setPosition(31);//30：社区公告  1：表示管家公告  31物业公告  32 banner
        } else {
            appNoticeRequest.setPageSize(4);
            appNoticeRequest.setPosition(30);//30：社区公告  1：表示管家公告  31物业公告  32 banner
        }

        GetAppNoticeResponse noticeResponse = noticeService.getAppIndexNoticeList(appNoticeRequest);
        List<ResNotice> noticeList = noticeResponse.getNoticeList();
        List<Notice> notices = Lists.newArrayList();

        if (CollectionUtils.isNotEmpty(noticeList)) {
            for (ResNotice resNotice : noticeList) {
                Notice noticeBean = new Notice();
                String pic = resNotice.getBigUrl();
                if (QDStringUtil.isNotEmpty(pic)) {
                    String[] img = {pic};
                    noticeBean.setImgs(img);
                }
                noticeBean.setBillboardId(String.valueOf(resNotice.getId()));
                noticeBean.setCreateTime(resNotice.getCreateAt());
                noticeBean.setName(resNotice.getTitle());
                noticeBean.setNoticeType(resNotice.getNoticeType());
                noticeBean.setIsShowFootImage(resNotice.getIsShowFootImage());
                noticeBean.setIsShare(resNotice.getIsShare());
                //如果是社区活动，则获取url
                //如果有url
                if (QDStringUtil.isNotEmpty(resNotice.getUrl())) {
                    String skipStr = skipMode.fittingSkipUrl(skipConfigMap, resNotice.getUrl(), 1, QDStringUtil.isNotNull(resNotice.getIsShare()) ? resNotice.getIsShare() : 0
                            , resNotice.getShareTitle(), resNotice.getShareContent(), resNotice.getShareImageUrl(), resNotice.getUrl(), version,String.valueOf(resNotice.getId()));
                    noticeBean.setSkipModel(skipStr);
                } else {  //没有url，则拼接公告地址访问
                    String noticeUrl = APIPropertiesClient.getSkipUrl(Constant.SkipType.NOTICE_DETAIL.toString());
                    noticeUrl = noticeUrl + resNotice.getId();
                    String skipStr = skipMode.fittingSkipUrl(skipConfigMap, noticeUrl, 1, QDStringUtil.isNotNull(resNotice.getIsShare()) ? resNotice.getIsShare() : 0
                            , resNotice.getShareTitle(), resNotice.getShareContent(), resNotice.getShareImageUrl(), noticeUrl, version,String.valueOf(resNotice.getId()));
                    noticeBean.setSkipModel(skipStr);
                }
                notices.add(noticeBean);
            }
        } else {
            return null;
        }

        NoticeBoard noticeBoard = new NoticeBoard();
        noticeBoard.setNoticeTypeList(APIPropertiesClient.selNoticeTypeInfoBean());
        noticeBoard.setList(notices);
        String remindImg ="";
        try{
            remindImg = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_PROJECT_INDEX.getGroupName(),Constant.Dict_K_V_Enum.DICT_PROJECT_INDEX.getDictKey());
        }catch (Exception e) {

        }
        noticeBoard.setRemindImg(remindImg);
        String skipModelStr = skipMode.fittingNoParameterSkipModel(skipConfigMap, Constant.SkipNo.SQGG_1002.toInteger());
        noticeBoard.setSkipModel(skipModelStr);

        return noticeBoard;

    }




    /**
     * 组装同城闲置板块
     *
     * @param projectId
     * @return
     */
    private ReplacementBoard fittingFeedBoard(Map<String, String> skipConfigMap, String projectId) throws Exception {

        ReplacementBoard replacementBoard = new ReplacementBoard();
        GetCityIdleAvtivityFeedsRequest cityIdleAvtivityFeedsRequest = new GetCityIdleAvtivityFeedsRequest();
        cityIdleAvtivityFeedsRequest.setProjectId(projectId);
        GetCityIdleAvtivityFeedsResponse cityIdleAvtivityFeedsResponse = hotCycleService.getCityIdleActivityFeeds(cityIdleAvtivityFeedsRequest);

        if (HttpStatus.OK.getStatusCode() == cityIdleAvtivityFeedsResponse.getReturnInfo().getCode()) {

            String activityid = cityIdleAvtivityFeedsResponse.getActivityId();
            List<FeedDto> feedList = cityIdleAvtivityFeedsResponse.getList();
            List<SimplFeedBean> feedBeanList = Lists.newArrayList();
            if (QDStringUtil.isNotNull(feedList) && feedList.size() > 0) {
                for (FeedDto feedDto : feedList) {
                    SimplFeedBean simplFeedBean = new SimplFeedBean();
                    simplFeedBean.setUserHeadImageUrl(feedDto.getUserHeadImageUrl());
                    simplFeedBean.setInterval(RelativeDateFormat.format(feedDto.getPublishTime()));
                    simplFeedBean.setFeedImage(feedDto.getImgUrl());
                    simplFeedBean.setFeedId(feedDto.getFeedId());
                    simplFeedBean.setFeedContent(feedDto.getFeedContent());
                    simplFeedBean.setCommunityName(feedDto.getCommunityName());
                    feedBeanList.add(simplFeedBean);
                }
            }
            replacementBoard.setReplacementList(feedBeanList);
            if (QDStringUtil.isNotEmpty(activityid)) {
                String skipModeStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.HDXQ_3003.toInteger(), activityid);
                replacementBoard.setSkipModel(skipModeStr);
            }
        }
        return replacementBoard;
    }


    /**
     * 组装猜你喜欢板块
     *
     * @throws ServiceException
     */
    private RecommendBoard fittingRecommendGoods(Map<String, String> skipConfigMap, String projectId, String memberId, int pageNo, int pageSize) throws Exception {

        PersonRecommendParams personRecommendParams = new PersonRecommendParams();
        personRecommendParams.setMid(memberId);
        personRecommendParams.setProjectId(Long.parseLong(projectId));
        personRecommendParams.setPage(pageNo);
        personRecommendParams.setSize(pageSize);
        personRecommendParams.setIsIncludeTheme(true);

        Long startTime = System.currentTimeMillis();
        PersonRecommendResponse personRecommendResponse = personRecommendRpcService.getRecommendInfoForAPI(personRecommendParams);
        Long endTime = System.currentTimeMillis();
        logger.info("==========> get  personRecommendRpcService.getRecommendInfoForAPI total time:" + (endTime - startTime));
        checkAndContinue(personRecommendResponse);
        RecommendBoard recommendBoard = new RecommendBoard();
        recommendBoard.setTotalCount(personRecommendResponse.getTotalCount());
        List<PersonRecommendModel> personRecommendModelList = personRecommendResponse.getPersonRecommendModelList();
        List<RecommendGood> tmpRecommendGoodList = Lists.newArrayList();
        List<Long> sortedSkuIds = Lists.newArrayList();
        boolean firstIsRecommend = false;
        int i = 0;
        for (PersonRecommendModel personRecommendModel : personRecommendModelList) {

            Integer locationType = personRecommendModel.getLocationType();
            //如果是商品
            if (0 == locationType) {
                if (QDStringUtil.isNotNull(personRecommendModel.getSukId())) {
                    if (i == 0) firstIsRecommend = true;
                    sortedSkuIds.add(personRecommendModel.getSukId());
                }

            } else if (1 == locationType) {

                RecommendGood recommendGood = transfor(RecommendGood.class, personRecommendModel);
                //如果是商品列表
                if (22 == personRecommendModel.getType()) {
                    List<Long> personRecommendModelThemeSkuIds = personRecommendModel.getThemeSkuIds();
                    if (QDStringUtil.isNotNull(personRecommendModelThemeSkuIds) && personRecommendModelThemeSkuIds.size() > 0) {
                        String skipModeStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.SPLB_5000.toInteger(), ListUtil.listForLongToString(personRecommendModelThemeSkuIds));
                        recommendGood.setSkipModel(skipModeStr);
                    }
                }
                //如果是url
                if (10 == personRecommendModel.getType() && QDStringUtil.isNotNull(personRecommendModel.getUrl())) {
                    String skipModeStr = checkAndConvertSkipModeForGoodDetail(skipConfigMap, personRecommendModel.getUrl(),"");/* skipMode.fittingSkipUrl(skipConfigMap, personRecommendModel.getUrl(),1);*/
                    recommendGood.setSkipModel(skipModeStr);
                }
                recommendGood.setDesc(personRecommendModel.getDescription());
                recommendGood.setName(personRecommendModel.getName());
                Goods good = new Goods();
                good.setGoodsDesc("");
                recommendGood.setGood(good);
                recommendGood.setRecommendType(Constant.RECOMMEND_THEME_TYPE);//主题位类型
                tmpRecommendGoodList.add(recommendGood);
            }

            i++;
        }

        List<RecommendGood> goods = getGoods(skipConfigMap, sortedSkuIds, Long.parseLong(projectId));
        List<RecommendGood> recommendGoodList = Lists.newArrayList();
        //如果第一位是商品，且的确查到了商品信息，则第一位放置商品
        if (firstIsRecommend && CollectionUtils.isNotEmpty(goods)) {
            recommendGoodList.add(goods.get(0));
        }
        //第二位放置主题活动
        recommendGoodList.addAll(tmpRecommendGoodList);
        //把剩余的商品放到第三位及以后位置
        if (CollectionUtils.isNotEmpty(goods)) {
            if (firstIsRecommend) {//如果第一个位置已经放置了商品这里则从第二位取所有
                recommendGoodList.addAll(goods.subList(1, goods.size()));
            } else {//如果第一位部位商品，则直接将所有商品放入里面
                recommendGoodList.addAll(goods);
            }
        }
        recommendBoard.setRecommendList(recommendGoodList);

        return recommendBoard;
    }


    //批量获取推荐商品信息并组装
    private List<RecommendGood> getGoods(Map<String, String> skipConfigMap, List<Long> sortedSkuIds, Long projectId) {

        LegouSkuRequest skuRequest = new LegouSkuRequest();
        skuRequest.setProjectId(projectId);
        skuRequest.setFindSellNum(true);
        skuRequest.setSortedSkuIds(sortedSkuIds);
        LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);
        List<LegouSkuDetailInfo> legouSkuDetailInfos = skuResponse.getSkus();
        List<RecommendGood> recommendGoodList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(legouSkuDetailInfos)) {
            for (LegouSkuDetailInfo detailInfo : legouSkuDetailInfos) {
                RecommendGood recommendGood = new RecommendGood();
                Goods good = transfor(Goods.class, detailInfo);
                recommendGood.setGood(good);
                String skipModeStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.SPXQ_5004.toInteger(), detailInfo.getSkuId());
                recommendGood.setSkipModel(skipModeStr);
                recommendGoodList.add(recommendGood);
            }
        }
        return recommendGoodList;
    }


    //通用板块组装方法
    private BoardTmplBean fittingBoard(Map<String, String> skipConfigMap, IndexRecommendDTO indexRecommend,Boolean compatibleFlag) throws Exception {


        BoardImg boardImg = getBoardImg(skipConfigMap, indexRecommend,compatibleFlag);
        List<BoardImg> boardImgList = Lists.newArrayList();
        boardImgList.add(boardImg);
        BoardTmplBean boardTmplBean = new BoardTmplBean();
        boardTmplBean.setImgList(boardImgList);

        //只有非通用业态的才有外层更多
        if (indexRecommend.getSubType() != Constant.CB_SUB_TYPE) {

            //如果是亲子和旅游板块走html
            if (Constant.Section.QIN_ZI.toInteger() == indexRecommend.getSectionId() ||
                    Constant.Section.LV_YOU.toInteger() == indexRecommend.getSectionId()) {

                String skipStr = "";
                //如果是旅游签证酒店的暂时不添加更多链接
                if (QDStringUtil.isNotEmpty(indexRecommend.getMoreButtonSkip())) {
                    skipStr = checkAndConvertSkipModeForGoodDetail(skipConfigMap, indexRecommend.getMoreButtonSkip(),indexRecommend.getRecommendContentId());/*skipMode.fittingSkipUrl(skipConfigMap, indexRecommend.getMoreButtonSkip(),1);*/
                } else {
                    String url = APIPropertiesClient.getSkipUrl(indexRecommend.getSectionId() + "_more");
                    skipStr = checkAndConvertSkipModeForGoodDetail(skipConfigMap, url,indexRecommend.getRecommendContentId());/*skipMode.fittingSkipUrl(skipConfigMap, url,1);*/
                }

                boardTmplBean.setSkipModel(skipStr);

                //如果是生鲜板块走idskipmode
            } else if (Constant.Section.SHENG_XIAN.toInteger() == indexRecommend.getSectionId()) {
                String skipStr = "";
                if (QDStringUtil.isNotEmpty(indexRecommend.getMoreButtonSkip())) {
                    skipStr = checkAndConvertSkipModeForGoodDetail(skipConfigMap, indexRecommend.getMoreButtonSkip(),indexRecommend.getRecommendContentId())/*skipMode.fittingSkipUrl(skipConfigMap, indexRecommend.getMoreButtonSkip(),1)*/;
                } else {
                    skipStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.LGSY_5006.toInteger(), String.valueOf(Constant.ORDER_SX_CATEGORY));
                }
                boardTmplBean.setSkipModel(skipStr);

            } else if (Constant.Section.LG_CENG.toInteger() == indexRecommend.getSectionId()) {

                String typeId = indexRecommend.getLeGouTypeId();
                if (QDStringUtil.isEmpty(typeId)) {
                    //url
                    if (QDStringUtil.isNotEmpty(indexRecommend.getMoreButtonSkip())) {
                        boardTmplBean.setSkipModel(checkAndConvertSkipModeForGoodDetail(skipConfigMap, indexRecommend.getMoreButtonSkip(),indexRecommend.getRecommendContentId())/*skipMode.fittingSkipUrl(skipConfigMap, indexRecommend.getMoreButtonSkip(),1)*/);
                    } else {
                        boardTmplBean.setSkipModel(checkAndConvertSkipModeForGoodDetail(skipConfigMap, indexRecommend.getUrl(),indexRecommend.getRecommendContentId())/*skipMode.fittingSkipUrl(skipConfigMap, indexRecommend.getUrl(),1)*/);
                    }

                } else {
                    String skipStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.LGSY_5006.toInteger(), typeId);
                    boardTmplBean.setSkipModel(skipStr);
                }

            }
        }
        //最外层跳转
        boardTmplBean.setUiTempType(indexRecommend.getUiTempType());
        boardTmplBean.setTitle(indexRecommend.getTitle());
        boardTmplBean.setDesc(indexRecommend.getSubTitle());
        boardTmplBean.setBtnName(indexRecommend.getMoreButtonTitle());

        return boardTmplBean;

    }

    /**
     * 组装图片信息
     *
     * @param indexRecommend
     * @return
     */
    private BoardImg getBoardImg(Map<String, String> skipConfigMap, IndexRecommendDTO indexRecommend,Boolean compatibleFlag) throws Exception {

        BoardImg boardImg = new BoardImg();
        List<IndexRecommendContentGoods> goodsList = indexRecommend.getSkuIdList();

        //如果是非通用业态类型的推荐信息
        if (indexRecommend.getSubType() != Constant.CB_SUB_TYPE) {

            //图片按钮跳转方式 （如果有url则跳转方式为url）
            if (/*Constant.Section.LG_CENG.toInteger() != indexRecommend.getSectionId() &&*/ QDStringUtil.isNotEmpty(indexRecommend.getUrl())) {
                String skipStr = checkAndConvertSkipModeForGoodDetail(skipConfigMap, indexRecommend.getUrl(),indexRecommend.getRecommendContentId());//skipMode.fittingSkipUrl(skipConfigMap, indexRecommend.getUrl(),1);
                boardImg.setSkipModel(skipStr);

            } else if (QDStringUtil.isNotNull(goodsList) && goodsList.size() > 0) {
                String skipModeStr = "";
                if (indexRecommend.getSectionId() == Constant.Section.SHENG_XIAN.toInteger()) { //如果是生鲜板块
                    skipModeStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.SPXQ_5004.toInteger(), goodsList.get(0).getSkuId());
                } else {
                    StringBuilder skuIds = new StringBuilder();
                    for (int i = 0; i < goodsList.size(); i++) {
                        IndexRecommendContentGoods good = goodsList.get(i);
                        skuIds.append(good.getSkuId());
                        if (i + 1 < goodsList.size()) {
                            skuIds.append(",");
                        }
                    }
                    //乐购板块针对唯一商品，skipMode跳转详情
                    if (indexRecommend.getSectionId() == Constant.Section.LG_CENG.toInteger() && goodsList.size() == 1) {
                        skipModeStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.SPXQ_5004.toInteger(), skuIds.toString());
                    } else {
                        if(compatibleFlag){ //兼容2.8版本IOS APP出错，这里走url
                            String url = APIPropertiesClient.getUrlContent("skip_good_list");
                            skipModeStr = skipMode.fittingSkipUrl(skipConfigMap,url+skuIds.toString(),0,0,indexRecommend.getRecommendContentId());
                        } else{
                            skipModeStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.SPLB_5000.toInteger(), skuIds.toString());

                        }
                    }
                }
                boardImg.setSkipModel(skipModeStr);
            }
        } else {
            if (goodsList.size() > 1) { //列表
                String recommendContentId = indexRecommend.getRecommendContentId();
                String url = APIPropertiesClient.getSkipUrl("good_list_CB");
                StringBuffer urlSb = new StringBuffer(url);
                urlSb.append(recommendContentId);
                urlSb.append("?type=" + indexRecommend.getBusinessTemplateId());
                String skipStr = checkAndConvertSkipModeForGoodDetail(skipConfigMap, urlSb.toString(),indexRecommend.getRecommendContentId());
                boardImg.setSkipModel(skipStr);
            } else if (goodsList.size() == 1) {//详情
                String url = APIPropertiesClient.getSkipUrl("good_detail_CB");
                StringBuffer urlSb = new StringBuffer(url);
                IndexRecommendContentGoods indexRecommendContentGoods = goodsList.get(0);
                urlSb.append(indexRecommendContentGoods.getSkuId());
                String skipStr = checkAndConvertSkipModeForGoodDetail(skipConfigMap, urlSb.toString(),indexRecommend.getRecommendContentId());
                boardImg.setSkipModel(skipStr);
            }
        }

        //如果是运营楼层
        if (Constant.Section.LOU_CENG.toInteger() == indexRecommend.getSectionId()) {
            boardImg.setImgTitle(indexRecommend.getActivityTitle());
            boardImg.setImgDesc(indexRecommend.getActivityDesc());
        }
        //图片路径和按钮标题
        boardImg.setImageUrl(indexRecommend.getImageUrl());
        boardImg.setBtnName(indexRecommend.getButtonTitle());
        return boardImg;
    }


    /**
     * 特色板块模块组装
     *
     * @return
     */
    public BoardTmplBean fittingSpecialWare(Map<String, String> skipConfigMap, IndexRecommendDTO indexRecommend) throws Exception {

        BoardTmplBean specialWareBoardBean = new BoardTmplBean();
        List<BoardImg> imgList = Lists.newArrayList();
        List<IndexRecommendContentGoods> goodsList = indexRecommend.getSkuIdList();
        List<Long> skuIds = Lists.newArrayList();
        Map<Long, String> indexRecommendGoodNameMap = new HashMap<Long, String>();
        for (IndexRecommendContentGoods goods : goodsList) {
            skuIds.add(Long.valueOf(goods.getSkuId()));
            if (QDStringUtil.isNotEmpty(goods.getSkuName())) {
                indexRecommendGoodNameMap.put(Long.valueOf(goods.getSkuId()), goods.getSkuName());
            }
        }

        LegouSkuRequest skuRequest = new LegouSkuRequest();
        skuRequest.setSortedSkuIds(skuIds);
        LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);
        List<LegouSkuDetailInfo> legouSkuDetailInfoList = skuResponse.getSkus();
        for (LegouSkuDetailInfo legouSkuDetailInfo : legouSkuDetailInfoList) {
            BoardImg boardImg = new BoardImg();
            String skuName = indexRecommendGoodNameMap.get(Long.valueOf(legouSkuDetailInfo.getSkuId()));
            if (QDStringUtil.isNotEmpty(skuName)) {
                boardImg.setImgTitle(skuName);//如果后台配置了skuName就用配置的
            } else {
                boardImg.setImgTitle(legouSkuDetailInfo.getName());//如果后台没有配置别名就用solr里的
            }
            if (QDStringUtil.isNotNull(legouSkuDetailInfo.getWareImgUrl())) {
                String[] imgs = legouSkuDetailInfo.getWareImgUrl();
                if (QDStringUtil.isNotNull(imgs) && imgs.length > 0) {
                    String waterMarkImg = com.qding.api.call.app.qding.v1_3_0.CallLegouGoods.getGoodsWaterMarkImg("detail", false, imgs[0], legouSkuDetailInfo.getWatermarkUrl());
                    boardImg.setImageUrl(waterMarkImg);
                }
            }
            String skipModeStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.SPXQ_5004.toInteger(), legouSkuDetailInfo.getSkuId());
            boardImg.setSkipModel(skipModeStr);
            imgList.add(boardImg);
        }

        specialWareBoardBean.setImgList(imgList);

        SpecialWare specialWare = indexRecommendRpcService.findSpecialWareByRecommendId(indexRecommend.getRecommendId());
        List<String> moreSkuIds = specialWare.getSkuIds();
        StringBuilder moreSkuIdsBuf = new StringBuilder();
        for (int i = 0; i < moreSkuIds.size(); i++) {
            moreSkuIdsBuf.append(moreSkuIds.get(i));
            if (i + 1 < goodsList.size()) {
                moreSkuIdsBuf.append(",");
            }
        }
        String skipModeStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.TSSP_5005.toInteger(), indexRecommend.getRecommendId());
        //最外层更多跳转
        specialWareBoardBean.setSkipModel(skipModeStr);
        specialWareBoardBean.setUiTempType(indexRecommend.getUiTempType());
        specialWareBoardBean.setTitle(indexRecommend.getTitle());
        specialWareBoardBean.setDesc(indexRecommend.getSubTitle());
        specialWareBoardBean.setBtnName(QDStringUtil.isEmpty(indexRecommend.getMoreButtonTitle()) && QDStringUtil.isNotEmpty(skipModeStr) ? "更多" : indexRecommend.getMoreButtonTitle());
        return specialWareBoardBean;
    }


    /**
     * 组装乐购信息 (APP test 临时接口)
     *
     * @return
     */
    public LgRevealCategoryBoard fittingLgRevealCategoryBoard(Map<String, String> skipConfigMap, String projectId) {

        String status = null;
        try {
            status = DictionaryClient.getClient().findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_APP_TEST.getGroupName(),Constant.Dict_K_V_Enum.DICT_APP_TEST.getDictKey());
            if (!"open".equals(status.toLowerCase())) return null;

            String projectIds = APIPropertiesClient.getValue("home_le_projects");
            if (QDStringUtil.isNotNull(projectIds)) {
                List projectList = Arrays.asList(projectIds.split(","));
                if (!projectList.contains(projectId)) return null;
            }

            LgRevealCategoryBoard lgBoardBean = new LgRevealCategoryBoard();
            List<BoardImg> imgList = Lists.newArrayList();
            List<LgConfigBean> lgConfigBeanList = APIPropertiesClient.fittingHomLgConfigBean();

            for (LgConfigBean lgConfigBean : lgConfigBeanList) {
                BoardImg boardImg = new BoardImg();
                String skipStr = "";
                if (QDStringUtil.isNotEmpty(lgConfigBean.getRevealCategoryId())) {
                    skipStr = skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.LGSY_5006.toInteger(), lgConfigBean.getRevealCategoryId());
                }

                boardImg.setSkipModel(skipStr);
                boardImg.setImageUrl(lgConfigBean.getImgUrl());
                boardImg.setImgTitle(lgConfigBean.getTitle());
                imgList.add(boardImg);
            }

            lgBoardBean.setImgList(imgList);
            return lgBoardBean;
        } catch (TException e) {

            return null;

        }

    }


    /**
     * 活动板块组装
     *
     * @param indexRecommend
     * @return
     */
    public BoardTmplBean fittingActivity(IndexRecommendDTO indexRecommend, BoardTmplBean activityBoardBean, String projectId, Integer salePlatform) throws Exception {

        Integer activityType = indexRecommend.getType();

        //如果是夺宝
        if (3 == activityType) {
            try {
                List<AuctionActivity> auctionActivityList = activityBoardBean.getAuctionActivity();
                GetRecentAuctionRequest getRecentAuctionRequest = new GetRecentAuctionRequest();
                GetRecentAuctionResponse recentAuction = auctionRpcService.getRecentAuction(getRecentAuctionRequest);
                AuctionActivity auctionActivity = new AuctionActivity();
                if (HttpStatus.OK.getStatusCode() == (recentAuction.getReturnInfo().getCode())) {
                    auctionActivity = transfor(AuctionActivity.class, recentAuction.getAuctionInfo());
                    auctionActivity.setImageUrl(recentAuction.getAuctionInfo().getImageUrl2());
                    if (QDStringUtil.isNotNull(recentAuction.getAuctionOrder())) {
                        auctionActivity.setLastPriceFen(recentAuction.getAuctionOrder().getCurrentPriceFen());
                    }
                    Integer activityStatus = auctionActivity.getAuctionStatus();
                    if (1 == activityStatus) {//如果是进行状态
                        auctionActivity.setUrl(APIPropertiesClient.getUrl("auction") + "/" + auctionActivity.getId());
                    } else if (3 == activityStatus) {//如果是结束状态
                        auctionActivity.setUrl(APIPropertiesClient.getUrl("history.auction"));
                    }
                }
                auctionActivityList.add(auctionActivity);
                activityBoardBean.setAuctionActivity(auctionActivityList);
            } catch (Exception ex) {

            }

        } else if (2 == activityType || 4 == activityType) {  //如果是秒杀或是url活动

            ActivitySale activitySale = new ActivitySale();
            activitySale.setImageUrl(indexRecommend.getImageUrl());
            activitySale.setUrl(indexRecommend.getUrl());
            activitySale.setType(10);
            activitySale.setDesc(indexRecommend.getSubTitle());
            activitySale.setTitle(indexRecommend.getTitle());
            List<ActivitySale> miaoshaActivityList = activityBoardBean.getMiaoshaActivity();
            miaoshaActivityList.add(activitySale);
            activityBoardBean.setMiaoshaActivity(miaoshaActivityList);

        } else if (1 == activityType) {   //如果是阶梯团购

            try {
                GrouponActivity grouponActivity = new GrouponActivity();
                List<GrouponActivity> grouponActivityList = activityBoardBean.getGrouponActivity();
                GetProductsRequest getProductRequest = new GetProductsRequest();
                getProductRequest.setProjectId(projectId);
                getProductRequest.setPageSize(1);
                getProductRequest.setPageNo(1);
                GetProductsResponse getProductResponse = grouponRpcService.getProducts(getProductRequest);
                if (getProductResponse.getProduct().size() > 0 && getProductResponse.getCurrentCount() > 0) {
                    ProductBean productBean = getProductResponse.getProduct().get(0);
                    grouponActivity = transfor(GrouponActivity.class, productBean);
                    grouponActivity.setNum(productBean.getSaleNum());
                    List<com.qding.groupon.struct.bean.ProductSalePolicyBean> lists = productBean.getProductSalePolicyList();
                    grouponActivity = initStage(grouponActivity, productBean.getSaleNum(), lists);
                    grouponActivity.setUrl(APIPropertiesClient.getUrl("groupon") + "/" + productBean.getId());
                    List<String> imagez = grouponActivity.getImagez();
                    String imgUrl = imagez.get(0) + "?imageMogr2/gravity/Center/crop/400x400";
                    if (QDStringUtil.isNotNull(productBean.getFirstImg())) {
                        imgUrl = productBean.getFirstImg();
                    }
                    imagez.set(0, imgUrl);
                }

                grouponActivityList.add(grouponActivity);
                activityBoardBean.setGrouponActivity(grouponActivityList);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return activityBoardBean;
    }


    /**
     * 初始化团购阶段
     *
     * @param grouponActivity
     * @param currentNum
     * @param lists
     * @return
     */
    public GrouponActivity initStage(GrouponActivity grouponActivity, int currentNum, List<com.qding.groupon.struct.bean.ProductSalePolicyBean> lists) {

        if (lists.size() > 0) {

            List<ProductSalePolicyBean> policies = Lists.newArrayList();

            for (int i = 0; i < lists.size(); i++) {

                com.qding.groupon.struct.bean.ProductSalePolicyBean sourceBean = lists.get(i);
                ProductSalePolicyBean productSalePolicyBean1 = transfor(ProductSalePolicyBean.class, sourceBean);
                int fromNum = sourceBean.getFromNum();

                if (currentNum < fromNum) {
                    int surplus = fromNum - (currentNum <= 0 ? 0 : currentNum);
                    productSalePolicyBean1.setDesc("差" + surplus + "件");
                    productSalePolicyBean1.setStatus(0);
                } else {
                    productSalePolicyBean1.setDesc("已达成");
                    productSalePolicyBean1.setStatus(1);
                }
                productSalePolicyBean1.setStage(i + 1);
                policies.add(productSalePolicyBean1);
            }
            grouponActivity.setPolicies(policies);
        }

        return grouponActivity;
    }

    /**
     * 获取推荐商品(首页猜你喜欢下划分页)
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getRecommendGoods")
    @ExplainAnnotation(explain = "获取推荐商品(首页猜你喜欢下划分页)")
    public Response<GetRecommendBoardResponseData> getRecommendGoods(GetRecommendBoardRequest request) {

        Response<GetRecommendBoardResponseData> response = new Response<GetRecommendBoardResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetRecommendBoardResponseData data = new GetRecommendBoardResponseData();
        try {
            String version = request.getAppDevice().getQdVersion();
            Map<String, String> skipConfigMap = null;
            if (QDStringUtil.isNotEmpty(version)) {
                skipConfigMap = skipMode.selSkipTemplateMap(version);
            }
            RecommendBoard recommendBoard = fittingRecommendGoods(skipConfigMap, request.getProjectId(), request.getMemberId(), request.getPageNo(), request.getPageSize());
            data.setRecommendBoard(recommendBoard);
            response.setData(data);

        } catch (Exception e) {
            return handleException(GetRecommendBoardResponseData.class, e);
        }
        return response;
    }

    /**
     * 根据板块ID和社区ID获取指定板块的列表信息
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getBoardBySectionIdAndProjectId")
    @ExplainAnnotation(explain = "根据板块ID和社区ID获取指定板块的列表信息")
    public Response<GetRecommendBoardBySectionIdAndProjectIdResponseData> getBoardBySectionIdAndProjectId(GetRecommendBoardBySectionIdAndProjectIdRequest request) {

        Response<GetRecommendBoardBySectionIdAndProjectIdResponseData> response = new Response<GetRecommendBoardBySectionIdAndProjectIdResponseData>();
        String version = request.getAppDevice().getQdVersion();
        Map<String, String> skipConfigMap = null;
        if (QDStringUtil.isNotEmpty(version)) {
            version = version.trim();
            skipConfigMap = skipMode.selSkipTemplateMap(version);
        } else {
            logger.info("project index can't get version");
        }
        List<IndexRecommendDTO> indexRecommendDTOList = indexRecommendRpcService.getIndexRecommendsBySectionIdAndProjectId(request.getProjectId(), request.getSectionId());
        List<BoardTmplBean> boardList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(indexRecommendDTOList)) {
            for (IndexRecommendDTO indexRecommend : indexRecommendDTOList) {
                BoardTmplBean boardTmplBean = null;
                try {
                    boardTmplBean = fittingBoard(skipConfigMap, indexRecommend,false);
                    boardList.add(boardTmplBean);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        GetRecommendBoardBySectionIdAndProjectIdResponseData data = new GetRecommendBoardBySectionIdAndProjectIdResponseData();
        data.setList(boardList);
        data.setTotalCount(Long.parseLong(boardList.size() + ""));
        response.setData(data);


        return response;
    }


    @HTTP(alias = "getProjectAroundInfo")
    @ExplainAnnotation(explain = "获取社区周边商铺信息")
    public Response<GetProjectAroundInfoResponseData> getProjectAroundInfo(GetProjectAroundInfoRequest request) {

        Response<GetProjectAroundInfoResponseData> response = new Response<GetProjectAroundInfoResponseData>();
        GetProjectAroundInfoResponseData data = new GetProjectAroundInfoResponseData();
        try {
            ProjectAroundRequest projectAroundRequest = transfor(ProjectAroundRequest.class, request);
            projectAroundRequest.setPage(request.getPageNo());
            projectAroundRequest.setSize(request.getPageSize());
            ProjectAroundResponse projectAroundResponse = projectReadService.getProjectAroundInfo(projectAroundRequest);
            checkAndContinue(projectAroundResponse);
            data = transfor(GetProjectAroundInfoResponseData.class, projectAroundResponse);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            return response;

        } catch (ServiceException e) {
            return handleException(GetProjectAroundInfoResponseData.class, e);
        }
    }


    @HTTP(alias = "getProjectAroundInfoById")
    @ExplainAnnotation(explain = "获取社区周边指定商铺信息")
    public Response<GetProjectAroundInfoByIdResponseData> getProjectAroundInfo(GetProjectAroundInfoByIdRequest request) {

        Response<GetProjectAroundInfoByIdResponseData> response = new Response<GetProjectAroundInfoByIdResponseData>();
        GetProjectAroundInfoByIdResponseData data = new GetProjectAroundInfoByIdResponseData();
        try {
            ProjectAroundRequest projectAroundInfoByIdRequest = transfor(ProjectAroundRequest.class, request);
            ProjectAroundResponse projectAroundResponse = projectReadService.getProjectAroundData(projectAroundInfoByIdRequest);
            checkAndContinue(projectAroundResponse);
            ProjectAround entity = transfor(ProjectAround.class, projectAroundResponse.getProjectAroundData());
            data.setEntity(entity);
            response.setCode(HttpStatus.OK.getStatusCode());
            response.setData(data);
            return response;

        } catch (ServiceException e) {
            return handleException(GetProjectAroundInfoByIdResponseData.class, e);
        }
    }


    /**
     * 商品详情访问如果是url则转为原生方式
     *
     * @param skipConfigMap
     * @param url
     *  recommendId 2.8用于点击统计使用
     * @return
     */
    private String checkAndConvertSkipModeForGoodDetail(Map<String, String> skipConfigMap, String url,String recommendId) {

        try {
            if (url.contains("shopping/details/")) {
                int index = url.lastIndexOf("/");
                if (index < url.length() - 1) {
                    String skuId = url.substring(index + 1, url.length());
                    return skipMode.fittingSkipModelByOnlyId(skipConfigMap, Constant.SkipNo.SPXQ_5004.toInteger(), skuId);
                }
            }
            return skipMode.fittingSkipUrl(skipConfigMap, url, 1, 0,recommendId);
        } catch (Exception ex) {
            return skipMode.fittingSkipUrl(skipConfigMap, url, 1, 0,recommendId);
        }
    }

}
