package com.qding.api.call.app.qding.v4_0_0;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v2_0_0.struct.project.BoardImg;
import com.qding.api.call.app.qding.v2_0_0.struct.project.NoticeBoard;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SalesBoard;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsPromotion;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.ProjectService;
import com.qding.api.call.app.qding.v3_0_0.struct.project.*;
import com.qding.api.call.app.qding.v3_0_0.struct.project.request.GetProjectIndexRequest;
import com.qding.api.call.app.qding.v3_0_1.struct.groupon.GradientPriceDto;
import com.qding.api.call.app.qding.v3_2_0.struct.project.GrouponActivity;
import com.qding.api.call.app.qding.v4_0_0.struct.project.BannerBoard;
import com.qding.api.call.app.qding.v4_0_0.struct.project.CustomBoard;
import com.qding.api.call.app.qding.v4_0_0.struct.project.CustomDTO;
import com.qding.api.call.app.qding.v4_0_0.struct.project.IndexBanner;
import com.qding.api.call.app.qding.v4_0_0.struct.project.InteractBoard;
import com.qding.api.call.app.qding.v4_0_0.struct.project.ProjectIndex;
import com.qding.api.call.app.qding.v3_2_0.struct.project.WelfareBoard;
import com.qding.api.call.app.qding.v4_0_0.struct.chatRecord.response.data.GetProjectIndexResponseData;
import com.qding.api.call.service.BrickService;
import com.qding.api.call.service.MemberGiftService;
import com.qding.api.call.service.NeighborService;
import com.qding.api.call.service.PromotionService;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.DateUtil;
import com.qding.api.util.EntitySerialUtil;
import com.qding.api.util.ListUtil;
import com.qding.api.util.QDVersionUtil;
import com.qding.api.util.SkipBean;
import com.qding.api.util.SkipModeFitting;
import com.qding.api.verifycode.store.RedisStoreDevice;
import com.qding.api.verifycode.store.StoreDevice;
import com.qding.brick.dto.SellBest;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.ServiceItem;
import com.qding.brick.remote.biz.AppConfigRemote;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.ware.KeywordConfRemote;
import com.qding.brick.remote.ware.WareRemoteService;
import com.qding.business.service.IRemoteOrderService;
import com.qding.business.struct.bean.ProductDetailBean;
import com.qding.business.struct.request.GetProductByIdRequest;
import com.qding.business.struct.response.GetProductByIdResponse;
import com.qding.compositebusiness.remote.qdapp.IQdAppRemote;
import com.qding.compositebusiness.struct.qdapp.bean.GetProductForAppIndexBean;
import com.qding.compositebusiness.struct.qdapp.request.GetProductForAppIndexRequest;
import com.qding.compositebusiness.struct.qdapp.response.GetProductForAppIndexResponse;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.api.struct.AppDevice;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.message.constant.MsgConstant;
import com.qding.message.domain.MsgMessage;
import com.qding.message.service.IMessageService;
import com.qding.message.struct.request.MsgInfoRequest;
import com.qding.message.struct.response.MsgInfoResponse;
import com.qding.message.util.Page;
import com.qding.neighbor.v3.dto.TopicDto;
import com.qding.neighbor.v3.rpc.ITopicRpcV3;
import com.qding.neighbor.v3.rpc.requst.GetTopicBytopicIdRequest;
import com.qding.neighbor.v3.rpc.response.data.GetTopicBytopicIdResponseData;
import com.qding.promotion.common.domain.PromotionGrouponPrice;
import com.qding.promotion.common.dto.PromotionDto;
import com.qding.promotion.common.dto.PromotionGrouponConfigDto;
import com.qding.promotion.common.service.IPromotionRemoteService;
import com.qding.promotion.common.struct.request.GetPromotionDetailRequest;
import com.qding.promotion.common.struct.request.GetPromotionGrouponDetailRequest;
import com.qding.promotion.common.struct.response.GetPromotionDetailResponse;
import com.qding.promotion.common.struct.response.GetPromotionGrouponDetailResponse;
import com.qding.solr.service.ISolrSkuService;
import com.qding.solr.struct.request.LegouSkuRequest;
import com.qding.solr.struct.response.LegouSkuResponse;
import com.qding.solr.struct.sku.LegouSkuDetailInfo;
import com.qding.sysconfig.dto.AppHomeConfigDto;
import com.qding.sysconfig.dto.AppHomeSectionDto;
import com.qding.sysconfig.rpc.request.AppHomeConfigRequest;
import com.qding.sysconfig.rpc.response.AppHomeConfigResponse;
import com.qding.sysconfig.rpc.service.AppHomeConfigRpcService;
import com.qding.thrift.model.dictionary.Dictionary;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.util.*;

import static com.qding.dictionary.client.DictionaryClient.findDictValueByGroupNameAndDictKey;

/**
 * Created by zhangguofeng on 18/4/18.
 */
@PropertySource({"classpath:config.properties"})
public class CallProject extends com.qding.api.call.app.qding.v3_2_0.CallProject {

    private static final Logger logger = Logger.getLogger("CallProject");

    @Resource
    private SkipModeFitting skipMode;

    @Resource
    private AppHomeConfigRpcService appHomeConfigRpcService;

    @Resource
    private ProjectReadRemote projectReadService;

    @Resource
    private KeywordConfRemote keywordConfRemote;

    @Resource
    private ISolrSkuService solrSku;

    @Resource
    private IPromotionRemoteService promotionRemoteService;

    @Resource
    private ITopicRpcV3 topicRpcV3;

    @Resource
    private IRemoteOrderService businessOrderService;

    @Resource
    private AppConfigRemote appConfigRemote;

    @Resource
    private WareRemoteService wareRemoteService;

    @Resource
    private ISolrSkuService skuService;

    @Autowired
    private PromotionService promotionService;

    @Resource
    private IQdAppRemote qdAppRemoteService;

    @Autowired
    private MemberGiftService memberGiftService;
    
    @Autowired
    private NeighborService neighborService;

    @Autowired
    private BrickService brickService;
    
    @Resource
    private IMessageService messageService;

    @Value("${EVALUATE_INTEGRAL}")
    private String domain;
    
    

