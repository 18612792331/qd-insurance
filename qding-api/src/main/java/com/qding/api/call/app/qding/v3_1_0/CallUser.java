package com.qding.api.call.app.qding.v3_1_0;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.qding.api.call.app.qding.v3_1_0.struct.user.request.*;
import com.qding.api.struct.ResponseData;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.member.model.MemberRoomDto;
import com.qding.member.rpc.IMemberRoomRPC;
import com.qding.member.rpc.request.member.MemberRoomCondition;
import com.qding.member.rpc.response.memberroom.GetMemberRoomDtoResponse;
import com.qding.member.rpc.response.memberroom.GetMemberRoomResponse;
import com.qding.message.constant.MsgConstant;
import com.qding.message.service.IMessageService;
import com.qding.message.struct.request.DeleteTokenRequest;
import com.qding.message.struct.response.DeleteTokenResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v3_1_0.struct.user.ProjectDto;
import com.qding.api.call.app.qding.v3_1_0.struct.user.RoomDto;
import com.qding.api.call.app.qding.v3_1_0.struct.user.RoomGroupDto;
import com.qding.api.call.app.qding.v3_1_0.struct.user.response.data.DaiKouStatusResponseData;
import com.qding.api.call.app.qding.v3_1_0.struct.user.response.data.GetDaiKouConfResponseData;
import com.qding.api.call.app.qding.v3_1_0.struct.user.response.data.GetDaiKouRoomListResponseData;
import com.qding.api.call.app.qding.v3_1_0.struct.user.response.data.GetProjectListResponseData;
import com.qding.api.call.app.qding.v3_1_0.struct.user.response.data.GetRoomListResponseData;
import com.qding.api.imessage.IntegralMessage;
import com.qding.api.process.security.UserToken;
import com.qding.api.sms.SendSms;
import com.qding.api.sms.SmsAction;
import com.qding.api.struct.Response;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.api.util.ImageUtil;
import com.qding.api.util.QDMemberRemote;
import com.qding.api.util.SkipModeFitting;
import com.qding.api.verifycode.VerifyCode;
import com.qding.api.verifycode.VerifyCodeConfig;
import com.qding.api.verifycode.store.RedisStoreDevice;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.member.model.MemberRoom;
import com.qding.passport.service.IPassportService;
import com.qding.passport.service.IProfileService;
import com.qding.passport.service.new_version.INewPassportService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.request.UpdataPayStatusRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.passport.struct.response.UpdataPayStatusResponse;


/**
 * Created by qd on 2017/3/3.
 */
@ExplainAnnotation(explain = "用户信息模块")
public class CallUser extends com.qding.api.call.app.qding.v3_0_0.CallUser {

    private static final Logger logger = Logger.getLogger(CallUser.class);

    @Autowired
    private IProfileService profileAPI;

    @Autowired
    private IMemberRoomRPC memberRoomAPI;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private RoomReadRemote roomReadRemoteClient;

    @Autowired
    private IMessageService messageService;


