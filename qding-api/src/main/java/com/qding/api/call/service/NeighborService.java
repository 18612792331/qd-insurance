package com.qding.api.call.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.NeighborBanner;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.ActivityPiazzaDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.TopicPiazzaDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.ActivityBoard;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefInteractionTagInfo;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefTopicInfo;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.InteractBoard;
import com.qding.api.call.app.qding.v3_1_0.struct.neighbor.index.ChoicenessBoard;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.GcRoomBoard;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.GcRoomDTO;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.MySkipDTO;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.PersonalBoard;
import com.qding.api.constant.Constant;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.ImageUtil;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.pojo.biz.Project;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.neighbor.common.Constants;
import com.qding.neighbor.dto.GcRoomInfoDTO;
import com.qding.neighbor.rpc.IGcRoomRpc;
import com.qding.neighbor.rpc.request.GetGcRoomBySortRequest;
import com.qding.neighbor.rpc.response.data.GetGcRoomBySortResponse;
import com.qding.neighbor.v3.dto.*;
import com.qding.neighbor.v3.enums.EnumModuleType;
import com.qding.neighbor.v3.rpc.ITopicRpcV3;
import com.qding.neighbor.v3.rpc.requst.GetActivity4IndexRequest;
import com.qding.neighbor.v3.rpc.requst.GetListOrderByTopicCountRequest;
import com.qding.neighbor.v3.rpc.requst.GetNearbyTopicRequest;
import com.qding.neighbor.v3.rpc.requst.TopicRecommendRequest;
import com.qding.neighbor.v3.rpc.response.data.GetLableListResponseData;
import com.qding.passport.struct.MemberInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by qd on 2017/9/12.
 */
public class NeighborService extends Callable {

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private IGcRoomRpc newGcRoomRpc;

    @Autowired
    private ITopicRpcV3 topicRpcV3;

    @Autowired
    private MemberService memberService;

    private static final Logger logger = Logger.getLogger(NeighborService.class);


    /**
     * 获取社区首页banner
     * @param project
     * @param skipModelMap
     * @return
     */
    public List<NeighborBanner> fittingNeighborIndexBanner(Project project, Map<String, String> skipModelMap){

        List<NeighborBanner> bannerList = Lists.newArrayList();
        try {
            TopicRecommendRequest recommendRequest = new TopicRecommendRequest();
            recommendRequest.setProjectId(project.getId());
            recommendRequest.setCityId(project.getRegionId());
            recommendRequest.setModuleType(EnumModuleType.PROJECT_BANNER);
            PageResultDto<TopicRecommendDto> topicRecommendPageResult = topicRpcV3.getTopicRecommend(recommendRequest);
            List<TopicRecommendDto> rpcBannerList = topicRecommendPageResult.getList();
            if (CollectionUtils.isNotEmpty(rpcBannerList)) {
                for (TopicRecommendDto topicRecommendDto : rpcBannerList) {
                    NeighborBanner neighborBanner = transfor(NeighborBanner.class, topicRecommendDto);
                    if (topicRecommendDto.getTopicType() != null && topicRecommendDto.getTopicType() == 99 &&
                            QDStringUtil.isNotEmpty(topicRecommendDto.getUrl())) { //如果是URL
                        neighborBanner.setSkipModel(
                                skipMode.fittingSkipUrl(skipModelMap, topicRecommendDto.getUrl(), 0, 0, null));
                    } else {
                        Integer topicType = topicRecommendDto.getTopicType();
                        Integer skipNo = null;
                        if (topicType == null) {
                            skipNo = Constant.SkipNo.P_TOPIC_DETAIL_3031.toInteger();
                        } else if (Constant.SubTopicTypeEnum.ACTIVITY_4.toInteger().intValue() == topicType) {
                            skipNo = Constant.SkipNo.P_ACTIVITY_DETAIL_3028.toInteger();
                        } else if (Constant.SubTopicTypeEnum.ENCYCOLPEDIA_7.toInteger().intValue() == topicType) {
                            skipNo = Constant.SkipNo.P_ENCYCLOPEDIA_DETAIL_3032.toInteger();
                        } else if (Constant.SubTopicTypeEnum.COMMON_5.toInteger().intValue() == topicType) {
                            skipNo = Constant.SkipNo.P_INTERACT_DETAIL_3025.toInteger();
                        } else if (Constant.SubTopicTypeEnum.VOTE_3.toInteger().intValue() == topicType) {
                            skipNo = Constant.SkipNo.P_VOTE_DETAIL_3033.toInteger();
                        } else {
                            skipNo = Constant.SkipNo.P_TOPIC_DETAIL_3031.toInteger();
                        }
                        if (QDStringUtil.isNotNull(skipNo)) {
                            neighborBanner.setSkipModel(skipMode.fittingSkipModelByOnlyId(skipModelMap,
                                    skipNo, topicRecommendDto.getTopicId()));
                        }
                    }
                    neighborBanner.setBannerId(topicRecommendDto.getId());
                    bannerList.add(neighborBanner);
                }
            }
        } catch (Exception ex) {
            logger.error("class :NeighborService ,method:fittingNeighborIndexBanner is error :",ex);
        }
        return bannerList;
    }

