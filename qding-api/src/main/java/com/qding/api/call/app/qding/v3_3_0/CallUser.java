package com.qding.api.call.app.qding.v3_3_0;

import com.alibaba.fastjson.JSON;
import com.github.knightliao.apollo.utils.time.DateUtils;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v3_1_0.struct.user.RoomDto;
import com.qding.api.call.app.qding.v3_1_0.struct.user.request.GetDaiKouRoomListRequest;
import com.qding.api.call.app.qding.v3_1_0.struct.user.response.data.GetDaiKouRoomListResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.user.AutoPayProject;
import com.qding.api.call.app.qding.v3_3_0.struct.user.request.GetAutoPayPropertyFeeProjectsByMidRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.user.request.GetHobbyConfigRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.user.request.GetRelNameStatusRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.user.request.LoginEtcpRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.user.request.StartupPageRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.user.request.UpdateMemberAutoPayPropertyFeeStatusRequest;
import com.qding.api.call.app.qding.v3_3_0.struct.user.response.data.GetAutoPayPropertyFeeProjectsByMidResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.user.response.data.GetHobbyConfigResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.user.response.data.GetRelNameStatusResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.user.response.data.LoginEtcpResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.user.response.data.StartPageResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.user.response.data.UpdateMemberAutoPayPropertyFeeStatusResponseData;
import com.qding.api.call.service.MemberService;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.rongcloud.util.CodeUtil;
import com.qding.api.struct.Response;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.EtcpUtil;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.brick.struts.request.ProjectRequest;
import com.qding.brick.struts.response.ProjectReadResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.manager.service.IHkCommonRPCService;
import com.qding.member.model.MemberRoomDto;
import com.qding.member.rpc.IMemberRoomRPC;
import com.qding.member.rpc.request.member.MemberRoomCondition;
import com.qding.member.rpc.response.memberroom.GetMemberRoomDtoResponse;
import com.qding.member.service.IMemberRpcService;
import com.qding.member.sync.dto.MemberPayAccountInfoDto;
import com.qding.member.sync.rpc.IRpcMemberService;
import com.qding.newcore.common.util.QDStringUtil;
import com.qding.passport.domain.EtcpAuthorizationLog;
import com.qding.passport.service.IAuthInfoService;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.profee.rpc.response.fee.WithholdProjectResponse;
import com.qding.profee.rpc.service.IFeeRpcService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by qd on 2017/7/12.
 */
public class CallUser extends com.qding.api.call.app.qding.v3_2_0.CallUser {

    private static final Logger logger = Logger.getLogger(CallUser.class);

    @Autowired
    IMemberRpcService memberRpcService;

    @Autowired
    private ProjectReadRemote projectReadService;

    @Autowired
    private IMemberRoomRPC memberRoomAPI;

    @Autowired
    IHkCommonRPCService commonRPCService;

    @Autowired
    private IFeeRpcService feeService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private RoomReadRemote roomReadRemoteClient;

    @Autowired
    private IProfileService profileAPI;

    @Autowired
    IAuthInfoService authInfoService;

    @Autowired
    IRpcMemberService rpcMemberService;

    @ExplainAnnotation(explain = "通用设置->启动页开启关闭")
    @HTTP(alias = "startupPage", isNeadToken = true, isRequireAuth = true)
    public Response<StartPageResponseData> startupPage(StartupPageRequest request, UserToken userToken) {
        Response<StartPageResponseData> response = new Response<StartPageResponseData>();
        StartPageResponseData data = new StartPageResponseData();
        try {
            memberRpcService.updateStartPageStatus(userToken.getMemberId(), request.getStatus());
            response.setCode(HttpStatus.OK.getStatusCode());
        } catch (Exception ex) {
            return handleException(StartPageResponseData.class, ex);
        }
        response.setData(data);
        return response;

    }


    @ExplainAnnotation(explain = "用户偏好设置，比如启动页开启关闭，云对讲设置，是否接受短信等")
    @HTTP(alias = "getHobbyConfig", isNeadToken = true, isRequireAuth = true)
    public Response<GetHobbyConfigResponseData> getHobbyConfig(GetHobbyConfigRequest request, UserToken userToken) {
        Response<GetHobbyConfigResponseData> response = new Response<GetHobbyConfigResponseData>();
        GetHobbyConfigResponseData data = new GetHobbyConfigResponseData();
        try {
            int status = memberRpcService.getStartPageStatus(userToken.getMemberId());
            data.setStatus(status);
            response.setCode(HttpStatus.OK.getStatusCode());
        } catch (Exception ex) {
            return handleException(GetHobbyConfigResponseData.class, ex);
        }
        response.setData(data);
        return response;

    }

