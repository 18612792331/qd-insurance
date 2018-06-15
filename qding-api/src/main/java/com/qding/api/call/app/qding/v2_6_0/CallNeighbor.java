package com.qding.api.call.app.qding.v2_6_0;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Account;
import com.qding.api.call.app.qding.v1_3_0.struct.user.AccountMember;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Member;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.GroupUser;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.*;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.UserInfo;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.*;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.AddTopicRequest;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.AddCommentRequest;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.DelCommentRequest;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.DelTopicRequest;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.DisbandmentGroupRequest;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.GetHistoryNotifyByMIdRequest;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.GetNotifyByMIdRequest;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.GetTopicDetailRequest;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.GetTopicsByDesignatedPersonRequest;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.GetTopicsByThemeIdRequest;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request.ReportTopicRequest;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data.*;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data.GetHistoryNotifyByMIdResponseData;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data.GetTopicDetailResponseData;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data.GetTopicsByDesignatedPersonResponseData;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data.GetTopicsByThemeIdResponseData;
import com.qding.api.call.app.qding.v2_8_0.struct.neighbor.ThemeTagInfo;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.struct.ResponseData;
import com.qding.api.util.ImageUtil;
import com.qding.api.util.SkipModeFitting;
import com.qding.brick.pojo.contract.Supplier;
import com.qding.brick.remote.contract.SupplierRemote;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.manager.common.ModelResult;
import com.qding.manager.domain.Puser;
import com.qding.manager.service.IPuserRPCService;
import com.qding.member.model.dto.MemberBindRoomDTO;
import com.qding.member.rpc.IRoomBindApplyRPC;
import com.qding.member.rpc.response.roombindapply.MemberBindRoomDTOsResponse;
import com.qding.neighbor.domain.ActivityTag;
import com.qding.neighbor.domain.Theme;
import com.qding.neighbor.domain.TopicMemberStatistics;
import com.qding.neighbor.dto.ProjectPlainDTO;
import com.qding.neighbor.dto.ThemeDetailDTO;
import com.qding.neighbor.dto.TopicCommentDTO;
import com.qding.neighbor.dto.TopicDetailRpcDTO;
import com.qding.neighbor.dto.cache.TopicNotifyDTO;
import com.qding.neighbor.rpc.IGcRoomRpc;
import com.qding.neighbor.rpc.IThemeRpc;
import com.qding.neighbor.rpc.ITopicRpc;
import com.qding.neighbor.rpc.request.*;
import com.qding.neighbor.rpc.response.data.*;
import com.qding.neighbor.v3.dto.TopicDto;
import com.qding.neighbor.v3.rpc.ITopicRpcV3;
import com.qding.neighbor.v3.rpc.requst.GetTopicBytopicIdRequest;
import com.qding.neighbor.v3.rpc.response.data.GetTopicBytopicIdResponseData;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberByAccountIdRequest;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.request.GetMembersRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.passport.struct.response.GetMembersResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import static org.apache.shiro.web.filter.mgt.DefaultFilter.user;

/**
 * Created by qd on 2016/9/9.
 */
@ExplainAnnotation(explain = "邻聚2.0")
public class CallNeighbor extends Callable {


    @Autowired
    private IRoomBindApplyRPC roomBindApplyService;

    @Autowired
    private IThemeRpc themeRpc;

    @Autowired
    private IGcRoomRpc newGcRoomRpc;

    @Autowired
    private ITopicRpc topicRpc;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private IProfileService profileAPI;

    @Autowired
    private ImageUtil imageUtil;

    @Autowired
    private IPuserRPCService puserRPCService;


    private static Logger logger = Logger.getLogger(CallNeighbor.class);

    /*********************************************
     * 查询类
     *********************************************/

    @HTTP(alias = "getNeighborIndex")
    @ExplainAnnotation(explain = "邻聚首页广场")
    @Deprecated
    public Response<GetNeighborIndexResponseData> getNeighborIndex(GetNeighborIndexRequest request) {

        String projectId = request.getProjectId();
        Response<GetNeighborIndexResponseData> response = new Response<GetNeighborIndexResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetNeighborIndexResponseData data = new GetNeighborIndexResponseData();
        String version = request.getAppDevice().getQdVersion();

        //主题列表
        try {
            List<BriefTheme> themeList = Lists.newArrayList();
            Integer totalCount = 0;
            Map<String, Object> themeMap = getThemes(projectId, 1, request.getShowThemeSize());
            if (QDStringUtil.isNotNull(themeMap)) {
                themeList = (List<BriefTheme>) themeMap.get("list");
                totalCount = (Integer) themeMap.get("totalCount");
            }
            data.setThemeCount(totalCount);
            data.setThemeList(themeList);

        } catch (Exception ex) {
            logger.error("method : getNeighborIndex get themes  error:", ex);
            return handleException(GetNeighborIndexResponseData.class, ex);

        }

        if (QDStringUtil.isNotEmpty(projectId)) {
            try {
                Integer permissionType = getVistorPermissionType(request.getMemberId(), projectId);
                //2、话题列表
                GetTopicsByProjectIdRequest topicsByProjectIdRequest = transfor(GetTopicsByProjectIdRequest.class, request);
                topicsByProjectIdRequest.setPageNo(1);
                topicsByProjectIdRequest.setPageSize(request.getShowTopicSize());
                topicsByProjectIdRequest.setType(permissionType);
                GetTopicsByProjectIdResponseData topicsByProjectIdResponseData = topicRpc.getTopicsByProjectId(topicsByProjectIdRequest);
                checkAndContinue(topicsByProjectIdResponseData);
                List<TopicDetailRpcDTO> topicDetailRpcDTOList = topicsByProjectIdResponseData.getList();
                List<TopicDetail> list = getTopicList(version, topicDetailRpcDTOList);
                if (CollectionUtils.isNotEmpty(list)) {
//                    data.setLastRefreshTime(topicDetailRpcDTOList.get(0).getCreateTime());
                    data.setLastRefreshTime(System.currentTimeMillis());
                }

                data.setNewTopicCount(topicsByProjectIdResponseData.getNewTopicCount());
                data.setTotalCount(topicsByProjectIdResponseData.getTotalCount());
                data.setTopicList(list);
            } catch (ServiceException e) {
                return handleException(GetNeighborIndexResponseData.class, e);
            }
        }

        response.setData(data);
        return response;
    }


    @HTTP(alias = "getNeighborIndexByPaging")
    @ExplainAnnotation(explain = "邻聚首页广场分页信息")
    public Response<GetNeighborIndexByPagingResponseData> getNeighborIndexByPaging(GetNeighborIndexByPagingRequest request) {

        Response<GetNeighborIndexByPagingResponseData> response = new Response<GetNeighborIndexByPagingResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetNeighborIndexByPagingResponseData data = new GetNeighborIndexByPagingResponseData();
        String version = request.getAppDevice().getQdVersion();
        Integer permissionType = getVistorPermissionType(request.getMemberId(), request.getProjectId());

        try {
            GetTopicsByProjectIdRequest topicsByProjectIdRequest = transfor(GetTopicsByProjectIdRequest.class, request);
            topicsByProjectIdRequest.setType(permissionType);
            GetTopicsByProjectIdResponseData topicsByProjectIdResponseData = topicRpc.getTopicsByProjectId(topicsByProjectIdRequest);
            checkAndContinue(topicsByProjectIdResponseData);
            List<TopicDetailRpcDTO> topicDetailRpcDTOList = topicsByProjectIdResponseData.getList();
            List<TopicDetail> list = getTopicList(version, topicDetailRpcDTOList);
            data.setTotalCount(topicsByProjectIdResponseData.getTotalCount());
            data.setTopicList(list);

        } catch (ServiceException e) {
            return handleException(GetNeighborIndexByPagingResponseData.class, e);
        }
        response.setData(data);
        return response;
    }