    /**
     * 获取群组板块
     * @param projectId
     * @param memberId
     */
    public GcRoomBoard fittingGcRoomBoard(String projectId, String memberId, Map<String, String> skipModelMap) {
        GcRoomBoard gcRoomBoard = new GcRoomBoard();
        try {
            String queryCount = APIPropertiesClient.getValue("neighbor_index_gc_query_count");
            Integer limitNum = QDStringUtil.isNotEmpty(queryCount)?Integer.parseInt(queryCount):6;
            GetGcRoomBySortRequest gcRoomBySortRequest = new GetGcRoomBySortRequest();
            gcRoomBySortRequest.setMemberId(memberId);
            gcRoomBySortRequest.setLimitNum(limitNum);//查询六个
            gcRoomBySortRequest.setProjectId(projectId);
            List<Integer> typeList = Lists.newArrayList();
            typeList.add(1);
            typeList.add(2);
            gcRoomBySortRequest.setTypeList(typeList);
            GetGcRoomBySortResponse gcRoomBySortResponse = newGcRoomRpc.getGcRoomBySort(gcRoomBySortRequest);
            if (gcRoomBySortResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode()) {
                List<GcRoomDTO> list = Lists.newArrayList();
                List<GcRoomInfoDTO> gcRoomInfoDTOList = gcRoomBySortResponse.getGcRoomList();
                if (gcRoomInfoDTOList.size()>(limitNum - 1)) {
                    gcRoomInfoDTOList.remove(gcRoomInfoDTOList.size()-1);
                }
                if ( gcRoomInfoDTOList.size()>0) {
                    gcRoomBoard.setSkipModel(skipMode.fittingNoParameterSkipModel(skipModelMap,Constant.SkipNo.LJQL_3013.toInteger()));
                }
                for (GcRoomInfoDTO gcRoomInfoDTO : gcRoomInfoDTOList) {
                    GcRoomDTO gcRoom = new GcRoomDTO();
                    gcRoom.setGcMemberCount(gcRoomInfoDTO.getRemark());
                    gcRoom.setGcMemberStatus(gcRoomInfoDTO.getGcMemberStatus());
                    gcRoom.setGcMemberType(gcRoomInfoDTO.getGcMemberType());
                    gcRoom.setGcRoomId(gcRoomInfoDTO.getGcRoomId());
                    gcRoom.setHeadUrl(gcRoomInfoDTO.getHeadUrl());
                    gcRoom.setName(gcRoomInfoDTO.getName());
                    list.add(gcRoom);
                }
                gcRoomBoard.setTitle("社区群聊");
                gcRoomBoard.setList(list);
            }
        } catch (Exception ex) {
            logger.error("class :NeighborService ,method:fittingGcRoomBoard is error :",ex);
        }
        return  gcRoomBoard;
    }

