package com.qding.api.call.app.qding.v1_4_1;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.*;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.request.*;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.request.GroupMessageSettingRequest;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.request.JoinGroupApplyListRequest;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.request.ReportGroupRequest;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.response.data.*;
import com.qding.api.struct.Response;
import com.qding.api.util.SkipModeFitting;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.groupchat.remote.groupchat.IGcRoomRemote;
import com.qding.groupchat.struct.groupchat.bean.GetGroupInfoBean;
import com.qding.groupchat.struct.groupchat.request.GetGroupInfoRequest;
import com.qding.groupchat.struct.groupchat.response.GetGroupInfoResponse;
import com.qding.hotcycle.service.IGCMemberRemoteService;
import com.qding.hotcycle.service.IGCRoomRemoteService;
import com.qding.hotcycle.struct.request.*;
import com.qding.hotcycle.struct.response.*;
import com.qding.hotcycle.struct.response.GetGcRoomResponse;
import com.qding.neighbor.rpc.IGCMemberRpc;
import com.qding.neighbor.rpc.IGcRoomRpc;
import com.qding.neighbor.v3.rpc.ITopicRpcV3;
import com.qding.neighbor.v3.rpc.requst.CheckActivityIsEndRequest;
import com.qding.neighbor.v3.rpc.response.data.CheckActivityIsEndResponseData;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.request.GetMemberByAccountIdRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qd on 2015/10/23.
 */
public class CallHotcycle extends com.qding.api.call.app.qding.v1_3_2.CallHotcycle {


    @Autowired
    private IGCMemberRemoteService igcMemberRemoteService;

    @Autowired
    private IGCMemberRpc gcMemberRpc;

    @Autowired
    private IGCRoomRemoteService roomRemoteService;

    @Autowired
    private IGcRoomRpc gcRoomRpc;

    @Autowired
    private SkipModeFitting skipMode;

    @Autowired
    private ITopicRpcV3 topicRpcV3;

    @Autowired
    private IProfileService profileService;

    @Autowired
    private IGcRoomRemote gcRoomRemoteService;


    /**
     * 邻聚社区首页接口（社区公共群列表，兴趣群列表）
     *
     * @param request
     * @return
     */
    @HTTP(alias = "hotcycleHomePage")
    @Deprecated
    public Response<HotcycleHomePageResponseData> hotcycleHomePage(HotcycleHomePageRequest request) {

        try {

            Response<HotcycleHomePageResponseData> response = new Response<HotcycleHomePageResponseData>();

            response.setCode(HttpStatus.OK.getStatusCode());

            GetGcRoomRequest getGcRoomRequest = transfor(GetGcRoomRequest.class, request);

            GetGcRoomResponse getGcRoomResponse = roomRemoteService.hotcycleHomePage(getGcRoomRequest);

            checkAndContinue(getGcRoomResponse);

            HotcycleHomePageResponseData data = transfor(HotcycleHomePageResponseData.class, getGcRoomResponse);

            response.setData(data);

            return response;

        } catch (Exception ex) {

            return handleException(HotcycleHomePageResponseData.class, ex);
        }

    }