    @HTTP(alias = "getBriefThemesByPaging")
    @ExplainAnnotation(explain = "分页获取邻聚首页广场简要的主题列表")
    public Response<GetBriefThemesByPagingResponseData> getBriefThemesByPaging(GetBriefThemesByPagingRequest request) {

        Response<GetBriefThemesByPagingResponseData> response = new Response<GetBriefThemesByPagingResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetBriefThemesByPagingResponseData data = new GetBriefThemesByPagingResponseData();
        try {
            List<BriefTheme> themeList = Lists.newArrayList();
            Integer totalCount = 0;
            Map<String, Object> themeMap = getThemes(request.getProjectId(), request.getPageNo(), request.getPageSize());
            if (QDStringUtil.isNotNull(themeMap)) {
                themeList = (List<BriefTheme>) themeMap.get("list");
                totalCount = (Integer) themeMap.get("totalCount");
            }
            data.setTotalCount(totalCount);
            data.setThemeList(themeList);

        } catch (Exception e) {
            logger.error("method : getNeighborIndex get themes  error:", e);
            return handleException(GetBriefThemesByPagingResponseData.class, e);
        }
        response.setData(data);

        return response;
    }


    @HTTP(alias = "getCommentsByPaging")
    @ExplainAnnotation(explain = "分页获取指定话题的评论信息列表")
    public Response<GetCommentsByPagingResponseData> getCommentsByPaging(GetCommentsByPagingRequest request) {

        Response<GetCommentsByPagingResponseData> response = new Response<GetCommentsByPagingResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetCommentsByPagingResponseData data = new GetCommentsByPagingResponseData();
        try {
            GetCommentsByTopicIdRequest commentsByTopicIdRequest = transfor(GetCommentsByTopicIdRequest.class, request);
            GetCommentsByTopicIdResponse commentsByTopicIdResponse = topicRpc.getCommentsByTopicId(commentsByTopicIdRequest);
            checkAndContinue(commentsByTopicIdResponse);
            String lzMemberId = commentsByTopicIdResponse.getLzMemberId();
            List<TopicCommentDTO> topicCommentDTOList = commentsByTopicIdResponse.getCommentDTOList();
            List<TopicComment> topicCommentList = Lists.newArrayList();
            for (TopicCommentDTO topicCommentDTO : topicCommentDTOList) {
                TopicComment comment = transfor(TopicComment.class, topicCommentDTO);
                if (QDStringUtil.isNotNull(comment.getReceiveMember()) && QDStringUtil.isNotEmpty(comment.getReceiveMember().getMemberId())
                        && lzMemberId.equals(comment.getReceiveMember().getMemberId())) {
                    comment.getReceiveMember().setIsLz(1);
                }
                if (QDStringUtil.isNotNull(comment.getSendMember()) && QDStringUtil.isNotEmpty(comment.getSendMember().getMemberId())
                        && lzMemberId.equals(comment.getSendMember().getMemberId())) {
                    comment.getSendMember().setIsLz(1);
                }
                topicCommentList.add(comment);
            }
            data.setList(topicCommentList);
            data.setTotalCount(commentsByTopicIdResponse.getTotalCount());
            response.setData(data);

        } catch (ServiceException e) {
            return handleException(GetCommentsByPagingResponseData.class, e);
        }

        return response;
    }


    @HTTP(alias = "getPraiseMembersByPaging")
    @ExplainAnnotation(explain = "分页获取指定话题的所有点赞信息列表")
    public Response<GetPraiseMembersByPagingResponseData> getPraiseMembersByPaging(GetPraiseMembersByPagingRequest request) {

        Response<GetPraiseMembersByPagingResponseData> response = new Response<GetPraiseMembersByPagingResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetPraiseMembersByPagingResponseData data = new GetPraiseMembersByPagingResponseData();

        try {
            GetPraisesMembersByTopicIdRequest praisesMembersByTopicIdRequest = transfor(GetPraisesMembersByTopicIdRequest.class, request);
            GetPraisesMembersByTopicIdResponse praisesMembersByTopicIdResponse = topicRpc.getPraisesMembersByTopicId(praisesMembersByTopicIdRequest);
            checkAndContinue(praisesMembersByTopicIdResponse);
            List<BriefMember> list = transforList(BriefMember.class, praisesMembersByTopicIdResponse.getPraiseMemberList());
            data.setTotalCount(praisesMembersByTopicIdResponse.getTotalCount());
            data.setList(list);
        } catch (ServiceException e) {
            return handleException(GetPraiseMembersByPagingResponseData.class, e);
        }

        response.setData(data);
        return response;
    }

    @HTTP(alias = "getEnrollMembersByPaging")
    @ExplainAnnotation(explain = "分页获取指定话题报名人列表")
    public Response<GetEnrollMembersByPagingResponseData> getEnrollMembersByPaging(GetEnrollMembersByPagingRequest request) {

        Response<GetEnrollMembersByPagingResponseData> response = new Response<GetEnrollMembersByPagingResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetEnrollMembersByPagingResponseData data = new GetEnrollMembersByPagingResponseData();

        try {
            GetEnrollMembersByTopicIdRequest enrollMembersByTopicIdRequest = transfor(GetEnrollMembersByTopicIdRequest.class, request);
            GetEnrollMembersByTopicIdResponse enrollMembersByTopicIdResponse = topicRpc.getEnrollMembersByTopicId(enrollMembersByTopicIdRequest);
            checkAndContinue(enrollMembersByTopicIdResponse);
            List<BriefMember> list = transforList(BriefMember.class, enrollMembersByTopicIdResponse.getActivityMemberList());
            data.setTotalCount(enrollMembersByTopicIdResponse.getTotalCount());
            data.setList(list);

        } catch (ServiceException e) {
            return handleException(GetEnrollMembersByPagingResponseData.class, e);
        }
        response.setData(data);

        return response;
    }


    @HTTP(alias = "getTopicsByThemeId")
    @ExplainAnnotation(explain = "通过主题ID获取话题列表")
    @Deprecated
    public Response<GetTopicsByThemeIdResponseData> getTopicsByThemeId(GetTopicsByThemeIdRequest request) {

        Response<GetTopicsByThemeIdResponseData> response = new Response<GetTopicsByThemeIdResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetTopicsByThemeIdResponseData data = new GetTopicsByThemeIdResponseData();
        String version = request.getAppDevice().getQdVersion();
        try {

            VerifyRule verifyRule = verifyRule(request.getAppDevice().getQdVersion(), request.getThemeId(), request.getMemberId(), request.getProjectId(), 1);
            if (verifyRule.getCanUse().intValue() <= 0) {
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                data.setMessage(verifyRule.getRemindMsg());
                response.setData(data);
                return response;
            }

            com.qding.neighbor.rpc.request.GetTopicsByThemeIdRequest topicsByThemeIdRequest = transfor(com.qding.neighbor.rpc.request.GetTopicsByThemeIdRequest.class, request);
            com.qding.neighbor.rpc.response.data.GetTopicsByThemeIdResponseData topicsByThemeIdResponseData = topicRpc.getTopicsByThemeId(topicsByThemeIdRequest);
            checkAndContinue(topicsByThemeIdResponseData);
            List<TopicDetailRpcDTO> topicDetailRpcDTOList = topicsByThemeIdResponseData.getList();
            List<TopicDetail> list = getTopicList(version, topicDetailRpcDTOList);

            GetThemeByIdRequest themeByIdRequest = new GetThemeByIdRequest();
            themeByIdRequest.setThemeId(request.getThemeId());
            themeByIdRequest.setSelProjects(false);

            GetThemeDetailByIdResponse themeDetailByIdResponse = themeRpc.selThemeDetailById(themeByIdRequest);
            checkAndContinue(themeDetailByIdResponse);
            ThemeDetailDTO themeDetailDTO = themeDetailByIdResponse.getTheme();
            BriefTheme themeInfo = transfor(BriefTheme.class, themeDetailDTO);
            themeInfo.setThemeId(themeDetailDTO.getId());

            data.setTotalCount(topicsByThemeIdResponseData.getTotalCount());
            data.setTopicList(list);

        } catch (Exception e) {
            return handleException(GetTopicsByThemeIdResponseData.class, e);
        }
        response.setData(data);

        return response;
    }