    @Deprecated
    @ExplainAnnotation(explain = "获取指定用户物业费自动代扣的社区列表")
    @HTTP(alias = "getAutoPayPropertyFeeProjectsByMid", isNeadToken = true, isRequireAuth = true)
    public Response<GetAutoPayPropertyFeeProjectsByMidResponseData> getAutoPayPropertyFeeProjectsByMid(GetAutoPayPropertyFeeProjectsByMidRequest request, UserToken userToken) {

        Response<GetAutoPayPropertyFeeProjectsByMidResponseData> response = new Response<GetAutoPayPropertyFeeProjectsByMidResponseData>();
        GetAutoPayPropertyFeeProjectsByMidResponseData data = new GetAutoPayPropertyFeeProjectsByMidResponseData();

        List<AutoPayProject> list = Lists.newArrayList();
        try {
            Integer pageNo = 0;
            Integer pageSize = 100;

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
            Set<Long> projectIdSet = new HashSet<>();
            if (CollectionUtils.isNotEmpty(lsMemberRoom)) {
                for (MemberRoomDto memberRoom : lsMemberRoom) {
                    projectIdSet.add(Long.parseLong(memberRoom.getProjectId()));
                }
            }

            ProjectRequest projectRequest = new ProjectRequest();
            projectRequest.setIdSet(projectIdSet);
            ProjectReadResponse projectResposne = projectReadService.getProjectsByRequest(projectRequest);
            List<Project> projectList = projectResposne.getProjects();
            for (Project project : projectList) {
                if (project.getIsOpenFee() == null || project.getIsOpenFee() != 1) { //是否开通缴费通径
                    continue;
                }
                WithholdProjectResponse withholdProjectResponse = feeService.getProjectWithholdStatus(project.getId());
                if (withholdProjectResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode() && 1 == withholdProjectResponse.getStatus().intValue()) {
                    AutoPayProject autoPayProject = new AutoPayProject();
                    autoPayProject.setProjectName(project.getName());
                    autoPayProject.setCityName(project.getRegionName());
                    autoPayProject.setProjectId(String.valueOf(project.getId()));
//                    autoPayProject.setOpenedStatus(1);
                    list.add(autoPayProject);
                }
            }
        } catch (ServiceException e) {
            return handleException(GetAutoPayPropertyFeeProjectsByMidResponseData.class, e);
        }
        return response;
    }


    @ExplainAnnotation(explain = "指定用户，指定社区下（业主、家庭成员、租客角色）下物业费代扣的房屋列表", desc = "已开通|未开通")
    @HTTP(alias = "getAutoPayPropertyFeeRoomsByMid", isRequireAuth = true, isNeadToken = true)
    @Deprecated
    public Response<GetDaiKouRoomListResponseData> getAutoPayPropertyFeeRoomsByMid(GetDaiKouRoomListRequest request, UserToken userToken) {

        Response<GetDaiKouRoomListResponseData> response = new Response<GetDaiKouRoomListResponseData>();
        GetDaiKouRoomListResponseData data = new GetDaiKouRoomListResponseData();
        List<com.qding.member.model.MemberRoom> rpcRoomList = memberService.getRoomsByMultiRole(userToken.getMemberId(), request.getProjectId(), Constant.AUTOPAY_ROLE_LIST, false);
        List<Long> roomIds = Lists.newArrayList();
        for (com.qding.member.model.MemberRoom memberRoom : rpcRoomList) {
            roomIds.add(Long.parseLong(memberRoom.getRoomId()));
        }
        List<Room> roomList = roomReadRemoteClient.getRooms(roomIds);
        List<RoomDto> list = Lists.newArrayList();
        for (Room room : roomList) {
            RoomDto dto = new RoomDto();
            dto.setRoomId(String.valueOf(room.getId()));
            dto.setRoomDes((StringUtils.isNotBlank(room.getGroupName()) ? room.getGroupName() + "-" : "")
                    + room.getBuildingName() + "-" + room.getName());
            list.add(dto);
        }
        data.setList(list);

        return response;

    }


    @Deprecated
    @ExplainAnnotation(explain = "物业费代扣状态维护")
    @HTTP(alias = "updateMemberAutoPayPropertyFeeStatus", isRequireAuth = true, isNeadToken = true)
    public Response<UpdateMemberAutoPayPropertyFeeStatusResponseData> updateMemberPayStatus(UpdateMemberAutoPayPropertyFeeStatusRequest request, UserToken userToken) {

        Response<UpdateMemberAutoPayPropertyFeeStatusResponseData> response = new Response<UpdateMemberAutoPayPropertyFeeStatusResponseData>();
        UpdateMemberAutoPayPropertyFeeStatusResponseData data = new UpdateMemberAutoPayPropertyFeeStatusResponseData();
        return response;
    }