    /**
     * 群管理列表 (加群申请列表，群成员列表)
     *
     * @param request
     * @return
     */
    @HTTP(alias = "groupManagerList")
    public Response<GroupManagerListResponseData> groupManagerList(GroupManagerListRequest request) {

        Response<GroupManagerListResponseData> response = new Response<GroupManagerListResponseData>();

        try {
            response.setCode(HttpStatus.OK.getStatusCode());

            GetMemberByAccountIdRequest getMemberByAccountIdRequest = new GetMemberByAccountIdRequest();
            getMemberByAccountIdRequest.setAccountId(request.getUserId());
            GetMemberResponse getMemberResponse = profileService.getMemberByAccountId(getMemberByAccountIdRequest);
            String mid = getMemberResponse.getMemberInfo().getId();

            com.qding.neighbor.rpc.request.GetGroupMemberListRequest groupMemberListRequest =
                    transfor(com.qding.neighbor.rpc.request.GetGroupMemberListRequest.class, request);

            groupMemberListRequest.setStatus(3);//已是会员
            groupMemberListRequest.setMemberId(mid);
            com.qding.neighbor.rpc.response.data.GetGroupMemberListResponse getGroupMemberListResponse
                    = gcMemberRpc.getsGroupMember(groupMemberListRequest);

            checkAndContinue(getGroupMemberListResponse);

            Long memberCount = getGroupMemberListResponse.getTotalCount();

            GroupUserList memberList = new GroupUserList();

            if (memberCount > 0) {

                List<GroupUser> groupUserList = transforList(GroupUser.class, getGroupMemberListResponse.getList());

                memberList.setList(groupUserList);

            } else {
                memberList.setList(new ArrayList<GroupUser>());

            }
            memberList.setTotalCount(memberCount);

            groupMemberListRequest.setStatus(1);//待审核

            groupMemberListRequest.setPageSize(100);

            com.qding.neighbor.rpc.response.data.GetGroupMemberListResponse getGroupApplyListResponse = gcMemberRpc.getsGroupMember(groupMemberListRequest);

            checkAndContinue(getGroupApplyListResponse);

            long applyCount = getGroupApplyListResponse.getTotalCount();

            JoinGroupApplyUserList applyList = new JoinGroupApplyUserList();

            if (applyCount > 0) {

                List<JoinGroupApplyUser> joinGroupApplyUserList = transforList(JoinGroupApplyUser.class, getGroupApplyListResponse.getList());

                applyList.setList(joinGroupApplyUserList);

            } else {

                applyList.setList(new ArrayList<JoinGroupApplyUser>());
            }
            applyList.setTotalCount(getGroupApplyListResponse.getTotalCount());

            String version = request.getAppDevice().getQdVersion();
            String initVersion = skipMode.initVersion(version);
            Integer gcRoomType = 0;

            //2.6版本需要知道当前群属于什么类型
            if (Integer.parseInt(initVersion) >= 260000) {
                com.qding.neighbor.rpc.request.GetGcRoomDetailRequest gcRoomDetailRequest = new com.qding.neighbor.rpc.request.GetGcRoomDetailRequest();
                gcRoomDetailRequest.setGcRoomId(request.getGcRoomId());
                com.qding.neighbor.rpc.response.data.GetGcRoomDetailResponse gcRoomDetailResponse = gcRoomRpc.getGcRoomInfo(gcRoomDetailRequest);
                checkAndContinue(gcRoomDetailResponse);
                gcRoomType = gcRoomDetailResponse.getGcRoomInfo().getType();
            }

            GroupManagerListResponseData data = new GroupManagerListResponseData();
            data.setCanQuitGroup(1);
            //判断该讨论组对应的活动是否已结束
            CheckActivityIsEndRequest checkActivityIsEndRequest = new CheckActivityIsEndRequest();
            checkActivityIsEndRequest.setTopicId(request.getGcRoomId());
            CheckActivityIsEndResponseData checkActivityIsEndResponseData = topicRpcV3.checkActivityIsEnd(checkActivityIsEndRequest);
            if (HttpStatus.OK.getStatusCode() == checkActivityIsEndResponseData.getReturnInfo().getCode()) {
                data.setCanQuitGroup(checkActivityIsEndResponseData.getIsEnd());
            }
            data.setGcRoomType(gcRoomType);
            data.setApplyList(applyList);
            data.setMemberList(memberList);
            response.setData(data);

        } catch (Exception ex) {
            return handleException(GroupManagerListResponseData.class, ex);
        }
        return response;

    }


    /**
     * 多个群里的加群申请
     *
     * @param request
     * @return
     */
    @HTTP(alias = "joinGroupApplyList")
    public Response<JoinGroupApplyListResponseData> joinGroupApplyList(JoinGroupApplyListRequest request) {

        Response<JoinGroupApplyListResponseData> response = new Response<JoinGroupApplyListResponseData>();

        response.setCode(HttpStatus.OK.getStatusCode());

        try {
            com.qding.neighbor.rpc.response.data.JoinGroupApplyListResponse joinGroupApplyListResponse = null;
            com.qding.neighbor.rpc.request.JoinGroupApplyListRequest joinGroupApplyListRequest = transfor(com.qding.neighbor.rpc.request.JoinGroupApplyListRequest.class, request);
            if (request.getAppDevice().getQdVersion().compareTo("2.6.0") >= 0) {//判断是否为新版邻聚
                joinGroupApplyListRequest.setNewVersion(true);
            }
            joinGroupApplyListResponse = gcMemberRpc.joinGroupApplyList(joinGroupApplyListRequest);
            checkAndContinue(joinGroupApplyListResponse);

            JoinGroupApplyListResponseData data = transfor(JoinGroupApplyListResponseData.class, joinGroupApplyListResponse);

            response.setData(data);


        } catch (Exception ex) {

            return handleException(JoinGroupApplyListResponseData.class, ex);
        }

        return response;
    }

