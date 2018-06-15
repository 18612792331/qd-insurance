package com.qding.api.call.app.qding.v3_1_0;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.NeighborBanner;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.ActivityPiazzaDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.BriefEncyclopedia;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.PageResultDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.TopicPiazzaDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.ActivityBoard;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefInteractionTagInfo;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefNewsInfo;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefTopicInfo;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.InteractBoard;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.NewsBoard;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.GetNeighborIndexRequest;
import com.qding.api.call.app.qding.v3_1_0.struct.neighbor.BkBoard;
import com.qding.api.call.app.qding.v3_1_0.struct.neighbor.InterestLableDto;
import com.qding.api.call.app.qding.v3_1_0.struct.neighbor.index.ChoicenessBoard;
import com.qding.api.call.app.qding.v3_1_0.struct.neighbor.request.GetInterestLableRequest;
import com.qding.api.call.app.qding.v3_1_0.struct.neighbor.request.ListBkPageRequest;
import com.qding.api.call.app.qding.v3_1_0.struct.neighbor.response.data.GetInterestLableResponseData;
import com.qding.api.call.app.qding.v3_1_0.struct.neighbor.response.data.GetNeighborIndexResponseData;
import com.qding.api.call.app.qding.v3_1_0.struct.neighbor.response.data.ListBkPageResponseData;
import com.qding.api.call.service.NeighborService;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.EntitySerialUtil;
import com.qding.api.util.SkipModeFitting;
import com.qding.api.verifycode.store.RedisStoreDevice;
import com.qding.api.verifycode.store.StoreDevice;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.neighbor.v3.domain.ActivityTagV3;
import com.qding.neighbor.v3.dto.ActivityDto;
import com.qding.neighbor.v3.dto.ActivityTagBaseDto;
import com.qding.neighbor.v3.dto.EncyclopediaBaseDto;
import com.qding.neighbor.v3.dto.PageCommonDto;
import com.qding.neighbor.v3.dto.PageResultDto;
import com.qding.neighbor.v3.dto.TopicDto;
import com.qding.neighbor.v3.dto.TopicRecommendDto;
import com.qding.neighbor.v3.enums.EnumModuleType;
import com.qding.neighbor.v3.rpc.ITopicRpcV3;
import com.qding.neighbor.v3.rpc.requst.GetActivity4IndexRequest;
import com.qding.neighbor.v3.rpc.requst.GetListOrderByTopicCountRequest;
import com.qding.neighbor.v3.rpc.requst.GetNearbyTopicRequest;
import com.qding.neighbor.v3.rpc.requst.ListBKPageRequest;
import com.qding.neighbor.v3.rpc.requst.TopicRecommendRequest;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by qd on 2017/2/23.
 */
public class CallNeighbor extends com.qding.api.call.app.qding.v3_0_0.CallNeighbor {

	@Autowired
	private ITopicRpcV3 topicRpcV3;
	
	@Autowired
	private ProjectReadRemote projectReadService;

	@Autowired
	private SkipModeFitting skipMode;

    @Autowired
    private NeighborService neighborService;

	private static Logger logger = Logger.getLogger(CallNeighbor.class);
	
	
	@ExplainAnnotation(explain = "社区生活首页")
    @HTTP(alias = "getNeighborIndex")
    public Response<GetNeighborIndexResponseData> getNeighborHome(GetNeighborIndexRequest request, UserToken userToken) {

        Response<GetNeighborIndexResponseData> response = new Response<GetNeighborIndexResponseData>();

        String memberId = "";
        if (QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId())) {
            memberId = userToken.getMemberId();
        }
        String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
        StoreDevice storeDevice = new RedisStoreDevice();
        String key = "neighborIndex:v:p:m:%s:%s:%s";
        String cacheKey = String.format(key, initVersion, request.getAppUser().getProjectId(), memberId);
        String cachString = storeDevice.getCode(cacheKey);
        if (QDStringUtil.isNotEmpty(cachString)) {

            response = JSON.parseObject(cachString, new TypeReference<Response<GetNeighborIndexResponseData>>() {
            });
            logger.info("app redis cache === 从缓存获取邻聚社区首页.... ......");
            return response;
        }