    /**
     * 组装社区首页我的相关板块
     * @param memberId
     * @param skipModelMap
     * @return
     */
    public PersonalBoard fittingPersonalBoard (String memberId, Map<String, String> skipModelMap){

        PersonalBoard personalBoard = new PersonalBoard();
        try {
            if (QDStringUtil.isNotEmpty(memberId)) {
                MemberInfo member = memberService.getMemberByMid(memberId);
                List<MySkipDTO> list = Lists.newArrayList();
                MySkipDTO  myActiviySkip = new MySkipDTO("我报名的","活动",skipMode.fittingSkipModelByOnlyId(skipModelMap,Constant.SkipNo.P_ACTIVITY_PERSON_LIST_3027.toInteger(),memberId));
                MySkipDTO  myTopicSkip = new MySkipDTO("我参与的","话题",skipMode.fittingSkipModelByOnlyId(skipModelMap,Constant.SkipNo.P_TOPIC_PERSON_LIST_3030.toInteger(),memberId));
                MySkipDTO  myCollectSkip = new MySkipDTO("我收藏的","内容",skipMode.fittingSkipModelByOnlyId(skipModelMap,Constant.SkipNo.P_TOPIC_PERSON_LIST_3030.toInteger(),memberId));
                list.add(myActiviySkip);
                list.add(myTopicSkip);
                list.add(myCollectSkip);
                personalBoard.setSkipModel(skipMode.fittingSkipModelByOnlyId(skipModelMap,Constant.SkipNo.PERSONAL_HOME_4000.toInteger(),memberId));
                personalBoard.setImgUrl(member.getHeadImg());
                personalBoard.setList(list);
            } else {
                personalBoard.setSkipModel("");
                personalBoard.setImgUrl("");
            }
        } catch (Exception ex) {
            logger.error("class :NeighborService ,method:fittingPersonalBoard is error :",ex);
        }

        return  personalBoard;
    }

    /**
     * 获取邻里互动标签
     * @param project
     * @param skipModelMap
     */
    public  List<BriefInteractionTagInfo> getInteractonTagList (Project project, Map<String, String> skipModelMap){

        List<BriefInteractionTagInfo> tagList = Lists.newArrayList();

        try {
            GetListOrderByTopicCountRequest listOrderByTopicCountRequest = new GetListOrderByTopicCountRequest();
            listOrderByTopicCountRequest.setProjectId(project.getId());
            listOrderByTopicCountRequest.setCityId(project.getRegionId());
            GetLableListResponseData lableResponse = topicRpcV3.getLableOrderByWeight();
            List<LableDto> rpcActivityTagList = lableResponse.getLableList();
            if (CollectionUtils.isNotEmpty(rpcActivityTagList) && rpcActivityTagList.size() >= 3) {
                int i=0;
                for (LableDto activityTagBaseDto : rpcActivityTagList) {
                    if (i>9) continue;
                    BriefInteractionTagInfo tagInfo = transfor(BriefInteractionTagInfo.class, activityTagBaseDto);
                    String skip = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                            Constant.SkipNo.P_INTERACT_TAG_LIST_3024.toInteger(), activityTagBaseDto.getId());
                    tagInfo.setSkipModel(skip); //指定标签聚合页
                    tagList.add(tagInfo);
                    i++;
                }
            }
        } catch (Exception ex) {
            logger.error("class :NeighborService ,method:getInteractonTagList is error :",ex);
        }