    @HTTP(alias = "index", isNeadProject = true, isNeedSign = false)
    @ExplainAnnotation(explain = "社区首页")
    public Response<GetProjectIndexResponseData> getProjectIndex4(GetProjectIndexRequest request, UserToken userToken) {
    	Response<GetProjectIndexResponseData> response = new Response<GetProjectIndexResponseData>();
    	GetProjectIndexResponseData data = new GetProjectIndexResponseData();

    	ProjectIndex projectIndex = new ProjectIndex();
        String version = request.getAppDevice().getQdVersion();

        String memberId = "";
        if (QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId())) {
            memberId = userToken.getMemberId();
        }
        String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
        Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(version);
        StoreDevice storeDevice = new RedisStoreDevice();
        String key = "projectIndex:v:p:m:%s:%s:%s";
        String cacheKey = String.format(key, initVersion, request.getAppUser().getProjectId(), memberId);
        String cachString = storeDevice.getCode(cacheKey);
        if (QDStringUtil.isNotEmpty(cachString)) {
            try {
                response = JSON.parseObject(cachString, new TypeReference<Response<GetProjectIndexResponseData>>() {
                });
                response.getData().getEntity().setRemindBoard(fittingRemindBoard(skipModelMap, userToken, request.getLastFreshTime(),null,response.getData().getEntity().getRemindBoard()));
                return response;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {

            String curVersion = appConfigRemote.getCurVersion(request.getAppDevice().getQdVersion());
            Integer salePlatform = Constant.transforPlatformByAppDevice(Constant.salePlatformMap, request.getAppDevice());
            AppHomeConfigRequest appHomeConfigRequest = new AppHomeConfigRequest();
            appHomeConfigRequest.setProjectId(Long.parseLong(request.getAppUser().getProjectId()));
            appHomeConfigRequest.setType(salePlatform);
            appHomeConfigRequest.setVersion(curVersion);
            if (QDStringUtil.isNotNull(userToken)){
                appHomeConfigRequest.setMemberId(userToken.getMemberId());
            }
            AppHomeConfigResponse appHomeConfigResponse = appHomeConfigRpcService.getAppHomeConfigByRequest(appHomeConfigRequest);
            checkAndContinue(appHomeConfigResponse);
            List<AppHomeSectionDto> sectionDtoList = appHomeConfigResponse.getAppHomeSectionDtos();
            Project project = projectReadService.get(Long.parseLong(request.getAppUser().getProjectId()));

            for (AppHomeSectionDto appHomeSectionDto : sectionDtoList) {

                String sectionCode = appHomeSectionDto.getCode(); //板块编码值
                String sectionName = appHomeSectionDto.getName();//板块名称
                List<AppHomeConfigDto> appHomeConfigs = appHomeSectionDto.getAppHomeConfigs();//板块具体配置项

                if (CollectionUtils.isEmpty(appHomeConfigs) && !Constant.noConfigBoardList.contains(sectionCode))
                    continue;//如果当前板块没有配置信息则跳过

                if (Constant.AH_GS.equals(sectionCode)) { // 福利铺

                    if (CollectionUtils.isNotEmpty(appHomeConfigs)) {
                        WelfareBoard welfareBoard = new WelfareBoard();
                        List<WelfareGoodsDTO> welfareGoodsList = Lists.newArrayList();
                        GrouponActivity grouponActivity = null;
                        for (AppHomeConfigDto appHomeConfig : appHomeConfigs) {
                            Integer configType = appHomeConfig.getConfigType();
                            // 1.阶梯团购
                            if (configType == 1) {
                                grouponActivity = getGrouponActivityBoard(project, skipModelMap, appHomeSectionDto, appHomeConfig, memberId);
                                grouponActivity.setName(appHomeConfig.getName());
                                grouponActivity.setImgUrl(appHomeConfig.getImgUrl());
                                grouponActivity.setId(appHomeConfig.getProductId());
                                welfareBoard.setGrouponActivity(grouponActivity);
                            } else {
                                WelfareGoodsDTO welfareGoods = fittingWelfareBoard(request, project, skipModelMap, appHomeSectionDto, appHomeConfig);
                                if (QDStringUtil.isNotNull(welfareGoods)) {
                                    if (appHomeConfig.getConfigType() == 7 || appHomeConfig.getConfigType() == 8) {
                                        welfareGoods.setUiType(1);
                                    } else {
                                        welfareGoods.setUiType(2);
                                    }
                                    welfareGoodsList.add(welfareGoods);
                                }
                            }
                        }
                        if (CollectionUtils.isNotEmpty(welfareGoodsList) || QDStringUtil.isNotNull(grouponActivity)) {
                            welfareBoard.setTitle(sectionName);
                            welfareBoard.setSectionCode(sectionCode);
                            welfareBoard.setSortIndex(appHomeSectionDto.getIndex());
                            welfareBoard.setWelfareGoodsList(welfareGoodsList);
                            projectIndex.setWelfareBoard(welfareBoard);
                        }
                    }

                } else if (Constant.AH_SF.equals(sectionCode)) {  //居家服务
                    LifeServicesBoard lifeServicesBoard = fittingLifeServicesBoard(project, skipModelMap, appHomeSectionDto, appHomeConfigs);
                    projectIndex.setLifeServicesBoard(lifeServicesBoard);

                } else if (Constant.AH_NT.equals(sectionCode)) { //公告
                    NoticeBoard noticeBoard = null;
                    try {
                        noticeBoard = fittingNoticeBoard(skipModelMap, project,memberId, request.getAppDevice().getQdVersion());
                        if (QDStringUtil.isNotNull(noticeBoard)) {
                            noticeBoard.setSortIndex(appHomeSectionDto.getIndex());
                            noticeBoard.setSectionCode(sectionCode);
                            projectIndex.setNoticeBoard(noticeBoard);
                        }
                    } catch (Exception e) {
                        logger.error("fittingNoticeBoard fail ", e);
                    }
                } else if (Constant.AH_PBS_4.equals(sectionCode)) { //基础服务
                    ProjectServicesBoard projectServicesBoard = fittingBasicServices(project, request.getAppDevice(), skipModelMap, appHomeSectionDto);
                    projectIndex.setBasicServices(projectServicesBoard);

                } else if (Constant.AH_PPS_4.equals(sectionCode)) { //业态服务
                    ProjectServicesBoard projectServicesBoard = fittingCustomServices(project, request.getAppDevice(), curVersion,skipModelMap, appHomeSectionDto);
                    projectIndex.setCustomServices(projectServicesBoard);

                } else if (Constant.AH_RI_4.equals(sectionCode)) { //轮播
                	BannerBoard bannerBoard = fittingBannerBoard(skipModelMap, appHomeSectionDto);
                    projectIndex.setBannerBoard(bannerBoard);

                } else if (Constant.AH_HS.equals(sectionCode) &&  !brickService.isAnnualMettingProject(request.getAppUser().getProjectId())) {
                	//同城热卖
                    CityWideRecommendBoard cityWideRecommendBoard = fittingCityWideRecommendBoard(project, skipModelMap, appHomeSectionDto);
                    projectIndex.setCityWideRecommendBoard(cityWideRecommendBoard);
                } else if (Constant.AH_FP.equals(sectionCode)) {
                    //4营销入口
                    ProductMarketBoard productMarketBoard = fittingProductMarketBoard(memberId,project, skipModelMap, appHomeSectionDto, appHomeConfigs);
                    projectIndex.setProductMarket(productMarketBoard);
                } else if (Constant.AH_LV.equals(sectionCode)) {
                    //出游玩乐
                    PlayBoard playBoard = fittingPlayBoard(project, skipModelMap, appHomeSectionDto, appHomeConfigs);
                    projectIndex.setPlayBoard(playBoard);
                } else if (Constant.CUSTOM_SECTION.equals(sectionCode)) {
                	//自定义楼层
                	CustomBoard customBoard = fittingCustomBoard(skipModelMap, appHomeSectionDto);
                    projectIndex.setCustomBoard(customBoard);
                } else if (Constant.AH_LLHD_4.equals(sectionCode)) {
                	//组装邻里互动
                    InteractBoard interactBoard = fittingInteractBoard(project, skipModelMap, appHomeSectionDto);
                    projectIndex.setInteractBoard(interactBoard);
                } else if (Constant.AH_RC.equals(sectionCode)) {
                	 projectIndex.setRemindBoard(fittingRemindBoard(skipModelMap, userToken, request.getLastFreshTime(),appHomeSectionDto,null));
                }
            }
            
            String keyWord = "";
            //获取搜索框关键字
            try {
                keyWord = keywordConfRemote.getByProjectId(project.getId());
                if (QDStringUtil.isEmpty(keyWord)) {
                    if (QDVersionUtil.getVersionCode(request.getAppDevice().getQdVersion()) < QDVersionUtil.VERSION_330){
                        keyWord = "搜索商品或服务";
                    } else {
                        keyWord = "搜索商品、服务、旅游";
                    }

                }
            } catch (Exception e) {
                logger.error("keyword fail ", e);
            }
            projectIndex.setKeyWord(keyWord);
            
            //管家电话
            projectIndex.setPhones(fittingHouseKeeperPhone(project));

            //首页签到入口
            projectIndex.setSignInEntry(fittingSignInEntry(skipModelMap));

        } catch (Exception e) {
            logger.error("service error ", e);
            return handleException(GetProjectIndexResponseData.class, e);
        }

        data.setEntity(projectIndex);
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());

        logger.info("app redis cache ===缓存未含有获取登陆后首页信息.... ......");

        try {
            String expirat = findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_APPCACHE_1.getGroupName(),Constant.Dict_K_V_Enum.DICT_APPCACHE_1.getDictKey());
            storeDevice.setCode(cacheKey, EntitySerialUtil.serial(response), Integer.parseInt(expirat));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("app redis cache ===" + JSON.toJSONString(request) + " : 缓存数据失败");
        }

