package com.qding.api.call.app.qding.v1_3_0;

import java.text.SimpleDateFormat;
import java.util.*;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.notify.request.SwitchProjectRequest;
import com.qding.api.struct.ResponseData;
import com.qding.api.util.APIPropertiesClient;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomOwnerRemote;
import com.qding.brick.struts.request.GetOwnerRoomsRequest;
import com.qding.brick.struts.response.GetOwnerRoomsResponse;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.hk.rpc.request.pass.PassFreeBarrierRequest;
import com.qding.hk.rpc.service.IPassFreeBarrierRpcService;
import com.qding.member.common.constant.MemberRole;
import com.qding.member.rpc.IMemberProjectRPC;
import com.qding.member.rpc.IMemberRoomRPC;
import com.qding.member.rpc.request.member.BindRoom;
import com.qding.member.rpc.request.member.MemberRoomSyncRequest;
import com.qding.message.constant.MsgConstant;
import com.qding.message.struct.request.MsgSendByMidRequest;
import com.qding.message.struct.response.MsgSendResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v1_3_0.struct.notify.LegouNotify;
import com.qding.api.call.app.qding.v1_3_0.struct.notify.request.BindNotifyTokenRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.notify.request.DeleteNotifiesRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.notify.request.GetLegouNotifiesRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.notify.response.data.BindNotifyTokenResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.notify.response.data.DeleteNotifiesResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.notify.response.data.GetLegouNotifiesResponseData;
import com.qding.api.struct.Response;
import com.qding.framework.common.api.struct.AppDevice;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.message.domain.MsgMessage;
import com.qding.message.service.IMessageService;
import com.qding.message.struct.request.BindTokenRequest;
import com.qding.message.struct.request.MsgInfoRequest;
import com.qding.message.struct.response.BindTokenResponse;
import com.qding.message.struct.response.MsgInfoResponse;
import com.qding.message.util.Page;
import com.qding.passport.service.IPassportService;
import com.qding.passport.struct.AccountInfo;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetAccountRequest;
import com.qding.passport.struct.response.GetAccountResponse;

/**
 * APP 消息通知
 * @author lichao
 *
 */
public class CallNotify extends Callable{
	private static final Logger logger = Logger.getLogger(CallNotify.class);

	@Autowired
	private IMessageService messageService;
	
	@Autowired
	private IMemberProjectRPC memberProjectService;

	@Autowired
	private IPassportService passportService;

	@Autowired
	private IPassFreeBarrierRpcService passFreeBarrierRpc;

	@Autowired
	private ProjectReadRemote projectReadRemote;

	@Autowired
	private IMemberRoomRPC memberRoomAPI;

	@Autowired
	private RoomOwnerRemote roomOwnerRemoteService;


	/**
	 * 绑定设备token
	 * @param request
	 * @return
     */
	@HTTP(alias="bindNotifyToken")
	public Response<BindNotifyTokenResponseData> bindNotifyToken(BindNotifyTokenRequest request) {

		Response<BindNotifyTokenResponseData> response = new Response<>();

		try {
			AppDevice appDevice = request.getAppDevice();
			
			if(appDevice == null) {
				
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "缺少appDevice");
			}
			
			String qdPlatform = appDevice.getQdPlatform();
			
			int mobileOsType = "android".equalsIgnoreCase(qdPlatform) ? 1 : 
				("ios".equalsIgnoreCase(qdPlatform) ? 2 : 
					("wp".equalsIgnoreCase(qdPlatform) ? 3 : -1)
				);
			
			if(mobileOsType == -1) {
				
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "未知设备");

			}
			
			// mobileOsType : 1 android 2 ios 3 wp
			//appType : "qding":千丁APP  "guanjia": 管家APP
			BindTokenRequest bindTokenRequest = new BindTokenRequest(
					"qding",
					request.getAccountId(), 
					request.getProjectId(), 
					request.getDeviceUniqueToken(), 
					String.valueOf(mobileOsType),
					request.getAppDevice().getQdVersion(),
					request.getDeviceType()
					);

			GetAccountResponse getAccountResponse = passportService.getAccountByAccountId(transfor(GetAccountRequest.class, request));

			checkAndContinue(getAccountResponse);

			GetAccountResponse getAccountResponse2 = passportService.getAppAccountByMobile(getAccountResponse.getMemberInfo().getMobile());

			if (getAccountResponse2.getReturnInfo().getCode() != HttpStatus.OK.getStatusCode()) {
				response.setData(new BindNotifyTokenResponseData());
				response.setCode(HttpStatus.OK.getStatusCode());
				return response;
			}
//			checkAndContinue(getAccountResponse2);

			String accountId = getAccountResponse2.getAccountInfo().getId();

