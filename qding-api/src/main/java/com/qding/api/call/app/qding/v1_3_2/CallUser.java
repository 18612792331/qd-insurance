package com.qding.api.call.app.qding.v1_3_2;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_2.struct.user.AuditFailInfo;
import com.qding.api.call.app.qding.v1_3_2.struct.user.PapersBindRoom;
import com.qding.api.call.app.qding.v1_3_2.struct.user.request.*;
import com.qding.api.call.app.qding.v1_3_2.struct.user.response.data.*;
import com.qding.api.call.app.qding.v1_3_2.struct.user.request.GetPassFreeBarrierRequest;
import com.qding.api.call.app.qding.v1_3_2.struct.user.request.SetPassFreeBarrierStatusRequest;
import com.qding.api.constant.Constant;
import com.qding.api.imessage.IntegralMessage;
import com.qding.api.imessage.IntegralMessageBeanT;
import com.qding.api.ip.IPUtil;
import com.qding.api.ip.TaoBaoCity;
import com.qding.api.sms.SmsAction;
import com.qding.api.struct.Response;
import com.qding.api.verifycode.RestCodeConfig;
import com.qding.api.verifycode.VerifyCode;
import com.qding.api.verifycode.VerifyCodeConfig;
import com.qding.api.verifycode.send.SmsSendCannel;
import com.qding.api.verifycode.store.RedisStoreDevice;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.hk.domain.PassFreeBarrier;
import com.qding.hk.rpc.request.pass.PassFreeBarrierRequest;
import com.qding.hk.rpc.response.pass.PassFreeBarrierResponse;
import com.qding.hk.rpc.service.IPassFreeBarrierRpcService;
import com.qding.member.model.RoomBindApply;
import com.qding.member.model.dto.apply.ImageDTO;
import com.qding.member.model.dto.apply.ImageDetailDTO;
import com.qding.member.model.dto.apply.RoomBindApplyDTO;
import com.qding.member.rpc.IMemberProjectRPC;
import com.qding.member.rpc.IRoomBindApplyRPC;
import com.qding.member.rpc.response.project.MemberProjectResponse;
import com.qding.member.rpc.response.roombindapply.RoomBindApplyDTOResponse;
import com.qding.member.service.*;
import com.qding.passport.service.IPassportService;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.*;
import com.qding.passport.struct.response.*;
import com.qding.popularize.remote.MemberPopularizeRemoteService;
import com.qding.popularize.struct.request.PopularizeLoginRequest;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class CallUser extends com.qding.api.call.app.qding.v1_3_0.CallUser {

	public CallUser() {}

	@Autowired
	private IPassportService passportAPI;


	@Autowired
	private MemberPopularizeRemoteService irMemberPopularizeService;

	@Autowired
	private IMemberProjectRPC memberProjectService;

	@Autowired
	private IRoomBindApplyRPC roomBindApplyService;

	@Autowired
	private RoomReadRemote roomReadRemoteService;

	@Autowired
	private IProfileService profileAPI;

	@Autowired
	private ProjectReadRemote projectReadService;

	@Autowired
	private IntegralMessage integralMessage;

	@Autowired
	private IPassFreeBarrierRpcService passFreeBarrierRpc;

	/**
	 * 登录
	 * @param request
	 * @return
	 */
	@HTTP(alias = "loginByMobile",isLogin=true,isNeedSign = false)
	@Deprecated
	public Response<UserLoginResponseData> loginByMobile(UserLoginRequest request, HttpServletRequest httpServletRequest) {

		try {
			Response<UserLoginResponseData> response = new Response<UserLoginResponseData>();

			LoginRequest loginRequest = transfor(LoginRequest.class,request );

			String ip = IPUtil.getIpAddress(httpServletRequest);

			TaoBaoCity city = IPUtil.getIpCity(ip);

			loginRequest.setLoginIp(ip);

			loginRequest.setProvinceName(city.getProvince());

			loginRequest.setCityName(city.getCity());

            if(!QDStringUtil.isMobile(request.getMobile())){
                //手机号码无效
                response.setCode(205);
                UserLoginResponseData data = new UserLoginResponseData();
                data.setMessage("手机号码无效");
                response.setData(data);
                return response;
            }

			LoginResponse loginResponse = passportAPI.loginByMobile(loginRequest);

            //新加code=204:手机号不存在 ; code=400:密码错误
            response.setCode(loginResponse.getReturnInfo().getCode());

            UserLoginResponseData data = transfor(UserLoginResponseData.class, loginResponse);

			checkAndContinue(loginResponse);

			PopularizeLoginRequest popularizeLoginRequest = new PopularizeLoginRequest();

			MemberInfo member = loginResponse.getMemberInfo();

			popularizeLoginRequest.setMemberId(member.getId());

			popularizeLoginRequest.setProjectId(request.getProjectId());

			popularizeLoginRequest.setIp(ip);

			BaseResponse baseResponse = irMemberPopularizeService.login(popularizeLoginRequest);

			if (HttpStatus.OK.getStatusCode()==baseResponse.getReturnInfo().getCode()){
				IntegralMessageBeanT integralMessageBeanT = new IntegralMessageBeanT(member.getId(), Constant.INTEGRAL_LOGIN,member.getId(), QDStringUtil.isNotNull(request.getProjectId())?Long.parseLong(request.getProjectId()):null, System.currentTimeMillis(), Constant.INCOME_OPT_TYPE, null,member.getId());
				integralMessage.assembleIntegralMessage(integralMessageBeanT);
			}
			String version = request.getAppDevice().getQdVersion();

			String userToken = afterLogin(data.getEntity(),version);

			data.setBaseToken(userToken);

			data.getEntity().setImToken( loginResponse.getAccountInfo().getRongCloudToken());

			response.setData(data);

			return response;

		} catch (Exception e) {

			return handleException(UserLoginResponseData.class, e);
		}
	}



	/**
	 * 重置服务器存储的短信验证码（用于上行短信验证码发送模式）
	 * @param request
	 * @return
	 */
	@HTTP(alias = "resetVerificationCode")
	public Response<ResetVerificationCodeResponseData> resetVerificationCode (ResetVerificationCodeRequest request) {

		Response<ResetVerificationCodeResponseData> response = new  Response<ResetVerificationCodeResponseData>();

		String mobile = request.getMobile();

		String code = request.getVerifyCode();

		Integer action = request.getAction();

		SmsAction smsAction = SmsAction.to(action);

		try {
			VerifyCode.resetCode(
                    new RestCodeConfig(
                            System.currentTimeMillis() + 100 * 1000,
                            smsAction,
							code,
                            new RedisStoreDevice(),
                            new SmsSendCannel(mobile, smsAction),
                            10 * 60
                    )
            );

			ResetVerificationCodeResponseData data = new ResetVerificationCodeResponseData();

			response.setCode(HttpStatus.OK.getStatusCode());

			response.setData(data);

			return response;

		} catch (ServiceException e) {

			return handleException(ResetVerificationCodeResponseData.class, e);
		}

	}

	/**
	 * 验证重置验证码是否成功
	 * @param request
	 * @return
	 */
	@HTTP(alias = "checkVerifyCodeIsReset")
	public Response<CheckVerifyCodeIsResetResponseData> checkVerifyCodeIsReset (CheckVerifyCodeIsResetRequest request) {

		Response<CheckVerifyCodeIsResetResponseData> response = new Response<CheckVerifyCodeIsResetResponseData>();

		CheckVerifyCodeIsResetResponseData data = new CheckVerifyCodeIsResetResponseData();

		data.setIsRest(0);

		response.setCode(HttpStatus.OK.getStatusCode());

		String mobile = request.getMobile();

		String code = request.getVerifyCode();

		Integer action = request.getAction();

		try {
			SmsAction smsAction = SmsAction.to(action);

			if(smsAction != null) {
				boolean isRest = VerifyCode.checkVerifyCodeIsReset(
						new VerifyCodeConfig(
								smsAction,
								code,
								mobile,
								new RedisStoreDevice()
						)
				);
				data.setIsRest(isRest ? 1 : 0);
			}
		} catch (ServiceException e) {

			e.printStackTrace();
		}
		response.setData(data);
 		return response;
	}

	/**
	 * 绑定社区
	 * @param request
	 * @return
	 */
	@HTTP(alias = "bindProject")
	public Response<BindProjectResponseData> bindProject (BindProjectRequest request) {

		try {
			Response<BindProjectResponseData> response = new Response<BindProjectResponseData>();

			response.setCode(HttpStatus.OK.getStatusCode());

		     //用户绑定社区
			if(request.getMemberId() != null) {

				Integer sourceType = Constant.transforPlatformByAppDevice(Constant.registerSourcreMap, request.getAppDevice());

				if (QDStringUtil.isNull(sourceType)) {

					throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "未知设备");

				}

				MemberProjectResponse responseMap = memberProjectService.save(request.getMemberId(), request.getProjectId(), sourceType.shortValue());

				checkAndContinue(responseMap);

			  }
			   return  response;
			}
		 catch (ServiceException e) {

			return handleException(BindProjectResponseData.class, e);
		}

	}

	/**
	 * 证件上传绑定房屋
	 * @param request
	 * @return
	 */

	@HTTP(alias = "papersBindRoom", isRequireAuth=true)
	public Response<PapersBindRoomResponseData> papersBindRoom (PapersBindRoomRequest request) {

		Response<PapersBindRoomResponseData> response = new Response<PapersBindRoomResponseData>();

		response.setCode(HttpStatus.OK.getStatusCode());

		PapersBindRoomResponseData data = new PapersBindRoomResponseData();

		String bindId = request.getBindId();

		ImageDetailDTO imageDetailDTO = setImageDetailDTO( request.getFrontIdentityCard(),request.getReverseIdentityCard(),request.getRoomPapers());
		try {
			if (  QDStringUtil.isNotEmpty(bindId) ) { //修改

				BaseResponse baseResponse = roomBindApplyService.updateRoomBindApply(bindId,imageDetailDTO);

				checkAndContinue(baseResponse);

			} else {

					com.qding.brick.pojo.biz.Room room = roomReadRemoteService.get(Long.parseLong(request.getRoomId()));

					GetMemberRequest getMemberRequest = transfor(GetMemberRequest.class, request);

					GetMemberResponse getMemberResponse = profileAPI.getMemberById(getMemberRequest);

					checkAndContinue(getMemberResponse);

					Project project = projectReadService.get(room.getProjectId());

					MemberInfo member = getMemberResponse.getMemberInfo();

					RoomBindApply roomBindApply = new RoomBindApply();
					roomBindApply.setCityId(String.valueOf(project.getRegionId()));
					roomBindApply.setCityName(project.getRegionName());
					roomBindApply.setMemberId(member.getId());
					roomBindApply.setMemberName(member.getName());
					roomBindApply.setMobile(member.getMobile());
					roomBindApply.setProjectId(String.valueOf(room.getProjectId()));
					roomBindApply.setProjectName(room.getProjectName());
					roomBindApply.setRole(request.getHkIndentity());
					roomBindApply.setRoomId(request.getRoomId());
					roomBindApply.setRoomName((QDStringUtil.isNotNull(room.getGroupName())?room.getGroupName():"") + (QDStringUtil.isNotNull(room.getBuildingName())?room.getBuildingName():"")+
							(QDStringUtil.isNotNull(room.getName())? room.getName():""));
					RoomBindApplyDTO roomBindApplyDTO = new RoomBindApplyDTO();
					roomBindApplyDTO.setImageDetailDTO(imageDetailDTO);
					roomBindApplyDTO.setRoomBindApply(roomBindApply);

					BaseResponse baseResponse = roomBindApplyService.addRoomBindApply(roomBindApplyDTO);

					checkAndContinue(baseResponse);

			}

		} catch (ServiceException e) {
			return handleException(PapersBindRoomResponseData.class, e);
		}

		response.setData(data);

		return response;

	}

	//构建ImageDetailDTO
	private   ImageDetailDTO  setImageDetailDTO( String aImage,String bImage,String roomImage){

		ImageDetailDTO imageDetailDTO = new ImageDetailDTO();
		ImageDTO aImageDTO = new ImageDTO();
		aImageDTO.setImageUrl(aImage);
		imageDetailDTO.setIdAImage(aImageDTO);

		ImageDTO bImageDTO = new ImageDTO();
		bImageDTO.setImageUrl(bImage);
		imageDetailDTO.setIdBImage(bImageDTO);

		ImageDTO roomImageDTO = new ImageDTO();
		roomImageDTO.setImageUrl(roomImage);
		imageDetailDTO.setRoomImage(roomImageDTO);

		return imageDetailDTO;
	}

	/**
	 * 检查证件绑定房屋的审核状态
	 * @param request
	 * @return
	 */
	@HTTP(alias = "checkPapersBindRoom", isRequireAuth=true)
	public Response<CheckPapersBindRoomResponseData> checkPapersBindRoom (CheckPapersBindRoomRequest request) {

		Response<CheckPapersBindRoomResponseData> response = new  Response<CheckPapersBindRoomResponseData>();

		CheckPapersBindRoomResponseData data = new CheckPapersBindRoomResponseData();

		response.setCode(HttpStatus.OK.getStatusCode());



		try {
			RoomBindApplyDTOResponse roomBindApplyDTOResponse = roomBindApplyService.findByApplyId(request.getBindId());
			checkAndContinue(roomBindApplyDTOResponse);
			RoomBindApply roomBindApply = roomBindApplyDTOResponse.getRoomBindApplyDTO().getRoomBindApply();
			ImageDetailDTO imageDetailDTO =  roomBindApplyDTOResponse.getRoomBindApplyDTO().getImageDetailDTO();

			AuditFailInfo auditFailInfo = new AuditFailInfo();

			if (2 == roomBindApply.getStatus()) {

				auditFailInfo.setFrontIdentityCardMsg(QDStringUtil.isNull(imageDetailDTO.getIdAImage().getImageDesc())||"".equals(imageDetailDTO.getIdAImage().getImageDesc())?"OK":imageDetailDTO.getIdAImage().getImageDesc());

				auditFailInfo.setReverseIdentityCardMsg(QDStringUtil.isNull(imageDetailDTO.getIdBImage().getImageDesc())||"".equals(imageDetailDTO.getIdBImage().getImageDesc()) ? "OK" : imageDetailDTO.getIdBImage().getImageDesc());

				auditFailInfo.setRoomPapersMsg(QDStringUtil.isNull(imageDetailDTO.getRoomImage().getImageDesc())||"".equals(imageDetailDTO.getRoomImage().getImageDesc())?"OK":imageDetailDTO.getRoomImage().getImageDesc());
			}
			com.qding.brick.pojo.biz.Room room = roomReadRemoteService.get(Long.parseLong(roomBindApply.getRoomId()));

			com.qding.api.call.app.qding.v1_3_0.struct.brick.Room r =
					transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, room);

			PapersBindRoom entity = new PapersBindRoom(roomBindApply.getId(),r, roomBindApply.getMobile(),
					imageDetailDTO.getRoomImage().getImageUrl(),
					imageDetailDTO.getIdAImage().getImageUrl(), imageDetailDTO.getIdBImage().getImageUrl(),
					roomBindApply.getStatus(),auditFailInfo,roomBindApply.getRole());

			data.setEntity(entity);
		} catch (ServiceException e) {
			return handleException(CheckPapersBindRoomResponseData.class, e);
		}

		response.setData(data);

		return  response;

	}




	@HTTP(alias = "getPassFreeBarrier", isRequireAuth = true)
	@ExplainAnnotation(explain = "获取当前用户是否有无感知门禁权限")
	public Response<GetPassFreeBarrierResponseData> getPassFreeBarrier(GetPassFreeBarrierRequest request) {
		PassFreeBarrierRequest passFreeBarrierRequest = transfor(PassFreeBarrierRequest.class, request);
		passFreeBarrierRequest.setMid(request.getMemberId());

		Response<GetPassFreeBarrierResponseData> response = new Response<GetPassFreeBarrierResponseData>();
		GetPassFreeBarrierResponseData data = new GetPassFreeBarrierResponseData();

		try {
			PassFreeBarrierResponse passFreeBarrierResponse = passFreeBarrierRpc.getPassFreeBarrierByMid(passFreeBarrierRequest);
			checkAndContinue(passFreeBarrierResponse);
			PassFreeBarrier passFreeBarrier = passFreeBarrierResponse.getPassFreeBarrier();
			if (QDStringUtil.isNull(passFreeBarrier)) {
				data.setPermissionStatus(0);
				data.setSelfStatus(0);
			} else {
				data.setPermissionStatus(1);
				data.setSelfStatus(passFreeBarrier.getStatus());
			}

			response.setData(data);
			response.setCode(HttpStatus.OK.getStatusCode());

		} catch (ServiceException e) {
			return handleException(GetPassFreeBarrierResponseData.class, e);
		}

		return response;

	}


	@HTTP(alias = "setPassFreeBarrierStatus", isRequireAuth = true)
	@ExplainAnnotation(explain = "设置当前用户是否打开无感知门禁功能", desc = "")
	public Response<SetPassFreeBarrierStatusResponseData> setPassFreeBarrierStatus(SetPassFreeBarrierStatusRequest request) {

		PassFreeBarrierRequest passFreeBarrierRequest = transfor(PassFreeBarrierRequest.class, request);
		passFreeBarrierRequest.setMid(request.getMemberId());

		Response<SetPassFreeBarrierStatusResponseData> response = new Response<SetPassFreeBarrierStatusResponseData>();
		SetPassFreeBarrierStatusResponseData data = new SetPassFreeBarrierStatusResponseData();

		try {
			PassFreeBarrierResponse passFreeBarrierResponse = passFreeBarrierRpc.togglePassFreeBarrierStatus(passFreeBarrierRequest);
			checkAndContinue(passFreeBarrierResponse);
			PassFreeBarrier passFreeBarrier = passFreeBarrierResponse.getPassFreeBarrier();
			if (QDStringUtil.isNull(passFreeBarrier)) {
				data.setPermissionStatus(0);
				data.setSelfStatus(0);
			} else {
				data.setPermissionStatus(1);
				data.setSelfStatus(passFreeBarrier.getStatus());
			}

			response.setData(data);
			response.setCode(HttpStatus.OK.getStatusCode());

		} catch (ServiceException e) {
			return handleException(SetPassFreeBarrierStatusResponseData.class, e);
		}

		return response;
	}
}