        return response;
    }

    /**
     * 首页签到入口
     *
     * @param skipModelMap
     * @return
     */
    private SignInEntry fittingSignInEntry(Map<String, String> skipModelMap) {
        boolean showSignIn = false;
        String skipUrl = "";
        try {
            String data = findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_SWITCH_4.getGroupName(),Constant.Dict_K_V_Enum.DICT_SWITCH_4.getDictKey());
            if ("open".equals(data)) {
                showSignIn = true;
                 skipUrl = skipMode.fittingSkipUrl(skipModelMap, APIPropertiesClient.getUrlContent("h5_domain") +"/checkin", 1, 0, "");
            } else {
                 skipUrl = skipMode.fittingSkipUrl(skipModelMap, APIPropertiesClient.getUrlContent("member_center_url"), 0, 0, "");
            }
        } catch (Exception ex) {
        }
        SignInEntry signInEntry = new SignInEntry();
        signInEntry.setShow(showSignIn);
        signInEntry.setSkipModel(skipUrl);
        return signInEntry;
    }
    
    private InteractBoard fittingInteractBoard(Project project,
			Map<String, String> skipModelMap,
			AppHomeSectionDto appHomeSectionDto) {
    	InteractBoard board = new InteractBoard();
		try {
            String projectStr = APIPropertiesClient.getValue("neighbor_project_blacklist");
            String[] projectArray = projectStr.split(",");
            if(QDStringUtil.isNotNull(projectArray) && projectArray.length>0){
                List<String> searchTypeList = Arrays.asList(projectArray);
                if (searchTypeList.contains(String.valueOf(project.getId()))) {
                    return board;
                }
            }

            //获取邻里互动标签列表
            board.setTagList(neighborService.getInteractonTagList(project, skipModelMap));
            board.setBoardTitle("邻里互动");
            String skip = skipMode.fittingNoParameterSkipModel(skipModelMap,
                    Constant.SkipNo.LJGC_3012.toInteger());
            board.setSkipModel(skip); //邻里互动列表页
        } catch (Exception ex) {
            logger.error("class :NeighborService ,method:fittingInteractBoard is error :",ex);
        }
		board.setSectionCode(appHomeSectionDto.getCode());
        board.setSortIndex(appHomeSectionDto.getIndex());
        return board;
	}

	private ProjectServicesBoard fittingCustomServices(Project project,
			AppDevice appDevice,String version, Map<String, String> skipModelMap,
			AppHomeSectionDto appHomeSectionDto) {
    	ProjectServicesBoard projectServicesBoard = new ProjectServicesBoard();
        try {
            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, appDevice);
            if (QDStringUtil.isNotNull(brickSourceType)) {
                List<ServiceItem> serviceItems = null;
                serviceItems = appConfigRemote.getIndexCustomServiceItem_4_0(project.getId(), brickSourceType, version);
                
                if (CollectionUtils.isNotEmpty(serviceItems)) {
                    List<ProjectService> services = Lists.newArrayList();
                    for (int i = 0; i < serviceItems.size(); i++) {
                    	if (i==4) {
                    		break;
                    	}
                        ServiceItem serviceItem = serviceItems.get(i);
                        try {
                            ProjectService projectService = transfor(ProjectService.class, serviceItem);
                            //填充角色身份3.2新增
                            fillRoomRole(serviceItem,projectService);
                            projectService.setImageUrl(serviceItem.getImgUrlColor());
                            projectService.setServiceId(String.valueOf(serviceItem.getId()));
                            String skipStr = "";
                            if (QDStringUtil.isNull(serviceItem.getSkipNo())) {
                                logger.error("get project service error :" + JSON.toJSONString(serviceItem));
                                continue;
                            }

                            SkipBean skipBean = new SkipBean();
                            skipBean.setProjectName(project.getName());
                            skipBean.setProjectId(String.valueOf(project.getId()));
                            skipBean.setIds(String.valueOf(serviceItem.getId()));
                            skipBean.setSkipNo(Integer.parseInt(serviceItem.getSkipNo()));
                            StringBuffer identity = new StringBuffer(Constant.DETAL_IDENTITY);
                            if (QDStringUtil.isNotNull(serviceItem.getAccessPrivilege())){
                                identity.setLength(0);
                                identity.append(serviceItem.getAccessPrivilege());
                            }
                            skipBean.setIdentity(identity.toString());
                            skipBean.setPcode( serviceItem.getPrivilegeType().intValue()==0?1:3);

                            if (Integer.parseInt(serviceItem.getSkipNo()) == Constant.SkipNo.URL_7000.toInteger()) {
                                skipBean.setSkipUrl(serviceItem.getContant());
                                skipStr = skipMode.fittingSkipUrl(skipModelMap, skipBean);
                            } else {
                                skipStr = skipMode.fittingSkipModelBySkipBean(skipModelMap,skipBean);
                            }
                            projectService.setSkipModel(skipStr);
                            services.add(projectService);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            outIndexLog(appHomeSectionDto, project, ex, JSON.toJSONString(serviceItem));
                            continue;
                        }
                    }
                    projectServicesBoard.setProjectServices(services);
                    projectServicesBoard.setSectionCode(appHomeSectionDto.getCode());
                    projectServicesBoard.setSortIndex(appHomeSectionDto.getIndex());

                }
            }
        	
        } catch (Exception ex) {
            ex.printStackTrace();
            outIndexLog(appHomeSectionDto, project, ex, "");
        }
        return projectServicesBoard;
	}

	private BannerBoard fittingBannerBoard(Map<String, String> skipModelMap,
			AppHomeSectionDto appHomeSectionDto) {
    	BannerBoard bannerBoard = new BannerBoard();
    	if (CollectionUtils.isNotEmpty(appHomeSectionDto.getAppHomeConfigs())) {
    		List<IndexBanner> banneList = new ArrayList<IndexBanner>();
    		for (AppHomeConfigDto appHomeConfig : appHomeSectionDto.getAppHomeConfigs()) {
    			IndexBanner indexBanner = new IndexBanner();
    			indexBanner.setBannerId(appHomeConfig.getId().toString());
    			indexBanner.setBannerName(appHomeConfig.getName());
    			indexBanner.setBannerImg(appHomeConfig.getImgUrl());
    			indexBanner.setSkipModel(skipMode.fittingSkipUrl(skipModelMap,appHomeConfig.getContentUrl(),0,0,""));
    			banneList.add(indexBanner);
			}
    		bannerBoard.setBannerList(banneList);
    		bannerBoard.setSectionCode(appHomeSectionDto.getCode());
    		bannerBoard.setSortIndex(appHomeSectionDto.getIndex());
    	}
		return bannerBoard;
	}


    private CustomBoard fittingCustomBoard(Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto){

    	CustomBoard board = new CustomBoard();
        List<AppHomeConfigDto> appHomeConfigs = appHomeSectionDto.getAppHomeConfigs();
        if (CollectionUtils.isNotEmpty(appHomeConfigs)){
        	List<CustomDTO> customDTOList = Lists.newArrayList();
            for (AppHomeConfigDto appHomeConfig : appHomeConfigs) {
        		CustomDTO custom = new CustomDTO();
        		custom.setId(appHomeConfig.getId().toString());
        		custom.setTitle(appHomeConfig.getName());
        		custom.setActivityTag(appHomeConfig.getDisplayTag());
        		custom.setDesc(appHomeConfig.getDescription());
        		custom.setGoodsImg(appHomeConfig.getImgUrl());
        		custom.setPlaceIndex(appHomeConfig.getCustomSectionTagPosition());
        		custom.setPrice(appHomeConfig.getPromotionPrice());
        		custom.setOriginalPrice(appHomeConfig.getMarketPrice());
        		if (StringUtils.isNoneBlank(appHomeConfig.getContentUrl())) {
        			custom.setSkipModel(skipMode.fittingSkipUrl(skipModelMap,appHomeConfig.getContentUrl(),0,0,""));
        		} else{
        			custom.setSkipModel(skipMode.fittingSkipModelByOnlyId(skipModelMap,
                            Constant.SkipNo.SPXQ_5004.toInteger(), appHomeConfig.getProductId()));
        		}
        		if(appHomeConfig.getLocation()==1){
                    board.setCustom(custom);
            	}else{
            		customDTOList.add(custom);
            	}
            }
            board.setList(customDTOList);
        }
        board.setSectionCode(appHomeSectionDto.getCode());
        board.setSortIndex(appHomeSectionDto.getIndex());
        board.setTitle(appHomeSectionDto.getName());
        return board;
    }

    /**
     * 出游玩乐
     *
     * @param project
     * @param skipModelMap
     * @param appHomeSectionDto
     * @param appHomeConfigs
     * @return
     */
    private PlayBoard fittingPlayBoard(Project project, Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto, List<AppHomeConfigDto> appHomeConfigs) {
        PlayBoard playBoard = new PlayBoard();
        List<BoardImg> playList = Lists.newArrayList();
        for (AppHomeConfigDto appHomeConfig : appHomeConfigs) {
            try {
                BoardImg playDto = new BoardImg();
                playDto.setImgTitle(appHomeConfig.getName());
                playDto.setImgDesc(appHomeConfig.getDescription());
                playDto.setImageUrl(appHomeConfig.getImgUrl());
                playDto.setSkipModel(fittingSkipUrl(appHomeConfig, skipModelMap));
                playDto.setTagName(appHomeConfig.getTagName());
                playList.add(playDto);
            } catch (Exception ex) {
                ex.printStackTrace();
                outIndexLog(appHomeSectionDto, project, ex, JSON.toJSONString(appHomeConfig));
                continue;
            }
        }
        if (CollectionUtils.isNotEmpty(playList)) {
            playBoard.setSectionCode(appHomeSectionDto.getCode());
            playBoard.setSortIndex(appHomeSectionDto.getIndex());
            playBoard.setPlayList(playList);
        }
        return playBoard;
    }

    /**
     * 首页4营销入口
     *
     * @param project
     * @param skipModelMap
     * @param appHomeSectionDto
     * @param appHomeConfigs
     * @return
     */
    private ProductMarketBoard fittingProductMarketBoard(String curMemberId,Project project, Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto, List<AppHomeConfigDto> appHomeConfigs) {
        ProductMarketBoard productMarketBoard = new ProductMarketBoard();
        List<BoardImg> productList = Lists.newArrayList();
        for (AppHomeConfigDto appHomeConfig : appHomeConfigs) {
            try {
                BoardImg product = new BoardImg();
                product.setImgTitle(appHomeConfig.getName());
                product.setImgDesc(appHomeConfig.getPromotionTagContent());
                product.setImageUrl(appHomeConfig.getImgUrl());
                product.setSkipModel(fittingSkipUrl(appHomeConfig, skipModelMap));
                product.setTagName(appHomeConfig.getTagName());
                productList.add(product);
            } catch (Exception ex) {
                ex.printStackTrace();
                outIndexLog(appHomeSectionDto, project, ex, JSON.toJSONString(appHomeConfig));
                continue;
            }
        }

        //临时增加特殊社区判断 赵今需求
        boolean hasTempProject =false;
        try {
            Dictionary dictionary = DictionaryClient.getDictionaryByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_PROJECT_DELIVERY.getGroupName(),String.valueOf(project.getId()));
            if(QDStringUtil.isNotNull(dictionary) && QDStringUtil.isNotEmpty(dictionary.getGroupName()) && QDStringUtil.isNotEmpty(dictionary.getDictValue()) && dictionary.getStatus()==1){
                String[] configArray = dictionary.getDictValue().split("\\|\\|");
                BoardImg product = new BoardImg();
                product.setImgTitle(configArray[0]);
                product.setImgDesc("");
                product.setImageUrl(configArray[2]);
                product.setSkipModel(skipMode.fittingSkipUrl(skipModelMap,configArray[3],1, 0, ""));
                product.setTagName(configArray[1]);
                productList.add(product);
                hasTempProject = true;
            }
        } catch (TException e) {
            logger.error("首页第四运营位显示特殊社区场景异常:",e);
        }


        if(!hasTempProject)  {
            //是否含有会员专享任务
            boolean hasTask = appHomeSectionDto.isHasMemberPromotion();
            //新手礼包部分
            try {
                String giftSwitch = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_SWITCH_3.getGroupName(),Constant.Dict_K_V_Enum.DICT_SWITCH_3.getDictKey());
                if ("open".equals(giftSwitch) && !hasTask ){
                    boolean canShow = memberGiftService.checkGiftPackage( curMemberId, project.getId());
                    if (canShow) {
                        BoardImg product = new BoardImg();
                        product.setImgTitle("新手礼包");
                        product.setImgDesc("");
                        product.setImageUrl(Constant.GIFT_IMG);
                        product.setSkipModel(skipMode.fittingSkipUrl(skipModelMap, APIPropertiesClient.getValue("gift_index"),1, 0, ""));
                        product.setTagName("");
                        productList.add(product);
                    }
                }
            } catch (Exception e) {
                logger.error("get gift swith error",e);
            }
        }

        if (CollectionUtils.isNotEmpty(productList)) {
            productMarketBoard.setSectionCode(appHomeSectionDto.getCode());
            productMarketBoard.setSortIndex(appHomeSectionDto.getIndex());
            productMarketBoard.setProductList(productList);
        }
        return productMarketBoard;
    }


    /*************************************
     * 私有工具方法
     ********************************************/

    /**
     * 消息浮层板块组装
     *
     * @param userToken
     * @param lastFreshTime
     * @return
     */
    private RemindBoard fittingRemindBoard(Map<String, String> skipModelMap, UserToken userToken, Long lastFreshTime,AppHomeSectionDto appHomeSectionDto,RemindBoard board) {
    	RemindBoard remindBoard = new RemindBoard();
        if (board != null) {
        	remindBoard.setSectionCode(board.getSectionCode());
        	remindBoard.setSortIndex(board.getSortIndex());
        }
        if (appHomeSectionDto!=null) {
        	remindBoard.setSectionCode(appHomeSectionDto.getCode());
        	remindBoard.setSortIndex(appHomeSectionDto.getIndex());
        }
        if (QDStringUtil.isNotNull(userToken)) {
            try {
                String memberId = userToken.getMemberId();
                MsgInfoRequest msgInfoRequest = new MsgInfoRequest();
                msgInfoRequest.setMid(memberId);
                Long beginForDay = DateUtil.getDayBeginTimestamp();
                if (QDStringUtil.isNull(lastFreshTime) || beginForDay > lastFreshTime) {
                    msgInfoRequest.setTimeStamp(beginForDay);
                } else {
                    msgInfoRequest.setTimeStamp(lastFreshTime);
                }
                String msgTypeStr = findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_NOTIFY_TYPES.getGroupName(),Constant.Dict_K_V_Enum.DICT_NOTIFY_TYPES.getDictKey());
                msgInfoRequest.setType(msgTypeStr);
                msgInfoRequest.setPageNo(1);
                msgInfoRequest.setPageSize(1);
                msgInfoRequest.setAppType(MsgConstant.APP_TYPE_QDING);
                logger.info(" project  index get notice remind rpc msgInfoRequest :" + JSON.toJSONString(msgInfoRequest));
                MsgInfoResponse msgInfoResponse = messageService.getMsgList(msgInfoRequest);
                logger.info(" project  index get notice remind rpc MsgInfoResponse :" + JSON.toJSONString(msgInfoResponse));
                Page<MsgMessage> page = msgInfoResponse.getPageList();
                List<MsgMessage> msgList = page.getList();
                if (CollectionUtils.isNotEmpty(msgList)) {
                    MsgMessage msgMessage = msgList.get(0);
                    Long msgType = msgMessage.getType();
                    try {
                        String imgUrl = findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_NOTIFY_IMG.getGroupName(), Constant.APP_NOTIFY_MEIQIA.toString().equals(String.valueOf(msgType)) ? "notify_img_2" : "notify_img_3");
                        String userName = findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_NOTIFY_USER.getGroupName(), Constant.APP_NOTIFY_MEIQIA.toString().equals(String.valueOf(msgType)) ? "notify_user_2" : "notify_user_3");
                        remindBoard.setSysNotify(msgMessage.getTitle());
                        remindBoard.setImg(imgUrl);
                        remindBoard.setName(userName);
                        remindBoard.setTotalCount(page.getTotalRow());

                        String skipStr = "";
                        if (Long.parseLong(Constant.APP_NOTIFY_MEIQIA) == msgMessage.getType()) { //美洽
                            skipStr = skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.MEIQI_4206.toInteger(), msgMessage.getContent());
                            remindBoard.setName("");

                        } else if (Long.parseLong(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM) == msgMessage.getType()) {
                            JSONObject detailJson = JSON.parseObject(msgMessage.getDetail());
                            if (QDStringUtil.isNotNull(detailJson)) {
                                com.qding.api.util.SkipBean skipBean = new com.qding.api.util.SkipBean();
                                String curVersion = "2.6.0";
                                if (QDStringUtil.isNotNull(detailJson.getString("cid"))) {
                                    skipBean.setProjectId(detailJson.getString("cid"));
                                    skipBean.setProjectName(detailJson.getString("cname"));
                                    curVersion = "2.7.0";
                                }
                                skipBean.setPcode(9);
                                skipBean.setSkipNo(4201);
                                skipStr = skipMode.fittingSkipModelBySkipBean(curVersion, skipBean);
                            }

                        } else if (Long.parseLong(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM_H5) == msgMessage.getType()) {
                            JSONObject detailJson = JSON.parseObject(msgMessage.getDetail());
                            if (QDStringUtil.isNotNull(detailJson)) {
                                String url = String.valueOf(detailJson.get("url"));
                                String curTime = String.valueOf(detailJson.get("curTime"));
                                if (QDStringUtil.isNotEmpty(url))
                                    skipStr = skipMode.fittingSkipUrl("2.7.0", url + "?pushid=" + curTime, 0, "");
                            }
                        } else if (Long.parseLong(Constant.APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM_V3) == msgMessage.getType()) {
                            JSONObject detailJson = JSON.parseObject(msgMessage.getDetail());
                            if (QDStringUtil.isNotNull(detailJson)) {
                                String id = String.valueOf(detailJson.get("id"));
                                skipStr = skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.BINDMEMBERLIST_4207.toInteger(), id);
                            }

                        } else if (Long.parseLong(MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_TOURISM_LINE)==msgMessage.getType()) {
                        	JSONObject detailJson = JSON.parseObject(msgMessage.getDetail());
                        	skipStr = skipMode.fittingSkipUrl(skipModelMap,detailJson.getString("url"),0,0,"");
                        } else { //乐购相关
                            JSONObject detailJson = JSON.parseObject(msgMessage.getDetail());
                            skipStr = skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.DDXQ_4008.toInteger(), detailJson.getString("orderId"));
                        }

                        remindBoard.setSkipModel(skipStr);

                    } catch (Exception ex) {
                        ex.printStackTrace();
                        logger.error("projectIndex get msg remind  error", ex);
                    }
                }
            } catch (TException e) {
                e.printStackTrace();
            }
        }
        return remindBoard;
    }


    /**
     * 阶梯团购板块组装
     *
     * @param skipModelMap
     * @param appHomeConfig
     * @return
     */
    private GrouponActivity getGrouponActivityBoard(Project project, Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto, AppHomeConfigDto appHomeConfig, String memberId) {

        GrouponActivity grouponActivity = new GrouponActivity();//阶梯团购
        try {
            GetPromotionGrouponDetailRequest getPromotionGrouponDetailRequest = new GetPromotionGrouponDetailRequest();
            getPromotionGrouponDetailRequest.setProjectId(project.getId());
            getPromotionGrouponDetailRequest.setPromotionGrouponId(Long.valueOf(appHomeConfig.getProductId()));
            getPromotionGrouponDetailRequest.setMid(memberId);
            GetPromotionGrouponDetailResponse getPromotionGrouponDetailResponse = promotionRemoteService.getPromotionGrouponDetail(getPromotionGrouponDetailRequest);
            if(getPromotionGrouponDetailResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode())
            {
                PromotionGrouponConfigDto promotionGrouponConfigDto = getPromotionGrouponDetailResponse.getPromotionGrouponConfigDto();
                grouponActivity.setBoughtCount(promotionGrouponConfigDto.getSkuBoughtCount());
                grouponActivity.setGradientPrices(Lists.transform(promotionGrouponConfigDto.getPromotionGrouponPrices(), new Function<PromotionGrouponPrice, GradientPriceDto>() {
                    @Override
                    public GradientPriceDto apply(PromotionGrouponPrice input) {
                        GradientPriceDto gradientPriceDto = new GradientPriceDto();
                        gradientPriceDto.setHighCount(input.getIntervalRightCount());
                        gradientPriceDto.setLowCount(input.getIntervalLeftCount());
                        gradientPriceDto.setPrice(input.getPrice());
                        return gradientPriceDto;
                    }
                }));
            }
            grouponActivity.setSkipModel(fittingSkipUrl(appHomeConfig, skipModelMap));
            return grouponActivity;
        } catch (Exception ex) {
            ex.printStackTrace();
            outIndexLog(appHomeSectionDto, project, ex, "");
        }

        return grouponActivity;
    }

    private WelfareGoodsDTO fittingWelfareBoard(GetProjectIndexRequest request, Project project, Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto, AppHomeConfigDto appHomeConfig) {

        WelfareGoodsDTO welfareGoods = new WelfareGoodsDTO();
        try {
            Integer isShow = appHomeConfig.getIsDisplayButton(); //0:否 1:是
            if (isShow == 1) {
                welfareGoods.setBtnName(appHomeConfig.getButtonName());
            }
            welfareGoods.setTitle(appHomeConfig.getTagName());
            welfareGoods.setGoodsImg(appHomeConfig.getImgUrl());
            Integer configType = appHomeConfig.getConfigType();
            if (configType == 3 || configType == 5 || configType == 2) {  //3.商城单个商品,5.商城商品列表 2:秒杀
                ArrayList<Long> sortedSkuIds = Lists.newArrayList();
                sortedSkuIds.add(Long.parseLong(appHomeConfig.getProductId()));
                HashMap<String,LegouSkuDetailInfo> skuMap = getLegouSkuDetailInfos(request, project, sortedSkuIds);

                if (QDStringUtil.isNotNull(skuMap) && skuMap.containsKey(appHomeConfig.getProductId())) {
                    LegouSkuDetailInfo legouSkuDetailInfo = skuMap.get(appHomeConfig.getProductId());
                    welfareGoods.setSellCount(Integer.parseInt(String.valueOf(legouSkuDetailInfo.getCountWareSellNum())));//购买数
                    welfareGoods.setGoodsName(legouSkuDetailInfo.getSkuName());//商品名称
                    welfareGoods.setPrice(legouSkuDetailInfo.getPrice());//现价
                    welfareGoods.setOriginalPrice(legouSkuDetailInfo.getMarketPrice());//市场价
                    welfareGoods.setSurplusCount(Integer.parseInt(String.valueOf(legouSkuDetailInfo.getCountStock())));//剩余库存
                    welfareGoods.setUnlimitedStock(legouSkuDetailInfo.getUnlimitedStock()); //是否是无限或有限库存
                }
                welfareGoods.setType(1);

                if (configType == 2) { //秒杀

                    GetPromotionDetailRequest promotionDetailRequest = new GetPromotionDetailRequest();
                    promotionDetailRequest.setPromotionId(appHomeConfig.getExtendId());//秒杀的促销ID
                    GetPromotionDetailResponse promotionDetailResponse = promotionRemoteService.getPromotionDetail(promotionDetailRequest);
                    GoodsPromotion goodsPromotion = promotionService.getGoodsOptimizePromotion(project.getId(),Long.parseLong(appHomeConfig.getProductId()));
                    if (QDStringUtil.isNotNull(goodsPromotion)) {
                        String discountWarePrice = goodsPromotion.getPromotionPrice();
                        welfareGoods.setOriginalPrice(welfareGoods.getOriginalPrice());//市场价存储现价
                        welfareGoods.setPrice(discountWarePrice);//现价存储促销价
                    }

                    if (HttpStatus.OK.getStatusCode() == promotionDetailResponse.getReturnInfo().getCode()) {
                        PromotionDto promotionDto = promotionDetailResponse.getPromotionDto();
                        Integer status = promotionDetailResponse.getStatus();
                        welfareGoods.setGoodsName(appHomeConfig.getName());
                        welfareGoods.setStatus(status);
                        if (status == -1) { //未开始
                            Long nextStartTime = promotionDetailResponse.getCurrentValidTime().getValidStart();
                            if (nextStartTime != 0) {
                                Long curTime = System.currentTimeMillis();
                                welfareGoods.setPromotionStartTime(nextStartTime - curTime);//秒杀开始时间 获取
                            }
                        }
                        if (QDStringUtil.isEmpty(promotionDto.getStockRealtimeCounts())) {
                            welfareGoods.setSurplusCount(99999);
                            welfareGoods.setUnlimitedStock(1); //无限库存
                        } else {
                            String wardIdStr = promotionDto.getWareIds();
                            String stockStr = promotionDto.getStockRealtimeCounts();
                            String[] wardIdArray = wardIdStr.split(",");
                            String[] stockArray = stockStr.split(",");
                            for (int i = 0; i < wardIdArray.length; i++) {
                                if (wardIdArray[i].equals(appHomeConfig.getProductId())) {
                                    welfareGoods.setSurplusCount(Integer.parseInt(stockArray[i]));
                                }
                            }
                            welfareGoods.setUnlimitedStock(0); //有限库存
                        }
                    }
                    welfareGoods.setType(2);
                }
            } else if (configType == 0 || configType == 4) {  //0.通用业态单个商品 4.通用业态商品列表
                ProductDetailBean businessProductDetailBean = getBusinessProductInfo(appHomeConfig.getProductId());
                if (QDStringUtil.isNotNull(businessProductDetailBean)) {
                    welfareGoods.setSellCount(Integer.valueOf(String.valueOf(businessProductDetailBean.getSoldProductNum())));//购买数
                    welfareGoods.setGoodsName(businessProductDetailBean.getTitle());//商品名称
                    Integer priceType = businessProductDetailBean.getPriceType();
                    BigDecimal amount;
                    if (priceType == 0) { //如果是固定价
                        amount = new BigDecimal(businessProductDetailBean.getPrice());
                        amount = amount.divide(new BigDecimal(100));//分转元处理
                        welfareGoods.setPrice(String.valueOf(amount));
                    } else {
                        String priceRangeStr = businessProductDetailBean.getPriceRange();
                        if (QDStringUtil.isNotEmpty(priceRangeStr)) {
                            String[] rangeArray = priceRangeStr.split(",");
                            StringBuffer sb = new StringBuffer();
                            for (int i = 0; i < rangeArray.length; i++) {
                                if (i == 1) {
                                    sb.append("-");
                                }
                                amount = new BigDecimal(rangeArray[i]);
                                amount = amount.divide(new BigDecimal(100));
                                sb.append(amount);
                            }
                            welfareGoods.setPrice(sb.toString());
                        }
                    }
                }
                welfareGoods.setType(3);

            } else if (configType == 7 || configType == 8) { //url+id
                welfareGoods.setTitle(appHomeConfig.getTagName());
                welfareGoods.setGoodsImg(appHomeConfig.getImgUrl());
                welfareGoods.setGoodsName(appHomeConfig.getName());
                welfareGoods.setDesc(appHomeConfig.getDescription());
                welfareGoods.setType(4);

            } else if (configType == 9) {

                GetProductForAppIndexBean  productForAppIndexBean = getCompositebusinessInfo(appHomeConfig.getProductId());
                if (QDStringUtil.isNotNull(productForAppIndexBean)){
                    welfareGoods.setPrice(productForAppIndexBean.getPrice());
                    welfareGoods.setGoodsName(productForAppIndexBean.getTitle());
                    welfareGoods.setSellCount(productForAppIndexBean.getSoldNum().intValue());
                }
                welfareGoods.setTitle(appHomeConfig.getTagName());
                welfareGoods.setGoodsImg(appHomeConfig.getImgUrl());

                welfareGoods.setDesc(appHomeConfig.getDescription());
                welfareGoods.setType(3);
            }
            welfareGoods.setSkipModel(fittingSkipUrl(appHomeConfig, skipModelMap));

        } catch (Exception ex) {
            ex.printStackTrace();
            outIndexLog(appHomeSectionDto, project, ex, "");
        }
        return welfareGoods;
    }

    private  HashMap<String,LegouSkuDetailInfo> getLegouSkuDetailInfos(GetProjectIndexRequest request, Project project, ArrayList<Long> sortedSkuIds) {
        LegouSkuRequest skuRequest = transfor(LegouSkuRequest.class, request);
        skuRequest.setFindSellNum(true);
        skuRequest.setFindSkuStock(true);
        skuRequest.setSortedSkuIds(sortedSkuIds);
        skuRequest.setProjectId(project.getId());
        LegouSkuResponse skuResponse = solrSku.queryLegouSku(skuRequest);
        List<LegouSkuDetailInfo> skuList = skuResponse.getSkus();
        HashMap<String,LegouSkuDetailInfo> skuMap = new HashMap<>();
        for (LegouSkuDetailInfo legouSkuDetailInfo : skuList) {
            skuMap.put(legouSkuDetailInfo.getSkuId(),legouSkuDetailInfo);
        }

        return skuMap;
    }


    /**
     * 居家服务板块
     *
     * @param project
     * @param skipModelMap
     * @param appHomeSectionDto
     * @param appHomeConfigs
     * @return
     */
    private LifeServicesBoard fittingLifeServicesBoard(Project project, Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto, List<AppHomeConfigDto> appHomeConfigs) {

        LifeServicesBoard lifeServicesBoard = new LifeServicesBoard();
        List<BoardImg> lifeServiceList = Lists.newArrayList();
        for (AppHomeConfigDto appHomeConfig : appHomeConfigs) {
            try {
                SalesBoard lifeService = new SalesBoard();
                lifeService.setImgTitle(appHomeConfig.getName());
                lifeService.setImgDesc(appHomeConfig.getDescription());
                lifeService.setImageUrl(appHomeConfig.getImgUrl());
                lifeService.setSkipModel(fittingSkipUrl(appHomeConfig, skipModelMap));
                lifeService.setTagName(appHomeConfig.getTagName());
                lifeService.setEndTime(appHomeConfig.getEndTime());
                lifeService.setMarketPrice(appHomeConfig.getMarketPrice());
                lifeService.setPromotionPrice(appHomeConfig.getPromotionPrice());
                if (Constant.AH_NSF_PT.equals(appHomeConfig.getCode())) {
                    lifeServicesBoard.setLifeServiceMarket(lifeService);
                } else {
                    lifeService.setImgDesc(appHomeConfig.getPromotionTagContent());
                    lifeServiceList.add(lifeService);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
                outIndexLog(appHomeSectionDto, project, ex, JSON.toJSONString(appHomeConfig));
                continue;
            }
        }
        if (CollectionUtils.isNotEmpty(lifeServiceList) || QDStringUtil.isNotNull(lifeServicesBoard.getLifeServiceMarket())) {
            lifeServicesBoard.setTitle(appHomeSectionDto.getName());
            lifeServicesBoard.setSortIndex(appHomeSectionDto.getIndex());
            lifeServicesBoard.setLifeServicesList(lifeServiceList);
            lifeServicesBoard.setSectionCode(appHomeSectionDto.getCode());
        }

        return lifeServicesBoard;
    }

    /**
     * 社区服务工具板块组装
     *
     * @param project
     * @param appDevice
     * @param skipModelMap
     * @param appHomeSectionDto
     * @return
     */
    private ProjectServicesBoard fittingBasicServices(Project project, AppDevice appDevice, Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto) {

        ProjectServicesBoard projectServicesBoard = new ProjectServicesBoard();
        try {
            Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, appDevice);
            if (QDStringUtil.isNotNull(brickSourceType)) {
                List<ServiceItem> serviceItems = null;
                serviceItems = appConfigRemote.getIndexPageServiceItem(project.getId(), brickSourceType, appDevice.getQdVersion());
                if (CollectionUtils.isNotEmpty(serviceItems)) {
                    List<ProjectService> services = Lists.newArrayList();
                    for (int i = 0; i < serviceItems.size(); i++) {
                		if(i==4){
                        	break;
                        }
                        ServiceItem serviceItem = serviceItems.get(i);
                        try {
                            ProjectService projectService = transfor(ProjectService.class, serviceItem);
                            //填充角色身份3.2新增
                            fillRoomRole(serviceItem,projectService);
                            projectService.setImageUrl(serviceItem.getImgUrlColor());
                            projectService.setServiceId(String.valueOf(serviceItem.getId()));
                            String skipStr = "";
                            if (QDStringUtil.isNull(serviceItem.getSkipNo())) {
                                logger.error("get project service error :" + JSON.toJSONString(serviceItem));
                                continue;
                            }

                            SkipBean skipBean = new SkipBean();
                            skipBean.setProjectName(project.getName());
                            skipBean.setProjectId(String.valueOf(project.getId()));
                            skipBean.setIds(String.valueOf(serviceItem.getId()));
                            skipBean.setSkipNo(Integer.parseInt(serviceItem.getSkipNo()));
                            StringBuffer identity = new StringBuffer(Constant.DETAL_IDENTITY);
                            if (QDStringUtil.isNotNull(serviceItem.getAccessPrivilege())){
                                identity.setLength(0);
                                identity.append(serviceItem.getAccessPrivilege());
                            }
                            skipBean.setIdentity(identity.toString());
                            skipBean.setPcode( serviceItem.getPrivilegeType().intValue()==0?1:3);

                            if (Integer.parseInt(serviceItem.getSkipNo()) == Constant.SkipNo.URL_7000.toInteger()) {
                                skipBean.setSkipUrl(serviceItem.getContant());
                                skipStr = skipMode.fittingSkipUrl(skipModelMap, skipBean);
                            } else {
                                skipStr = skipMode.fittingSkipModelBySkipBean(skipModelMap,skipBean);
                            }
                            projectService.setSkipModel(skipStr);
                            services.add(projectService);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            outIndexLog(appHomeSectionDto, project, ex, JSON.toJSONString(serviceItem));
                            continue;
                        }
                    }

                    projectServicesBoard.setProjectServices(services);
                    projectServicesBoard.setSectionCode(appHomeSectionDto.getCode());
                    projectServicesBoard.setSortIndex(appHomeSectionDto.getIndex());

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            outIndexLog(appHomeSectionDto, project, ex, "");
        }
        return projectServicesBoard;
    }
    
    private void fillRoomRole(ServiceItem serviceItem,
			ProjectService projectService) {
		//3.2新增
		projectService.setTagName(serviceItem.getTagName());
		projectService.setPermType(serviceItem.getPrivilegeType());
		if(serviceItem.getAccessPrivilege()!=null){
			//业主，家庭成员，租客，装修负责人，朋友，保姆，司机
			String s=Integer.toBinaryString(serviceItem.getAccessPrivilege());
			List<String> bindRoomRole=new ArrayList<String>();
			int length=s.length();
			String init="0000000";
			if(length!=7 && length<7){
				s=init.substring(0,7-length)+s;
			}
			length=s.length();
			//ONWER(1, "业主"), RELATIVE(2, "家庭成员"), FRIENDS(3, "业主朋友"), RENTER(4, "租客"), DECORATOR(5, "装修负责人"), VISTOR(6, "游客"),
			//NANNY(9,"保姆"), DRIVER(10,"司机"),FAMILY(11,"家庭成员");
			if(length>0 && s.charAt(0)=='1') bindRoomRole.add("1");
			if(length>1 && s.charAt(1)=='1') bindRoomRole.add("2");
			if(length>2 && s.charAt(2)=='1') bindRoomRole.add("4");
			if(length>3 && s.charAt(3)=='1') bindRoomRole.add("5");
			if(length>4 && s.charAt(4)=='1') bindRoomRole.add("3"); 
			if(length>5 && s.charAt(5)=='1') bindRoomRole.add("9");
			if(length>6 && s.charAt(6)=='1') bindRoomRole.add("10");
			projectService.setBindRoomRole(bindRoomRole);
		}
	}

    /**
     * 同城热卖板块
     *
     * @param project
     * @param skipModelMap
     * @param appHomeSectionDto
     * @return
     */
    private CityWideRecommendBoard fittingCityWideRecommendBoard(Project project, Map<String, String> skipModelMap, AppHomeSectionDto appHomeSectionDto) {

        CityWideRecommendBoard cityWideRecommendBoard = new CityWideRecommendBoard();
        try {
            List<SellBest> sellBestList = wareRemoteService.getSellBestByCity("NG", project.getId(), 4);
            if (CollectionUtils.isNotEmpty(sellBestList)) {
                List<Long> skuIds = Lists.newArrayList();
                Map<String, Integer> sellMap = new HashMap<>();
                for (SellBest sellBest : sellBestList) {
                    skuIds.add(sellBest.getSkuId());
                    sellMap.put(String.valueOf(sellBest.getSkuId()), Integer.parseInt(String.valueOf(sellBest.getSellNum())));
                }
                LegouSkuRequest legouSkuRequest = new LegouSkuRequest();
                legouSkuRequest.setSortedSkuIds(skuIds);
                legouSkuRequest.setFindAllStatus(true);
                LegouSkuResponse skuResponse = skuService.queryLegouSku(legouSkuRequest);
                if (HttpStatus.OK.getStatusCode() == skuResponse.getReturnInfo().getCode()) {
                    List<RecommendGood> recommendGoodList = Lists.newArrayList();
                    List<LegouSkuDetailInfo> skuInfoList = skuResponse.getSkus();
                    if (CollectionUtils.isNotEmpty(skuInfoList)) {
                        for (LegouSkuDetailInfo legouSkuDetailInfo : skuInfoList) {
                            try {
                                RecommendGood recommendGood = new RecommendGood();
                                String skuId = legouSkuDetailInfo.getSkuId();
                                recommendGood.setSellCount(sellMap.containsKey(skuId) ? sellMap.get(skuId) : 0);
                                recommendGood.setSkuId(skuId);
                                recommendGood.setGoodsImg(QDStringUtil.isNotNull(legouSkuDetailInfo.getSkuImgUrl())
                                        ? legouSkuDetailInfo.getSkuImgUrl()[0] : "");
                                recommendGood.setGoodsName(legouSkuDetailInfo.getSkuName());
                                recommendGood.setPrice(legouSkuDetailInfo.getPrice());
                                recommendGood.setOriginalPrice(legouSkuDetailInfo.getMarketPrice());
                                String skipModeStr = skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.SPXQ_5004.toInteger(), skuId);
                                recommendGood.setSkipModel(skipModeStr);
                                recommendGoodList.add(recommendGood);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                outIndexLog(appHomeSectionDto, project, ex, JSON.toJSONString(legouSkuDetailInfo));
                                continue;
                            }

                        }
                        cityWideRecommendBoard.setSectionCode(appHomeSectionDto.getCode());
                        cityWideRecommendBoard.setRecommendList(recommendGoodList);
                        cityWideRecommendBoard.setTitle(appHomeSectionDto.getName());
                        cityWideRecommendBoard.setTotalCount(null);
                        cityWideRecommendBoard.setSortIndex(appHomeSectionDto.getIndex());
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            outIndexLog(appHomeSectionDto, project, ex, "");
        }
        return cityWideRecommendBoard;
    }


    /**
     * 首页板块异常日志输出
     *
     * @param appHomeSectionDto
     * @param project
     * @param ex
     */
    private void outIndexLog(AppHomeSectionDto appHomeSectionDto, Project project, Exception ex, String desc) {

        logger.error("class:CallProject - version:3.0 - Method:index fitting section  " +
                "" + appHomeSectionDto.getCode() + " , sectionName is " + appHomeSectionDto.getName() + " , projectId is " + project.getId() + " ," +
                " projectName" + project.getName() + " is error , desc :" + desc, ex);
    }

    private String fittingSkipUrl(AppHomeConfigDto appHomeConfig, Map<String, String> skipModelMap) {

        Integer configType = appHomeConfig.getConfigType();
        String skipModeStr = "";

        if (configType == 0) { //单个通用业态商品详情
            String url = APIPropertiesClient.getSkipUrl("good_detail_CB");
            skipModeStr = skipMode.fittingSkipUrl(skipModelMap, url + appHomeConfig.getProductId().toString(), 1, appHomeConfig.getIsShare(), appHomeConfig.getProductId().toString());

        } else if (configType == 3) { //跳转商城单个商品
            skipModeStr = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                    Constant.SkipNo.SPXQ_5004.toInteger(), appHomeConfig.getProductId());

        } else if (configType == 4) { //通用业态商品列表
            String url = APIPropertiesClient.getSkipUrl("good_list_CB_v3");
            url += appHomeConfig.getId() + "?type=" + appHomeConfig.getExtendId();
            skipModeStr = skipMode.fittingSkipUrl(skipModelMap, url, 1, appHomeConfig.getIsShare(), String.valueOf(appHomeConfig.getId()));

        } else if (configType == 5) { //跳转商城商品列表
            if (CollectionUtils.isNotEmpty(appHomeConfig.getProducts())) {
                skipModeStr = skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.SPLB_5000.toInteger(),
                        ListUtil.listForStringToString(appHomeConfig.getProducts()));
            }

        } else if (configType == 1 || configType == 2 || configType == 6 || configType == 7 || configType == 8 || configType ==9) { //1:阶梯团购 2: 秒杀 //6.旅游：url+线路id  //7 活动：url+活动id  //8:指定url链接 //9聚合业态
            skipModeStr = skipMode.fittingSkipUrl(skipModelMap, appHomeConfig.getContentUrl(), 1, appHomeConfig.getIsShare(),
                    appHomeConfig.getShareTitle(), appHomeConfig.getShareDescription(), appHomeConfig.getShareImgUrl(), appHomeConfig.getContentUrl(), "", appHomeConfig.getProductId());

        }
        return skipModeStr;
    }

    /**
     * 获取聚合业态商品
     * @param productId
     * @return
     */
    private GetProductForAppIndexBean getCompositebusinessInfo(String productId){

        GetProductForAppIndexBean productForAppIndexBean = null;
        GetProductForAppIndexRequest productForAppIndexRequest = new GetProductForAppIndexRequest();
        productForAppIndexRequest.setProductId(productId);
        GetProductForAppIndexResponse productForAppIndexResponse = qdAppRemoteService.getProductForIndex(productForAppIndexRequest);
        if (HttpStatus.OK.getStatusCode() == productForAppIndexResponse.getReturnInfo().getCode()) {
             productForAppIndexBean = productForAppIndexResponse.getProductForAppIndexBean();
        }
        return  productForAppIndexBean;
    }

    /**
     * 获取通用业态商品
     * @param productId
     * @return
     */
    private ProductDetailBean getBusinessProductInfo(String productId) {

        ProductDetailBean businessProductDetailBean = null;
        GetProductByIdRequest productByIdRequest = new GetProductByIdRequest();
        productByIdRequest.setProductId(productId);
        GetProductByIdResponse productByIdResponse = businessOrderService.getProductById(productByIdRequest);
        if (HttpStatus.OK.getStatusCode() == productByIdResponse.getReturnInfo().getCode()) {
            businessProductDetailBean = productByIdResponse.getEntity();
        }
        return businessProductDetailBean;
    }


    /**
     * 获取
     *
     * @param topicId
     */
    public TopicDto getTopicByTopicId(String topicId) {
        GetTopicBytopicIdRequest request = new GetTopicBytopicIdRequest();
        request.setTopicId(topicId);
        GetTopicBytopicIdResponseData responseData = topicRpcV3.getTopicBytopicId(request);
        return responseData.getTopic();
    }



}