			bindTokenRequest.setUserId(accountId);

			BindTokenResponse bindTokenResponse = messageService.bindToken(bindTokenRequest);

			checkAndContinue(bindTokenResponse);

			MemberInfo memberInfo = getAccountResponse.getMemberInfo();
			
			AccountInfo accountInfo = getAccountResponse.getAccountInfo();
			
			//用户绑定社区
			if(memberInfo != null) {

				bindMemberProject(memberInfo,accountInfo,request.getProjectId());

			}
			response.setData(new BindNotifyTokenResponseData());
			
			return response;
		} catch (Exception e) {

			return handleException(BindNotifyTokenResponseData.class, e);
		}
	}

	/**
	 * 切换社区 2.0版本
	 * @param request
	 * @return
     */
	@HTTP(alias = "switchProject")
	@ExplainAnnotation(explain = "切换社区")
	public Response<ResponseData> switchProject (SwitchProjectRequest request) {

		Response<ResponseData> response = new  Response<ResponseData>();
		response.setCode(HttpStatus.OK.getStatusCode());
		try {

			GetAccountResponse getAccountResponse = passportService.getAccountByAccountId(transfor(GetAccountRequest.class, request));
			checkAndContinue(getAccountResponse);
			MemberInfo memberInfo = getAccountResponse.getMemberInfo();
			AccountInfo accountInfo = getAccountResponse.getAccountInfo();
			bindMemberProject(memberInfo,accountInfo,request.getProjectId());
			PassFreeBarrierRequest passFreeBarrierRequest = new PassFreeBarrierRequest();
			passFreeBarrierRequest.setMid(memberInfo.getId());
			passFreeBarrierRequest.setProjectId(Long.parseLong(request.getProjectId()));
			try {
				passFreeBarrierRpc.switchProject(passFreeBarrierRequest);
			}catch (Exception e){

			}
			Project project = projectReadRemote.get(Long.parseLong(request.getProjectId()));
			/* 自动绑定涉及场景4-7
			 * 100(4): 获取绑定提醒后主动选择房屋
			 * 101(5): 获取绑定提醒后主动选择房屋 && 扫描邀请码
			 * 110(6): 获取绑定提醒后主动选择房屋 && 上传资料完成验证
			 * 111(7): 获取绑定提醒后主动选择房屋 && 上传资料完成验证 && 扫描邀请码
			* */
			if (project != null && project.getBindType() >= 4 && project.getBindType() <= 7) {
				//判断是否满足隐藏手机号自动绑定逻辑(隐藏手机号+匹配条数) 不满足时发push
				GetOwnerRoomsRequest getOwnerRoomsRequest = new GetOwnerRoomsRequest();
				getOwnerRoomsRequest.setMobile(memberInfo.getMobile());
				getOwnerRoomsRequest.setProjectId(project.getId());
				//查询隐藏号码方式下的有效房屋列表
				GetOwnerRoomsResponse getOwnerRoomsResponse = roomOwnerRemoteService.getOwnerRooms(getOwnerRoomsRequest);
				checkAndContinue(getOwnerRoomsResponse);
				boolean isConflict = getOwnerRoomsResponse.isConflict();
				List<Room> list = getOwnerRoomsResponse.getRooms();
				/*isConflict:true push
				 *自动绑定 false&&rooms.size=>0
				 *isConflict: (false&&rooms==null) 不处理
				 */
				if (isConflict == false && CollectionUtils.isNotEmpty(list)) {
					autoBindOwnerRoom(memberInfo.getId(),memberInfo.getMobile(), list);
				} else if (isConflict == true) {
					List<String> mids = new ArrayList<>();
					mids.add(memberInfo.getId());
					String tid = "bindRoomNotify"; //7000跳H5 无参数模板
					Map<String, String> tdata = new HashMap<>();
					tdata.put("curTime", String.valueOf(System.currentTimeMillis() / 1000));
					tdata.put("type",MsgConstant.APP_PUSH_MESSAGE_CENTER_TYPE_BIND_ROOM_H5);
					tdata.put("url", APIPropertiesClient.getUrlContent("skip_url_btn_h5")+"/house/match");//skip_url_btn_h5=http://qam.iqdnet.com
					tdata.put("cityName",project.getRegionName());
					tdata.put("projectName",project.getName());
					MsgSendByMidRequest msgSendByMidRequest = new MsgSendByMidRequest(mids, tid, tdata, MsgConstant.APP_TYPE_QDING);
					msgSendByMidRequest.setMessageType(MsgConstant.APP_PUSH_MESSAGE_TYPE_SYS);
					msgSendByMidRequest.setPush(true);
					msgSendByMidRequest.setVersion(MsgConstant.APP_PUSH_VERSION_2_0);
					MsgSendResponse msgSendResponse = messageService.sendMessageByMids(msgSendByMidRequest);
					logger.info("messageService.sendMessageByMids params:" + msgSendByMidRequest.toString() + " ,result:" + msgSendResponse.toString());
				}
			}
			return response;
		} catch (Exception e) {

			return handleException(ResponseData.class, e);
		}

	}

	//用户绑定社区
	private void bindMemberProject (MemberInfo memberInfo ,AccountInfo accountInfo,String projectId){

		memberProjectService.save(memberInfo.getId(), projectId, accountInfo.getSourceType().shortValue());
	}
	
	/**
	 * 删除多条消息记录
	 * @param request
	 * @return
	 */
	@HTTP(alias="deleteNotifies")
	public Response<DeleteNotifiesResponseData> deleteNotifies(DeleteNotifiesRequest request) {

		try {
			Response<DeleteNotifiesResponseData> response = new Response<>();
			
			StringBuffer ids = new StringBuffer();
			
			for(String id : request.getIds()) {
				ids.append(",").append(id);
			}
			
			MsgInfoResponse deleteMsgResponse = messageService.delMsgs(
					ids.toString().replaceFirst(",", "")
					);
			
			checkAndContinue(deleteMsgResponse);
			
			response.setData(new DeleteNotifiesResponseData());
			
			return response;
		} catch (Exception e) {
			
			return handleException(DeleteNotifiesResponseData.class, e);
		}
		
	}
	
	/**
	 * 获取用户乐购消息列表
	 * @param request
	 * @return
	 */
	@HTTP(alias="getLegouNotifies")
	public Response<GetLegouNotifiesResponseData> getLegouNotifies(GetLegouNotifiesRequest request) {
		
		try {
			Response<GetLegouNotifiesResponseData> response = new Response<>();
			
			MsgInfoRequest msgRequest = transfor(MsgInfoRequest.class, request);
			
			//乐购消息
			msgRequest.setType("4,5,6");
			
			List<LegouNotify> legouNotifies = new ArrayList<>();
			
			MsgInfoResponse msgResponse = messageService.getMsgList(msgRequest);
			
			checkAndContinue(msgResponse);
			
			Page<MsgMessage> page = msgResponse.getPageList();

			for(MsgMessage m : page.getList()) {
				
				/**
				 * 乐购消息结构
				 * {"address":"北京市朝阳区常通路3号院星座5栋1层","content":"您订购的以下商品已到货，请到物业处领取。",
				 * "createTime":"2015-01-07 13:58:15","goodsName":"褚橙","orderId":"LG2015010709415745085944",
				 * "projectId":"35","projectName":"长楹天街","title":"【长楹天街】到货通知","total":"100.0","type":"6"}
				 */

				try {
					
					JSONObject json = JSON.parseObject(m.getDetail());
				
					legouNotifies.add(new LegouNotify(
							m.getId().toString(),
							json.getString("title"),
							new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(json.getString("createTime")).getTime(),
							json.getString("content"),
							json.getString("orderId"),
							json.getString("total"),
							json.getString("goodsName"),
							json.getString("address")
							));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
			GetLegouNotifiesResponseData data = new GetLegouNotifiesResponseData();
			
			data.setList(legouNotifies);
			data.setRecordCount(legouNotifies.size());
			data.setTotalCount(page.getTotalRow());
			response.setData(data);
			
			return response;
		} catch (Exception e) {
			
			return handleException(GetLegouNotifiesResponseData.class, e);
		}
	}

	/**
	 * 自动绑定房屋
	 */
	protected void autoBindOwnerRoom(String memberId ,String mobile , List<Room> allRooms) {

		//同步房屋数据
		try {
			List<Room> deleteRooms = roomOwnerRemoteService.getDeleteRoomByMobile(mobile);

			MemberRoomSyncRequest request = new MemberRoomSyncRequest();
			Set<BindRoom> bindRooms = new HashSet<>();
			if (allRooms != null) {
				for (Room room : allRooms) {

					bindRooms.add(new BindRoom(
							String.valueOf(room.getId()),
							String.valueOf(room.getProjectId()), MemberRole.ONWER
					));
				}
			}
			Set<String> unBindRoomIds = new HashSet<>();
			if (deleteRooms != null) {
				for (Room room : deleteRooms) {
					unBindRoomIds.add(String.valueOf(room.getId()));
				}
			}

			request.setMemberId(memberId);
			request.setBindRooms(bindRooms);
			request.setUnBindRoomIds(unBindRoomIds);

			BaseResponse baseResponse = memberRoomAPI.syncMemberRoom(request);
			checkAndContinue(baseResponse);

		} catch (Exception e) {
			e.printStackTrace();
			//skip
		}
	}
}