        Long projectId = Long.parseLong(request.getAppUser().getProjectId());
        Project project = projectReadService.get(projectId);
        Long regionId = project.getRegionId();
        Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());

        GetNeighborIndexResponseData data = new GetNeighborIndexResponseData();
        // banner部分
        List<NeighborBanner>  bannerList = neighborService.fittingNeighborIndexBanner( project,  skipModelMap);
        data.setBannerList(bannerList);

        //新闻部分------------------------------------------------------------------------------------2
        try {
            NewsBoard newsBoardEntity = new NewsBoard();
            PageResultDTO<BriefNewsInfo> newsPage = fittingIndexNewsList(regionId, projectId, System.currentTimeMillis(), 3,
                    skipModelMap, DateTime.now().minusDays(7).getMillis());
            if (QDStringUtil.isNotNull(newsPage) && CollectionUtils.isNotEmpty(newsPage.getList())) {
                String skip = skipMode.fittingNoParameterSkipModel(skipModelMap,
                        Constant.SkipNo.P_NEWS_LIST_3021.toInteger());
                newsBoardEntity.setSkipModel(skip);
                newsBoardEntity.setNewsList(newsPage.getList());
                newsBoardEntity.setBoardTitle("社区新闻");
                data.setNewsBoard(newsBoardEntity);
            }
        } catch (Exception ex) {
        }
        //邻里互动标签及帖子列表------------------------------------------------------------------------------------3
        InteractBoard interactBoardEntity = new InteractBoard();
        //邻里互动标签
        List<BriefInteractionTagInfo> tagList = Lists.newArrayList();
        GetListOrderByTopicCountRequest listOrderByTopicCountRequest = new GetListOrderByTopicCountRequest();
        listOrderByTopicCountRequest.setProjectId(projectId);
        listOrderByTopicCountRequest.setCityId(regionId);
        PageResultDto<ActivityTagBaseDto> rpcActivityTagPageResult = topicRpcV3
                .getListOrderByTopicCount(listOrderByTopicCountRequest);
        List<ActivityTagBaseDto> rpcActivityTagList = rpcActivityTagPageResult.getList();
        if (CollectionUtils.isNotEmpty(rpcActivityTagList) && rpcActivityTagList.size() >= 3) {
            for (ActivityTagBaseDto activityTagBaseDto : rpcActivityTagList) {
                BriefInteractionTagInfo tagInfo = transfor(BriefInteractionTagInfo.class, activityTagBaseDto);
                String skip = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                        Constant.SkipNo.P_INTERACT_TAG_LIST_3024.toInteger(), activityTagBaseDto.getTagId());
                tagInfo.setSkipModel(skip); //指定标签聚合页
                tagList.add(tagInfo);
            }
        }

        interactBoardEntity.setTagList(tagList);

        //邻里互动帖子列表
        List<BriefTopicInfo> topicList = Lists.newArrayList();
        GetNearbyTopicRequest nearByTopicRequest = new GetNearbyTopicRequest();
        nearByTopicRequest.setProjectId(request.getAppUser().getProjectId());
        nearByTopicRequest.setSize(6);
        nearByTopicRequest.setSortType("1");
        PageResultDto<TopicDto> topicDtoPageResult = topicRpcV3.getNearbyTopic(nearByTopicRequest);
        List<TopicDto> nearByTopicList = topicDtoPageResult.getList();
        if (CollectionUtils.isNotEmpty(nearByTopicList)) {
            for (TopicDto topicDto : nearByTopicList) {
                BriefTopicInfo briefTopicInfo = transfor(BriefTopicInfo.class, topicDto);
                BriefInteractionTagInfo tagInfo = new BriefInteractionTagInfo();
                tagInfo.setTagId(topicDto.getTagId());
                tagInfo.setTagName(topicDto.getTagName());
                String skipTagList = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                        Constant.SkipNo.P_INTERACT_TAG_LIST_3024.toInteger(), topicDto.getTagId());
                tagInfo.setSkipModel(skipTagList);
                briefTopicInfo.setTagInfo(tagInfo);
                //帖子skip
                String skipTopicDetail = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                        Constant.SkipNo.P_INTERACT_DETAIL_3025.toInteger(), topicDto.getId());
                briefTopicInfo.setSkipModel(skipTopicDetail);//去往邻里互动帖子详情页
                topicList.add(briefTopicInfo);
            }
        }

        interactBoardEntity.setTopicList(topicList);
        interactBoardEntity.setBoardTitle("邻里互动");
        String skip = skipMode.fittingNoParameterSkipModel(skipModelMap,
                Constant.SkipNo.P_INTERACT_LIST_3023.toInteger());
        interactBoardEntity.setSkipModel(skip); //邻里互动列表页
        data.setInteractBoard(interactBoardEntity);

        //活动报名板块
        ActivityBoard   activityBoard = neighborService.fittingActivityBoard( project, memberId,  skipModelMap);
        data.setActivityBoard(activityBoard);

        //精选话题------------------------------------------------------------------------------------5
        ChoicenessBoard choicenessBoard = new ChoicenessBoard();
        List<TopicPiazzaDTO> choicenessList = Lists.newArrayList();
        TopicRecommendRequest recommendRequest = new TopicRecommendRequest();
        recommendRequest.setProjectId(projectId);
        recommendRequest.setCityId(regionId);
        recommendRequest.setModuleType(EnumModuleType.WELL_CHOSEN);
        PageResultDto<TopicRecommendDto> choicenessPageResult = topicRpcV3.getTopicRecommend(recommendRequest);
        List<TopicRecommendDto> rpcChoicenessList = choicenessPageResult.getList();
        if (CollectionUtils.isNotEmpty(rpcChoicenessList)) {
            for (TopicRecommendDto topicRecommendDto : rpcChoicenessList) {
                TopicPiazzaDTO topicPiazzaDTO = new TopicPiazzaDTO();
                String skip1 = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                        Constant.SkipNo.P_TOPIC_DETAIL_3031.toInteger(), topicRecommendDto.getTopicId());
                if (Constant.SubTopicTypeEnum.VOTE_3.toInteger().intValue() == topicRecommendDto.getTopicType()) {
                    skip1 = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                            Constant.SkipNo.P_VOTE_DETAIL_3033.toInteger(), topicRecommendDto.getTopicId());
                }
                topicPiazzaDTO.setSubTopicType(topicRecommendDto.getTopicType());
                topicPiazzaDTO.setSubTopicTypeName(topicRecommendDto.getTopicTypeName());
                topicPiazzaDTO.setTopicId(topicRecommendDto.getTopicId());
                topicPiazzaDTO.setTopicTitle(topicRecommendDto.getTitle());
                topicPiazzaDTO.setTopImg(topicRecommendDto.getImgUrl());
                topicPiazzaDTO.setBeginTime(topicRecommendDto.getStartTime());
                topicPiazzaDTO.setDescription(topicRecommendDto.getDescription());
                topicPiazzaDTO.setEndTime(topicRecommendDto.getEndTime());
                topicPiazzaDTO.setIntroduction(topicRecommendDto.getIntroduction());
                topicPiazzaDTO.setPeopleNum(topicRecommendDto.getPersonCount());
                topicPiazzaDTO.setEnded(topicRecommendDto.getEndTime()< System.currentTimeMillis());
                topicPiazzaDTO.setSkipModel(skip1); //帖子详情
                choicenessList.add(topicPiazzaDTO);
            }
        }
        choicenessBoard.setBoardTitle("精选话题");
        choicenessBoard.setTopicPiazzaDTOList(choicenessList);
        String skip3 = skipMode.fittingNoParameterSkipModel(skipModelMap,
                Constant.SkipNo.P_TOPIC_LIST_3029.toInteger());
        choicenessBoard.setSkipModel(skip3); //精选话题列表
        data.setChoicenessBoard(choicenessBoard);
        //生活百科------------------------------------------------------------------------------------6
        try {
            BkBoard encyclopediaBoard = new BkBoard();
            encyclopediaBoard.setBoardTitle("生活百科");
            Response<GetInterestLableResponseData> re=getInterestLable(null);
            if(re.getCode()==HttpStatus.OK.getStatusCode()){
            	encyclopediaBoard.setListLable(re.getData().getList());
            }
            data.setEncyclopediaBoard(encyclopediaBoard);
        } catch (Exception ex) {
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        logger.info("app redis cache === 未从缓存获取邻聚社区首页.... ......");

        try {
            String expirat = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_APPCACHE_1.getGroupName(),Constant.Dict_K_V_Enum.DICT_APPCACHE_1.getDictKey());
            storeDevice.setCode(cacheKey, EntitySerialUtil.serial(response), Integer.parseInt(expirat));
        } catch (TException e) {
            e.printStackTrace();
            logger.error("app redis cache ===" + JSON.toJSONString(request) + " : 缓存数据失败");
        }

        return response;
    }

	

	@ExplainAnnotation(explain = "生活百科引导栏兴趣分类标签列表")
	@HTTP(alias = "getInterestLable")
	public Response<GetInterestLableResponseData> getInterestLable(GetInterestLableRequest request) {
		Response<GetInterestLableResponseData> response = new  Response<GetInterestLableResponseData>();
		GetInterestLableResponseData data=new GetInterestLableResponseData();
		try {
			List<ActivityTagV3> list=topicRpcV3.getInterestLable();
			List<InterestLableDto> list1=new ArrayList<InterestLableDto>();
			list1.add(new InterestLableDto("1","最新"));
			list1.add(new InterestLableDto("2","最热"));
			if(list!=null && list.size()>0){
				for(ActivityTagV3 v3:list){
					InterestLableDto d=new InterestLableDto();
					d.setLableId(v3.getId());
					d.setLableName(v3.getTagName());
					list1.add(d);
				}
				data.setList(list1);
			}
		} catch (Exception ex) {
			logger.error("method : GetInterestLableResponseData  error:", ex);
			return handleException(GetInterestLableResponseData.class, ex);
		}
		response.setData(data);
		response.setCode(HttpStatus.OK.getStatusCode());
		return  response;
	}

	@ExplainAnnotation(explain = "百科列表、根据最新，最热，百科兴趣类型查询列表")
	@HTTP(alias = "getEncyclopediaList", isNeadProject = true)
	public Response<ListBkPageResponseData> ListBkPage(ListBkPageRequest request) {
		Response<ListBkPageResponseData> response = new Response<ListBkPageResponseData>();
		try {
			Integer sortType=null;
			String lableId=null;
			if(request.getLableId().equals("1") || request.getLableId().equals("2")){
				sortType=Integer.parseInt(request.getLableId());
			}else{
				sortType=1;
				lableId=request.getLableId();
			}
			Long projectId = Long.parseLong(request.getAppUser().getProjectId());
			Project project = projectReadService.get(projectId);
			Long regionId = project.getRegionId();
			Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());
			ListBkPageResponseData data = fittingEncyclopediaList(regionId, projectId,
					request.getPageSize(), skipModelMap,sortType,lableId,request.getPageNo());
			response.setCode(HttpStatus.OK.getStatusCode());
			response.setData(data);
		} catch (Exception ex) {
			logger.error("method : ListBkPage  error:", ex);
			return handleException(ListBkPageResponseData.class, ex);
		}
		return response;
	}

	public ListBkPageResponseData fittingEncyclopediaList(Long regionId, Long projectId,
			Integer pageSize, Map<String, String> skipModelMap,Integer orderType,String lableId,Integer pageNo) throws ServiceException {
		ListBkPageResponseData page = new ListBkPageResponseData();
		List<BriefEncyclopedia> encyclopediaList = Lists.newArrayList();
		ListBKPageRequest encyclopediaListRequest = new ListBKPageRequest();
		encyclopediaListRequest.setProjectId(projectId);
		encyclopediaListRequest.setCityId(regionId);
		encyclopediaListRequest.setPageSize(pageSize);
		encyclopediaListRequest.setInterestTagId(lableId);
		encyclopediaListRequest.setSortType(orderType);
		encyclopediaListRequest.setPageNo(pageNo);
		PageCommonDto<EncyclopediaBaseDto> rpcEncycloppediaPageResult = topicRpcV3
				.listBkPage(encyclopediaListRequest);
		checkAndContinue(rpcEncycloppediaPageResult);
		List<EncyclopediaBaseDto> rpcEncycloppediaList = rpcEncycloppediaPageResult.getList();
		if (CollectionUtils.isNotEmpty(rpcEncycloppediaList)) {
			for (EncyclopediaBaseDto topicDto : rpcEncycloppediaList) {
				BriefEncyclopedia encyclopediaDTO = new BriefEncyclopedia();
				this.transfor(encyclopediaDTO, topicDto);
				String skipmode = skipMode
						.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.P_ENCYCLOPEDIA_DETAIL_3032.toInteger(),
								topicDto.getId());
				encyclopediaDTO.setSkipModel(skipmode);
				encyclopediaDTO.setPublishTime(topicDto.getPublishTime());
				encyclopediaDTO.setTypeName(topicDto.getTypeName());
				encyclopediaList.add(encyclopediaDTO);
			}
			page.setTotalCount(rpcEncycloppediaPageResult.getTotalNum());
			page.setEncyclopediaList(encyclopediaList);
		}
		return page;
	}



}
