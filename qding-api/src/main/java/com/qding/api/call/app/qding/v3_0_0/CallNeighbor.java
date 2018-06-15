package com.qding.api.call.app.qding.v3_0_0;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.UserInfo;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Account;
import com.qding.api.call.app.qding.v1_3_0.struct.user.AccountMember;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Member;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.CommentBriefMember;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicComment;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicNotify;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.DelCommentRequest;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.DelTopicRequest;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.ReportTopicRequest;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data.AddCommentResponseData;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data.AddPraiseResponseData;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data.DelCommentResponseData;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data.DelTopicResponseData;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data.GetHistoryNotifyByMIdResponseData;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data.GetNotifyByMIdResponseData;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data.ReportTopicResponseData;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data.VoteResponseData;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.NeighborBanner;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.ActivityInfo;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.ActivityPiazzaDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.ActivitySessionDto;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.BriefEncyclopedia;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.BriefNews;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.BriefVoteItemDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.EncyclopediaDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.EnrollMember;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.FeedPageOrderByRuleDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.PageResultDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.RecommendEncyclopediaDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.Tag;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.TopicCommon;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.TopicDetail;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.TopicPiazzaDTO;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.VoteInfo;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.ActivityBoard;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefInteractionTagInfo;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefMember;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefNewsInfo;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefTopicInfo;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.ChoicenessBoard;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.ChoicenessTopicInfo;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.EncyclopediaBoard;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.InteractBoard;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.NewsBoard;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.AddCommentRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.AddPraiseRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.BaiKeListRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.BaoMingRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.CommentListRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.DianZanRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.EnrollRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.GetActivityPiazzaIndexRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.GetHistoryNotifyByMIdRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.GetNeighborIndexRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.GetNewsListRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.GetNotifyByMIdRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.GetPersonIndexRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.GetTagListRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.GetTopicDetailRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.GetTopicFlowRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.GetTopicPiazzaIndexRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.PublishTopicRequest;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data.BaiKeListResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data.BaoMingResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data.CommentListResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data.DianZanResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data.EnrollResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data.GetActivityPiazzaIndexResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data.GetNeighborIndexResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data.GetNewsListResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data.GetPersonIndexResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data.GetTagListResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data.GetTopicDetailResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data.GetTopicFlowResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data.GetTopicPiazzaIndexResponseData;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data.PublishTopicResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.RecommendDto;
import com.qding.api.call.app.qding.v3_3_0.struct.neighbor.ReferenceTopicDto;
import com.qding.api.call.service.NeighborService;
import com.qding.api.call.service.SkipModelService;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.EntitySerialUtil;
import com.qding.api.util.ImageUtil;
import com.qding.api.util.QDVersionUtil;
import com.qding.api.util.SkipModeFitting;
import com.qding.api.verifycode.store.RedisStoreDevice;
import com.qding.api.verifycode.store.StoreDevice;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.contract.Supplier;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.contract.SupplierRemote;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.manager.common.ModelResult;
import com.qding.manager.domain.Puser;
import com.qding.manager.service.IPuserRPCService;
import com.qding.neighbor.common.Constants;
import com.qding.neighbor.dto.cache.TopicNotifyDTO;
import com.qding.neighbor.rpc.ITopicRpc;
import com.qding.neighbor.rpc.request.EnrollTopicRequest;
import com.qding.neighbor.rpc.request.GetMemberStatusRequest;
import com.qding.neighbor.rpc.request.GetTopicFavoriteRequest;
import com.qding.neighbor.rpc.request.HandlePraiseRequest;
import com.qding.neighbor.rpc.response.data.AddCommentResponse;
import com.qding.neighbor.rpc.response.data.GetMemberStatusResponse;
import com.qding.neighbor.rpc.response.data.GetNotifyByMIdResponse;
import com.qding.neighbor.rpc.response.data.GetTopicFavoriteResponse;
import com.qding.neighbor.v3.domain.ActivitySession;
import com.qding.neighbor.v3.domain.TopicV3;
import com.qding.neighbor.v3.dto.ActivityDto;
import com.qding.neighbor.v3.dto.ActivityTagBaseDto;
import com.qding.neighbor.v3.dto.CommentDto;
import com.qding.neighbor.v3.dto.EncyclopediaBaseDto;
import com.qding.neighbor.v3.dto.LableDto;
import com.qding.neighbor.v3.dto.MemberDto;
import com.qding.neighbor.v3.dto.NewsDto;
import com.qding.neighbor.v3.dto.PageResultDto;
import com.qding.neighbor.v3.dto.TopicBaseDto;
import com.qding.neighbor.v3.dto.TopicDto;
import com.qding.neighbor.v3.dto.TopicRecommendDto;
import com.qding.neighbor.v3.dto.VoteDto;
import com.qding.neighbor.v3.enums.EnumModuleType;
import com.qding.neighbor.v3.enums.EnumNewsType;
import com.qding.neighbor.v3.rpc.ITopicRpcV3;
import com.qding.neighbor.v3.rpc.requst.AddTopicRequestV3;
import com.qding.neighbor.v3.rpc.requst.GetActivity4IndexRequest;
import com.qding.neighbor.v3.rpc.requst.GetActivityListRequest;
import com.qding.neighbor.v3.rpc.requst.GetCommentsBytopicIdRequest;
import com.qding.neighbor.v3.rpc.requst.GetEncyclopediaListRequest;
import com.qding.neighbor.v3.rpc.requst.GetJoinActivityMemberBytopicIdRequest;
import com.qding.neighbor.v3.rpc.requst.GetLikeListBytopicRequest;
import com.qding.neighbor.v3.rpc.requst.GetListOrderByTopicCountRequest;
import com.qding.neighbor.v3.rpc.requst.GetMemberActiveCountRequest;
import com.qding.neighbor.v3.rpc.requst.GetNearbyTopicRequest;
import com.qding.neighbor.v3.rpc.requst.GetNewsDtoListRequest;
import com.qding.neighbor.v3.rpc.requst.GetSignUpActivityListRequest;
import com.qding.neighbor.v3.rpc.requst.GetTakePartInTopicListRequest;
import com.qding.neighbor.v3.rpc.requst.GetTopicByLableRequest;
import com.qding.neighbor.v3.rpc.requst.GetTopicByPidRequest;
import com.qding.neighbor.v3.rpc.requst.GetTopicBytopicIdRequest;
import com.qding.neighbor.v3.rpc.requst.GetTopicCollectionRequest;
import com.qding.neighbor.v3.rpc.requst.GetTopicDtosByMemberIdRequest;
import com.qding.neighbor.v3.rpc.requst.GetVoteDtoRequest;
import com.qding.neighbor.v3.rpc.requst.TopicRecommendRequest;
import com.qding.neighbor.v3.rpc.requst.VoteRequest;
import com.qding.neighbor.v3.rpc.response.data.GetLableByIdResponseData;
import com.qding.neighbor.v3.rpc.response.data.GetLableListResponseData;
import com.qding.neighbor.v3.rpc.response.data.GetMemberActiveCountResponseData;
import com.qding.neighbor.v3.rpc.response.data.GetTopicBytopicIdResponseData;
import com.qding.neighbor.v3.rpc.response.data.VoteDtoResponseData;
import com.qding.passport.service.IPassportService;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetAccountRequest;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetAccountResponse;
import com.qding.passport.struct.response.GetMemberResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;
import org.joda.time.DateTime;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by qd on 2017/2/23.
 */
public class CallNeighbor extends com.qding.api.call.app.qding.v2_8_0.CallNeighbor {


    @Autowired
    private ITopicRpcV3 topicRpcV3;

    @Autowired
    private ITopicRpc topicRpc;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private IPassportService passportAPI;

    @Autowired
    private IProfileService profileAPI;

    @Autowired
    private IPuserRPCService puserRPCService;

    @Autowired
    private ImageUtil imageUtil;
    
    @Autowired
    private SkipModelService skipService;

    @Autowired
    private NeighborService neighborService;

    private static Logger logger = Logger.getLogger(CallNeighbor.class);

    @HTTP(alias = "publishTopic", isRequireAuth = true, isNeadProject = true)
    @ExplainAnnotation(explain = "发布帖子")
    public Response<PublishTopicResponseData> publishTopic(PublishTopicRequest request, UserToken userToken) {
        Response<PublishTopicResponseData> response = new Response<PublishTopicResponseData>();
        PublishTopicResponseData data = new PublishTopicResponseData();
        try {
            AddTopicRequestV3 v3 = new AddTopicRequestV3();
            v3.setMemberId(userToken.getMemberId());
            v3.setUserId(userToken.getAccountId());
            v3.setParentTopicId(request.getParentTopicId());
            v3.setSubTopicType(request.getSubTopicType());
            v3.setTopicContent(request.getTopicContent());
            v3.setTagId(request.getTagId());
            v3.setProjectId(Long.parseLong(request.getAppUser().getProjectId()));
            if (CollectionUtils.isNotEmpty(request.getTopicImage())) {
                JSONArray array = new JSONArray();
                int index = 1;
                for (String url : request.getTopicImage()) {
                    if (StringUtils.isNotEmpty(url)) {
                        JSONObject sub = new JSONObject();
                        sub.put("index", index);
                        sub.put("url", url);
                        array.add(sub);
                    }
                    index++;
                }
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("images", array);
                v3.setTopicImage(jsonObject.toJSONString());
            }
            BaseResponse baseResponse = topicRpcV3.addTopicV3(v3);
            checkAndContinue(baseResponse);
            response.setCode(HttpStatus.OK.getStatusCode());
        } catch (Exception ex) {
            return handleException(PublishTopicResponseData.class, ex);
        }
        response.setData(data);
        return response;
    }


    @ExplainAnnotation(explain = "社区生活首页")
    @HTTP(alias = "getNeighborIndex")
    public Response<GetNeighborIndexResponseData> getNeighborIndex(GetNeighborIndexRequest request, UserToken userToken) {

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

        GetNeighborIndexResponseData data = new GetNeighborIndexResponseData();
        Long projectId = Long.parseLong(request.getAppUser().getProjectId());
        Project project = projectReadService.get(projectId);
        Long regionId = project.getRegionId(); //城市ID
        Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());

        // banner部分------------------------------------------------------------------------------------1
        List<NeighborBanner> bannerList = Lists.newArrayList();
        TopicRecommendRequest recommendRequest = new TopicRecommendRequest();
        recommendRequest.setProjectId(projectId);
        recommendRequest.setCityId(regionId);
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