    @HTTP(alias = "getTopicDetail")
    @ExplainAnnotation(explain = "获取话题详情")
    @Deprecated
    public Response<GetTopicDetailResponseData> getTopicDetail(GetTopicDetailRequest request) {

        Response<GetTopicDetailResponseData> response = new Response<GetTopicDetailResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetTopicDetailResponseData data = new GetTopicDetailResponseData();

        try {
            if (QDStringUtil.isNotEmpty(request.getMemberId())) {
                GetMemberStatusRequest memberStatusRequest = new GetMemberStatusRequest();
                memberStatusRequest.setMemberId(request.getMemberId());
                try {
                    GetMemberStatusResponse memberStatusResponse = topicRpc.getMemberStatsInfo(memberStatusRequest);
                    TopicMemberStatistics topicMemberStatistics = memberStatusResponse.getTopicMemberStatistics();
                    Integer isFreeze = topicMemberStatistics.getIsFreeze();
                    data.setIsFreeze(isFreeze);
                } catch (Exception ex) {
                    logger.error("获取会员ID" + request.getMemberId() + "的冻结状态失败:", ex);
                }
            }

            com.qding.neighbor.rpc.request.GetTopicDetailRequest topicDetailRequest = transfor(com.qding.neighbor.rpc.request.GetTopicDetailRequest.class, request);
            com.qding.neighbor.rpc.response.data.GetTopicDetailResponseData topicDetailResponseData = topicRpc.getTopicDetail(topicDetailRequest);
            checkAndContinue(topicDetailResponseData);
            TopicDetailRpcDTO topicDetailRpcDTO = topicDetailResponseData.getEntity();

            if (QDStringUtil.isNull(topicDetailRpcDTO) || topicDetailRpcDTO.getStatus() == 4 || topicDetailRpcDTO.getStatus() == 5 ||
                    QDStringUtil.isNotEmpty(request.getMemberId()) && !request.getMemberId().equals(topicDetailRpcDTO.getMemberId()) && topicDetailRpcDTO.getStatus() == 6) {
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());//临时定义无权限415
                data.setMessage("当前话题已被冻结或删除");
                response.setData(data);
                return response;
            }

            if (QDStringUtil.isNotNull(request.getProjectId())) {
                String themeId = topicDetailRpcDTO.getThemeId();
                GetThemeByIdRequest themeByIdRequest = new GetThemeByIdRequest();
                themeByIdRequest.setThemeId(themeId);
                themeByIdRequest.setSelProjects(true);
                GetThemeDetailByIdResponse themeDetailByIdResponse = themeRpc.selThemeDetailById(themeByIdRequest);
                checkAndContinue(themeDetailByIdResponse);
                ThemeDetailDTO themeDetailDTO = themeDetailByIdResponse.getTheme();
                List<ProjectPlainDTO> projectPlainDTOList = themeDetailDTO.getProjectList();
                int isExists = 0;
                for (ProjectPlainDTO projectPlainDTO : projectPlainDTOList) {
                    if (request.getProjectId().equals(String.valueOf(projectPlainDTO.getProjectId()))) {
                        isExists = 1;
                        break;
                    }
                }
                if (isExists == 0) {
                    response.setCode(415);//临时定义无权限415
                    data.setMessage("当前社区下无权限查看");
                    response.setData(data);
                    return response;
                }

                if (2 == themeDetailDTO.getAccessPermission() && QDStringUtil.isNotEmpty(request.getMemberId())) {
                    MemberBindRoomDTOsResponse memberBindRoomDTOsResponse = roomBindApplyService.findByMemberIdAndProjectId(request.getMemberId(), request.getProjectId());
                    checkAndContinue(memberBindRoomDTOsResponse);
                    Boolean bindFlag = false;
                    if (CollectionUtils.isNotEmpty(memberBindRoomDTOsResponse.getList())) {
                        for (MemberBindRoomDTO memberBindRoomDTO : memberBindRoomDTOsResponse.getList()) {
                            if (QDStringUtil.isNotNull(memberBindRoomDTO.getAuditStatus()) && memberBindRoomDTO.getAuditStatus().intValue() == 1) {//绑定并审核通过
                                bindFlag = true;
                                break;
                            }
                        }
                    }
                    if (!bindFlag) {
                        response.setCode(415);//临时定义无权限415
                        data.setMessage("当前社区下无权限查看");
                        response.setData(data);
                        return response;
                    }
                }

            }

            TopicDetail topicDetail = fittingTopicInfo(request.getAppDevice().getQdVersion(), topicDetailRpcDTO);
            if (Constant.XZS.equals(String.valueOf(topicDetailRpcDTO.getMemberType()))) {
                topicDetail.setProjectName("#" + topicDetailRpcDTO.getMember().getMemo() + "#");//如果是邻聚小助手信息这里不显示社区名称
                topicDetail.setCityName("");
            }
            topicDetail.getMemberInfo().setMemberType(String.valueOf(topicDetailRpcDTO.getMemberType()));
            data.setEntity(topicDetail);

        } catch (Exception e) {
            return handleException(GetTopicDetailResponseData.class, e);
        }

