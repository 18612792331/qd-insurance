package com.qding.api.call.app.qding.v2_3_0;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.response.data.*;
import com.qding.api.call.app.qding.v2_3_0.struct.hotcycle.request.JoinGroupApplyRequest;
import com.qding.api.call.app.qding.v3_1_0.struct.user.response.data.GetProjectListResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.hotcycle.struct.request.*;
import com.qding.member.model.dto.MemberBindRoomDTO;
import com.qding.member.rpc.IRoomBindApplyRPC;
import com.qding.member.rpc.response.roombindapply.MemberBindRoomDTOsResponse;
import com.qding.neighbor.rpc.IGcRoomRpc;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.request.GetMemberByAccountIdRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

/**
 * Created by qd on 2015/10/23.
 */
public class CallHotcycle extends com.qding.api.call.app.qding.v2_1_0.CallHotcycle {

    @Autowired
    private IRoomBindApplyRPC roomBindApplyService;

    @Autowired
    private IGcRoomRpc gcRoomRpc;

    @Autowired
    private IProfileService profileAPI;


    /**
     * 加入群申请
     * @param request
     * @return
     */
    @HTTP(alias="joinGroupApply")
    public Response<JoinGroupApplyResponseData> joinGroupApply(JoinGroupApplyRequest request) {

        Response<JoinGroupApplyResponseData> response = new Response<JoinGroupApplyResponseData>();
        JoinGroupApplyResponseData data = new JoinGroupApplyResponseData();
        String memberId = "";
        String projectId = "";
        if (QDStringUtil.isNull(request.getMemberId())) {
            try {
                GetMemberByAccountIdRequest memberByAccountIdRequest = new GetMemberByAccountIdRequest();
                memberByAccountIdRequest.setAccountId(request.getUserId());
                GetMemberResponse getMemberResponse = profileAPI.getMemberByAccountId(memberByAccountIdRequest);
                checkAndContinue(getMemberResponse);
                memberId = getMemberResponse.getMemberInfo().getId();
            } catch (ServiceException e) {
                return handleException(JoinGroupApplyResponseData.class, e);
            }
        } else {
            memberId = request.getMemberId();
        }

        if (QDStringUtil.isNull(request.getProjectId())) {
            try {
                com.qding.neighbor.rpc.request.GetGcRoomDetailRequest gcRoomDetailRequest
                        = new com.qding.neighbor.rpc.request.GetGcRoomDetailRequest();
                gcRoomDetailRequest.setGcRoomId(request.getGcRoomId());
                com.qding.neighbor.rpc.response.data.GetGcRoomDetailResponse gcRoomDetailResponse = gcRoomRpc.getGcRoomInfo(gcRoomDetailRequest);
                checkAndContinue(gcRoomDetailResponse);
                com.qding.neighbor.dto.GcRoomInfoDetail gcRoomInfoDetail =  gcRoomDetailResponse.getGcRoomInfo();
                projectId = String.valueOf(gcRoomInfoDetail.getProjectId());
            } catch (ServiceException e) {
                return handleException(JoinGroupApplyResponseData.class, e);
            }
        } else {
            projectId = request.getProjectId();
        }

        try {
            MemberBindRoomDTOsResponse memberBindRoomDTOsResponse =  roomBindApplyService.findByMemberIdAndProjectId(memberId, projectId);
            checkAndContinue(memberBindRoomDTOsResponse);
            if (CollectionUtils.isNotEmpty(memberBindRoomDTOsResponse.getList())) {
                for (MemberBindRoomDTO memberBindRoomDTO : memberBindRoomDTOsResponse.getList()) {
                    short indentity = memberBindRoomDTO.getRole();
                    if (Constant.hk_indentity_apply_gcroom.contains(indentity)) {
                        try {
                            OptGroupMemberRequest optGroupMemberRequest = transfor(OptGroupMemberRequest.class,request);
                            optGroupMemberRequest.setOptAt(request.getOptName());
                            return joinGroupApplyForInner(optGroupMemberRequest);
                        }catch (Exception e) {
                            return handleException(JoinGroupApplyResponseData.class, e);
                        }
                    }
                }
                data.setMessage("您当前身份权限不能申请加入此群");
            } else {
                data.setMessage("请先绑定房屋再进行加群申请");
            }
        } catch (Exception e) {
            return handleException(JoinGroupApplyResponseData.class, e);
        }
        response.setData(data);
        response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
        return response;
    }

}