        return tagList;
    }


    /**
     * 3.3版本 社区首页邻里互动板块组装
     * @param project
     * @param skipModelMap
     * @return
     */
    public InteractBoard fittingInteractBoard (Project project, Map<String, String> skipModelMap){

        InteractBoard interactBoardEntity = new InteractBoard();
        try {
            String projectStr = APIPropertiesClient.getValue("neighbor_project_blacklist");
            String[] projectArray = projectStr.split(",");
            if(QDStringUtil.isNotNull(projectArray) && projectArray.length>0){
                List<String> searchTypeList = Arrays.asList(projectArray);
                if (searchTypeList.contains(String.valueOf(project.getId()))) {
                    interactBoardEntity.setIsShow(0);
                    return interactBoardEntity;
                }
            }

            //获取邻里互动标签列表
            interactBoardEntity.setTagList(getInteractonTagList(project, skipModelMap));

            //邻里互动帖子列表
            List<BriefTopicInfo> topicList = Lists.newArrayList();
            GetNearbyTopicRequest nearByTopicRequest = new GetNearbyTopicRequest();
            nearByTopicRequest.setProjectId(String.valueOf(project.getId()));
            String queryType = APIPropertiesClient.getValue("neighbor_index_topic_query_count");
            nearByTopicRequest.setSize(QDStringUtil.isNotEmpty(queryType)?Integer.parseInt(queryType):2);
            nearByTopicRequest.setSortType("1");
            PageResultDto<TopicDto> topicDtoPageResult = topicRpcV3.getNearbyTopicForNew(nearByTopicRequest);
            List<TopicDto> nearByTopicList = topicDtoPageResult.getList();
            if (CollectionUtils.isNotEmpty(nearByTopicList)) {
                for (TopicDto topicDto : nearByTopicList) {
                    BriefTopicInfo briefTopicInfo = transfor(BriefTopicInfo.class, topicDto);
                    String  topciImgs = topicDto.getTopicImage();
                    if (QDStringUtil.isNotEmpty(topciImgs)) {
                       JSONObject topicImgJson =  JSON.parseObject(topciImgs);
                        if (topicImgJson.size()>0) {
                            JSONArray imgJsonJSONArray =  topicImgJson.getJSONArray("images");
                            if (imgJsonJSONArray.size()>0) {
                                JSONObject indexImgJsonObj = imgJsonJSONArray.getJSONObject(0);
                                String headImg = indexImgJsonObj.getString("url");
                                ImageUtil imageUtil = new ImageUtil();
                                briefTopicInfo.setTopicImg(imageUtil.httpsUrl(headImg));
                            }
                        }
                    }
                    BriefInteractionTagInfo tagInfo = new BriefInteractionTagInfo();
                    tagInfo.setTagId(topicDto.getTagId());
                    tagInfo.setTagName(topicDto.getTagName());
                    String skipModel = "";
                    int subTopicType = topicDto.getSubTopicType();
                    if (subTopicType == Constants.SubTopicType.Neighborhood.getValue()) {
                        skipModel = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                                Constant.SkipNo.P_INTERACT_TAG_LIST_3024.toInteger(), topicDto.getTagId());
                    } else {
                        if (subTopicType == Constants.SubTopicType.TopicST.getValue() || subTopicType == Constants.SubTopicType.TopicTL.getValue()) { //去话题详情

                            skipModel = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                                    Constant.SkipNo.P_TOPIC_DETAIL_3031.toInteger(), topicDto.getParentTopicId());
                        } else if (subTopicType == Constants.SubTopicType.Enroll.getValue()) { //去活动详情
                            skipModel = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                                    Constant.SkipNo.P_ACTIVITY_DETAIL_3028.toInteger(), topicDto.getParentTopicId());
                        } else if (subTopicType == Constants.SubTopicType.News.getValue()) { //新闻
                            skipModel = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                                    Constant.SkipNo.P_NEWS_DETAIL_3022.toInteger(), topicDto.getParentTopicId());

                        } else if (subTopicType == Constants.SubTopicType.Encyclopedia.getValue()) { //百科
                            skipModel = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                                    Constant.SkipNo.P_ENCYCLOPEDIA_DETAIL_3032.toInteger(), topicDto.getParentTopicId());

                        } else if (subTopicType == Constants.SubTopicType.Vote.getValue()) { //投票
                            skipModel = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                                    Constant.SkipNo.P_VOTE_DETAIL_3033.toInteger(), topicDto.getParentTopicId());
                        }
                    }
                    tagInfo.setSkipModel(skipModel);
                    briefTopicInfo.setTagInfo(tagInfo);
                    //帖子skip
                    String skipTopicDetail = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                            Constant.SkipNo.P_INTERACT_DETAIL_3025.toInteger(),topicDto.getId());
                    briefTopicInfo.setSkipModel(skipTopicDetail);
                    topicList.add(briefTopicInfo);
                }
            }

            interactBoardEntity.setTopicList(topicList);
            interactBoardEntity.setBoardTitle("邻里互动");
            String skip = skipMode.fittingNoParameterSkipModel(skipModelMap,
                    Constant.SkipNo.P_INTERACT_LIST_3023.toInteger());
            interactBoardEntity.setSkipModel(skip); //邻里互动列表页
        } catch (Exception ex) {
            logger.error("class :NeighborService ,method:fittingInteractBoard is error :",ex);
        }
        return  interactBoardEntity;
    }

    /**
     * 组装活动板块
     * @param project
     * @param memberId
     * @param skipModelMap
     * @return
     */
    public  ActivityBoard fittingActivityBoard(Project project,String memberId, Map<String, String> skipModelMap){

        ActivityBoard activityBoardEntity = new ActivityBoard();
        try {
            List<ActivityPiazzaDTO> briefActivityList = Lists.newArrayList();
            GetActivity4IndexRequest activity4IndexRequest = new GetActivity4IndexRequest();
            activity4IndexRequest.setCityId(project.getRegionId());
            activity4IndexRequest.setProjectId(project.getId());
            if (QDStringUtil.isNotEmpty(memberId)) {
                activity4IndexRequest.setMemberId(memberId);
            }
            PageResultDto<ActivityDto> activityDtoPageResult = topicRpcV3.getActivity4Index(activity4IndexRequest);
            List<ActivityDto> rpcActivityList = activityDtoPageResult.getList();
            if (CollectionUtils.isNotEmpty(rpcActivityList)) {
                for (ActivityDto activityDto : rpcActivityList) {
                    ActivityPiazzaDTO activityPiazzaDTO = transfor(ActivityPiazzaDTO.class, activityDto);
                    activityPiazzaDTO.setJoinStatus(activityDto.isHaveSignUp() ? 1 : 0);//活动参与状态
                    Long currentTime = System.currentTimeMillis();
                    if (activityDto.getStartTime() != null &&
                            activityDto.getStartTime() > currentTime) {
                        activityPiazzaDTO.setSurplusTime(activityDto.getStartTime() - currentTime); //活动开始剩余时间
                    }
                    activityPiazzaDTO.setStep(getActivityStep(activityPiazzaDTO.getStartTime(), activityPiazzaDTO.getEndTime()));
                    String skip1 = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                            Constant.SkipNo.P_ACTIVITY_DETAIL_3028.toInteger(), activityDto.getId());
                    activityPiazzaDTO.setSkipModel(skip1); //活动详情页
                    briefActivityList.add(activityPiazzaDTO);
                }
            }
            String skip2 = skipMode.fittingNoParameterSkipModel(skipModelMap, Constant.SkipNo.P_ACTIVITY_LIST_3026.toInteger());
            activityBoardEntity.setBoardTitle("报名活动");
            activityBoardEntity.setSkipModel(skip2); //活动列表
            activityBoardEntity.setList(briefActivityList);
        } catch (Exception ex) {
            logger.error("class :NeighborService ,method:fittingActivityBoard is error :",ex);
        }
        return activityBoardEntity;
    }

    private Integer getActivityStep(Long startTime, Long endTime) {
        if (startTime == null || endTime == null) {
            return Constant.TOPIC_STEP_1;
        }
        Long currentTime = System.currentTimeMillis();
        Integer step = null;
        if (startTime > currentTime) {
            step = Constant.TOPIC_STEP_0;
        } else if (startTime <= currentTime && endTime > currentTime) {
            step = Constant.TOPIC_STEP_1;
        } else {
            step = Constant.TOPIC_STEP_2;
        }
        return step;
    }

    /**
     * 组装精选话题板块
     * @param project
     * @param skipModelMap
     * @return
     */
    public ChoicenessBoard fittingChoicenessBoard(Project project, Map<String, String> skipModelMap ){

        ChoicenessBoard choicenessBoard = new ChoicenessBoard();
        try {
            List<TopicPiazzaDTO> choicenessList = Lists.newArrayList();
            TopicRecommendRequest recommendRequest = new TopicRecommendRequest();
            recommendRequest.setProjectId(project.getId());
            recommendRequest.setCityId(project.getRegionId());
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
        } catch (Exception ex) {
            logger.error("class :NeighborService ,method:fittingChoicenessBoard is error :",ex);
        }
        return choicenessBoard;
    }


    public   String fittingNeighborSkipModel(Map<String, String> skipModelMap, Integer subTopicType, String topicId, String parentTopicId) {

        String skip = "";

        if (subTopicType != Constants.SubTopicType.Neighborhood.getValue() && QDStringUtil.isEmpty(parentTopicId)) { //如果是话题或活动类型且没有父ID则认为是原帖
            if (subTopicType == Constants.SubTopicType.TopicST.getValue() || subTopicType == Constants.SubTopicType.TopicTL.getValue()) { //去话题详情

                skip = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                        Constant.SkipNo.P_TOPIC_DETAIL_3031.toInteger(), topicId);
            } else if (subTopicType == Constants.SubTopicType.Enroll.getValue()) { //去活动详情
                skip = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                        Constant.SkipNo.P_ACTIVITY_DETAIL_3028.toInteger(), topicId);
            } else if (subTopicType == Constants.SubTopicType.News.getValue()) { //新闻
                skip = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                        Constant.SkipNo.P_NEWS_DETAIL_3022.toInteger(), topicId);

            } else if (subTopicType == Constants.SubTopicType.Encyclopedia.getValue()) { //百科
                skip = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                        Constant.SkipNo.P_ENCYCLOPEDIA_DETAIL_3032.toInteger(), topicId);

            } else if (subTopicType == Constants.SubTopicType.Vote.getValue()) { //投票
                skip = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                        Constant.SkipNo.P_VOTE_DETAIL_3033.toInteger(), topicId);
            }

        } else { //邻里互动，或跟帖详情
            skip = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                    Constant.SkipNo.P_INTERACT_DETAIL_3025.toInteger(), topicId);
        }

        return skip;
    }

}