        //社区报名(活动) ------------------------------------------------------------------------------------4
        ActivityBoard activityBoardEntity = new ActivityBoard();
        List<ActivityPiazzaDTO> briefActivityList = Lists.newArrayList();
        GetActivity4IndexRequest activity4IndexRequest = new GetActivity4IndexRequest();
        activity4IndexRequest.setCityId(regionId);
        activity4IndexRequest.setProjectId(projectId);
        if (userToken != null) {
            activity4IndexRequest.setMemberId(userToken.getMemberId());
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
        String skip2 = skipMode.fittingNoParameterSkipModel(skipModelMap,
                Constant.SkipNo.P_ACTIVITY_LIST_3026.toInteger());
        activityBoardEntity.setBoardTitle("社区活动");
        activityBoardEntity.setSkipModel(skip2); //活动列表
        activityBoardEntity.setList(briefActivityList);
        data.setActivityBoard(activityBoardEntity);

        //精选话题------------------------------------------------------------------------------------5
        ChoicenessBoard choicenessBoard = new ChoicenessBoard();
        List<ChoicenessTopicInfo> choicenessList = Lists.newArrayList();
        recommendRequest.setModuleType(EnumModuleType.WELL_CHOSEN);
        PageResultDto<TopicRecommendDto> choicenessPageResult = topicRpcV3.getTopicRecommend(recommendRequest);
        List<TopicRecommendDto> rpcChoicenessList = choicenessPageResult.getList();
        if (CollectionUtils.isNotEmpty(rpcChoicenessList)) {
            for (TopicRecommendDto topicRecommendDto : rpcChoicenessList) {
                ChoicenessTopicInfo choicenessTopicInfo = transfor(ChoicenessTopicInfo.class, topicRecommendDto);
                String skip1 = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                        Constant.SkipNo.P_TOPIC_DETAIL_3031.toInteger(), topicRecommendDto.getTopicId());
                if (Constant.SubTopicTypeEnum.VOTE_3.toInteger().intValue() == topicRecommendDto.getTopicType()) {
                    skip1 = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                            Constant.SkipNo.P_VOTE_DETAIL_3033.toInteger(), topicRecommendDto.getTopicId());
                }
                choicenessTopicInfo.setSkipModel(skip1); //帖子详情
                choicenessList.add(choicenessTopicInfo);
            }
        }
        choicenessBoard.setBoardTitle("精选话题");
        choicenessBoard.setChoicenessList(choicenessList);
        String skip3 = skipMode.fittingNoParameterSkipModel(skipModelMap,
                Constant.SkipNo.P_TOPIC_LIST_3029.toInteger());
        choicenessBoard.setSkipModel(skip3); //精选话题列表
        data.setChoicenessBoard(choicenessBoard);
        //生活百科------------------------------------------------------------------------------------6
        try {
            EncyclopediaBoard encyclopediaBoard = new EncyclopediaBoard();
            encyclopediaBoard.setBoardTitle("生活百科");
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


    @ExplainAnnotation(explain = "获取新闻分页列表")
    @HTTP(alias = "getNewsList", isNeadProject = true)
    public Response<GetNewsListResponseData> getNewsList(GetNewsListRequest request,UserToken userToken) {

        Response<GetNewsListResponseData> response = new Response<GetNewsListResponseData>();
        GetNewsListResponseData data = new GetNewsListResponseData();
        FeedPageOrderByRuleDTO rule = getMultipleOrderFields(request.getOrderByRule());
        try {
            Long projectId = Long.parseLong(request.getAppUser().getProjectId());
            Project project = projectReadService.get(projectId);
            Long regionId = project.getRegionId(); //城市ID
            Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());
            
            GetNewsDtoListRequest getNewsDtoListRequest = new GetNewsDtoListRequest();
            getNewsDtoListRequest.setCityId(regionId);
            getNewsDtoListRequest.setProjectId(projectId);
            getNewsDtoListRequest.setLastPublishTime(rule.getLastTime());
            getNewsDtoListRequest.setPageSize(request.getPageSize());
            if(StringUtils.isNotBlank(request.getNewsType()) && !request.getNewsType().equals("0")){
            	 getNewsDtoListRequest.setNewsType(request.getNewsType());
            }
            String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
            if (Integer.parseInt(initVersion) >=330000 && userToken!=null){
            	//3.3以前没有标记新闻是new，3.3 标记新闻为new， member为空就不进行new校验
            	getNewsDtoListRequest.setMemberId(userToken.getMemberId());
                getNewsDtoListRequest.setCheckSource(1);
            }  
            PageResultDTO<BriefNewsInfo> newsPage = fittingPageWithnewLable(getNewsDtoListRequest,skipModelMap);
            if (QDStringUtil.isNotNull(newsPage) && CollectionUtils.isNotEmpty(newsPage.getList())) {
                List<BriefNewsInfo> list = newsPage.getList();
                FeedPageOrderByRuleDTO orderByRuleDto = new FeedPageOrderByRuleDTO();
                BriefNewsInfo lastInfo = list.get(list.size() - 1);
                orderByRuleDto.setLastTime(lastInfo.getPublishTime());
                data.setOrderByRule(JSON.toJSONString(orderByRuleDto));
                data.setNewsList(list);
                data.setHaveNextPage(newsPage.isHaveNextPage());
            }
            response.setData(data);
        } catch (Exception e) {
            logger.error("method : getNewsList  error:", e);
            return handleException(GetNewsListResponseData.class, e);
        }

        return response;
    }


    @ExplainAnnotation(explain = "百科列表")
    @HTTP(alias = "getEncyclopediaList", isNeadProject = true)
    public Response<BaiKeListResponseData> getEncyclopediaDetail(BaiKeListRequest request) {
        Response<BaiKeListResponseData> response = new Response<BaiKeListResponseData>();
        BaiKeListResponseData data = new BaiKeListResponseData();
        try {
            Long projectId = Long.parseLong(request.getAppUser().getProjectId());
            Project project = projectReadService.get(projectId);
            Long regionId = project.getRegionId();
            Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());
            FeedPageOrderByRuleDTO rule = getMultipleOrderFields(request.getOrderByRule());
            PageResultDTO<BriefEncyclopedia> newsPage = fittingEncyclopediaList(regionId, projectId, rule.getLastTime(),
                    request.getPageSize(), skipModelMap);
            if (QDStringUtil.isNotNull(newsPage) && CollectionUtils.isNotEmpty(newsPage.getList())) {
                List<BriefEncyclopedia> list = newsPage.getList();
                FeedPageOrderByRuleDTO orderByRuleDto = new FeedPageOrderByRuleDTO();
                BriefEncyclopedia lastInfo = list.get(list.size() - 1);
                orderByRuleDto.setLastTime(lastInfo.getPublishTime());
                data.setOrderByRule(JSON.toJSONString(orderByRuleDto));
                data.setEncyclopediaList(list);
                data.setHaveNextPage(newsPage.isHaveNextPage());
                response.setCode(HttpStatus.OK.getStatusCode());
                response.setData(data);
            }
        } catch (Exception ex) {
            logger.error("method : getEncyclopediaDetail  error:", ex);
            return handleException(BaiKeListResponseData.class, ex);
        }
        return response;
    }


    /**
     * 获取多排序规则数据方法
     *
     * @param orderRuleStr
     * @return
     */
    public FeedPageOrderByRuleDTO getMultipleOrderFields(String orderRuleStr) {

        FeedPageOrderByRuleDTO rule =null;
        if (QDStringUtil.isNotEmpty(orderRuleStr)) {
            rule = JSON.parseObject(orderRuleStr, FeedPageOrderByRuleDTO.class);
        }
        if (rule== null) {
            rule = new FeedPageOrderByRuleDTO();
            rule.setLastTime(Long.MAX_VALUE);
            rule.setLastStartTime(Long.MAX_VALUE);
            rule.setLastEndTime(Long.MAX_VALUE);
        }
        return rule;
    }


    /**
     * 组装新闻列表
     *
     * @param regionId
     * @param projectId
     * @param skipModelMap
     * @param bigThanTime
     * @return
     * @throws ServiceException
     */
    private PageResultDTO<BriefNewsInfo> fittingPageNewsList(Long regionId, Long projectId, Long lastPublishTime,
                                                             Integer pageSize, Map<String, String> skipModelMap, Long bigThanTime) throws ServiceException {

        PageResultDTO<BriefNewsInfo> page = new PageResultDTO<BriefNewsInfo>();
        List<BriefNewsInfo> newsList = Lists.newArrayList();
        GetNewsDtoListRequest getNewsDtoListRequest = new GetNewsDtoListRequest();
        getNewsDtoListRequest.setCityId(regionId);
        getNewsDtoListRequest.setProjectId(projectId);
        getNewsDtoListRequest.setLastPublishTime(lastPublishTime);
        if (bigThanTime != null) {
            getNewsDtoListRequest.setBigThanTime(bigThanTime);
        }
        getNewsDtoListRequest.setPageSize(pageSize);
        PageResultDto<NewsDto> newsPageResult = topicRpcV3.getNewsPageDtoList(getNewsDtoListRequest);
        checkAndContinue(newsPageResult);
        List<NewsDto> rpcNewsList = newsPageResult.getList();
        if (CollectionUtils.isNotEmpty(rpcNewsList)) {
            for (NewsDto newsDto : rpcNewsList) {
                BriefNewsInfo briefNewsInfo = new BriefNewsInfo();
                briefNewsInfo.setId(newsDto.getId());
                briefNewsInfo.setNewsType(newsDto.getTypeName());
                briefNewsInfo.setTitle(newsDto.getTitle());
                briefNewsInfo.setPublishTime(newsDto.getPublishTime());
                briefNewsInfo.setImgUrl(newsDto.getImgUrl());
                briefNewsInfo.setSkipModel(
                        skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.P_NEWS_DETAIL_3022.toInteger(),
                                newsDto.getId()));
                newsList.add(briefNewsInfo);
            }
            page.setList(newsList);
            page.setHaveNextPage(newsPageResult.isHaveNextPage());
        }
        return page;
    }
    
    /**
     * 组装新闻列表
     *
     * @param skipModelMap
     * @return
     * @throws ServiceException
     */
    private PageResultDTO<BriefNewsInfo> fittingPageWithnewLable(GetNewsDtoListRequest request,Map<String, String> skipModelMap) throws ServiceException {

        PageResultDTO<BriefNewsInfo> page = new PageResultDTO<BriefNewsInfo>();
        List<BriefNewsInfo> newsList = Lists.newArrayList();
        PageResultDto<NewsDto> newsPageResult = topicRpcV3.getNewsPageWithNewLable(request);
        checkAndContinue(newsPageResult);
        List<NewsDto> rpcNewsList = newsPageResult.getList();
        if (CollectionUtils.isNotEmpty(rpcNewsList)) {
            for (NewsDto newsDto : rpcNewsList) {
                BriefNewsInfo briefNewsInfo = new BriefNewsInfo();
                briefNewsInfo.setId(newsDto.getId());
                briefNewsInfo.setNewsType(newsDto.getTypeName());
                briefNewsInfo.setTitle(newsDto.getTitle());
                briefNewsInfo.setPublishTime(newsDto.getPublishTime());
                briefNewsInfo.setImgUrl(newsDto.getImgUrl());
                briefNewsInfo.setIsNew(newsDto.getIsNew());
                briefNewsInfo.setSkipModel(
                        skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.P_NEWS_DETAIL_3022.toInteger(),
                                newsDto.getId()));
                newsList.add(briefNewsInfo);
            }
            page.setList(newsList);
            page.setHaveNextPage(newsPageResult.isHaveNextPage());
        }
        return page;
    }
    

    /**
     * 组装新闻列表
     *
     * @param regionId
     * @param projectId
     * @param skipModelMap
     * @param bigThanTime
     * @return
     * @throws ServiceException
     */
    public PageResultDTO<BriefNewsInfo> fittingIndexNewsList(Long regionId, Long projectId, Long lastPublishTime,
                                                             Integer pageSize, Map<String, String> skipModelMap, Long bigThanTime) throws ServiceException {

        PageResultDTO<BriefNewsInfo> page = new PageResultDTO<BriefNewsInfo>();
        List<BriefNewsInfo> newsList = Lists.newArrayList();
        GetNewsDtoListRequest getNewsDtoListRequest = new GetNewsDtoListRequest();
        getNewsDtoListRequest.setCityId(regionId);
        getNewsDtoListRequest.setProjectId(projectId);
        getNewsDtoListRequest.setLastPublishTime(lastPublishTime);
        if (bigThanTime != null) {
            getNewsDtoListRequest.setBigThanTime(bigThanTime);
        }
        getNewsDtoListRequest.setPageSize(pageSize);
        PageResultDto<NewsDto> newsPageResult = topicRpcV3.getNewsDtoList(getNewsDtoListRequest);
        checkAndContinue(newsPageResult);
        List<NewsDto> rpcNewsList = newsPageResult.getList();
        if (CollectionUtils.isNotEmpty(rpcNewsList)) {
            for (NewsDto newsDto : rpcNewsList) {
                BriefNewsInfo briefNewsInfo = new BriefNewsInfo();
                briefNewsInfo.setId(newsDto.getId());
                briefNewsInfo.setNewsType(newsDto.getTypeName());
                briefNewsInfo.setTitle(newsDto.getTitle());
                briefNewsInfo.setPublishTime(newsDto.getPublishTime());
                briefNewsInfo.setImgUrl(newsDto.getImgUrl());
                briefNewsInfo.setSkipModel(
                        skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.P_NEWS_DETAIL_3022.toInteger(),
                                newsDto.getId()));
                newsList.add(briefNewsInfo);
            }
            page.setList(newsList);
            page.setHaveNextPage(newsPageResult.isHaveNextPage());
        }
        return page;
    }


    /**
     * 组装百科列表
     *
     * @param regionId
     * @param projectId
     * @param lastPublishTime
     * @param pageSize
     * @param skipModelMap
     * @return
     * @throws ServiceException
     */
    private PageResultDTO<BriefEncyclopedia> fittingEncyclopediaList(Long regionId, Long projectId,
                                                                     Long lastPublishTime, Integer pageSize, Map<String, String> skipModelMap) throws ServiceException {

        PageResultDTO<BriefEncyclopedia> page = new PageResultDTO<BriefEncyclopedia>();
        List<BriefEncyclopedia> encyclopediaList = Lists.newArrayList();
        GetEncyclopediaListRequest encyclopediaListRequest = new GetEncyclopediaListRequest();
        encyclopediaListRequest.setProjectId(projectId);
        encyclopediaListRequest.setCityId(regionId);
        encyclopediaListRequest.setPageSize(pageSize);
        encyclopediaListRequest.setLastPublishTime(lastPublishTime);
        PageResultDto<EncyclopediaBaseDto> rpcEncycloppediaPageResult = topicRpcV3
                .getEncyclopediaList(encyclopediaListRequest);
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
            page.setHaveNextPage(rpcEncycloppediaPageResult.isHaveNextPage());
            page.setList(encyclopediaList);
        }
        return page;
    }


    @HTTP(alias = "getTopicDetail")
    @ExplainAnnotation(explain = "获取话题详情")
    public Response<GetTopicDetailResponseData> getTopicDetail(GetTopicDetailRequest request, UserToken userToken) {

    	Response<GetTopicDetailResponseData> response = new Response<GetTopicDetailResponseData>();
    	GetTopicDetailResponseData data = new GetTopicDetailResponseData();
    	response.setCode(HttpStatus.OK.getStatusCode());
    	try {
    		GetTopicBytopicIdRequest topicBytopicIdRequest = transfor(GetTopicBytopicIdRequest.class, request);
    		topicBytopicIdRequest.setIncludeCommentList(request.getShowCommentSize() != null && request.getShowCommentSize() > 0);
    		topicBytopicIdRequest.setIncludeLikeList(request.getShowPraiseSize() != null && request.getShowPraiseSize() > 0);
    		topicBytopicIdRequest.setIncludeJoinList(request.getShowEnrollSize() != null && request.getShowEnrollSize() > 0);
    		if (userToken != null && userToken.getMemberId() != null) {
    			topicBytopicIdRequest.setMemberId(userToken.getMemberId());
    		}
    		GetTopicBytopicIdResponseData topicBytopicIdResponseData = topicRpcV3
    				.getTopicBytopicId(topicBytopicIdRequest);
    		checkAndContinue(topicBytopicIdResponseData);
    		TopicDetail entity = new TopicDetail();
    		TopicDto topicDto = topicBytopicIdResponseData.getTopic();
    		Long projectId=null;
			if(StringUtils.isNotBlank(request.getAppUser().getProjectId())){
				projectId=Long.parseLong(request.getAppUser().getProjectId());
			}
    		//数据校验
    		if (topicDto == null || topicDto.getStatus() == 4) {
    			response.setCode(412);
    			data.setMessage("您查看的内容已冻结");
    			response.setData(data);
    			return response;
    		}
    		if (topicDto == null || topicDto.getStatus() == 7) {
    			response.setCode(416);
    			data.setMessage("您查看的内容已下架");
    			response.setData(data);
    			return response;
    		}
    		if (topicDto.getStatus() == 5) {
    			response.setCode(413);
    			data.setMessage("您查看的内容已删除");
    			response.setData(data);
    			return response;
    		}
    		if ((topicDto.getStatus() == 6 && userToken == null) ||
    				(topicDto.getStatus() == 6 && !userToken.getMemberId().equals(topicDto.getMemberId()))) {
    			response.setCode(415);
    			data.setMessage("用户无权限");
    			response.setData(data);
    			return response;
    		}
    		if (Constant.SubTopicTypeEnum.VOTE_3.toInteger() == topicDto.getSubTopicType()) { //投票
    			GetVoteDtoRequest voteDtoRequest = new GetVoteDtoRequest();
    			if (QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId())) {
    				voteDtoRequest.setMemberId(userToken.getMemberId());
    			}
    			voteDtoRequest.setTopicId(request.getTopicId());
    			VoteDtoResponseData voteDtoResponseData = topicRpcV3.getVoteDto(voteDtoRequest);
    			checkAndContinue(voteDtoResponseData);
    			VoteDto voteDto = voteDtoResponseData.getVoteDto();
    			entity = new TopicDetail();
    			TopicCommon topicCommon = transfor(TopicCommon.class, topicDto);
    			topicCommon.setStep(getActivityStep(voteDto.getStartTime(), voteDto.getEndTime()));
    			topicCommon.setSubTopicType(topicDto.getSubTopicType());
    			topicCommon.setTopicDesc(voteDto.getContent());
    			topicCommon.setIntroduction(voteDto.getIntroduction());
    			topicCommon.setTopicContent(voteDto.getContent());
    			entity.setCommonInfo(topicCommon);
    			VoteInfo voteInfo = transfor(VoteInfo.class, voteDto);
    			voteInfo.setJoinStatus(voteDto.isHaveVoted() ? 1 : 0);
    			voteInfo.setStartTime(voteDto.getStartTime());
    			voteInfo.setEndTime(voteDto.getEndTime());
    			//填充投票选项配置的百科连接
    			if(voteInfo.getVoteList()!=null && voteInfo.getVoteList().size()>0){
    				Map<String, String> skipModelMap = 
    						skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());
    				for(BriefVoteItemDTO dto:voteInfo.getVoteList()){
    					if(StringUtils.isNotBlank(dto.getTopicId())){
    						String skipmode = skipMode
    								.fittingSkipModelByOnlyId(skipModelMap, 
    										Constant.SkipNo.P_ENCYCLOPEDIA_DETAIL_3032.toInteger(),
    										dto.getTopicId());
    						dto.setSkipModel(skipmode);
    					}
    				}
    			}
    			Integer voteStatus = 1;
    			voteInfo.setVoteTotalCount(voteDto.getTotalCount());
    			if (voteDto.getEndTime() != null && voteDto.getEndTime() < System.currentTimeMillis()) {
    				voteStatus = 0;
    			}
    			voteInfo.setVoteStatus(voteStatus);
    			entity.setVoteInfo(voteInfo);
    		} else {
    			TopicCommon topicCommon = transfor(TopicCommon.class, topicDto);
    			topicCommon.setStep(getActivityStep(topicDto.getActivityBeginTime(), topicDto.getActivityEndTime()));
    			topicCommon.setIsCollapse(topicDto.getIsCollapse());
    			topicCommon.setIntroduction(topicDto.getIntroduction());
    			entity.setCommonInfo(topicCommon);
    			Integer subTopicType = topicDto.getSubTopicType();
    			if (Constant.SubTopicTypeEnum.ACTIVITY_4.toInteger() == subTopicType) { //活动
    				final ActivityInfo activityInfo = transfor(ActivityInfo.class, topicDto);
    				if (CollectionUtils.isNotEmpty(topicBytopicIdResponseData.getListJoin())) {
    					List<BriefMember> memberList = transforList(BriefMember.class,
    							topicBytopicIdResponseData.getListJoin());
    					activityInfo.setMemberList(memberList); //报名人列表

    				}
    				Long surplusTime = 0L;
    				if (topicDto.getActivityBeginTime() != null) {
    					surplusTime = topicDto.getActivityBeginTime() - System.currentTimeMillis();
    				}
    				activityInfo.setSurplusTime(surplusTime > 0 ? surplusTime : 0);
    				if (activityInfo.getEnrollCount() != null && activityInfo.getActivityTotalCount() != null) {
    					activityInfo.setEnrollStatus(
    							activityInfo.getEnrollCount() < activityInfo.getActivityTotalCount() ? 0 : 1);
    				}
    				Integer step = getActivityStep(activityInfo.getStartTime(), activityInfo.getEndTime());
    				activityInfo.setStep(step);
    				if (Constant.TOPIC_STEP_0 != step) {
    					topicCommon.setIsCollapse(true);
    				}
    				activityInfo.setJoinStatus(topicDto.getIsBm() ? 1 : 0);
    				String coordinate = topicDto.getActivityAddrCoordinate();
    				if (StringUtils.isNotBlank(coordinate)) {
    					String[] arrays = coordinate.split(",");
    					activityInfo.setLongitude(arrays[0]);
    					activityInfo.setLatitude(arrays[1]);
    				}
    				activityInfo.setLimitCountPer(topicDto.getActivityLimitPer());


    				List<ActivitySession> activitySessions = topicRpcV3.getActivitySessionList(request.getTopicId());
    				if (CollectionUtils.isNotEmpty(activitySessions)) {
    					List<ActivitySessionDto> activitySessionDtos = Lists.newArrayList();
    					int enrollCount = 0;
    					for(ActivitySession activitySession: activitySessions)
    					{
    						enrollCount += activitySession.getEnrollCount();
    						ActivitySessionDto activitySessionDto = new ActivitySessionDto(activitySession.getId(), activitySession.getTitle(), activitySession.getLimitCount(), activitySession.getEnrollCount());
    						activitySessionDtos.add(activitySessionDto);
    					}
    					activityInfo.setActivitySessionDtoList(activitySessionDtos);
    					activityInfo.setEnrollCount(QDStringUtil.isNotNull(topicDto.getBmCount())?topicDto.getBmCount():0);
    				}
    				entity.setActivityInfo(activityInfo);

    			} else if (Constant.SubTopicTypeEnum.NEWS_6.toInteger() == subTopicType) { //新闻
    				BriefNews newsInfo = transfor(BriefNews.class, topicDto);
    				newsInfo.setNewsTypeId(newsInfo.getNewsType());
    				newsInfo.setNewsType(EnumNewsType.getEnum(Integer.valueOf(newsInfo.getNewsType())).getDescription());
    				entity.setNewsInfo(newsInfo);
    				topicCommon.setCreateTime(topicDto.getPublishTime());
    				//以下3.3 功能 
    				packageNewsBaikeContent(request, userToken, topicCommon);
    				//填充推荐
    				List<TopicDto> list=topicRpcV3.getTopicRecomment(request.getTopicId(),projectId);
    				if(list!=null && list.size()>0){
    					List<RecommendDto> recommendList=new ArrayList<RecommendDto>();
    					Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());
    					for(TopicDto d:list){
    						RecommendDto dr=new RecommendDto();
    						dr.setPublishTime(d.getPublishTime());
    						dr.setTopicTitle(d.getTopicTitle());
    						dr.setTypeName(EnumNewsType.getEnum(Integer.parseInt(d.getNewsType())).getDescription());
    						dr.setSkipModel(
    				                skipMode.fittingSkipModelByOnlyId(skipModelMap, Constant.SkipNo.P_NEWS_DETAIL_3022.toInteger(),
    				                        d.getId()));
    						List<String> listimage=new ArrayList<String>();
    						listimage.add(d.getTopicImage());
    						dr.setTopicImage(listimage);
    						recommendList.add(dr);
    					}
    					newsInfo.setRecommendList(recommendList);
    				}
    				//跟帖走gettopicflow接口
    				
    			} else if (Constant.SubTopicTypeEnum.ENCYCOLPEDIA_7.toInteger() == subTopicType) { //百科

    				BriefEncyclopedia briefEncyclopedia = transfor(BriefEncyclopedia.class, topicDto);
    				briefEncyclopedia.setAuthor(topicDto.getEncyclopediaAuthor());
    				EncyclopediaDTO encyclopediaInfo = transfor(EncyclopediaDTO.class, briefEncyclopedia);
    				encyclopediaInfo.setTypeNameId(topicDto.getInterestTagId());
    				encyclopediaInfo.setTypeName(topicDto.getBkTypeName());
    				List<RecommendEncyclopediaDTO> getRecommendList = Lists.newArrayList();
    				try {
    					List<TopicDto> list=topicRpcV3.getTopicRecomment(request.getTopicId(),projectId);
    					if (CollectionUtils.isNotEmpty(list)) {
    						for (TopicDto encyclopediaBaseDto : list) {
    							if (getRecommendList.size() >= 3) {
    								break;
    							}
    							RecommendEncyclopediaDTO encyclopediaDTO = new RecommendEncyclopediaDTO();
    							encyclopediaDTO.setTopicTitle(encyclopediaBaseDto.getTopicTitle());
                                encyclopediaDTO.setTypeName(encyclopediaBaseDto.getInterestTagName());
                                encyclopediaDTO.setPublishTime(encyclopediaBaseDto.getPublishTime());
                                
                                List<String> listimage=new ArrayList<String>();
        						listimage.add(encyclopediaBaseDto.getTopicImage());
        						encyclopediaDTO.setTopicImage(listimage);
                                
    							String skipmode = skipMode
    									.fittingSkipModelByOnlyId(request.getAppDevice().getQdVersion(),
    											Constant.SkipNo.P_ENCYCLOPEDIA_DETAIL_3032.toInteger(),
    											encyclopediaBaseDto.getId());
    							encyclopediaDTO.setSkipModel(skipmode);
    							getRecommendList.add(encyclopediaDTO);
    						}
    					}
    					
    					//以下3.3 功能 
        				packageNewsBaikeContent(request, userToken, topicCommon);
    					
    				} catch (Exception ex) {
    					ex.printStackTrace();
    					logger.error("class: CallNeighbor(3.0) get encyclopediaList error: ", ex);
    				}
    				encyclopediaInfo.setRecommendList(getRecommendList);
    				entity.setEncyclopediaInfo(encyclopediaInfo);
    				topicCommon.setCreateTime(topicDto.getPublishTime());
    			} else if (Constant.SubTopicTypeEnum.IMG_1.toInteger() == subTopicType 
    					|| Constant.SubTopicTypeEnum.DISCUSS_2.toInteger() == subTopicType) {
    			}
 
    			
    		}
    		//3.3 跟帖增加引用
    		String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
        	if(Integer.parseInt(initVersion) >=330000 && topicDto.getReferenceTopicV3()!=null){
            	ReferenceTopicDto r=new ReferenceTopicDto();
            	r.setTopicTitle(topicDto.getReferenceTopicV3().getTopicTitle());
            	r.setTopicType(Constants.SubTopicType.getSubTopicType(
            			topicDto.getReferenceTopicV3().getSubTopicType()).getKey());
            	r.setTopicImage(topicDto.getReferenceTopicV3().getTopicImage());
            	Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());
            	int subTopicType=topicDto.getReferenceTopicV3().getSubTopicType();
            	if(subTopicType!=Constants.SubTopicType.Neighborhood.getValue()){
            		String skip=skipService.getSkipModel(subTopicType, skipModelMap, null,topicDto.getReferenceTopicV3().getId());
            		r.setSkipModel(skip);
            		entity.getCommonInfo().setReference(r);
            	}
            	
            }
    		
    		//设置标签
    		if (entity.getCommonInfo() != null && StringUtils.isNotBlank(topicDto.getTagName())) {
    			Tag tag = new Tag();
    			tag.setTagId(topicDto.getTagId());
    			tag.setTagName(topicDto.getTagName());
    			Integer subTopicType = topicDto.getSubTopicType();
    			String skipmode = skipMode
    					.fittingSkipModelByOnlyId(request.getAppDevice().getQdVersion(),
    							Constant.SkipNo.P_INTERACT_TAG_LIST_3024.toInteger(),
    							topicDto.getTagId());
    			tag.setSkipModel(skipmode);
    			if (topicDto.getParentTopicId() != null) {

    				skipmode = skipMode
    						.fittingSkipModelByOnlyId(request.getAppDevice().getQdVersion(),
    								Constant.SkipNo.P_INTERACT_DETAIL_3025.toInteger(),
    								topicDto.getId());
    			} else {
    				if (Constant.SubTopicTypeEnum.ACTIVITY_4.toInteger() == subTopicType) {
    					skipmode = skipMode
    							.fittingSkipModelByOnlyId(request.getAppDevice().getQdVersion(),
    									Constant.SkipNo.P_ACTIVITY_DETAIL_3028.toInteger(),
    									topicDto.getTagId());
    				} else if (Constant.SubTopicTypeEnum.IMG_1.toInteger() == subTopicType || Constant.SubTopicTypeEnum.DISCUSS_2.toInteger() == subTopicType) {
    					skipmode = skipMode
    							.fittingSkipModelByOnlyId(request.getAppDevice().getQdVersion(),
    									Constant.SkipNo.P_TOPIC_DETAIL_3031.toInteger(),
    									topicDto.getTagId());
    				}
    			}
    			tag.setSkipModel(skipmode);
    			entity.getCommonInfo().setTag(tag);
    		}
    		data.setEntity(entity);
    	} catch (ServiceException e) {
    		logger.error(e.getMessage(), e);
    	}

    	response.setData(data);
    	response.setCode(HttpStatus.OK.getStatusCode());
    	return response;

    }


	private void packageNewsBaikeContent(GetTopicDetailRequest request,
			UserToken userToken, TopicCommon topicCommon)
			throws ServiceException {
		
		//老版本兼容过滤
		String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
        if (Integer.parseInt(initVersion) <330000){
        	return;
        }
		
		GetTopicFavoriteRequest re=new GetTopicFavoriteRequest();
		re.setTopicId(request.getTopicId());
		GetTopicFavoriteResponse res=topicRpc.getTopicFavorite(re);
		checkAndContinue(res);
		//收藏数
		topicCommon.setFavoriteNum(res.getFavorite().getInitNum()+res.getFavorite().getRealNum());
		//是否已经收藏
		int i=topicRpc.existFavoriteMember(request.getTopicId(), userToken.getMemberId());
		topicCommon.setIsFavorite(i);
		//引用贴，评论展示用
		/*if(StringUtils.isNotBlank(topicCommon.getParentTopicId())){
			GetTopicBytopicIdRequest topicBytopicIdRequest = 
					transfor(GetTopicBytopicIdRequest.class, request);
			topicBytopicIdRequest.setTopicId(topicCommon.getParentTopicId());
    		if (userToken != null && userToken.getMemberId() != null) {
    			topicBytopicIdRequest.setMemberId(userToken.getMemberId());
    		}
    		GetTopicBytopicIdResponseData pidTopic = topicRpcV3
    				.getTopicBytopicId(topicBytopicIdRequest);
    		checkAndContinue(pidTopic);
    		
    		if(pidTopic.getTopic()!=null){
            	ReferenceTopicDto r=new ReferenceTopicDto();
            	r.setTopicTitle(pidTopic.getTopic().getTopicTitle());
            	r.setTopicType(Constants.SubTopicType.getSubTopicType(
            			pidTopic.getTopic().getSubTopicType()).getKey());
            	r.setTopicImage(pidTopic.getTopic().getTopicImage());
            	Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());
            	int subTopicType=pidTopic.getTopic().getSubTopicType();
            	if(subTopicType==Constants.SubTopicType.Neighborhood.getValue()){
            		String skip=skipService.getSkipModel(subTopicType, skipModelMap, null,topicCommon.getParentTopicId());
            		r.setSkipModel(skip);
            		topicCommon.setReference(r);
            	}
            	
            	
            }
    		
    		
		}*/
		
		
	}

    public Integer getActivityStep(Long startTime, Long endTime) {
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

    @ExplainAnnotation(explain = "帖子分页列表")
    @HTTP(alias = "getTopicFlow", isNeadProject = true)
    public Response<GetTopicFlowResponseData> getTopicFlow(GetTopicFlowRequest request, UserToken userToken) {

        Response<GetTopicFlowResponseData> response = new Response<GetTopicFlowResponseData>();
        GetTopicFlowResponseData data = new GetTopicFlowResponseData();
        response.setData(data);
        String accountId = request.getUserId();
        if (StringUtils.isNotBlank(accountId) && accountId.startsWith(Constant.SUPPER_PREFIX)) {
            data.setList(Lists.<TopicCommon>newArrayList());
            data.setHaveNextPage(false);
            response.setCode(HttpStatus.OK.getStatusCode());
            return response;
        }
        String memberId = null;
        if (QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId())) {
            memberId = userToken.getMemberId();
        }
        GetTopicByPidRequest topicByPidRequest = new GetTopicByPidRequest();
        topicByPidRequest.setMemberId(memberId);
        topicByPidRequest.setProjectId(request.getAppUser().getProjectId());
        topicByPidRequest.setSortType(String.valueOf(request.getQueryType()));
        FeedPageOrderByRuleDTO rule = getMultipleOrderFields(request.getOrderByRule());
        topicByPidRequest.setCursorTime(rule.getLastTime());
        topicByPidRequest.setSize(request.getPageSize());
        if (request.getQueryType() == 2) {
            //最热
            topicByPidRequest.setCursorSystemHot(rule.getCursorSystemHot());
            topicByPidRequest.setCursorArtificialHot(rule.getCursorArtificialHot());
        }

        PageResultDto<TopicDto> pageResult = null;
        String tagId = request.getParentTopicId();
        String tagName = null;
        switch (request.getFlowType()) {
            case 1:    //1: 跟帖列表
                if (QDStringUtil.isEmpty(request.getParentTopicId())) {
                    response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                    data.setMessage("没有指定来源贴信息");
                    return response;
                }
                try {
                    GetTopicBytopicIdRequest re = new GetTopicBytopicIdRequest();
                    re.setTopicId(request.getParentTopicId());
                    GetTopicBytopicIdResponseData datadetail = topicRpcV3.getTopicBytopicId(re);
                    checkAndContinue(datadetail);
                    if (datadetail.getTopic() == null) {
                        response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                        data.setMessage("没有指定来源贴信息");
                        return response;
                    }
                    tagName = datadetail.getTopic().getTopicTitle();
                    topicByPidRequest.setPtopicId(request.getParentTopicId());
                    pageResult = topicRpcV3.getTopicByPid(topicByPidRequest);
                } catch (ServiceException e) {
                    return handleException(GetTopicFlowResponseData.class, e);
                }
                break;
            case 2:   //2:邻里互动
                GetNearbyTopicRequest nearbyTopicRequest = transfor(GetNearbyTopicRequest.class, topicByPidRequest);
                
                String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
                if (Integer.parseInt(initVersion) >=330000){
                	//3.3邻里互动需求
                	pageResult=topicRpcV3.getNearbyTopicForNew(nearbyTopicRequest);
                }else{
                	pageResult = topicRpcV3.getNearbyTopic(nearbyTopicRequest);
                }
                break;
            case 3:   //3：邻里互动标签聚合页
                if (QDStringUtil.isEmpty(request.getTagId())) {
                    response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                    data.setMessage("没有指定标签");
                    return response;
                }
                //获取标签下的帖子
                GetTopicByLableRequest topicByLableRequest = transfor(GetTopicByLableRequest.class, topicByPidRequest);
                topicByLableRequest.setLableId(request.getTagId());
                pageResult = topicRpcV3.getNearbyTopicByLable(topicByLableRequest);
                //设置标签对象，独立显示
                GetLableByIdResponseData lableByIdResponse = topicRpcV3.getLableById(request.getTagId());
                LableDto lableDto = lableByIdResponse.getLable();
                if (QDStringUtil.isNotNull(lableDto)) {
                    data.setTag(transfor(Tag.class, lableDto));
                }
                break;
            case 4:   //4：我(他)动态聚合页 （个人中心）
                if (QDStringUtil.isEmpty(request.getUserId())) {
                    response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                    data.setMessage("缺少用户信息");
                    return response;
                }
                GetTopicDtosByMemberIdRequest topicDtosByMemberIdRequest = new GetTopicDtosByMemberIdRequest();
                GetAccountRequest accountRequest = new GetAccountRequest();
                accountRequest.setAccountId(request.getUserId());
                GetAccountResponse accountResponse = passportAPI.getAccountByAccountId(accountRequest);
                try {
                    checkAndContinue(accountResponse);
                    MemberInfo memberInfo = accountResponse.getMemberInfo();
                    topicDtosByMemberIdRequest.setMemberId(memberInfo.getId());
                    topicDtosByMemberIdRequest.setLastPublishTime(rule.getLastTime());
                    topicDtosByMemberIdRequest.setPageSize(request.getPageSize());

                    String initVersion1 = skipMode.initVersion(request.getAppDevice().getQdVersion());
                    if (Integer.parseInt(initVersion1) >=330000){
                    	pageResult=topicRpcV3.getTopicDtosByMemberIdForNew(topicDtosByMemberIdRequest);
                    }else{
                    	pageResult = topicRpcV3.getTopicDtosByMemberId(topicDtosByMemberIdRequest);
                    }
                } catch (ServiceException e) {
                    e.printStackTrace();
                    logger.error("class : CallNeighbor,method:getTopicFlow is error", e);
                    return handleException(GetTopicFlowResponseData.class, e);
                }
                break;
        }

        //获取跳转模板
        Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());

        if (QDStringUtil.isNotNull(pageResult) && CollectionUtils.isNotEmpty(pageResult.getList())) {
            List<TopicDto> rpcTopicDtoList = pageResult.getList();
            List<TopicCommon> commonTopicList = new ArrayList<TopicCommon>();
            for (TopicDto dto : rpcTopicDtoList) {
                //帖子信息转换
                TopicCommon common = new TopicCommon();
                String skipTopicDetail = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                        Constant.SkipNo.P_INTERACT_DETAIL_3025.toInteger(), dto.getId());
                common.setSkipModel(skipTopicDetail);//邻里互动，跟帖详情跳转
                this.transfor(common, dto);
                //会员信息转换
                BriefMember brief = new BriefMember();
                this.transfor(brief, dto.getMember());
                common.setMemberInfo(brief);
                if (tagName != null) {
                    //报名，话题类型的跟帖补充跟帖的lable标签
                    Tag tag = new Tag();
                    tag.setTagId(tagId);
                    tag.setTagName(tagName);
                    common.setTag(tag);
                } else {
                    if (StringUtils.isNoneBlank(dto.getTagId())) {
                        Tag tag = new Tag();
                        tag.setTagId(dto.getTagId());
                        tag.setTagName(dto.getTagName());
                        common.setTag(tag);

                        //标签跳转 三种类型  常态   话题   报名
                        if (dto.getSubTopicType() != null) {
                        	String skip=skipService.getSkipModel(dto.getSubTopicType(), skipModelMap, 
                    				tag.getTagId(), dto.getParentTopicId());
                        	common.getTag().setSkipModel(skip);
                        }
                        
                        
                    }
                    

                }
                //3.3 新增帖子引用 信息转换
                String initVersion = skipMode.initVersion(request.getAppDevice().getQdVersion());
                if (Integer.parseInt(initVersion) >=330000){
                	if(dto.getReferenceTopicV3()!=null){
                    	ReferenceTopicDto r=new ReferenceTopicDto();
                    	r.setTopicTitle(dto.getReferenceTopicV3().getTopicTitle());
                    	r.setTopicType(Constants.SubTopicType.getSubTopicType(
                    			dto.getReferenceTopicV3().getSubTopicType()).getKey());
                    	r.setTopicImage(dto.getReferenceTopicV3().getTopicImage());
                    	String skip=null;
                    	int subTopicType=dto.getReferenceTopicV3().getSubTopicType();
                    	if(subTopicType!=Constants.SubTopicType.Neighborhood.getValue()){
                    		//排除邻里互动，邻里互动没有帖子引用
                    		skip=skipService.getSkipModel(subTopicType, skipModelMap, 
                    				null, dto.getParentTopicId());
                    		r.setSkipModel(skip);
                    		common.setReference(r);
                    	}
                    	
                    	
                    }
                    //-------------------------3.3 引用结束
                }
                commonTopicList.add(common);
                
            }
            data.setList(commonTopicList);
            data.setHaveNextPage(pageResult.isHaveNextPage());
            TopicDto lastTopic = rpcTopicDtoList.get(rpcTopicDtoList.size() - 1);
            FeedPageOrderByRuleDTO pageRule = new FeedPageOrderByRuleDTO();
            pageRule.setLastTime(lastTopic.getPublishTime());
            if (request.getQueryType() == 2) { //最热
                pageRule.setCursorArtificialHot(lastTopic.getArtificialHeatVal());
                pageRule.setCursorSystemHot(lastTopic.getHeatVal());
            }
            data.setOrderByRule(JSON.toJSONString(pageRule));
        } else {
            response.setCode(HttpStatus.OK.getStatusCode());
            data.setMessage("没有更多信息");
        }
        response.setData(data);
        return response;

    }


    @ExplainAnnotation(explain = "社区话题广场页|个人中心参与的话题列表页")
    @HTTP(alias = "getTopicPiazzaIndex", isNeadProject = true)
    public Response<GetTopicPiazzaIndexResponseData> getTopicPiazzaIndex(GetTopicPiazzaIndexRequest request) {

        Response<GetTopicPiazzaIndexResponseData> response = new Response<GetTopicPiazzaIndexResponseData>();
        GetTopicPiazzaIndexResponseData data = new GetTopicPiazzaIndexResponseData();
        String accountId = request.getUserId();
        if (StringUtils.isNotBlank(accountId)&& accountId.startsWith(Constant.SUPPER_PREFIX)) {
            data.setList(Lists.<TopicPiazzaDTO>newArrayList());
            data.setHaveNextPage(false);
            response.setCode(HttpStatus.OK.getStatusCode());
            return response;
        }
        List<TopicPiazzaDTO> list = Lists.newArrayList();
        Long projectId = Long.parseLong(request.getAppUser().getProjectId());
        Project project = projectReadService.get(projectId);
        Long regionId = project.getRegionId();
        FeedPageOrderByRuleDTO rule = getMultipleOrderFields(request.getOrderByRule());
        PageResultDto<TopicBaseDto> resultPage = null;
        try {
            if (1 == request.getQueryType()) {
                GetTopicCollectionRequest topicCollectionRequest = new GetTopicCollectionRequest();
                topicCollectionRequest.setPageSize(request.getPageSize());
                topicCollectionRequest.setLastBeginTime(rule.getLastStartTime());
                topicCollectionRequest.setLastEndTime(rule.getLastEndTime());
                topicCollectionRequest.setCityId(regionId);
                topicCollectionRequest.setProjectId(projectId);
                resultPage = topicRpcV3.getTopicCollection(topicCollectionRequest);

            } else {
                if (QDStringUtil.isEmpty(request.getUserId())) {
                    //告知失败
                }
                GetAccountRequest accountRequest = new GetAccountRequest();
                accountRequest.setAccountId(request.getUserId());
                GetAccountResponse accountResponse = passportAPI.getAccountByAccountId(accountRequest);
                checkAndContinue(accountResponse);
                MemberInfo memberInfo = accountResponse.getMemberInfo();
                GetTakePartInTopicListRequest takPartInTopicListRequest = new GetTakePartInTopicListRequest();
                takPartInTopicListRequest.setPageSize(request.getPageSize());
                takPartInTopicListRequest.setLastSignUpTime(rule.getLastTime());
                takPartInTopicListRequest.setMemberId(memberInfo.getId());
                resultPage = topicRpcV3.getTakePartInTopicList(takPartInTopicListRequest);
            }
            checkAndContinue(resultPage);
            List<TopicBaseDto> rpcTopicList = resultPage.getList();
            if (CollectionUtils.isNotEmpty(rpcTopicList)) {
                for (TopicBaseDto topicBaseDto : rpcTopicList) {
                    TopicPiazzaDTO topicPiazza = transfor(TopicPiazzaDTO.class, topicBaseDto);
                    if (Integer.parseInt(topicBaseDto.getType()) == 3) {
                        topicPiazza.setPeopleNum(topicBaseDto.getVoteCount());
                        String skipmode = skipMode
                                .fittingSkipModelByOnlyId(request.getAppDevice().getQdVersion(),
                                        Constant.SkipNo.P_VOTE_DETAIL_3033.toInteger(),
                                        topicPiazza.getTopicId());
                        topicPiazza.setSkipModel(skipmode);
                    } else {
                        topicPiazza.setPeopleNum(topicBaseDto.getFollowCount());
                        String skipmode = skipMode
                                .fittingSkipModelByOnlyId(request.getAppDevice().getQdVersion(),
                                        Constant.SkipNo.P_TOPIC_DETAIL_3031.toInteger(),
                                        topicPiazza.getTopicId());
                        topicPiazza.setSkipModel(skipmode);
                    }
                    topicPiazza.setBeginTime(topicBaseDto.getBeginTime());
                    topicPiazza.setEndTime(topicBaseDto.getEndTime());
                    topicPiazza.setDescription(topicBaseDto.getDescription());
                    topicPiazza.setEnded(topicBaseDto.getEndTime() < System.currentTimeMillis());
                    list.add(topicPiazza);
                }
                FeedPageOrderByRuleDTO orderRule = new FeedPageOrderByRuleDTO();
                TopicBaseDto topicBaseDto = rpcTopicList.get(rpcTopicList.size() - 1);
                if (1 == request.getQueryType()) {

                    orderRule.setLastStartTime(topicBaseDto.getBeginTime());
                    orderRule.setLastEndTime(topicBaseDto.getEndTime());
                } else {
                    orderRule.setLastTime(topicBaseDto.getSignUpTime());
                }
                data.setOrderByRule(JSON.toJSONString(orderRule));
            }

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        data.setHaveNextPage(resultPage.isHaveNextPage());
        data.setList(list);
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }


    @ExplainAnnotation(explain = "社区活动广场页|个人中心参与的活动列表页")
    @HTTP(alias = "getActivityPiazzaIndex")
    public Response<GetActivityPiazzaIndexResponseData> getActivityPiazzaIndex(GetActivityPiazzaIndexRequest request,
                                                                               UserToken userToken) {

        Response<GetActivityPiazzaIndexResponseData> response = new Response<GetActivityPiazzaIndexResponseData>();
        GetActivityPiazzaIndexResponseData data = new GetActivityPiazzaIndexResponseData();
        List<ActivityPiazzaDTO> list = Lists.newArrayList();
        String accountId = request.getUserId();
        if (StringUtils.isNotBlank(accountId) && accountId.startsWith(Constant.SUPPER_PREFIX)) {
            data.setList(list);
            data.setHaveNextPage(false);
            response.setCode(HttpStatus.OK.getStatusCode());
            return response;
        }
        try {
            Long projectId = Long.parseLong(request.getAppUser().getProjectId());
            Project project = projectReadService.get(projectId);
            Long regionId = project.getRegionId();
            FeedPageOrderByRuleDTO rule = getMultipleOrderFields(request.getOrderByRule());
            PageResultDto<ActivityDto> pageResult = null;

            if (1 == request.getQueryType()) { //活动广场
                GetActivityListRequest activityListRequest = new GetActivityListRequest();
                if (QDStringUtil.isNotNull(userToken) && QDStringUtil.isNotEmpty(userToken.getMemberId())) {
                    activityListRequest.setMemberId(userToken.getMemberId());
                }
                activityListRequest.setProjectId(project.getId());
                activityListRequest.setCityId(regionId);
                activityListRequest.setLastCreateTime(rule.getLastTime());
                activityListRequest.setLastEndTime(rule.getLastEndTime());
                activityListRequest.setLastStartTime(rule.getLastStartTime());
                if (request.getAppUser() != null && request.getAppUser().getCurMemberId() != null) {
                    activityListRequest.setMemberId(request.getAppUser().getCurMemberId());
                }

                pageResult = topicRpcV3.getActivityList(activityListRequest);

            } else {

                if (QDStringUtil.isEmpty(request.getUserId())) {
                    //告知失败
                }
                GetAccountRequest accountRequest = new GetAccountRequest();
                accountRequest.setAccountId(request.getUserId());
                GetAccountResponse accountResponse = passportAPI.getAccountByAccountId(accountRequest);
                checkAndContinue(accountResponse);
                MemberInfo memberInfo = accountResponse.getMemberInfo();
                GetSignUpActivityListRequest signUpActivityListRequest = new GetSignUpActivityListRequest();
                signUpActivityListRequest.setMemberId(memberInfo.getId());
                signUpActivityListRequest.setLastSignUpTime(rule.getLastTime());
                signUpActivityListRequest.setPageSize(request.getPageSize());
                pageResult = topicRpcV3.getSignUpActivityList(signUpActivityListRequest);
            }
            checkAndContinue(pageResult);
            Map<String, String> skipModelMap = skipMode.selSkipTemplateMap(request.getAppDevice().getQdVersion());
            List<ActivityDto> rpcActivityList = pageResult.getList();
            if (CollectionUtils.isNotEmpty(rpcActivityList)) {
                list = transforList(ActivityPiazzaDTO.class, rpcActivityList);
                for (ActivityPiazzaDTO activityPiazzaDTO : list) {
                    activityPiazzaDTO.setStep(getActivityStep(activityPiazzaDTO.getStartTime(), activityPiazzaDTO.getEndTime()));
                    Long surplusTime = activityPiazzaDTO.getStartTime() - System.currentTimeMillis();
                    activityPiazzaDTO.setSurplusTime(surplusTime > 0 ? surplusTime : 0);
                    String skip1 = skipMode.fittingSkipModelByOnlyId(skipModelMap,
                            Constant.SkipNo.P_ACTIVITY_DETAIL_3028.toInteger(), activityPiazzaDTO.getId());
                    activityPiazzaDTO.setSkipModel(skip1);
                    activityPiazzaDTO.setJoinStatus(activityPiazzaDTO.isHaveSignUp() ? 1 : 0);
                    activityPiazzaDTO.setEnrollStatus(activityPiazzaDTO.getEnrollCount() >= activityPiazzaDTO.getActivityTotalCount() ? 1 : 0);
                }
                ActivityDto lastActivity = rpcActivityList.get(rpcActivityList.size() - 1);
                FeedPageOrderByRuleDTO orderRule = new FeedPageOrderByRuleDTO();
                orderRule.setLastTime(lastActivity.getCreateTime());
                orderRule.setLastStartTime(lastActivity.getStartTime());
                orderRule.setLastEndTime(lastActivity.getEndTime());
                data.setOrderByRule(JSON.toJSONString(orderRule));
            }
            data.setHaveNextPage(pageResult.isHaveNextPage());
        } catch (Exception ex) {

        }
        String calendarIndexUrl = APIPropertiesClient.getValue("calendar_index_url");
        data.setSkipModel(skipMode.fittingSkipUrl(request.getAppDevice().getQdVersion(), calendarIndexUrl, 0, "", 1));
        data.setList(list);
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }


    @ExplainAnnotation(explain = "个人中心首页")
    @HTTP(alias = "getPersonIndex")
    public Response<GetPersonIndexResponseData> getPersonIndex(GetPersonIndexRequest request) {

        Response<GetPersonIndexResponseData> response = new Response<GetPersonIndexResponseData>();
        GetAccountRequest getAccountRequest = new GetAccountRequest(request.getUserId());
        GetPersonIndexResponseData data = new GetPersonIndexResponseData();
        try {
            String accountId = request.getUserId();
            if (StringUtils.isNotBlank(accountId)&& accountId.startsWith(Constant.SUPPER_PREFIX)) {
                String puserId= accountId.replace(Constant.SUPPER_PREFIX,"");
                ModelResult modelResult = puserRPCService.getPuserInfoByPuserId(puserId);
                UserInfo userInfo = new UserInfo();
                AccountMember entity = new AccountMember();
                if (HttpStatus.OK.getStatusCode() == modelResult.getCode()) {
                    Puser puser = (Puser) modelResult.getEntity();
                    Account user = new Account();
                    user.setAccountId(puserId);
                    user.setAccountAvatar(imageUtil.get(Constant.DEFAULT_SUPPLIER_HEAD_IMG,150,150));
                    user.setAccountNickName(puser.getName());
                    Member member  = new Member();
                    member.setMemberId(puserId);
                    member.setMemberGender("1");
                    entity.setUser(user);
                    entity.setMember(member);
                }

                data.setEntity(entity);
            } else {
                //会员信息
                GetAccountResponse getAccountResponse = passportAPI.getAccountByAccountId(getAccountRequest);
                checkAndContinue(getAccountResponse);
                data = transfor(GetPersonIndexResponseData.class, getAccountResponse);
                //获取发帖，跟帖数
                GetMemberActiveCountRequest getMemberActiveCountRequest = new GetMemberActiveCountRequest();
                getMemberActiveCountRequest.setMemberId(getAccountResponse.getMemberInfo().getId());
                GetMemberActiveCountResponseData getMemberActiveCountResponseData = topicRpcV3
                        .getMemberActiveCount(getMemberActiveCountRequest);
                checkAndContinue(getMemberActiveCountResponseData);
                data.setDynamicCount(getMemberActiveCountResponseData.getActiveCount());
                data.getEntity().getMember().setMemberId(null);
                //会员邻居状态
                //给场景只能传入accountId
                GetMemberStatusRequest getMemberStatusRequest = new GetMemberStatusRequest();
                getMemberStatusRequest.setMemberId(getAccountResponse.getMemberInfo().getId());
                GetMemberStatusResponse getMemberStatusResponse = topicRpcV3.getMemberStatsInfo(getMemberStatusRequest);
                checkAndContinue(getMemberStatusResponse);
                if (QDStringUtil.isNotNull(getMemberStatusResponse.getTopicMemberStatistics())) {
                    data.setIsSay(getMemberStatusResponse.getTopicMemberStatistics().getIsSay());
                    data.setIsFreeze(getMemberStatusResponse.getTopicMemberStatistics().getIsFreeze());
                }
            }
            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());
        } catch (Exception ex) {
            return handleException(GetPersonIndexResponseData.class, ex);
        }
        return response;
    }


    @ExplainAnnotation(explain = "标签列表")
    @HTTP(alias = "getTagList")
    public Response<GetTagListResponseData> getTagList(GetTagListRequest request) {

        Response<GetTagListResponseData> response = new Response<GetTagListResponseData>();
        GetTagListResponseData data = new GetTagListResponseData();
        try {
            //默认提取15个标签
            GetLableListResponseData responseData = topicRpcV3.getLableList(15);
            checkAndContinue(responseData);
            List<Tag> list = new ArrayList<Tag>();
            if (responseData.getLableList() != null) {
                for (LableDto dto : responseData.getLableList()) {
                    Tag t = new Tag();
                    t.setTagId(dto.getId());
                    t.setTagName(dto.getTagName());
                    t.setTagDesc(dto.getTagDesc());
                    t.setTagStatus(dto.getStatus());
                    t.setTagImg(dto.getImg());
                    list.add(t);
                }
            }
            data.setList(list);
        } catch (Exception e) {
            return handleException(GetTagListResponseData.class, e);
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }


    @HTTP(alias = "vote", isRequireAuth = true, isNeadProject = true)
    @ExplainAnnotation(explain = "投票")
    public Response<VoteResponseData> vote(com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request.VoteRequest request, UserToken userToken) {
        Response<VoteResponseData> response = new Response<VoteResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            VoteResponseData data = new VoteResponseData();
            VoteRequest voteTopicRequest = new VoteRequest();
            voteTopicRequest.setMemberId(userToken.getMemberId());
            voteTopicRequest.setTopicId(request.getTopicId());
            voteTopicRequest.setUserId(userToken.getAccountId());
            voteTopicRequest.setOptionIndexs(request.getCheckedIndexs());
            BaseResponse baseResponse = topicRpcV3.voteTopic(voteTopicRequest);
            checkAndContinue(baseResponse);
            response.setData(data);

        } catch (Exception ex) {
            return handleException(VoteResponseData.class, ex);
        }
        return response;
    }

    @HTTP(alias = "handlePraise", isRequireAuth = true, isNeadProject = true)
    @ExplainAnnotation(explain = "点赞")
    public Response<AddPraiseResponseData> handlePraise(AddPraiseRequest request, UserToken userToken) {

        Response<AddPraiseResponseData> response = new Response<AddPraiseResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            AddPraiseResponseData data = new AddPraiseResponseData();
            HandlePraiseRequest handlePraiseRequest = new HandlePraiseRequest();
            handlePraiseRequest.setMemberId(userToken.getMemberId());
            handlePraiseRequest.setTopicId(request.getTopicId());
            handlePraiseRequest.setUserId(userToken.getAccountId());
            BaseResponse baseResponse = topicRpcV3.handlePraise(handlePraiseRequest);
            checkAndContinue(baseResponse);
            response.setData(data);
        } catch (Exception ex) {
            return handleException(AddPraiseResponseData.class, ex);
        }
        return response;
    }

    @HTTP(alias = "addComment", isRequireAuth = true, isNeadProject = true)
    @ExplainAnnotation(explain = "发布评论", desc = "(包含回复评论)")
    public Response<AddCommentResponseData> addComment(AddCommentRequest request, UserToken userToken) {
        Response<AddCommentResponseData> response = new Response<AddCommentResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            AddCommentResponseData data = new AddCommentResponseData();
            com.qding.neighbor.rpc.request.AddCommentRequest addCommentRequest = new com.qding.neighbor.rpc.request.AddCommentRequest();
            addCommentRequest.setMemberId(userToken.getMemberId());
            addCommentRequest.setTopicId(request.getTopicId());
            addCommentRequest.setContent(request.getContent());
            addCommentRequest.setPid(request.getPcommentId());
            addCommentRequest.setUserId(userToken.getAccountId());
            AddCommentResponse addCommentResponse = topicRpcV3.addComment(addCommentRequest);
            checkAndContinue(addCommentResponse);
            TopicComment topicComment = transfor(TopicComment.class, addCommentResponse.getTopicComment());
            GetMemberRequest getMemberRequest = new GetMemberRequest();
            getMemberRequest.setMemberId(addCommentResponse.getTopicComment().getMemberId());
            GetMemberResponse getMemberResponse = profileAPI.getMemberById(getMemberRequest);
            checkAndContinue(getMemberResponse);
            CommentBriefMember commentBriefMember = new CommentBriefMember();
            commentBriefMember.setMemberId(getMemberResponse.getMemberInfo().getId());
            commentBriefMember.setMemberName(getMemberResponse.getMemberInfo().getName());
            commentBriefMember.setMemberAvatar(getMemberResponse.getMemberInfo().getHeadImg());
            commentBriefMember.setUserId(userToken.getAccountId());
            topicComment.setSendMember(commentBriefMember);
            if (StringUtils.isNotEmpty(addCommentResponse.getTopicComment().getpMemberId())) {
                getMemberRequest.setMemberId(addCommentResponse.getTopicComment().getpMemberId());
                getMemberResponse = profileAPI.getMemberById(getMemberRequest);
                checkAndContinue(getMemberResponse);
                commentBriefMember = new CommentBriefMember();
                commentBriefMember.setMemberId(getMemberResponse.getMemberInfo().getId());
                commentBriefMember.setMemberName(getMemberResponse.getMemberInfo().getName());
                commentBriefMember.setMemberAvatar(getMemberResponse.getMemberInfo().getHeadImg());
                commentBriefMember.setUserId(addCommentResponse.getTopicComment().getpUserId());
                topicComment.setReceiveMember(commentBriefMember);
            }
            data.setTopicComment(topicComment);
            response.setData(data);
        } catch (Exception ex) {
            return handleException(AddCommentResponseData.class, ex);
        }
        return response;
    }

    @HTTP(alias = "getJoinActivityMember")
    @ExplainAnnotation(explain = "获取报名人员列表")
    public Response<BaoMingResponseData> getJoinActivityMemberBytopicId(BaoMingRequest request) {
        Response<BaoMingResponseData> response = new Response<BaoMingResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        BaoMingResponseData data = new BaoMingResponseData();
        try {
            GetJoinActivityMemberBytopicIdRequest baomingRequest =
                    transfor(GetJoinActivityMemberBytopicIdRequest.class, request);
            PageResultDto<MemberDto> response1 = topicRpcV3.getJoinActivityMemberBytopicId(baomingRequest);
            checkAndContinue(response1);
            List<MemberDto> list2 = response1.getList();
            List<EnrollMember> list = transforList(EnrollMember.class, list2);
            if (list2 != null && list2.size() > 0) {
                data.setCursorTime(list2.get(list2.size() - 1).getCreateTime());
            }
            data.setHaveNextPage(response1.isHaveNextPage());
            data.setList(list);
        } catch (ServiceException e) {
            return handleException(BaoMingResponseData.class, e);
        }
        response.setData(data);

        return response;
    }


    @HTTP(alias = "getLikeListBytopic")
    @ExplainAnnotation(explain = "获取点赞人员列表")
    public Response<DianZanResponseData> getLikeListBytopic(DianZanRequest request) {
        Response<DianZanResponseData> response = new Response<DianZanResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        DianZanResponseData data = new DianZanResponseData();
        try {
            GetLikeListBytopicRequest dianzanRequest =
                    transfor(GetLikeListBytopicRequest.class, request);
            PageResultDto<MemberDto> response1 = topicRpcV3.getLikeListBytopic(dianzanRequest);
            checkAndContinue(response1);
            List<MemberDto> list2 = response1.getList();
            List<BriefMember> list = transforList(BriefMember.class, list2);
            if (list2 != null && list2.size() > 0) {
                data.setCursorTime(list2.get(list2.size() - 1).getCreateTime());
            }
            data.setHaveNextPage(response1.isHaveNextPage());
            data.setList(list);
        } catch (ServiceException e) {
            return handleException(DianZanResponseData.class, e);
        }
        response.setData(data);

        return response;
    }


    @HTTP(alias = "enroll", isRequireAuth = true, isNeadProject = true)
    @ExplainAnnotation(explain = "报名")
    public Response<EnrollResponseData> enroll(EnrollRequest request, UserToken userToken) {
        Response<EnrollResponseData> response = new Response<EnrollResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            EnrollResponseData data = new EnrollResponseData();
            EnrollTopicRequest enrollTopicRequest = new EnrollTopicRequest();
            enrollTopicRequest.setMemberId(userToken.getMemberId());
            enrollTopicRequest.setTopicId(request.getTopicId());
            enrollTopicRequest.setUserId(userToken.getAccountId());
            enrollTopicRequest.setActivitySessionId(request.getActivitySessionId());
            enrollTopicRequest.setPersonCount(request.getPersonCount());
            enrollTopicRequest.setProjectId(Long.valueOf(request.getAppUser().getProjectId()));
            List<ActivitySession> activitySessions = topicRpcV3.getActivitySessionList(request.getTopicId());
            if(CollectionUtils.isNotEmpty(activitySessions) &&  QDVersionUtil.getVersionCode(request.getAppDevice().getQdVersion()) < QDVersionUtil.VERSION_310)
            {
                data.setMessage("报名失败：该活动需分场次进行报名，您目前的版本无法支持，请更新APP");
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
            }
            else
            {
                BaseResponse baseResponse = topicRpcV3.enroll(enrollTopicRequest);
                checkAndContinue(baseResponse);
            }
            response.setData(data);
        } catch (Exception ex) {
            return handleException(EnrollResponseData.class, ex);
        }
        return response;
    }

    @HTTP(alias = "delTopic")
    @ExplainAnnotation(explain = "删除话题")
    public Response<DelTopicResponseData> delTopic(DelTopicRequest request) {
        Response<DelTopicResponseData> response = new Response<DelTopicResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            DelTopicResponseData data = new DelTopicResponseData();
            com.qding.neighbor.rpc.request.DelTopicRequest delTopicRequest = new com.qding.neighbor.rpc.request.DelTopicRequest();
            delTopicRequest.setTopicId(request.getTopicId());
            delTopicRequest.setMemberId(request.getMemberId());
            BaseResponse baseResponse = topicRpcV3.delTopic(delTopicRequest);
            checkAndContinue(baseResponse);
            response.setData(data);
        } catch (Exception ex) {
            return handleException(DelTopicResponseData.class, ex);
        }
        return response;
    }

    @HTTP(alias = "reportTopic")
    @ExplainAnnotation(explain = "举报话题")
    public Response<ReportTopicResponseData> reportTopic(ReportTopicRequest request) {
        Response<ReportTopicResponseData> response = new Response<ReportTopicResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            com.qding.neighbor.rpc.request.ReportTopicRequest reportTopicRequest = new com.qding.neighbor.rpc.request.ReportTopicRequest();
            reportTopicRequest.setMemberId(request.getMemberId());
            reportTopicRequest.setTopicId(request.getTopicId());
            reportTopicRequest.setContent(request.getContent());
            reportTopicRequest.setUserId(request.getUserId());
            BaseResponse baseResponse = topicRpcV3.reportTopic(reportTopicRequest);
            checkAndContinue(baseResponse);
            ReportTopicResponseData data = new ReportTopicResponseData();
            response.setData(data);
        } catch (Exception ex) {
            return handleException(ReportTopicResponseData.class, ex);
        }
        return response;
    }


    @HTTP(alias = "delComment")
    @ExplainAnnotation(explain = "删除评论")
    public Response<DelCommentResponseData> delComment(DelCommentRequest request) {
        Response<DelCommentResponseData> response = new Response<DelCommentResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            DelCommentResponseData data = new DelCommentResponseData();
            com.qding.neighbor.rpc.request.DelCommentRequest delCommentRequest
                    = new com.qding.neighbor.rpc.request.DelCommentRequest();
            delCommentRequest.setMemberId(request.getMemberId());
            delCommentRequest.setCommentId(request.getCommentId());
            BaseResponse baseResponse = topicRpcV3.delComment(delCommentRequest);
            checkAndContinue(baseResponse);
            response.setData(data);
        } catch (Exception ex) {
            return handleException(DelCommentResponseData.class, ex);
        }
        return response;
    }

    @HTTP(alias = "getCommentsBytopicId")
    @ExplainAnnotation(explain = "分页获取评论信息列表")
    public Response<CommentListResponseData> getCommentsBytopicId(CommentListRequest request) {

        Response<CommentListResponseData> response = new Response<CommentListResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        CommentListResponseData data = new CommentListResponseData();
        try {
            /*GetTopicBytopicIdRequest t=new GetTopicBytopicIdRequest();
            t.setTopicId(request.getTopicId());
        	GetTopicBytopicIdResponseData topic=topicRpcV3.getTopicBytopicId(t);
        	checkAndContinue(topic);
        	String lzMemberId="";
        	if(topic.getTopic()!=null){
        		lzMemberId=topic.getTopic().getMemberId();
        	}*/
            GetCommentsBytopicIdRequest commentsByTopicIdRequest = transfor(GetCommentsBytopicIdRequest.class, request);
            commentsByTopicIdRequest.setSize(request.getPageSize());
            PageResultDto<CommentDto> commentsByTopicIdResponse = topicRpcV3.getCommentsBytopicId(commentsByTopicIdRequest);
            checkAndContinue(commentsByTopicIdResponse);
            List<CommentDto> list = commentsByTopicIdResponse.getList();
            List<TopicComment> topicCommentList = Lists.newArrayList();
            if (list != null && list.size() > 0) {
                for (CommentDto topicCommentDTO : list) {
                    TopicComment comment = transfor(TopicComment.class, topicCommentDTO);
                    /*if (QDStringUtil.isNotNull(comment.getReceiveMember())
        					&& QDStringUtil.isNotEmpty(comment.getReceiveMember().getMemberId())
                            && lzMemberId.equals(comment.getReceiveMember().getMemberId())) {
                        comment.getReceiveMember().setIsLz(1);
                    }
                    if (QDStringUtil.isNotNull(comment.getSendMember()) 
                    		&& QDStringUtil.isNotEmpty(comment.getSendMember().getMemberId())
                            && lzMemberId.equals(comment.getSendMember().getMemberId())) {
                        comment.getSendMember().setIsLz(1);
                    }*/
                    topicCommentList.add(comment);
                }
                data.setCursorTime(list.get(list.size() - 1).getCreateTime());
            }
            data.setHaveNextPage(commentsByTopicIdResponse.isHaveNextPage());
            data.setList(topicCommentList);
            response.setData(data);
        } catch (ServiceException e) {
            return handleException(CommentListResponseData.class, e);
        }
        return response;
    }


    @ExplainAnnotation(explain = "获取用户历史被点赞，评论消息列表")
    @HTTP(alias = "getHistoryNotifysByMId", isRequireAuth = true, isNeadToken = true)
    public Response<GetHistoryNotifyByMIdResponseData> getHistoryNotifyByMId(GetHistoryNotifyByMIdRequest request, UserToken userToken) {
        Response<GetHistoryNotifyByMIdResponseData> response = new Response<GetHistoryNotifyByMIdResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetHistoryNotifyByMIdResponseData data = new GetHistoryNotifyByMIdResponseData();
        try {
            List<TopicNotify> list = Lists.newArrayList();
            com.qding.neighbor.rpc.request.GetHistoryNotifyByMIdRequest historyNotifyByMIdRequest = new com.qding.neighbor.rpc.request.GetHistoryNotifyByMIdRequest();
            historyNotifyByMIdRequest.setMemberId(userToken.getMemberId());
            historyNotifyByMIdRequest.setPageNo(request.getPageNo());
            historyNotifyByMIdRequest.setPageSize(request.getPageSize());
            com.qding.neighbor.rpc.response.data.GetHistoryNotifyByMIdResponse historyNotifyByMIdResponse = topicRpc.getHistoryNotifyByMId(historyNotifyByMIdRequest);
            checkAndContinue(historyNotifyByMIdResponse);
            List<TopicNotifyDTO> notifyDTOList = historyNotifyByMIdResponse.getList();
            list = getTopicNotifyList(notifyDTOList, userToken.getMemberId(), request.getAppDevice().getQdVersion());
            Integer count = historyNotifyByMIdResponse.getTotalCount();
            data.setTotalCount(count);
            data.setList(list);
            response.setData(data);
        } catch (ServiceException ex) {
            return handleException(GetHistoryNotifyByMIdResponseData.class, ex);
        }
        return response;
    }


    @ExplainAnnotation(explain = "获取未读取的点赞，评论消息")
    @HTTP(alias = "getNotifysByMId", isRequireAuth = true, isNeadToken = true)
    public Response<GetNotifyByMIdResponseData> getNotifysByMId(GetNotifyByMIdRequest request, UserToken userToken) {

        Response<GetNotifyByMIdResponseData> response = new Response<GetNotifyByMIdResponseData>();
        GetNotifyByMIdResponseData data = new GetNotifyByMIdResponseData();
        response.setCode(HttpStatus.OK.getStatusCode());
        List<TopicNotify> list = Lists.newArrayList();

        try {
            com.qding.neighbor.rpc.request.GetNotifyByMIdRequest notifyByMidRequest = new com.qding.neighbor.rpc.request.GetNotifyByMIdRequest();
            notifyByMidRequest.setMemberId(userToken.getMemberId());
            notifyByMidRequest.setReset(request.getReset());
            GetNotifyByMIdResponse notifyByMidResponse = topicRpc.getNotifyByMId(notifyByMidRequest);

            checkAndContinue(notifyByMidResponse);
            List<TopicNotifyDTO> notifyDTOList = notifyByMidResponse.getList();
            list = getTopicNotifyList(notifyDTOList, userToken.getMemberId(), request.getAppDevice().getQdVersion());
            data.setList(list);
            data.setTotalCount(notifyByMidResponse.getNoticeNum());
            response.setData(data);

        } catch (ServiceException e) {
            return handleException(GetNotifyByMIdResponseData.class, e);
        }
        return response;
    }


    /**
     * 组装用户点赞，评论消息列表
     *
     * @param notifyDTOList
     * @param requestMid
     * @return
     */
    private List<TopicNotify> getTopicNotifyList(List<TopicNotifyDTO> notifyDTOList, String requestMid, String version) {

        List<TopicNotify> list = Lists.newArrayList();
        Set<String> mIdSet = new HashSet<>();
        for (TopicNotifyDTO topicNotifyDTO : notifyDTOList) {
            mIdSet.add(topicNotifyDTO.getrMemberId());
            mIdSet.add(topicNotifyDTO.getsMemberId());
        }
        List<MemberInfo> memberList = batchGetMembers(mIdSet);
        HashMap<String, MemberInfo> memberMap = new HashMap<>();
        for (MemberInfo memberInfo : memberList) {
            memberMap.put(memberInfo.getId(), memberInfo);
        }
        ImageUtil imageUtil = new ImageUtil();


        for (TopicNotifyDTO topicNotifyDTO : notifyDTOList) {
            try {
                TopicNotify topicNoticy = transfor(TopicNotify.class, topicNotifyDTO);
                topicNoticy.setTopicImg(imageUtil.httpsUrl(topicNoticy.getTopicImg()));
                com.qding.api.call.app.qding.v2_6_0.struct.neighbor.BriefMember bRmember = new com.qding.api.call.app.qding.v2_6_0.struct.neighbor.BriefMember();
                if (topicNoticy.getType() == 2) {
                    MemberInfo rMember = memberMap.get(topicNotifyDTO.getrMemberId());
                    bRmember = transfor(com.qding.api.call.app.qding.v2_6_0.struct.neighbor.BriefMember.class, rMember);
                    bRmember.setUserId(topicNotifyDTO.getrUserId());
                    if (bRmember.getMemberId().equals(requestMid)) {
                        bRmember.setMemberName("我");
                    }
                }
                topicNoticy.setrMember(QDStringUtil.isNotNull(bRmember) ? bRmember : new com.qding.api.call.app.qding.v2_6_0.struct.neighbor.BriefMember());//回复评论
                MemberInfo sMember = memberMap.get(topicNotifyDTO.getsMemberId());
                com.qding.api.call.app.qding.v2_6_0.struct.neighbor.BriefMember bSmember = transfor(com.qding.api.call.app.qding.v2_6_0.struct.neighbor.BriefMember.class, sMember);
                bSmember.setUserId(topicNotifyDTO.getsUserId());
                if (bSmember.getMemberId().equals(requestMid)) {
                    bSmember.setMemberName("我");
                }
                topicNoticy.setsMember(bSmember);//评论，点赞用户
                Map<String, String> skipConfigMap = null;
                if (QDStringUtil.isNotEmpty(version)) {
                    version = version.trim();
                    skipConfigMap = skipMode.selSkipTemplateMap(version);
                } else {
                    logger.info("project index can't get version");
                }
                if (QDStringUtil.isNotNull(topicNotifyDTO.getSubTopicType())) {
                    String skipModel =neighborService.fittingNeighborSkipModel(skipConfigMap, topicNotifyDTO.getSubTopicType(), topicNotifyDTO.getTopicId(), topicNotifyDTO.getParentTopicId());
                    topicNoticy.setSkipModel(skipModel);
                }

                list.add(topicNoticy);
            } catch (Exception ex) {
                logger.error("getTopicNotifyList:", ex);
                continue;
            }

        }
        return list;
    }




}