    @ExplainAnnotation(explain = "查询实名认证状态")
    @HTTP(alias = "getRelNameStatus")
    public Response<GetRelNameStatusResponseData> getRelNameStatus(GetRelNameStatusRequest request) {
    	logger.info("GetRelNameStatusRequest"+request+"----"+request.getMemberId());
        GetMemberRequest getMemberRequest = new GetMemberRequest();
        getMemberRequest.setMemberId(request.getMemberId());
        GetMemberResponse getMemberResponse = profileAPI.getMemberById(getMemberRequest);
        logger.info("getMemberResponse=" + getMemberResponse);
        String memberNo = null;
        if (getMemberResponse != null && getMemberResponse.getMemberInfo() != null && StringUtils.isNotBlank(getMemberResponse.getMemberInfo().getMemberNo())) {
            memberNo = getMemberResponse.getMemberInfo().getMemberNo();
        }
        String relNameStatus = rpcMemberService.getRealNameStatus(memberNo);
        GetRelNameStatusResponseData data = new GetRelNameStatusResponseData();
        data.setRelNameStatus(relNameStatus);

        //用户认证成功之后才去查询对应的用户状态
        if(StringUtils.isNotEmpty(relNameStatus) && "S".equals(relNameStatus)){
        	MemberPayAccountInfoDto memberPayAccountInfoDto = rpcMemberService.getMemberPayAccountInfo(memberNo);
            if(QDStringUtil.isNotNull(memberPayAccountInfoDto) && QDStringUtil.isNotEmpty(memberPayAccountInfoDto.getActSts())  ){
                data.setActSts(memberPayAccountInfoDto.getActSts());
            }
        }

        Response<GetRelNameStatusResponseData> response = new Response<GetRelNameStatusResponseData>();
        response.setData(data);
        response.setCode(HttpStatus.OK.getStatusCode());
        return response;
    }

    @HTTP(alias = "loginEtcp", isNeadToken = true, isRequireAuth = true)
    public Response<LoginEtcpResponseData> loginEtcp(LoginEtcpRequest request, UserToken userToken) {
        Response<LoginEtcpResponseData> respon = new Response<LoginEtcpResponseData>();
        LoginEtcpResponseData data1 = new LoginEtcpResponseData();
        respon.setData(data1);
        HttpURLConnection conn = null;
        try {
            GetMemberRequest getMemberRequest = new GetMemberRequest();
            getMemberRequest.setMemberId(userToken.getMemberId());
            GetMemberResponse getMemberResponse = profileAPI.getMemberById(getMemberRequest);
            checkAndContinue(getMemberResponse);
            String mobile = getMemberResponse.getMemberInfo().getMobile();
            String mid = getMemberResponse.getMemberInfo().getId();
            EtcpAuthorizationLog log = authInfoService.getEtcpAuthorizationLog(mid);
            if (request.getState() == 0) {
                //判断是否授权
                if (log == null) {
                    data1.setAuthorization(0);
                    return respon;
                }
            } else if (request.getState() == 1) {
                if (log == null) {
                    authInfoService.addEtcpAuthorizationLog(mid, mobile);
                }
            } else {
                //不支持的请求
                respon.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                return respon;
            }
            String url = APIPropertiesClient.getUrlContent("etcp.url");
            String path = APIPropertiesClient.getUrlContent("etcp.path");
            String merchant_no = APIPropertiesClient.getUrlContent("etcp.merchant_no");
            String merchant_key = APIPropertiesClient.getUrlContent("etcp.merchant_key");
            String time_stamp = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
            String plateNumber = "";
            conn = EtcpUtil.CreatePostHttpConnection(url + path);
            String data = "{\"merchantNo\":\":" + merchant_no + "\",\"mobile\":\"" + mobile + "\",\"plateNumber\":\"" + plateNumber + "\"}";
            String sign = CodeUtil.md5(data + merchant_key + time_stamp).toUpperCase();
            StringBuilder sb = new StringBuilder();
            sb.append("merchant_no=").append(URLEncoder.encode(merchant_no, "UTF-8"));
            sb.append("&time_stamp=").append(URLEncoder.encode(time_stamp, "UTF-8"));
            sb.append("&data=").append(URLEncoder.encode(data, "UTF-8"));
            sb.append("&sign=").append(URLEncoder.encode(sign, "UTF-8"));
            logger.info("etcp url=" + url + path);
            logger.info("etcp param=" + sb.toString());
            EtcpUtil.setBodyParameter(sb, conn);
            String str = EtcpUtil.getResult(conn);
            logger.info("etcp result=" + str);
            Gson gson = new Gson();
            EtcpResponse res = gson.fromJson(str, EtcpResponse.class);
            if (res.getCode() == 0) {
                data1.setAuthorization(1);//授权
                data1.setRedirectUrl(URLDecoder.decode(res.getData().getRedirectUrl(), "UTF-8"));
                respon.setCode(HttpStatus.OK.getStatusCode());
                return respon;
            }
            respon.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
        } catch (Exception ex) {
            return handleException(LoginEtcpResponseData.class, ex);
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return respon;

    }

    class EtcpResponse {

        int code;

        String message;

        Data data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public Data getData() {
            return data;
        }

        public void setData(Data data) {
            this.data = data;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }


    }

    class Data {

        String authCode;
        String redirectUrl;

        public String getAuthCode() {
            return authCode;
        }

        public void setAuthCode(String authCode) {
            this.authCode = authCode;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }

        public void setRedirectUrl(String redirectUrl) {
            this.redirectUrl = redirectUrl;
        }


    }


}