    /**
     * 加入群申请
     *
     * @param request
     * @return
     */
    @HTTP(alias = "joinGroupApply")
    @Deprecated
    public Response<JoinGroupApplyResponseData> joinGroupApply(JoinGroupApplyRequest request) {

        try {
            OptGroupMemberRequest optGroupMemberRequest = transfor(OptGroupMemberRequest.class, request);
            return joinGroupApplyForInner(optGroupMemberRequest);
        } catch (Exception e) {
            return handleException(JoinGroupApplyResponseData.class, e);
        }

    }


    public Response<JoinGroupApplyResponseData> joinGroupApplyForInner(OptGroupMemberRequest optGroupMemberRequest) throws Exception {

        Response<JoinGroupApplyResponseData> response = new Response<JoinGroupApplyResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        BaseResponse baseResponse = igcMemberRemoteService.joinGroupApply(optGroupMemberRequest);
        checkAndContinue(baseResponse);
        response.setData(new JoinGroupApplyResponseData());
        return response;
    }

    /**
     * 群申请通过
     *
     * @param request
     * @return
     */
    @HTTP(alias = "passGroupApply")
    public Response<PassGroupApplyResponseData> passGroupApply(PassGroupApplyRequest request) {

        Response<PassGroupApplyResponseData> response = new Response<PassGroupApplyResponseData>();
        try {
            response.setCode(HttpStatus.OK.getStatusCode());
            BaseResponse baseResponse = gcMemberRpc.passGroupApply(transfor(com.qding.neighbor.rpc.request.OptGroupMemberRequest.class, request));
            checkAndContinue(baseResponse);
            PassGroupApplyResponseData data = new PassGroupApplyResponseData();
            response.setData(data);
            return response;
        } catch (Exception e) {
            return handleException(PassGroupApplyResponseData.class, e);
        }

    }

    /**
     * 忽略申请
     *
     * @param request
     * @return
     */
    @HTTP(alias = "rejectGroupApple")
    public Response<RejectGroupAppleResponseData> rejectGroupApple(RejectGroupApplyRequest request) {

        Response<RejectGroupAppleResponseData> response = new Response<RejectGroupAppleResponseData>();

        try {
            response.setCode(HttpStatus.OK.getStatusCode());

            BaseResponse baseResponse = gcMemberRpc.rejectGroupApple(transfor(com.qding.neighbor.rpc.request.OptGroupMemberRequest.class, request));

            checkAndContinue(baseResponse);

            RejectGroupAppleResponseData data = new RejectGroupAppleResponseData();

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(RejectGroupAppleResponseData.class, e);
        }
    }


    /**
     * 移出群
     *
     * @param request
     * @return
     */
    @HTTP(alias = "shiftOutGroup")
    public Response<ShiftOutGroupResponseData> shiftOutGroup(ShiftOutGroupRequest request) {

        Response<ShiftOutGroupResponseData> response = new Response<ShiftOutGroupResponseData>();

        try {
            response.setCode(HttpStatus.OK.getStatusCode());

            BaseResponse baseResponse = gcMemberRpc.shiftOutGroup(transfor(com.qding.neighbor.rpc.request.OptGroupMemberRequest.class, request));

            checkAndContinue(baseResponse);

            ShiftOutGroupResponseData data = new ShiftOutGroupResponseData();

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(ShiftOutGroupResponseData.class, e);
        }
    }

    /**
     * 退出群
     *
     * @param request
     * @return
     */
    @HTTP(alias = "quitGroup")
    @Deprecated
    public Response<QuitGroupResponseData> quitGroup(QuitGroupRequest request) {

        Response<QuitGroupResponseData> response = new Response<QuitGroupResponseData>();

        try {
            response.setCode(HttpStatus.OK.getStatusCode());

            BaseResponse baseResponse = igcMemberRemoteService.quitGroup(transfor(OptGroupMemberRequest.class, request));

            checkAndContinue(baseResponse);

            QuitGroupResponseData data = new QuitGroupResponseData();

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(QuitGroupResponseData.class, e);
        }
    }

    /**
     * 举报群
     *
     * @param request
     * @return
     */
    @HTTP(alias = "reportGroup")
    @Deprecated
    public Response<ReportGroupResponseData> reportGroup(ReportGroupRequest request) {

        Response<ReportGroupResponseData> response = new Response<ReportGroupResponseData>();

        try {
            response.setCode(HttpStatus.OK.getStatusCode());

            BaseResponse baseResponse = igcMemberRemoteService.reportGroup(transfor(com.qding.hotcycle.struct.request.ReportGroupRequest.class, request));

            checkAndContinue(baseResponse);

            ReportGroupResponseData data = new ReportGroupResponseData();

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(ReportGroupResponseData.class, e);
        }
    }