    @ExplainAnnotation(explain = "当前账户业主身份绑定的房屋列表（房间列表）")
    @HTTP(alias = "getRoomList", isRequireAuth = true)
    public Response<GetRoomListResponseData> getRoomList(GetRoomListRequest request, UserToken userToken) {
        Response<GetRoomListResponseData> response = new Response<GetRoomListResponseData>();
        GetRoomListResponseData data = new GetRoomListResponseData();
        try {
            MemberRoomCondition memberRoomCondition = new MemberRoomCondition();
            memberRoomCondition.setMemberId(userToken.getMemberId());
            memberRoomCondition.setStatus(1);
            memberRoomCondition.setRole(1);
            GetMemberRoomResponse memberRoomResponse  = memberRoomAPI.page(memberRoomCondition, 1, Integer.MAX_VALUE);
            checkAndContinue(memberRoomResponse);
            List<MemberRoom> lsMemberRoom = memberRoomResponse.getList();
            Integer total = memberRoomResponse.getTotalRow();
            if (total == null) {
                total = 0;
            }
            data.setTotalNum(total);
            data.setPageSize(lsMemberRoom.size());
            List<RoomGroupDto> list = new ArrayList<RoomGroupDto>();
            Map<String, List<RoomDto>> group = new HashMap<String, List<RoomDto>>();

            if (lsMemberRoom != null && lsMemberRoom.size() > 0) {

                List<Long> roomIdList = Lists.newArrayList();
                for (MemberRoom memberRoom : lsMemberRoom) {
                    roomIdList.add(Long.parseLong(memberRoom.getRoomId()));
                }

                if (CollectionUtils.isNotEmpty(roomIdList)) {
                    List<Room> roomList = roomReadRemoteClient.getRooms(roomIdList);
                    for (Room room : roomList) {
                        if (room == null) {
                            continue;
                        }
                        RoomDto dto = new RoomDto();
                        dto.setRoomId(String.valueOf(room.getId()));
                        dto.setRoomDes((StringUtils.isNotBlank(room.getGroupName())? room.getGroupName()+"-" : "")
                                + room.getBuildingName()+"-"+room.getName());
                        //dto.setRoomDes(room.getBuildingName() + room.getName());
                        if (group.containsKey(String.valueOf(room.getProjectId()))) {
                            group.get(String.valueOf(room.getProjectId())).add(dto);
                        } else {
                            List<RoomDto> listsub = new ArrayList<RoomDto>();
                            listsub.add(dto);
                            group.put(String.valueOf(room.getProjectId()), listsub);
                        }
                    }
                }

                //填充社区名称
                if (!group.isEmpty()) {
                    for (String projectId : group.keySet()) {
                        com.qding.brick.pojo.biz.Project p = projectReadService.get(Long.parseLong(projectId));
                        if (p == null) {
                            logger.warn("过滤掉--------" + projectId);
                            continue;
                        }
                        RoomGroupDto projectGroup = new RoomGroupDto();
                        projectGroup.setProjectName(p.getName());
                        projectGroup.setProjectId(String.valueOf(p.getId()));
                        projectGroup.setCityId(String.valueOf(p.getRegionId()));
                        projectGroup.setCityName(p.getRegionName());
                        projectGroup.setList(group.get(projectId));
                        list.add(projectGroup);
                    }
                }
            }
            data.setList(list);
        } catch (ServiceException e) {
            return handleException(GetRoomListResponseData.class, e);
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }

    @ExplainAnnotation(explain = "获取会员是否开通物业代扣")
    @HTTP(alias = "getDaiKouBymid", isRequireAuth = true)
    public Response<GetDaiKouConfResponseData> getDaiKouBymid(GetDaiKouConfRequest request, UserToken userToken) {
        Response<GetDaiKouConfResponseData> response = new Response<GetDaiKouConfResponseData>();
        GetDaiKouConfResponseData data = new GetDaiKouConfResponseData();
        try {
            GetMemberRequest req = new GetMemberRequest();
            req.setMemberId(userToken.getMemberId());
            GetMemberResponse res = profileAPI.getMemberById(req);
            checkAndContinue(res);
            if (res.getMemberInfo() != null) {
                data.setStatus(res.getMemberInfo().getPayStatus());
            } else {
                data.setStatus(0);
            }
        } catch (ServiceException e) {
            return handleException(GetDaiKouConfResponseData.class, e);
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }

    @ExplainAnnotation(explain = "指定用户指定城市满足物业代扣的社区列表")
    @HTTP(alias = "getProjectList", isRequireAuth = true)
    public Response<GetProjectListResponseData> getProjectList(GetProjectListRequest request, UserToken userToken) {
        Response<GetProjectListResponseData> response = new Response<GetProjectListResponseData>();
        GetProjectListResponseData data = new GetProjectListResponseData();
        try {
            Integer pageNo = request.getPageNo();
            Integer pageSize = request.getPageSize();
            if (pageNo == null) {
                pageNo = 0;
            }
            if (pageSize == null || pageSize == 0) {
                pageSize = 100;
            }
            MemberRoomCondition memberRoomCondition = new MemberRoomCondition();
            memberRoomCondition.setMemberId(userToken.getMemberId());
            memberRoomCondition.setStatus(1);
            List<Integer> roleList = Lists.newArrayList();
            roleList.add(1);
            roleList.add(2);
            roleList.add(4);
            memberRoomCondition.setRoleIds(roleList);
            GetMemberRoomDtoResponse memberRoomDtoResponse = memberRoomAPI.getProjectListPage(memberRoomCondition, pageNo, pageSize);
            checkAndContinue(memberRoomDtoResponse);
            List<MemberRoomDto> lsMemberRoom = memberRoomDtoResponse.getList();
            Integer total = memberRoomDtoResponse.getTotalRow();
            if (total == null) {
                total = 0;
            }
            data.setTotalNum(total);
            Set<String> listProjectId = new HashSet<String>();
            List<ProjectDto> list = new ArrayList<ProjectDto>();
            if (lsMemberRoom != null && lsMemberRoom.size() > 0) {
                for (MemberRoomDto room : lsMemberRoom) {
                    if (listProjectId.contains(room.getProjectId())) {
                    } else {
                        listProjectId.add(room.getProjectId());
                        com.qding.brick.pojo.biz.Project p = projectReadService.get(Long.parseLong(room.getProjectId()));
                        if (p.getIsOpenFee() == null || p.getIsOpenFee() != 1) {
                            continue;
                        }
                        ProjectDto d = new ProjectDto();
                        d.setProjectName(p.getName());
                        d.setCityName(p.getRegionName());
                        d.setProjectId(room.getProjectId());
                        list.add(d);
                    }

                }
                data.setList(list);
            }
        } catch (ServiceException e) {
            return handleException(GetProjectListResponseData.class, e);
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }

    @ExplainAnnotation(explain = "指定用户，指定社区下（业主、家庭成员、租客角色）下支持物业费代扣的房屋列表")
    @HTTP(alias = "getDaiKouRoomList", isRequireAuth = true)
    public Response<GetDaiKouRoomListResponseData> getDaiKouRoomList(GetDaiKouRoomListRequest request, UserToken userToken) {
        Response<GetDaiKouRoomListResponseData> response = new Response<GetDaiKouRoomListResponseData>();
        GetDaiKouRoomListResponseData data = new GetDaiKouRoomListResponseData();

        try {
            MemberRoomCondition memberRoomCondition = new MemberRoomCondition();
            memberRoomCondition.setMemberId(userToken.getMemberId());
            memberRoomCondition.setStatus(1);
            memberRoomCondition.setProjectId(Long.parseLong(request.getProjectId()));
            List<Integer> roleList = Lists.newArrayList();
            roleList.add(1);
            roleList.add(2);
            roleList.add(4);
            memberRoomCondition.setRoleIds(roleList);

            GetMemberRoomResponse memberRoomResponse = memberRoomAPI.page(memberRoomCondition, 0, 1000);
            checkAndContinue(memberRoomResponse);
            List<MemberRoom> lsMemberRoom = memberRoomResponse.getList();
            Set<String> listRoom = new HashSet<String>();
            List<RoomDto> list = new ArrayList<RoomDto>();
            if (lsMemberRoom != null && lsMemberRoom.size() > 0) {
                for (MemberRoom room : lsMemberRoom) {
                    if (listRoom.contains(room.getRoomId())) {
                    } else {
                        listRoom.add(room.getRoomId());
                        com.qding.brick.pojo.biz.Room room1 = roomReadRemoteClient.get(Long.parseLong(room.getRoomId()));
                        if (room1 == null) {
                            continue;
                        }
                        RoomDto dto = new RoomDto();
                        dto.setRoomId(room.getRoomId());
                        dto.setRoomDes((StringUtils.isNotBlank(room1.getGroupName())? room1.getGroupName()+"-" : "")
                                + room1.getBuildingName()+"-"+room1.getName());
                        list.add(dto);
                    }

                }
                data.setList(list);
            }
        } catch (ServiceException e) {
            return handleException(GetDaiKouRoomListResponseData.class, e);
        }
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }

    @ExplainAnnotation(explain = "物业费代扣状态维护")
    @HTTP(alias = "editDaiKouStatus", isRequireAuth = true)
    public Response<DaiKouStatusResponseData> editDaiKouStatus(DaiKouStatusRequest request, UserToken userToken) {

        Response<DaiKouStatusResponseData> response = new Response<DaiKouStatusResponseData>();
        try {
            GetMemberRequest memberRequest = new GetMemberRequest();
            memberRequest.setMemberId(userToken.getMemberId());
            GetMemberResponse memberResponse = profileAPI.getMemberById(memberRequest);
            checkAndContinue(memberResponse);
            //1--------------------------------开通验证码校验
            if (request.getPayStatus() == 1) {
                VerifyCode.verifyCode(
                        new VerifyCodeConfig(
                                SmsAction.OPEN_PROPERTY_FEE_PAY_VERIFY,
                                request.getCheckCode(),
                                memberResponse.getMemberInfo().getMobile(),
                                new RedisStoreDevice()
                        )
                );
            }
            //2-------------------------------
            UpdataPayStatusRequest updataPayStatusRequest = new UpdataPayStatusRequest();
            updataPayStatusRequest.setMemberId(userToken.getMemberId());
            updataPayStatusRequest.setPayStatus(request.getPayStatus());
            UpdataPayStatusResponse updataPayStatusResponse = profileAPI.modifyPayStatus(updataPayStatusRequest);
            checkAndContinue(updataPayStatusResponse);
            //如果开通代扣成功，发送短信提醒
            if (request.getPayStatus().intValue() == 1) {
                if (HttpStatus.OK.getStatusCode() == memberResponse.getReturnInfo().getCode()) {
                    MemberInfo memberInfo = memberResponse.getMemberInfo();
                    if (QDStringUtil.isNotNull(memberInfo) && QDStringUtil.isNotEmpty(memberInfo.getMobile())) {
                        Map<String, String> data = new HashMap<>();
                        data.put("nickName", memberInfo.getName());
                        SendSms sendSms = ApplicationContextUtil.getBeansOfType(SendSms.class);
                        sendSms.send(SmsAction.OPEN_PROPERTY_FEE_PAY, memberInfo.getMobile(), data);
                    }
                }
            }
            response.setCode(HttpStatus.OK.getStatusCode());
        } catch (ServiceException e) {
            return handleException(DaiKouStatusResponseData.class, e);
        }
        return response;
    }

    @ExplainAnnotation(explain = "登出")
    @HTTP(alias = "loginOut", isRequireAuth = true)
    public Response<ResponseData> hkLoginOut(BaseRequest request, UserToken userToken) {

        Response<ResponseData> response = new Response<ResponseData>();
        response.setCode(HttpStatus.OK.getStatusCode());
        try {
            String accountId = userToken.getAccountId();
            DeleteTokenRequest deleteTokenRequest = new DeleteTokenRequest(accountId);
            deleteTokenRequest.setAppType(MsgConstant.APP_TYPE_QDING);
            DeleteTokenResponse deleteTokenResponse = messageService.deleteToken(deleteTokenRequest);
            checkAndContinue(deleteTokenResponse);
        } catch (ServiceException e) {
            return handleException(ResponseData.class, e);
        }
        return response;
    }


}