        response.setData(data);
        return response;
    }


    @HTTP(alias = "getTopicsByDesignatedPerson")
    @ExplainAnnotation(explain = "获取指定用户参与或发起的话题")
    public Response<GetTopicsByDesignatedPersonResponseData> getTopicsByDesignatedPerson(GetTopicsByDesignatedPersonRequest request) {

        Response<GetTopicsByDesignatedPersonResponseData> response = new Response<GetTopicsByDesignatedPersonResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetTopicsByDesignatedPersonResponseData data = new GetTopicsByDesignatedPersonResponseData();
        String version = request.getAppDevice().getQdVersion();
        GetMemberByAccountIdRequest memberByAccountIdRequest = new GetMemberByAccountIdRequest();
        memberByAccountIdRequest.setAccountId(request.getUserId());
        try {

            GetMemberResponse memberResponse = profileAPI.getMemberByAccountId(memberByAccountIdRequest);
            checkAndContinue(memberResponse);
            MemberInfo memberInfo = memberResponse.getMemberInfo();
            com.qding.neighbor.rpc.request.GetTopicsByDesignatedPersonRequest topicsByDesignatedPersonRequest = transfor(com.qding.neighbor.rpc.request.GetTopicsByDesignatedPersonRequest.class, request);
            topicsByDesignatedPersonRequest.setvMemberId(memberInfo.getId());
            //如果访问的是别人需要传入访问权限类型
            if (!request.getMemberId().equals(memberInfo.getId())) {
                Integer permissionType = getVistorPermissionType(request.getMemberId(), request.getProjectId());
                topicsByDesignatedPersonRequest.setType(permissionType);
            }
            com.qding.neighbor.rpc.response.data.GetTopicsByDesignatedPersonResponseData topicsByDesignatedPersonResponseData = topicRpc.getTopicsByDesignatedPerson(topicsByDesignatedPersonRequest);
            checkAndContinue(topicsByDesignatedPersonResponseData);
            List<TopicDetailRpcDTO> topicDetailRpcDTOList = topicsByDesignatedPersonResponseData.getList();
            List<TopicDetail> list = getTopicList(version, topicDetailRpcDTOList);
            data.setTotalCount(topicsByDesignatedPersonResponseData.getTotalCount());
            data.setTopicList(list);

        } catch (Exception e) {
            return handleException(GetTopicsByDesignatedPersonResponseData.class, e);
        }
        response.setData(data);

        return response;
    }


    @HTTP(alias = "verifyRuleForTheme")
    @ExplainAnnotation(explain = "获取用户权限针对指定主题")
    public Response<GetMemberRuleForVoteAndActivityResponseData> verifyRuleForTheme(GetMemberRuleForVoteAndActivityRequest request) {

        Response<GetMemberRuleForVoteAndActivityResponseData> response = new Response<GetMemberRuleForVoteAndActivityResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            VerifyRule verifyRule = verifyRule(request.getAppDevice().getQdVersion(), request.getThemeId(), request.getMemberId(), request.getProjectId(), 1);
            GetMemberRuleForVoteAndActivityResponseData data = transfor(GetMemberRuleForVoteAndActivityResponseData.class, verifyRule);
            Theme theme = verifyRule.getTheme();
            data.setTitle(QDStringUtil.isNotNull(theme.getThemeName()) ? theme.getThemeName() : "");
            data.setThemeTagInfoList(verifyRule.getTagList());

            data.setList(verifyRule.getVerifyRuleList());
            response.setData(data);

        } catch (Exception e) {
            return handleException(GetMemberRuleForVoteAndActivityResponseData.class, e);
        }
        return response;
    }

    /**
     * 邻聚社区首页接口（社区公共群列表，兴趣群列表）
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getGcIndex")
    @ExplainAnnotation(explain = "群聊首页")
    public Response<GetGcIndexResponseData> getGcIndex(GetGcIndexRequest request) {

        try {
            Response<GetGcIndexResponseData> response = new Response<GetGcIndexResponseData>();
            response.setCode(HttpStatus.OK.getStatusCode());
            Integer oldVersionFlag = 0;
            if (request.getAppDevice().getQdVersion().compareTo("3.0.0") < 0) {//老版本app不显示讨论组不在
                oldVersionFlag = 1;
            }
            GetGcRoomRequest getGcRoomRequest = transfor(GetGcRoomRequest.class, request);
            getGcRoomRequest.setOldVersionFlag(oldVersionFlag);
            GetGcRoomResponse getGcRoomResponse = newGcRoomRpc.getGcRoom(getGcRoomRequest);
            checkAndContinue(getGcRoomResponse);
            GetGcIndexResponseData data = transfor(GetGcIndexResponseData.class, getGcRoomResponse);
            response.setData(data);

            return response;

        } catch (Exception ex) {
            return handleException(GetGcIndexResponseData.class, ex);
        }

    }


    @ExplainAnnotation(explain = "讨论组人员列表")
    @HTTP(alias = "groupMemberList")
    public Response<GroupMemberListResponseData> groupMemberList(GroupMemberListRequest request) {

        Response<GroupMemberListResponseData> response = new Response<GroupMemberListResponseData>();

        try {
            GroupMemberListResponseData data = new GroupMemberListResponseData();
            response.setCode(HttpStatus.OK.getStatusCode());
            GetDiscussionGroupUserRequest discussionGroupUserRequest = transfor(GetDiscussionGroupUserRequest.class, request);
            GetDiscussionGroupUserResonse discussionGroup = newGcRoomRpc.getDiscussionGroup(discussionGroupUserRequest);
            checkAndContinue(discussionGroup);
            Integer memberCount = discussionGroup.getTotalCount();

            if (memberCount > 0) {
                List<GroupUser> groupUserList = transforList(GroupUser.class, discussionGroup.getList());
                data.setList(groupUserList);
                data.setTotalCount(memberCount);
            } else {
                data.setList(new ArrayList<GroupUser>());
                data.setTotalCount(0);
            }

            response.setData(data);

        } catch (Exception ex) {
            return handleException(GroupMemberListResponseData.class, ex);
        }
        return response;
    }


    @ExplainAnnotation(explain = "退出群")
    @HTTP(alias = "quitGroup")
    public Response<QuitGroupResponseData> quitGroup(QuitGroupRequest request) {

        Response<QuitGroupResponseData> response = new Response<QuitGroupResponseData>();
        try {
            response.setCode(HttpStatus.OK.getStatusCode());
            BaseResponse baseResponse = newGcRoomRpc.quitGroup(transfor(com.qding.neighbor.rpc.request.OptGroupMemberRequest.class, request));
            checkAndContinue(baseResponse);
            QuitGroupResponseData data = new QuitGroupResponseData();
            response.setData(data);
            return response;
        } catch (Exception e) {
            return handleException(QuitGroupResponseData.class, e);
        }
    }


    @ExplainAnnotation(explain = "解散群")
    @HTTP(alias = "disbandmentGroup")
    public Response<DisbandmentGroupResponseData> disbandmentGroup(DisbandmentGroupRequest request) {

        Response<DisbandmentGroupResponseData> response = new Response<DisbandmentGroupResponseData>();
        try {
            response.setCode(HttpStatus.OK.getStatusCode());
            BaseResponse baseResponse = newGcRoomRpc.disbandmentGroup(transfor(com.qding.neighbor.rpc.request.DisbandmentGroupRequest.class, request));
            checkAndContinue(baseResponse);
            DisbandmentGroupResponseData data = new DisbandmentGroupResponseData();
            response.setData(data);
            return response;
        } catch (Exception e) {
            return handleException(DisbandmentGroupResponseData.class, e);
        }
    }


    @ExplainAnnotation(explain = "获取用户历史被点赞，评论消息列表")
    @HTTP(alias = "getHistoryNotifysByMId")
    public Response<GetHistoryNotifyByMIdResponseData> getHistoryNotifyByMId(GetHistoryNotifyByMIdRequest request) {
        Response<GetHistoryNotifyByMIdResponseData> response = new Response<GetHistoryNotifyByMIdResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        GetHistoryNotifyByMIdResponseData data = new GetHistoryNotifyByMIdResponseData();
        try {
            List<TopicNotify> list = Lists.newArrayList();
            com.qding.neighbor.rpc.request.GetHistoryNotifyByMIdRequest historyNotifyByMIdRequest = transfor(com.qding.neighbor.rpc.request.GetHistoryNotifyByMIdRequest.class, request);
            com.qding.neighbor.rpc.response.data.GetHistoryNotifyByMIdResponse historyNotifyByMIdResponse = topicRpc.getHistoryNotifyByMId(historyNotifyByMIdRequest);
            checkAndContinue(historyNotifyByMIdResponse);
            List<TopicNotifyDTO> notifyDTOList = historyNotifyByMIdResponse.getList();
            list = getTopicNotifyList(notifyDTOList, request.getMemberId());
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
    @HTTP(alias = "getNotifysByMId")
    public Response<GetNotifyByMIdResponseData> getNotifysByMId(GetNotifyByMIdRequest request) {

        Response<GetNotifyByMIdResponseData> response = new Response<GetNotifyByMIdResponseData>();
        GetNotifyByMIdResponseData data = new GetNotifyByMIdResponseData();
        response.setCode(HttpStatus.OK.getStatusCode());
        List<TopicNotify> list = Lists.newArrayList();

        try {
            com.qding.neighbor.rpc.request.GetNotifyByMIdRequest notifyByMidRequest = transfor(com.qding.neighbor.rpc.request.GetNotifyByMIdRequest.class, request);
            GetNotifyByMIdResponse notifyByMidResponse = topicRpc.getNotifyByMId(notifyByMidRequest);
            checkAndContinue(notifyByMidResponse);
            List<TopicNotifyDTO> notifyDTOList = notifyByMidResponse.getList();
            list = getTopicNotifyList(notifyDTOList, request.getMemberId());
            data.setList(list);
            data.setTotalCount(notifyByMidResponse.getNoticeNum());
            response.setData(data);

        } catch (ServiceException e) {
            return handleException(GetNotifyByMIdResponseData.class, e);
        }
        return response;
    }

    @HTTP(alias = "getMemberStatsInfo")
    @ExplainAnnotation(explain = "获取会员邻聚状态信息")
    public Response<GetMemberStatsInfoResponseData> getMemberStatsInfo(GetMemberStatsInfoRequest request) {

        Response<GetMemberStatsInfoResponseData> response = new Response<GetMemberStatsInfoResponseData>();
        GetMemberStatsInfoResponseData data = new GetMemberStatsInfoResponseData();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            String accountId = request.getUserId();
            if (accountId.startsWith(Constant.SUPPER_PREFIX)) {
                String puserId= accountId.replace(Constant.SUPPER_PREFIX,"");
                ModelResult modelResult = puserRPCService.getPuserInfoByPuserId(puserId);
                UserInfo user = new UserInfo();
                if (HttpStatus.OK.getStatusCode() == modelResult.getCode()) {
                    Puser puser = (Puser) modelResult.getEntity();
                    user.setUserId(puserId);
                    user.setUserHeadImageUrl(imageUtil.get(Constant.DEFAULT_SUPPLIER_HEAD_IMG,150,150));
                    user.setUserName(puser.getName());
                    user.setUserSex(1);
                    user.setIsSay(1);
                    user.setMemberId(puserId);
                }
                data.setEntity(user);
            } else {
                //给场景只能传入accountId
                GetMemberByAccountIdRequest getMemberByAccountIdRequest = new GetMemberByAccountIdRequest();
                getMemberByAccountIdRequest.setAccountId(request.getUserId());
                GetMemberResponse memberResponse = profileAPI.getMemberByAccountId(getMemberByAccountIdRequest);
                checkAndContinue(memberResponse);
                MemberInfo memberInfo = memberResponse.getMemberInfo();
                GetMemberStatusRequest getMemberStatusRequest = new GetMemberStatusRequest();
                getMemberStatusRequest.setMemberId(memberInfo.getId());
                GetMemberStatusResponse getMemberStatusResponse = topicRpc.getMemberStatsInfo(getMemberStatusRequest);
                checkAndContinue(getMemberStatusResponse);
                UserInfo userInfo = new UserInfo();
                userInfo.setMemberId(memberInfo.getId());
                userInfo.setUserHeadImageUrl(memberInfo.getHeadImg());
                userInfo.setUserId(request.getUserId());
                userInfo.setUserName(memberInfo.getName());
                userInfo.setUserSex(memberInfo.getGender());
                userInfo.setUserSign(memberInfo.getSignature());
                data.setEntity(userInfo);
                data.getEntity().setIsSay(getMemberStatusResponse.getTopicMemberStatistics().getIsSay());
                data.getEntity().setIsFreeze(getMemberStatusResponse.getTopicMemberStatistics().getIsFreeze());
                userInfo.setUserHeadImageUrl(
                        imageUtil.get(userInfo.getUserHeadImageUrl(), 150, 150));
            }
            response.setData(data);
            return response;
        } catch (Exception ex) {
            return handleException(GetMemberStatsInfoResponseData.class, ex);
        }
    }

    /*******************************************
     * 操作类
     *****************************************************************/

    @HTTP(alias = "addTopic")
    @ExplainAnnotation(explain = "发布话题帖子")
    public Response<AddTopicResponseData> addTopic(AddTopicRequest request) {
        Response<AddTopicResponseData> response = new Response<AddTopicResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        AddTopicResponseData data = new AddTopicResponseData();
        try {
            VerifyRule verifyRule = verifyRule(request.getAppDevice().getQdVersion(), request.getThemeId(), request.getMemberId(), String.valueOf(request.getProjectId()), 2);
            if (verifyRule.getCanUse() <= 0) {//1:可用，0：不可用
                data.setMessage(verifyRule.getRemindMsg());
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                response.setData(data);
                return response;
            }
            if (request.getTopicType() == 3 && StringUtils.isEmpty(request.getTopicTitle())) {
                response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                data.setMessage("报名活动类的报名标题不能为空");
                response.setData(data);
                return response;
            }
            com.qding.neighbor.rpc.request.AddTopicRequest addTopicRequest = new com.qding.neighbor.rpc.request.AddTopicRequest();
            addTopicRequest.setMemberId(request.getMemberId());
            addTopicRequest.setThemeId(request.getThemeId());
            addTopicRequest.setTopicType(request.getTopicType());
            addTopicRequest.setTopicContent(request.getTopicContent());
            // List<String> 转json,填充index
            //例："{\"images\":[{\"index\":\"1\",\"url\":\"http://a.jpg\"},{\"index\":\"2\",\"url\":\"http://b.jpg\"}]}"
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
                addTopicRequest.setTopicImage(jsonObject.toJSONString());
            }

            if (request.getTopicType() == 2) { //2投票 3报名活动 需要手工增加index
                int i = 1;
                request.getVote().setStartTime(System.currentTimeMillis());
                for (BriefVoteItem item : request.getVote().getVoteList()) {
                    item.setIndex(String.valueOf(i));
                    i++;
                }
                addTopicRequest.setTopicStruct(JSON.toJSONString(request.getVote()));
            } else if (request.getTopicType() == 3) {
                //{"startTime":"1445331780779","endTime":"1445331780779","maxPepole":"100",dismissalTime:3}
                JSONObject sub = new JSONObject();
                sub.put("startTime", System.currentTimeMillis());
                sub.put("endTime", request.getActivity().getEndTime());
                sub.put("maxPepole", request.getActivity().getActivityTotalCount());
                sub.put("dismissalTime", 0);
                addTopicRequest.setTopicStruct(sub.toJSONString());
            }
            addTopicRequest.setProjectId(request.getProjectId());
            addTopicRequest.setUserId(request.getUserId());
            addTopicRequest.setTopicTitle(request.getTopicTitle());
            if (QDStringUtil.isNotEmpty(request.getTagId()))
                addTopicRequest.setTagId(request.getTagId());
            BaseResponse baseResponse = topicRpc.addTopic(addTopicRequest);
            checkAndContinue(baseResponse);
        } catch (Exception ex) {
            return handleException(AddTopicResponseData.class, ex);
        }

        return response;
    }

    @HTTP(alias = "delTopic")
    @ExplainAnnotation(explain = "删除话题")
    @Deprecated
    public Response<DelTopicResponseData> delTopic(DelTopicRequest request) {
        Response<DelTopicResponseData> response = new Response<DelTopicResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            DelTopicResponseData data = new DelTopicResponseData();
            com.qding.neighbor.rpc.request.DelTopicRequest delTopicRequest = new com.qding.neighbor.rpc.request.DelTopicRequest();
            delTopicRequest.setTopicId(request.getTopicId());
            delTopicRequest.setMemberId(request.getMemberId());
            BaseResponse baseResponse = topicRpc.delTopic(delTopicRequest);
            checkAndContinue(baseResponse);
            response.setData(data);
        } catch (Exception ex) {
            return handleException(DelTopicResponseData.class, ex);
        }
        return response;
    }


    @HTTP(alias = "addComment")
    @ExplainAnnotation(explain = "发布评论", desc = "(包含回复评论)")
    @Deprecated
    public Response<AddCommentResponseData> addComment(AddCommentRequest request) {
        Response<AddCommentResponseData> response = new Response<AddCommentResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            AddCommentResponseData data = new AddCommentResponseData();
            com.qding.neighbor.rpc.request.AddCommentRequest addCommentRequest = new com.qding.neighbor.rpc.request.AddCommentRequest();
            addCommentRequest.setMemberId(request.getSendMemberId());
            addCommentRequest.setTopicId(request.getTopicId());
            addCommentRequest.setContent(request.getContent());
            addCommentRequest.setPid(request.getpCommentId());
            addCommentRequest.setUserId(request.getUserId());
            AddCommentResponse addCommentResponse = topicRpc.addComment(addCommentRequest);
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
            commentBriefMember.setUserId(request.getUserId());
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


    @HTTP(alias = "handlePraise")
    @ExplainAnnotation(explain = "点赞")
    @Deprecated
    public Response<AddPraiseResponseData> handlePraise(AddPraiseRequest request) {

        Response<AddPraiseResponseData> response = new Response<AddPraiseResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            AddPraiseResponseData data = new AddPraiseResponseData();
            HandlePraiseRequest handlePraiseRequest = new HandlePraiseRequest();
            handlePraiseRequest.setMemberId(request.getMemberId());
            handlePraiseRequest.setTopicId(request.getTopicId());
            handlePraiseRequest.setUserId(request.getUserId());
            BaseResponse baseResponse = topicRpc.handlePraise(handlePraiseRequest);
            checkAndContinue(baseResponse);
            response.setData(data);
        } catch (Exception ex) {
            return handleException(AddPraiseResponseData.class, ex);
        }
        return response;
    }


    @HTTP(alias = "delComment")
    @ExplainAnnotation(explain = "删除评论")
    @Deprecated
    public Response<DelCommentResponseData> delComment(DelCommentRequest request) {
        Response<DelCommentResponseData> response = new Response<DelCommentResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            DelCommentResponseData data = new DelCommentResponseData();
            com.qding.neighbor.rpc.request.DelCommentRequest delCommentRequest
                    = new com.qding.neighbor.rpc.request.DelCommentRequest();
            delCommentRequest.setMemberId(request.getMemberId());
            delCommentRequest.setCommentId(request.getCommentId());
            BaseResponse baseResponse = topicRpc.delComment(delCommentRequest);
            checkAndContinue(baseResponse);
            response.setData(data);
        } catch (Exception ex) {
            return handleException(DelCommentResponseData.class, ex);
        }
        return response;
    }


    @HTTP(alias = "reportTopic")
    @ExplainAnnotation(explain = "举报话题")
    @Deprecated
    public Response<ReportTopicResponseData> reportTopic(ReportTopicRequest request) {
        Response<ReportTopicResponseData> response = new Response<ReportTopicResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            com.qding.neighbor.rpc.request.ReportTopicRequest reportTopicRequest = new com.qding.neighbor.rpc.request.ReportTopicRequest();
            reportTopicRequest.setMemberId(request.getMemberId());
            reportTopicRequest.setTopicId(request.getTopicId());
            reportTopicRequest.setContent(request.getContent());
            reportTopicRequest.setUserId(request.getUserId());
            BaseResponse baseResponse = topicRpc.reportTopic(reportTopicRequest);
            checkAndContinue(baseResponse);
            ReportTopicResponseData data = new ReportTopicResponseData();
            response.setData(data);
        } catch (Exception ex) {
            return handleException(ReportTopicResponseData.class, ex);
        }
        return response;
    }


    @HTTP(alias = "enroll")
    @ExplainAnnotation(explain = "报名")
    public Response<EnrollResponseData> enroll(EnrollRequest request) {
        Response<EnrollResponseData> response = new Response<EnrollResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            EnrollResponseData data = new EnrollResponseData();
            EnrollTopicRequest enrollTopicRequest = new EnrollTopicRequest();
            enrollTopicRequest.setMemberId(request.getMemberId());
            enrollTopicRequest.setTopicId(request.getTopicId());
            enrollTopicRequest.setUserId(request.getUserId());
            BaseResponse baseResponse = topicRpc.enroll(enrollTopicRequest);
            checkAndContinue(baseResponse);
            response.setData(data);
        } catch (Exception ex) {
            return handleException(EnrollResponseData.class, ex);
        }
        return response;
    }

    @HTTP(alias = "cancelRegistration")
    @ExplainAnnotation(explain = "取消报名")
    public Response<CancelRegistrationResponseData> cancelRegistration(CancelRegistrationRequest request) {

        Response<CancelRegistrationResponseData> response = new Response<CancelRegistrationResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        CancelRegistrationResponseData data = new CancelRegistrationResponseData();
        response.setData(data);

        return response;
    }

    @HTTP(alias = "vote")
    @ExplainAnnotation(explain = "投票")
    @Deprecated
    public Response<VoteResponseData> vote(VoteRequest request) {
        Response<VoteResponseData> response = new Response<VoteResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            VoteResponseData data = new VoteResponseData();
            VoteTopicRequest voteTopicRequest = new VoteTopicRequest();
            voteTopicRequest.setMemberId(request.getMemberId());
            voteTopicRequest.setTopicId(request.getTopicId());
            voteTopicRequest.setUserId(request.getUserId());
            voteTopicRequest.setCheckIndex(request.getCheckedIndex());
            BaseResponse baseResponse = topicRpc.vote(voteTopicRequest);
            checkAndContinue(baseResponse);
            response.setData(data);

        } catch (Exception ex) {
            return handleException(VoteResponseData.class, ex);
        }
        return response;
    }


    /***************************************************
     * 私有公共方法区
     **************************************************/

    /**
     * 发帖验证方法
     *
     * @param themeId
     * @param memberId
     * @param projectId
     * @param handleType 1：发帖前验证 2：发帖提交时验证
     * @return
     */
    public VerifyRule verifyRule(String version, String themeId, String memberId, String projectId, Integer handleType) throws Exception {

        VerifyRule verifyRule = new VerifyRule();
        GetThemeByIdRequest getThemeByIdRequest = new GetThemeByIdRequest();
        getThemeByIdRequest.setThemeId(themeId);
        getThemeByIdRequest.setSelProjects(true);
        GetThemeDetailByIdResponse themeByIdResponse = themeRpc.selThemeDetailById(getThemeByIdRequest);
        checkAndContinue(themeByIdResponse);
        ThemeDetailDTO theme = themeByIdResponse.getTheme();
        Theme themeDto = transfor(Theme.class, theme);
        Integer canUse = 1;
        String remindMsg = "";
        List<ThemeRuleForMember> list = Lists.newArrayList();
        verifyRule.setAccessPermission(theme.getAccessPermission());
        verifyRule.setTheme(themeDto);
        //如果主题上架状态
        if (1 == theme.getStatus().intValue()) {
            List<ProjectPlainDTO> projectList = theme.getProjectList();
            boolean isContains = false;
            if (CollectionUtils.isNotEmpty(projectList)) {
                for (ProjectPlainDTO projectPlainDTO : projectList) {
                    if (String.valueOf(projectPlainDTO.getProjectId()).equals(projectId)) {
                        isContains = true;
                        break;
                    }
                }
            }
            if (!isContains) {
                verifyRule.setVerifyRuleList(list);
                verifyRule.setCanUse(0);
                verifyRule.setRemindMsg("当前社区无权限针对该主题发布话题贴");
                return verifyRule;
            }

            Integer accessPermission = theme.getAccessPermission();
            if (accessPermission.intValue() == 2) { //绑定房屋权限
                MemberBindRoomDTOsResponse memberBindRoomDTOsResponse  = roomBindApplyService.findByMemberIdAndProjectId(memberId, projectId);
                checkAndContinue(memberBindRoomDTOsResponse);
                Set<Integer> bindFlagSet = new HashSet<>();
                if (CollectionUtils.isNotEmpty(memberBindRoomDTOsResponse.getList())) {
                    for (MemberBindRoomDTO memberBindRoomDTO : memberBindRoomDTOsResponse.getList()) {
                        if (QDStringUtil.isNotNull(memberBindRoomDTO.getAuditStatus())) {
                            bindFlagSet.add(memberBindRoomDTO.getAuditStatus());
                        }
                    }
                }

                if (bindFlagSet.size() == 0) {//没有房屋
                    canUse = -1;
                    remindMsg = "当前社区下没有绑定房屋";
                } else {
                    if (bindFlagSet.contains(1)) { //审核通过
                        canUse = 1;
                        remindMsg = "";
                    } else { //待审核
                        canUse = -2;
                        remindMsg = "您尚未绑定房屋或房屋未通过验证";
                    }
                }

                if (handleType.intValue() == 1) {

                    List<String> limitMsg = Lists.newArrayList();
                    ThemeRuleForMember themeRuleForMember = new ThemeRuleForMember(Constant.TopicType.Common.getValue(), 1, 0, ""); //  此时可以保证普通帖子可以发布
                    list.add(themeRuleForMember);

                    //获取用户话题和投票发帖数
                    GetCountForTopicByMidAndThemeIdRequest countForTopicByMidAndThemeIdRequest = new GetCountForTopicByMidAndThemeIdRequest();
                    countForTopicByMidAndThemeIdRequest.setMemberId(memberId);
                    countForTopicByMidAndThemeIdRequest.setThemeId(themeId);
                    GetCountForTopicByMidAndThemeIdResponse getCountForTopicByMidAndThemeIdResponse = themeRpc.getCountForTopicByMidAndThemeId(countForTopicByMidAndThemeIdRequest);
                    checkAndContinue(getCountForTopicByMidAndThemeIdResponse);
                    Map<Integer, Boolean> countMap = getCountForTopicByMidAndThemeIdResponse.getTopicCountMap();

                    //如果当前主题支持活动话题
                    if (theme.getIsSupportVote().intValue() == 1) {
                        //检查当前用户已发布投票次数
                        Boolean voteIsLimit = countMap.containsKey(Constant.TopicType.Vote.getValue()) ? countMap.get(Constant.TopicType.Vote.getValue()) : false;
                        if (voteIsLimit) {//如果达到上线
                            //不可以发起投票
                            ThemeRuleForMember voteRule = new ThemeRuleForMember(Constant.TopicType.Vote.getValue(), 0, 1, "已达该主题站发布投票上限");
                            limitMsg.add("投票贴");
                            list.add(voteRule);
                        } else {
                            //可以发起投票
                            ThemeRuleForMember voteRule = new ThemeRuleForMember(Constant.TopicType.Vote.getValue(), 1, 0, "");
                            list.add(voteRule);
                        }
                    } else {
                        //不可以发起投票
//                        ThemeRuleForMember voteRule = new ThemeRuleForMember(Constant.TopicType.Vote.getValue(), 0, 0, "当前主题站不支持投票话题");
//                        list.add(voteRule);
                    }

                    //如果当前主题支持活动话题
                    if (theme.getIsSupportActivity().intValue() == 1) {
                        Boolean activityIsLimit = countMap.containsKey(Constant.TopicType.Activity.getValue()) ? countMap.get(Constant.TopicType.Activity.getValue()) : false;
                        if (activityIsLimit) {
                            ThemeRuleForMember supportActivityRule = new ThemeRuleForMember(Constant.TopicType.Activity.getValue(), 0, 1, "已达该主题站发布话题上限");
                            list.add(supportActivityRule);
                            limitMsg.add("报名贴");
                        } else {
                            ThemeRuleForMember supportActivityRule = new ThemeRuleForMember(Constant.TopicType.Activity.getValue(), 1, 0, "");
                            list.add(supportActivityRule);
                        }
                    } else {
//                        ThemeRuleForMember supportActivityRule = new ThemeRuleForMember(Constant.TopicType.Activity.getValue(), 0, 0, "当前主题站不支持报名话题");
//                        list.add(supportActivityRule);
                    }
                    if (CollectionUtils.isNotEmpty(limitMsg)) {
                        StringBuffer msgBufer = new StringBuffer("指定时间段内,");
                        for (int i = 0; i < limitMsg.size(); i++) {
                            if (i != 0) {
                                msgBufer.append("或");
                            }
                            msgBufer.append(limitMsg.get(i));
                        }
                        msgBufer.append("已达上限");
                        remindMsg = msgBufer.toString();
                    }
                }
            } else { //注册权限
                if (handleType.intValue() == 1) {
                    String initVersion = skipMode.initVersion(version);
                    if (Integer.parseInt(initVersion) < 270000) {
                        ThemeRuleForMember voteRule = new ThemeRuleForMember(Constant.TopicType.Vote.getValue(), 0, 0, "");
                        ThemeRuleForMember activiityRule = new ThemeRuleForMember(Constant.TopicType.Activity.getValue(), 0, 0, "");
                        list.add(voteRule);
                        list.add(activiityRule);
                    }
                    ThemeRuleForMember themeRuleForMember = new ThemeRuleForMember(Constant.TopicType.Common.getValue(), 1, 0, ""); //  此时可以保证普通帖子可以发布
                    list.add(themeRuleForMember);
                }
            }


        } else {
            canUse = 0;
            remindMsg = "当前主题已下架";
        }

        List<ActivityTag> activityTagList = themeByIdResponse.getTheme().getActivityTagList();
        List<ThemeTagInfo> tagList = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(activityTagList)) {
            for (ActivityTag activityTag : activityTagList) {
                if (QDStringUtil.isNotEmpty(activityTag.getImg())) {
                    com.alibaba.fastjson.JSONArray jsonArray = JSON.parseArray(activityTag.getImg());
                    List<String> imgList = Lists.newArrayList();
                    for (Object o : jsonArray) {
                        JSONObject urlJson = JSON.parseObject(String.valueOf(o));
                        imgList.add(urlJson.getString("url"));
                    }
                    ThemeTagInfo tag = transfor(ThemeTagInfo.class, activityTag);
                    if (activityTag.getStatus() == 2) {
                        tag.setTagStatus(1);//上架
                    } else if (activityTag.getStatus() == 1) {
                        tag.setTagStatus(2);//待审核
                    } else {
                        tag.setTagStatus(activityTag.getStatus());//其他状态不变
                    }
                    tag.setTagImg(imgList);
                    tagList.add(tag);
                }
            }
            verifyRule.setTagList(tagList);
        }
        verifyRule.setVerifyRuleList(list);
        verifyRule.setCanUse(canUse);
        verifyRule.setRemindMsg(remindMsg);

        return verifyRule;
    }


    public Integer getVistorPermissionType(String memberId, String projectId) {

        Integer queryType = 0;
        if (QDStringUtil.isNotEmpty(memberId)) {

            Integer bindFlag = 0;
            //查询当前用户在当前社区是否绑定了房屋
            MemberBindRoomDTOsResponse memberBindRoomDTOsResponse = roomBindApplyService.findByMemberIdAndProjectId(memberId, projectId);
            Set<Integer> bindFlagSet = new HashSet<>();
            if (memberBindRoomDTOsResponse.getReturnInfo().getCode()== HttpStatus.OK.getStatusCode() && CollectionUtils.isNotEmpty(memberBindRoomDTOsResponse.getList())) {
                for (MemberBindRoomDTO memberBindRoomDTO : memberBindRoomDTOsResponse.getList()) {
                    if (QDStringUtil.isNotNull(memberBindRoomDTO.getAuditStatus())) {
                        bindFlagSet.add(memberBindRoomDTO.getAuditStatus());
                    }
                }
            }

            if (bindFlagSet.size() > 0 && bindFlagSet.contains(1)) { //有审核通过
                bindFlag = 1;
            }

            if (bindFlag == 1) {//如果绑定了房屋可查询 注册权限和绑定房屋权限下的主题
                queryType = 2;
            } else {  //如果没有绑定好的房屋则只查询 注册权限下的主题
                queryType = 1;
            }
        }

        return queryType;
    }

    /**
     * 获取主题列表
     *
     * @param projectId
     * @param pageNo
     * @param pageSize
     * @return
     */
    public Map<String, Object> getThemes(String projectId, Integer pageNo, Integer pageSize) {

        //1、主题索引
        GetThemesByPidAndPermissionTypeRequest themesByPidAndPermissionTypeRequest = new GetThemesByPidAndPermissionTypeRequest();
//        themesByPidAndPermissionTypeRequest.setQueryType(permissionType);//主题列表查看所有主题，不对权限过滤
        themesByPidAndPermissionTypeRequest.setProjectId(projectId);
        themesByPidAndPermissionTypeRequest.setPageSize(pageSize);
        themesByPidAndPermissionTypeRequest.setPageNo(pageNo);

        GetThemesByPidAndPermissionTypeResponse themesByPidAndPermissionTypeResponse = themeRpc.getThemesByPidAndPermissionType(themesByPidAndPermissionTypeRequest);
        List<BriefTheme> themeList = Lists.newArrayList();
        if (HttpStatus.OK.getStatusCode() == themesByPidAndPermissionTypeResponse.getReturnInfo().getCode()) {
            List<Theme> tList = themesByPidAndPermissionTypeResponse.getList();
            themeList = transforList(BriefTheme.class, tList);
        } else {
            logger.error("private method :getThemes  error !" + JSON.toJSONString(themesByPidAndPermissionTypeRequest));
        }
        HashMap<String, Object> themeMap = new HashMap<>();
        themeMap.put("list", themeList);
        themeMap.put("totalCount", themesByPidAndPermissionTypeResponse.getTotalCount());
        return themeMap;
    }


    /**
     * 获取话题列表
     *
     * @param version
     * @param topicDetailRpcDTOList
     * @return
     */
    public List<TopicDetail> getTopicList(String version, List<TopicDetailRpcDTO> topicDetailRpcDTOList) {
        List<TopicDetail> list = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(topicDetailRpcDTOList)) {
            for (TopicDetailRpcDTO topicDetailRpcDTO : topicDetailRpcDTOList) {
                try {
                    TopicDetail topicDetail = fittingTopicInfo(version, topicDetailRpcDTO);
                    if (Constant.XZS.equals(String.valueOf(topicDetailRpcDTO.getMemberType()))) {
                        topicDetail.setProjectName("#" + topicDetailRpcDTO.getMember().getMemo() + "#");//如果是邻聚小助手信息这里不显示社区名称
                        topicDetail.setCityName("");
                    }
                    list.add(topicDetail);
                } catch (Exception ex) {
                    logger.error("get topicDetail error, topicId:" + topicDetailRpcDTO.getId(), ex);
                }
            }
        }
        return list;
    }


    /**
     * 组装帖子内容
     *
     * @param version
     * @param topicDetailRpcDTO
     */
    private TopicDetail fittingTopicInfo(String version, TopicDetailRpcDTO topicDetailRpcDTO) throws Exception {

        TopicDetail topicDetail = transfor(TopicDetail.class, topicDetailRpcDTO);
        topicDetail.setIsPraise(topicDetailRpcDTO.getIsPraise());

        //装载标签活动信息 2.8版本
        if (QDStringUtil.isNotEmpty(topicDetailRpcDTO.getTagId()) && QDStringUtil.isNotEmpty(topicDetailRpcDTO.getTagName())) {
            if (version.compareTo("3.0.0") < 0 && (topicDetailRpcDTO.getTagName().equals("邻里杂谈") || topicDetailRpcDTO.getTagName().equals("二手置换"))) {
                //解决3.0.0版本下数据迁移后多出标签问题
            } else {
                ThemeTagInfo themeTagInfo = new ThemeTagInfo(topicDetailRpcDTO.getTagId(), topicDetailRpcDTO.getTagName(), topicDetailRpcDTO.getTagStatus());
                topicDetail.setTag(themeTagInfo);
            }
        }

        if (topicDetailRpcDTO.getTopicType().intValue() == Constant.TopicType.Activity.getValue().intValue() && QDStringUtil.isNotNull(topicDetailRpcDTO.getTopicStruct())) {
            ActivityTopicStruct activityTopicStruct = JSON.parseObject(topicDetailRpcDTO.getTopicStruct(), ActivityTopicStruct.class);
            ActivityInfo activityInfo = transfor(ActivityInfo.class, activityTopicStruct);
            transfor(activityInfo, topicDetailRpcDTO.getActivityStatistics()); //组装截止日期和报名上线数
            topicDetail.setActivityInfo(activityInfo);
            //根据当前用户实际情况来定
            topicDetail.getActivityInfo().setJoinStatus(topicDetailRpcDTO.getJoinStatus());

        } else if (topicDetailRpcDTO.getTopicType().intValue() == Constant.TopicType.Vote.getValue().intValue() && QDStringUtil.isNotNull(topicDetailRpcDTO.getTopicStruct())) {

            VoteTopicStruct voteTopicStruct = JSON.parseObject(topicDetailRpcDTO.getTopicStruct(), VoteTopicStruct.class);
            List<BriefVoteItem> voteItems = Lists.newArrayList();
            try {
                Map<String, Integer> voteOptionStatisticsMap = topicDetailRpcDTO.getVoteStatistics().getVoteStatisticsMap();
                voteItems = transforList(BriefVoteItem.class, voteTopicStruct.getVoteList());//组装投票选项
                for (BriefVoteItem voteItem : voteItems) {
                    voteItem.setVoteCount(voteOptionStatisticsMap.containsKey(voteItem.getIndex()) ? voteOptionStatisticsMap.get(voteItem.getIndex()) : 0);//组装投票选项的投票数
                }
            } catch (Exception e) {
                logger.error("获取投票统计信息报错", e);
            }

            topicDetail.getVoteInfo().setVoteList(voteItems);
            topicDetail.getVoteInfo().setEndTime(voteTopicStruct.getEndTime());
            topicDetail.getVoteInfo().setVoteStatus(topicDetailRpcDTO.getVoteStatistics().getVoteStatus());
            topicDetail.getVoteInfo().setJoinStatus(topicDetailRpcDTO.getJoinStatus());
            topicDetail.getVoteInfo().setSelOptionIndex(topicDetailRpcDTO.getSelOptionIndex());

        }

        //如果是营销帖
        if (topicDetailRpcDTO.getTopicType().intValue() == Constant.TopicType.MarketingUrl.getValue().intValue()) {
            if (QDStringUtil.isNotNull(topicDetailRpcDTO.getSkipUrl())) {
                BtnSkip btnSkipModel = new BtnSkip();
                btnSkipModel.setBtnName(topicDetailRpcDTO.getSkipButton());
                btnSkipModel.setSkipModel(skipMode.fittingSkipUrl(version, topicDetailRpcDTO.getSkipUrl(), 0, topicDetailRpcDTO.getId()));
                topicDetail.setBtnSkip(btnSkipModel);
            }
        }

        //如果是营销banner图
        if (topicDetailRpcDTO.getTopicType().intValue() == Constant.TopicType.MarketingBanner.getValue().intValue()) {
            BtnSkip btnSkipModel = new BtnSkip();
            btnSkipModel.setBtnName("");
            btnSkipModel.setSkipModel(skipMode.fittingSkipUrl(version, topicDetailRpcDTO.getSkipUrl(), 0, topicDetailRpcDTO.getId()));
            topicDetail.setBtnSkip(btnSkipModel);
        }

        //遍历评论列表，设定评论发布人或接收人是否是楼主
        if (CollectionUtils.isNotEmpty(topicDetail.getTopicCommentList())) {
            List<TopicComment> commentList = topicDetail.getTopicCommentList();
            for (TopicComment topicComment : commentList) {
                if (QDStringUtil.isNotEmpty(topicComment.getPid())) {
                    topicComment.getReceiveMember().setIsLz(topicDetailRpcDTO.getMemberId().equals(topicComment.getReceiveMember().getMemberId()) ? 1 : 0);
                }
                topicComment.getSendMember().setIsLz(topicDetailRpcDTO.getMemberId().equals(topicComment.getSendMember().getMemberId()) ? 1 : 0);
            }
        }

        return topicDetail;
    }


    /**
     * 批量获取会员信息
     *
     * @param mIdSet
     * @return
     */
    public List<MemberInfo> batchGetMembers(Set<String> mIdSet) {

        List<MemberInfo> list = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(mIdSet)) {
            GetMembersRequest memberRequest = new GetMembersRequest();
            memberRequest.setList(new ArrayList<>(mIdSet));
            GetMembersResponse memberResponse = profileAPI.getMembersByIds(memberRequest);
            list = memberResponse.getList();
        }
        return list;
    }


    /**
     * 组装用户点赞，评论消息列表
     *
     * @param notifyDTOList
     * @param requestMid
     * @return
     */
    private List<TopicNotify> getTopicNotifyList(List<TopicNotifyDTO> notifyDTOList, String requestMid) {

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
                BriefMember bRmember = new BriefMember();
                if (topicNoticy.getType() == 2) {
                    MemberInfo rMember = memberMap.get(topicNotifyDTO.getrMemberId());
                    bRmember = transfor(BriefMember.class, rMember);
                    bRmember.setUserId(topicNotifyDTO.getrUserId());
                    if (bRmember.getMemberId().equals(requestMid)) {
                        bRmember.setMemberName("我");
                    }
                }
                topicNoticy.setrMember(QDStringUtil.isNotNull(bRmember) ? bRmember : new BriefMember());//回复评论
                MemberInfo sMember = memberMap.get(topicNotifyDTO.getsMemberId());
                BriefMember bSmember = transfor(BriefMember.class, sMember);
                bSmember.setUserId(topicNotifyDTO.getsUserId());
                if (bSmember.getMemberId().equals(requestMid)) {
                    bSmember.setMemberName("我");
                }
                topicNoticy.setsMember(bSmember);//评论，点赞用户
                list.add(topicNoticy);
            } catch (Exception ex) {
                logger.error("getTopicNotifyList:", ex);
            }

        }
        return list;
    }


    @Deprecated
    protected <T extends ResponseData> Response<T> standardResponse(BaseResponse rpcResponse, T responseData) {
        Response<T> response = new Response<T>();
        int code = rpcResponse.getReturnInfo().getCode();

        if (code != 200) {
            responseData.setMessage(rpcResponse.getReturnInfo().getMessage());
        }
        response.setCode(code);
        response.setData(responseData);
        return response;
    }


}