    /**
     * 屏蔽/接收群消息
     *
     * @param request
     * @return
     */
    @HTTP(alias = "groupMessageSetting")
    public Response<GroupMessageSettingResponseData> groupMessageSetting(GroupMessageSettingRequest request) {

        Response<GroupMessageSettingResponseData> response = new Response<GroupMessageSettingResponseData>();

        try {
            response.setCode(HttpStatus.OK.getStatusCode());

            BaseResponse baseResponse = gcMemberRpc.groupMessageSetting(transfor(com.qding.neighbor.rpc.request.GroupMessageSettingRequest.class, request));

            checkAndContinue(baseResponse);

            GroupMessageSettingResponseData data = new GroupMessageSettingResponseData();

            response.setData(data);

            return response;

        } catch (Exception e) {

            return handleException(GroupMessageSettingResponseData.class, e);
        }
    }

    /**
     * 兴趣群列表
     *
     * @param request
     * @return
     */
    @HTTP(alias = "interestGroupList")
    public Response<InterestGroupListResponseData> interestGroupList(InterestGroupListRequest request) {

        try {

            Response<InterestGroupListResponseData> response = new Response<InterestGroupListResponseData>();

            response.setCode(HttpStatus.OK.getStatusCode());

            InterestGroupListResponseData data = new InterestGroupListResponseData();

            com.qding.neighbor.rpc.response.data.GetGcRoomInterestResponse getGcRoomInterestResponse
                    = gcRoomRpc.interestGroupList(transfor(com.qding.neighbor.rpc.request.GetGcRoomByPageRequest.class, request));

            checkAndContinue(getGcRoomInterestResponse);

            List<UserGroup> groupList = transforList(UserGroup.class, getGcRoomInterestResponse.getInterestGroupList());

            data.setTotalCount(getGcRoomInterestResponse.getTotalCount());

            data.setList(groupList);

            response.setData(data);

            return response;

        } catch (Exception ex) {

            return handleException(InterestGroupListResponseData.class, ex);
        }

    }


    /**
     * 新建群公告
     *
     * @param request
     * @return
     */
    @HTTP(alias = "newGroupNotice")
    public Response<NewGroupNoticeResponseData> newGroupNotice(NewGroupNoticeRequest request) {

        try {

            Response<NewGroupNoticeResponseData> response = new Response<NewGroupNoticeResponseData>();

            response.setCode(HttpStatus.OK.getStatusCode());

            com.qding.neighbor.rpc.response.data.AddGcBillboardResponse addGcBillboardResponse
                    = gcRoomRpc.newGroupNotice(transfor(com.qding.neighbor.rpc.request.AddGcBillboardRequest.class, request));

            checkAndContinue(addGcBillboardResponse);

            NewGroupNoticeResponseData data = new NewGroupNoticeResponseData();

            response.setData(data);

            return response;

        } catch (Exception ex) {

            return handleException(NewGroupNoticeResponseData.class, ex);
        }

    }

    /**
     * 群公告列表
     *
     * @param request
     * @return
     */
    @HTTP(alias = "groupNoticeList")
    public Response<GroupNoticeListResponseData> groupNoticeList(GroupNoticeListRequest request) {

        try {
            Integer gcRoomType = 0;
            Response<GroupNoticeListResponseData> response = new Response<GroupNoticeListResponseData>();
            response.setCode(HttpStatus.OK.getStatusCode());
            String version = request.getAppDevice().getQdVersion();
            String initVersion = skipMode.initVersion(version);
            //2.6版本需要知道当前群属于什么类型
            if (Integer.parseInt(initVersion) >= 260000) {
                com.qding.neighbor.rpc.request.GetGcRoomDetailRequest gcRoomDetailRequest = new com.qding.neighbor.rpc.request.GetGcRoomDetailRequest();
                gcRoomDetailRequest.setGcRoomId(request.getGcRoomId());
                com.qding.neighbor.rpc.response.data.GetGcRoomDetailResponse gcRoomDetailResponse = gcRoomRpc.getGcRoomInfo(gcRoomDetailRequest);
                checkAndContinue(gcRoomDetailResponse);
                gcRoomType = gcRoomDetailResponse.getGcRoomInfo().getType();
            }
            com.qding.neighbor.rpc.response.data.GetGcBillboardResponse getGcBillboardResponse
                    = gcRoomRpc.groupNoticeList(transfor(com.qding.neighbor.rpc.request.GetGcBillboardByPageRequest.class, request));
            checkAndContinue(getGcBillboardResponse);
            GroupNoticeListResponseData data = transfor(GroupNoticeListResponseData.class, getGcBillboardResponse);
            data.setGcRoomType(gcRoomType);
            List<Notice> list = transforList(Notice.class, getGcBillboardResponse.getList());

            data.setList(list);

            response.setData(data);

            return response;

        } catch (Exception ex) {
            return handleException(GroupNoticeListResponseData.class, ex);
        }

    }


    /**
     * 群公告详情
     *
     * @param request
     * @return
     */
    @HTTP(alias = "groupNoticeDetail")
    @Deprecated
    public Response<GroupNoticeDetailResponseData> groupNoticeDetail(GroupNoticeDetailRequest request) {

        try {
            Response<GroupNoticeDetailResponseData> response = new Response<GroupNoticeDetailResponseData>();

            response.setCode(HttpStatus.OK.getStatusCode());

            GetGcBillboardDetailResponse getGcBillboardDetailResponse = roomRemoteService.groupNoticeDetail(transfor(GetGcBillboardDetailRequest.class, request));

            checkAndContinue(getGcBillboardDetailResponse);

            GroupNoticeDetailResponseData data = transfor(GroupNoticeDetailResponseData.class, getGcBillboardDetailResponse);

            response.setData(data);

            return response;

        } catch (Exception ex) {

            return handleException(GroupNoticeDetailResponseData.class, ex);
        }

    }

    /**
     * 删除群公告
     *
     * @param request
     * @return
     */
    @HTTP(alias = "delGroupNotice")
    public Response<GroupNoticeDelResponseData> delGroupNotice(GroupNoticeDelRequest request) {

        try {
            Response<GroupNoticeDelResponseData> response = new Response<GroupNoticeDelResponseData>();

            response.setCode(HttpStatus.OK.getStatusCode());

            com.qding.neighbor.rpc.response.data.DeleteGcBillboardResponse deleteGcBillboardResponse
                    = gcRoomRpc.delGroupNotice(transfor(com.qding.neighbor.rpc.request.DeleteGcBillboardRequest.class, request));

            checkAndContinue(deleteGcBillboardResponse);

            response.setData(new GroupNoticeDelResponseData());

            return response;

        } catch (Exception ex) {

            return handleException(GroupNoticeDelResponseData.class, ex);
        }

    }

    /**
     * 通过群组ID获取群组详情信息
     *
     * @param request
     * @return
     */
    @HTTP(alias = "getGcRoomInfo")
    public Response<GetGcRoomInfoResponseData> getGcRoomInfo(GetGcRoomInfoRequest request) {

        Response<GetGcRoomInfoResponseData> response = new Response<GetGcRoomInfoResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            Group entity = null;
            if (request.getGcRoomId().startsWith("TL_")) {
                GetGroupInfoRequest groupInfoRequest = new GetGroupInfoRequest();
                groupInfoRequest.setGroupId(request.getGcRoomId());
                GetGroupInfoResponse groupInfoResponse = gcRoomRemoteService.getGroupChatInfo(groupInfoRequest);
                checkAndContinue(groupInfoResponse);
                GetGroupInfoBean getGroupInfoBean = groupInfoResponse.getGetGroupInfoBean();
                entity = transfor(Group.class, getGroupInfoBean);
                entity.setGcMemberStatus(3);
                entity.setType(5);//旅游
            } else {
                com.qding.neighbor.rpc.request.GetGcRoomDetailRequest gcRoomDetailRequest
                        = transfor(com.qding.neighbor.rpc.request.GetGcRoomDetailRequest.class, request);
                com.qding.neighbor.rpc.response.data.GetGcRoomDetailResponse gcRoomDetailResponse
                        = gcRoomRpc.getGcRoomInfo(gcRoomDetailRequest);
                checkAndContinue(gcRoomDetailResponse);
                entity = transfor(Group.class, gcRoomDetailResponse.getGcRoomInfo());
            }

            GetGcRoomInfoResponseData data = new GetGcRoomInfoResponseData();
            data.setEntity(entity);
            response.setData(data);
            return response;

        } catch (Exception e) {
            return handleException(GetGcRoomInfoResponseData.class, e);
        }

    }

}
