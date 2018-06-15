package com.qding.api.call.app.qding.v3_2_0;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.qding.api.annotation.DependencyHttp;
import com.qding.api.annotation.DependencyRpc;

import com.qding.member.rpc.IMemberAddressRPC;
import com.qding.member.rpc.IMemberRoomRPC;
import com.qding.member.rpc.response.memberaddress.MemberAddressResponse;
import com.qding.member.rpc.response.memberroom.GetSelfRoomResponse;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.user.ProjectRoom;
import com.qding.api.call.app.qding.v2_5_0.struct.user.Addresses;
import com.qding.api.call.app.qding.v2_5_0.struct.user.request.AddUserForReceiveMessageReuqest;
import com.qding.api.call.app.qding.v3_2_0.struct.user.MemberBindRoomGroupDTO;
import com.qding.api.call.app.qding.v3_2_0.struct.user.RoomRoleDto;
import com.qding.api.call.app.qding.v3_2_0.struct.user.request.AddAdressReuqest;
import com.qding.api.call.app.qding.v3_2_0.struct.user.request.BindRoomCancelRequest;
import com.qding.api.call.app.qding.v3_2_0.struct.user.request.BindRoomDelRequest;
import com.qding.api.call.app.qding.v3_2_0.struct.user.request.BindRoomInitRequest;
import com.qding.api.call.app.qding.v3_2_0.struct.user.request.BindRoomRequest;
import com.qding.api.call.app.qding.v3_2_0.struct.user.request.GetRoomsBymidRequest;
import com.qding.api.call.app.qding.v3_2_0.struct.user.request.UpdateAdressReuqest;
import com.qding.api.call.app.qding.v3_2_0.struct.user.response.data.AddAdressResponseData;
import com.qding.api.call.app.qding.v3_2_0.struct.user.response.data.BindRoomCancelResponseData;
import com.qding.api.call.app.qding.v3_2_0.struct.user.response.data.BindRoomDelResponseData;
import com.qding.api.call.app.qding.v3_2_0.struct.user.response.data.BindRoomInitResponseData;
import com.qding.api.call.app.qding.v3_2_0.struct.user.response.data.BindRoomResponseData;
import com.qding.api.call.app.qding.v3_2_0.struct.user.response.data.GetRoomBymidResponseData;
import com.qding.api.call.app.qding.v3_2_0.struct.user.response.data.UpdateAdressResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.api.util.QDMemberRemote;
import com.qding.brick.pojo.biz.Project;
import com.qding.brick.pojo.biz.RegionGroup;
import com.qding.brick.pojo.biz.Room;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.DateUtil;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.manager.dto.HkAuthorInfoDto;
import com.qding.manager.service.IHkCommonRPCService;
import com.qding.manager.strut.request.housekeeper.AppMenuInfoRequest;
import com.qding.manager.strut.request.housekeeper.HKRoleInfoRequest;
import com.qding.manager.strut.response.housekeeper.HKCommonResponse;
import com.qding.member.model.MemberAddress;
import com.qding.member.model.MemberRoom;
import com.qding.member.model.dto.MemberBindRoomDTO;
import com.qding.member.response.roombindapply.GetRoomBindApplyDetailResponse;
import com.qding.member.rpc.IRoomBindApplyRPC;
import com.qding.member.rpc.response.roombindapply.MemberBindRoomDTOsResponse;
import com.qding.member.service.IRoomBindApplyRpcService;
import com.qding.member.vo.RoomBindApplyVo;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qding.task.dto.TaskInfoDto;
import com.qding.task.enums.TaskType;
import com.qding.task.rpc.service.ITaskinfoRpc;
import com.qding.task.rpc.struct.request.CallBackRequest;
import com.qding.task.rpc.struct.request.PushTaskInfoRequest;
import com.qding.task.rpc.struct.response.CallBackResponse;
import com.qding.task.rpc.struct.response.PushTaskInfoResponse;

import static org.apache.solr.client.solrj.SolrQuery.ORDER.desc;

/**
 * Created by qd on 2017/7/12.
 */
public class CallUser extends com.qding.api.call.app.qding.v3_1_0.CallUser {
	
	private static final Logger logger = Logger.getLogger(CallUser.class);
	
	@Autowired
    private IMemberAddressRPC memberAddressService;
	
	@Autowired
	private ProjectReadRemote projectReadService;
	
	@Autowired
	private IMemberRoomRPC memberRoomAPI;
	
	@Autowired
    private IRoomBindApplyRPC roomBindApplyService;
	
	@Autowired
	private IRoomBindApplyRpcService roomBindApplyRpcService;

	@Autowired
    private RoomReadRemote roomReadRemoteClient;
	
	@Autowired
	private IProfileService profileAPI;
	
	@Autowired
	private ITaskinfoRpc taskinfoRpc;
	
	@Autowired
	IHkCommonRPCService commonRPCService;
	

	
	@ExplainAnnotation (explain = "业主申请绑定房屋初始页面")
    @HTTP(alias = "bindRoomInit")
	public Response<BindRoomInitResponseData> bindRoomInit(BindRoomInitRequest request) {
		Response<BindRoomInitResponseData> response = new  Response<BindRoomInitResponseData>();
		BindRoomInitResponseData data = new BindRoomInitResponseData();
		//1业主 2业主亲戚 3业主朋友 4租客
		data.setRoomId(request.getRoomId());
		List<RoomRoleDto> list=new ArrayList<RoomRoleDto>();
		list.add(new RoomRoleDto("1","业主"));
		list.add(new RoomRoleDto("2","家庭成员"));
		list.add(new RoomRoleDto("4","租客"));
		data.setRoleList(list);
		data.setInfotip("请确认您选择的房屋，并选择相应的身份，点击“提交申请”后，物业工作人员将接收并审核您的申请，审核结果将以短信和推送方式通知您。");
		response.setCode(HttpStatus.OK.getStatusCode());
		response.setData(data);
		return response;

	}
	
	@ExplainAnnotation (explain = "业主申请绑定房屋校验",desc="校验用户是否绑定过该房屋，或者审核中")
    @HTTP(alias = "bindRoomCheck", isNeadToken = true,isRequireAuth = true)
	@DependencyRpc(clazz = RoomReadRemote.class,method ={"get"})
	public Response<BindRoomResponseData> bindRoomCheck(BindRoomRequest request,UserToken userToken) {
		Response<BindRoomResponseData> response = new  Response<BindRoomResponseData>();
		BindRoomResponseData data = new BindRoomResponseData();
		response.setData(data);
		response.setCode(HttpStatus.OK.getStatusCode());
		try{
			com.qding.brick.pojo.biz.Room room=roomReadRemoteClient.get(Long.parseLong(request.getRoomId()));
			if(room==null){
				data.setMessage("房屋不存在");
				response.setCode(600);
				return response;
			}
			StringBuffer adr = new StringBuffer("");
		    adr.append(QDStringUtil.isNotEmpty(room.getGroupName()) ? room.getGroupName()+"-" : "");
		    adr.append(QDStringUtil.isNotEmpty(room.getBuildingName()) ? room.getBuildingName()+"-" : "");
		    adr.append(QDStringUtil.isNotEmpty(room.getName()) ? room.getName() : "");
            String addr = adr.toString();
			//1、若用户选择的房屋已绑定（无论绑定身份是否一致），则要弹框提示用户
			GetSelfRoomResponse selfRoomResponse =memberRoomAPI.getVaildSelfRoom(userToken.getMemberId(), request.getRoomId());
			checkAndContinue(selfRoomResponse);
			MemberRoom rm = selfRoomResponse.getMemberRoom();
			if (rm!=null) {
				data.setMessage("你已绑定该房屋");
				response.setCode(600);
				return response;
			}
			//2若用户选择的房屋已在“审核中”，则要弹框提示用户
			List<RoomBindApplyVo> list=roomBindApplyRpcService.getStatus0BymidAndroomId(userToken.getMemberId(), request.getRoomId());
			if(list!=null && list.size()>0){
				data.setMessage("你在“"+addr+"”绑定申请已在审核中，无需重复申请");
				response.setCode(600);
				return response;
			}
			//3、若用户在当日提交的绑定申请已达上限
			Project project=projectReadService.get(room.getProjectId());
			Integer total=roomBindApplyRpcService.getTodayBymidAndprojectId(userToken.getMemberId(), String.valueOf(room.getProjectId()));
			if(total!=null && total>=project.getUserBindLimit()){
				data.setMessage("当日提交的绑定申请已达上限");
				response.setCode(600);
				return response;
			}
			//4、同一个房屋的绑定申请（不区分绑定身份），只有3次机会，第三次被驳回后，无法再发起申请
			list=roomBindApplyRpcService.getStatus2BymidAndroomId(userToken.getMemberId(), request.getRoomId());
			if(list!=null && list.size()>=3){
				data.setMessage("你对“"+addr+"”绑定申请已被驳回3次，无法再提交申请，如有疑问请联系物业");
				response.setCode(600);
				return response;
			}
		}catch(Exception e){
			return handleException(BindRoomResponseData.class, e);
		}
		return response;

	}

	
	@ExplainAnnotation (explain = "业主申请绑定房屋提交")
    @HTTP(alias = "bindRoom",isNeadToken = true ,isRequireAuth = true)
	public Response<BindRoomResponseData> bindRoom(BindRoomRequest request,UserToken userToken) {
		Response<BindRoomResponseData> response = new  Response<BindRoomResponseData>();
		BindRoomResponseData data = new BindRoomResponseData();
		response.setData(data);
		try{
			com.qding.brick.pojo.biz.Room room = roomReadRemoteClient.get(Long.parseLong(request.getRoomId()));
			Project project = projectReadService.get(room.getProjectId());
			GetMemberRequest getMemberRequest = new GetMemberRequest();
			getMemberRequest.setMemberId(userToken.getMemberId());
			GetMemberResponse getMemberResponse = profileAPI.getMemberById(getMemberRequest);
			checkAndContinue(getMemberResponse);
			com.qding.member.domain.RoomBindApply roomBindApply = new com.qding.member.domain.RoomBindApply();
			roomBindApply.setId(Constant.roomBindApplyUg.generate());
			roomBindApply.setCityId(String.valueOf(project.getRegionId()));
			roomBindApply.setCityName(project.getRegionName());
			roomBindApply.setMemberId(userToken.getMemberId());
			roomBindApply.setMemberName(getMemberResponse.getMemberInfo().getName());
			roomBindApply.setMobile(getMemberResponse.getMemberInfo().getMobile());
			roomBindApply.setProjectId(String.valueOf(room.getProjectId()));
			roomBindApply.setProjectName(room.getProjectName());
			roomBindApply.setRole(request.getRoleType());
			roomBindApply.setRoomId(request.getRoomId());
			roomBindApply.setRoomName((QDStringUtil.isNotNull(room.getGroupName())?room.getGroupName():"") + (QDStringUtil.isNotNull(room.getBuildingName())?room.getBuildingName():"")+
					(QDStringUtil.isNotNull(room.getName())? room.getName():""));
			roomBindApply.setApplyType(2);//业主自动绑定
			roomBindApply.setOverTime(getOverTime(project.getApplyTimeoutTime()));
			Long i=roomBindApplyRpcService.addRoomBindApply(roomBindApply);
			logger.warn("业主自住房屋绑定申请 id="+roomBindApply.getId()+" 结果 i="+i);
			if(i==null || i==0){
				data.setMessage("房屋申请绑定失败");
				response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
				return response;
			}
			//任务平台
			List<String> listpuserid=getPuserIdByProject(String.valueOf(project.getId()));
			if(listpuserid!=null && listpuserid.size()>0){
				PushTaskInfoRequest taskreq=new PushTaskInfoRequest();
				TaskInfoDto dto=new TaskInfoDto();
				dto.setTaskName("绑定申请");
				dto.setTaskDes("地址:"+room.getProjectName()+roomBindApply.getRoomName());
				dto.setProjectId(String.valueOf(project.getId()));
				dto.setTaskTypeId(TaskType.NORMAL_TASK.getValue());
				dto.setBusinessCode(roomBindApply.getId());
				dto.setBusinessId(roomBindApply.getId());
				dto.setProductNo("roombindapply");
				dto.setOwnerMid(userToken.getMemberId());
				dto.setEndTime(roomBindApply.getOverTime());//过期时间
				dto.setStatusName("待审核");
				taskreq.setTaskInfo(dto);
				taskreq.setListMember(listpuserid);
				PushTaskInfoResponse res=taskinfoRpc.pushTaskInfo(taskreq);
				logger.warn("业主自住房屋绑定申请 id="+roomBindApply.getId()+" 推送到任务平台 PushTaskInfoResponse="+res+" request="+taskreq);
			}else{
				logger.warn("无法获取社区管家的puserId, 不会像任务平台推送 projectId="+project.getId());
			}
		}catch(Exception e){
			logger.error("业主申请绑定房屋提交异常",e);
			return handleException(BindRoomResponseData.class, e);
		}
		response.setCode(HttpStatus.OK.getStatusCode());
		response.setData(data);
		return response;
	}
	
	
	@ExplainAnnotation (explain = "业主申请绑定房屋取消校验",desc="“审核通过”，则弹出提示框，提示用户绑定已审核通")
    @HTTP(alias = "bindRoomCancelCheck", isNeadToken = true,isRequireAuth = true)
	public Response<BindRoomCancelResponseData> bindRoomCancelCheck(BindRoomCancelRequest request) {
		Response<BindRoomCancelResponseData> response = new  Response<BindRoomCancelResponseData>();
		BindRoomCancelResponseData data = new BindRoomCancelResponseData();
		try{
			GetRoomBindApplyDetailResponse res=roomBindApplyRpcService.getRoomBindApplyDetail(request.getApplyId());
			checkAndContinue(res);
			if(res.getDetail().getStatus()==0){
				response.setCode(HttpStatus.OK.getStatusCode());
				response.setData(data);
				return response;
			}else if(res.getDetail().getStatus()==1){
    			data.setMessage("申请已被审核");
    		}else if(res.getDetail().getStatus()==-1){
    			data.setMessage("申请已被取消");
    		}else if(res.getDetail().getStatus()==2){
    			data.setMessage("申请已被驳回");
    		}
			response.setCode(600);
			return response;
		}catch(Exception e){
			return handleException(BindRoomCancelResponseData.class, e);
		}
		
	}
	
	@ExplainAnnotation (explain = "业主申请绑定房屋取消")
    @HTTP(alias = "bindRoomCancel",isNeadToken = true ,isRequireAuth = true)
	public Response<BindRoomCancelResponseData> bindRoomCancel(BindRoomCancelRequest request,UserToken userToken) {
		Response<BindRoomCancelResponseData> response = new  Response<BindRoomCancelResponseData>();
		BindRoomCancelResponseData data = new BindRoomCancelResponseData();
		try{
			BaseResponse res=roomBindApplyRpcService.handleApply(request.getApplyId(),-1,userToken.getMemberId());
			checkAndContinue(res);
			CallBackRequest req=new CallBackRequest();
			req.setBusinessId(request.getApplyId());
			req.setProductNo("roombindapply");
			CallBackResponse rese=taskinfoRpc.callBackService(req);
			logger.info("业主申请绑定房屋取消  回调任务平台 结果 applyId="+request.getApplyId()+" result="+rese.isResult());
		}catch(Exception e){
			return handleException(BindRoomCancelResponseData.class, e);
		}
		response.setCode(HttpStatus.OK.getStatusCode());
		response.setData(data);
		return response;
	}
	
	@ExplainAnnotation (explain = "业主申请绑定房屋删除",desc="备注：对房屋解绑")
    @HTTP(alias = "bindRoomDel",isNeadToken = true ,isRequireAuth = true)
	public Response<BindRoomDelResponseData> bindRoomDel(BindRoomDelRequest request,UserToken userToken) {
		Response<BindRoomDelResponseData> response = new  Response<BindRoomDelResponseData>();
		BindRoomDelResponseData data = new BindRoomDelResponseData();
		try {
            String memberId = userToken.getMemberId();
            String roomId = request.getRoomId();
            String bindId = request.getApplyId();
            //如果不是审核失败 则为解绑房屋操作
            if (QDStringUtil.isNull(bindId) || QDStringUtil.isEmpty(bindId)) {
				BaseResponse baseResponse = memberRoomAPI.unbind(memberId, roomId);
                checkAndContinue(baseResponse);
                data.setMessage("解除房间绑定成功");
            } else {
                boolean resultFlag = roomBindApplyRpcService.delRoomBindApply(bindId);
                if (!resultFlag) {
                    response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
                    data.setMessage("删除房屋绑定申请失败");
                } else {
                    data.setMessage("删除房屋绑定申请成功");
                }
            }
            response.setData(data);
            response.setCode(HttpStatus.OK.getStatusCode());
            return response;
        } catch (Exception e) {
            return handleException(BindRoomDelResponseData.class, e);
        }
		
	}
	
	
	@ExplainAnnotation (explain = "访客通行房屋列表，邀请加入的房屋列表，我的房屋页面 统一使用该接口",desc="用户所有社区房屋")
    @HTTP(alias = "getRoomsBymid",isNeadToken = true ,isRequireAuth = true ,isNeadProject = true)
	public Response<GetRoomBymidResponseData> getRoomsBymid(GetRoomsBymidRequest request,UserToken userToken) {

		Response<GetRoomBymidResponseData> response = new  Response<GetRoomBymidResponseData>();
		GetRoomBymidResponseData data = new GetRoomBymidResponseData();
		try{
			
			MemberBindRoomDTOsResponse memberBindRoomResponse = roomBindApplyService.findByMemberId(userToken.getMemberId());
			List<MemberBindRoomDTO> listr= memberBindRoomResponse.getList();
//			List<MemberBindRoomDTO> listr=roomBindApplyService.findByMemberId(userToken.getMemberId());
			Map<String,List<ProjectRoom>> roomGroupByPidMap = new HashMap<String,List<ProjectRoom>>();

			if (CollectionUtils.isNotEmpty(listr)) {
				for (MemberBindRoomDTO memberRoom : listr) {
					String projectId = memberRoom.getProjectId();
					Room room=roomReadRemoteService.get(Long.parseLong(memberRoom.getRoomId()));
					if(room==null){
						continue;
					}
					//获取房间对象
                    ProjectRoom pr = transfor(ProjectRoom.class,room);
                    com.qding.api.call.app.qding.v1_3_0.struct.brick.Room r =
                            transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, room);
                    pr.setHkIndentity(memberRoom.getRole());
                    pr.setAuditStatus(memberRoom.getAuditStatus());
                    pr.setApplyType(memberRoom.getApplyType());
                    pr.setRoleName(memberRoom.getRoleName());
                    pr.setBindId(memberRoom.getApplyId());
                    pr.setRoom(r);
					if ( roomGroupByPidMap.containsKey(projectId)) {
						List<ProjectRoom> list = roomGroupByPidMap.get(projectId);
						list.add(pr);
					} else {
						List<ProjectRoom> list = Lists.newArrayList();
						list.add(pr);
						roomGroupByPidMap.put(projectId,list);
					}
				}
			}

			List<MemberBindRoomGroupDTO> list = Lists.newArrayList();
			String curProjectId = request.getAppUser().getProjectId();
			if ( roomGroupByPidMap.containsKey(curProjectId)) {
				MemberBindRoomGroupDTO roomGroupDTO = new MemberBindRoomGroupDTO();
				Project project = projectReadService.get(Long.parseLong(curProjectId));
				roomGroupDTO.setProjectId(String.valueOf(project.getId()));
				roomGroupDTO.setProjectName(project.getName());
				roomGroupDTO.setRoomList(roomGroupByPidMap.get(curProjectId));
				list.add(roomGroupDTO);
				roomGroupByPidMap.remove(curProjectId);
			}

			for (Map.Entry<String, List<ProjectRoom>> entry : roomGroupByPidMap.entrySet()) {
				MemberBindRoomGroupDTO roomGroupDTO = new MemberBindRoomGroupDTO();
				Project project = projectReadService.get(Long.parseLong(entry.getKey()));
				if(project!=null){
					roomGroupDTO.setProjectId(String.valueOf(project.getId()));
					roomGroupDTO.setProjectName(project.getName());
				}
				roomGroupDTO.setRoomList(entry.getValue());
				list.add(roomGroupDTO);
			}
			data.setList(list);

		}catch(Exception e){
			return handleException(GetRoomBymidResponseData.class, e);
		}
		response.setCode(HttpStatus.OK.getStatusCode());
		response.setData(data);
		return response;
	}
	
	@ExplainAnnotation(explain = "新增收件人地址信息")
    @HTTP(alias = "addUserForReceiveMessage", isNeadToken = true,isRequireAuth = true)
    public Response<AddAdressResponseData> addUserForReceiveMessage(AddAdressReuqest req, UserToken userToken) {
        try {
            MemberAddress memberAddress = transfor(MemberAddress.class, req);
            memberAddress.setMemberId(userToken.getMemberId());
            memberAddress.setVersion(2);
            memberAddress.setPhone(req.getMobile());
            
            //-------------------------------------------------------------------------------------3.2
            //社区存在通过 房屋添加收货地址
            if(StringUtils.isNotBlank(req.getRoomId())){
            	AddUserForReceiveMessageReuqest request=new AddUserForReceiveMessageReuqest();
            	request.setRoomId(req.getRoomId());
            	request.setProjectId(req.getProjectId());
            	Room room = roomReadRemoteService.get(Long.parseLong(request.getRoomId()));
            	String groupName=room.getGroupName();
            	if(StringUtils.isNotBlank(groupName)){
            		List<RegionGroup> li=projectReadService.
            				getRegionListByProjectId(Long.parseLong(req.getProjectId()));
            		if(li!=null && li.size()>0){
            			for(RegionGroup group:li){
                			if(StringUtils.isNotBlank(group.getRegionName()) 
                					&& group.getRegionName().equals(groupName)){
                				//填充组团地址
                				memberAddress.setGroupName(group.getRegionName());
                				memberAddress.setGroupAddress(group.getAddress());
                				break;
                			}
                		}
            		}
            		
            	}
            	fillMemberAddressInfo(request, memberAddress);
            	//填充省市县乡
            	Project project=projectReadService.get(Long.parseLong(req.getProjectId()));
            	memberAddress.setRoomAddress(project.getStreetInfo());
            	if(project.getProvinceId()!=null){
            		memberAddress.setProvinceId(project.getProvinceId().intValue());
            	}
            	memberAddress.setProvinceName(project.getProvinceName());
            	memberAddress.setCityId((int)project.getRegionId());
            	memberAddress.setCityName(project.getRegionName());
            	if(project.getDistrictId()!=null){
            		memberAddress.setAreaId(project.getDistrictId().intValue());
            	}
            	memberAddress.setAreaName(project.getDistrictName());
            }else{
            	//没有找到社区，通过省市县 添加地址
            }
            //-------------------------------------------------------------------------------------3.2
			MemberAddressResponse memberAddressResponse = memberAddressService.save(memberAddress);
            checkAndContinue(memberAddressResponse);
            MemberAddress mas = memberAddressResponse.getMemberAddress();
            Addresses addresses = transfor(Addresses.class, mas);
            setAddress(addresses);
            if (1 == req.getDefaultFlag()) {
				MemberAddressResponse  setDefaultResponse = memberAddressService.defaultMa(mas.getMemberId(), mas.getId());
                checkAndContinue(setDefaultResponse);
            }
            //版本号 1 为老版本（或则null），2为新版本
            if (2 == mas.getVersion()) {
                addresses.setIsSetting(1);
            }
            Response<AddAdressResponseData> response = new Response<AddAdressResponseData>();
            AddAdressResponseData data = new AddAdressResponseData();
            data.setEntity(addresses);
            response.setData(data);
            return response;
        } catch (ServiceException e) {
            return handleException(AddAdressResponseData.class, e);
        }
    }

    @ExplainAnnotation(explain = "修改收件人地址信息")
    @HTTP(alias = "updateUserForReceiveMessage", isRequireAuth = true ,isNeadToken = true)
    public Response<UpdateAdressResponseData> updateUserForReceiveMessage(UpdateAdressReuqest req ,UserToken userToken) {
        try {
            Response<UpdateAdressResponseData> response = new Response<UpdateAdressResponseData>();
            MemberAddress memberAddress = transfor(MemberAddress.class, req);
            memberAddress.setMemberId(userToken.getMemberId());
            memberAddress.setVersion(2);
            //-------------------------------------------------------------------------------------3.2
            //社区存在通过 房屋添加收货地址
            if(StringUtils.isNotBlank(req.getRoomId())){
            	AddUserForReceiveMessageReuqest request=new AddUserForReceiveMessageReuqest();
            	request.setRoomId(req.getRoomId());
            	request.setProjectId(req.getProjectId());
            	Room room = roomReadRemoteService.get(Long.parseLong(request.getRoomId()));
            	String groupName=room.getGroupName();
            	if(StringUtils.isNotBlank(groupName)){
            		List<RegionGroup> li=projectReadService.
            				getRegionListByProjectId(Long.parseLong(req.getProjectId()));
            		if(li!=null && li.size()>0){
            			for(RegionGroup group:li){
                			if(StringUtils.isNotBlank(group.getRegionName()) 
                					&& group.getRegionName().equals(groupName)){
                				//填充组团地址
                				memberAddress.setGroupName(group.getRegionName());
                				memberAddress.setGroupAddress(group.getAddress());
                				break;
                			}
                		}
            		}
            		
            	}
            	fillMemberAddressInfo(request, memberAddress);
            	//填充省市县乡
            	Project project=projectReadService.get(Long.parseLong(req.getProjectId()));
            	memberAddress.setRoomAddress(project.getStreetInfo());
            	if(project.getProvinceId()!=null){
            		memberAddress.setProvinceId(project.getProvinceId().intValue());
            	}
            	memberAddress.setProvinceName(project.getProvinceName());
            	memberAddress.setCityId((int)project.getRegionId());
            	memberAddress.setCityName(project.getRegionName());
            	if(project.getDistrictId()!=null){
            		memberAddress.setAreaId(project.getDistrictId().intValue());
            	}
            	memberAddress.setAreaName(project.getDistrictName());
            }else{
            	memberAddress.setGroupId("");
            	memberAddress.setGroupName("");
            	memberAddress.setGroupAddress("");
            	memberAddress.setRoomId("");
            	memberAddress.setRoomAddress("");
            	memberAddress.setProjectId("");
            	memberAddress.setProjectName("");
            	//没有找到社区，通过省市县 添加地址
            }
            //-------------------------------------------------------------------------------------3.2
			MemberAddressResponse  memberAddressResponse = memberAddressService.update(memberAddress);
            checkAndContinue(memberAddressResponse);
            if (1 == req.getDefaultFlag()) {
				MemberAddressResponse  setDefaultResponse = memberAddressService.defaultMa(userToken.getMemberId(), req.getId());
                checkAndContinue(setDefaultResponse);
            }
            MemberAddress mas = memberAddressResponse.getMemberAddress();
            Addresses addresses = transfor(Addresses.class, mas);
            setAddress(addresses);
            //版本号 1 为老版本（或则null），2为新版本
            if(2==mas.getVersion()){
                addresses.setIsSetting(1);
            }
            UpdateAdressResponseData data = new UpdateAdressResponseData();
            data.setEntity(addresses);
            response.setData(data);
            return response;
        } catch (ServiceException e) {
            return handleException(UpdateAdressResponseData.class, e);
        }

    }
	
    private Long getOverTime(int buffer){
    	try{
    		String current=DateUtil.getToday(DateUtil.FORMAT_DEFAULT);
        	Date start=DateUtil.parseDate(current+" 08:00:00", DateUtil.FORMAT_ALL);
        	Date end=DateUtil.parseDate(current+" 20:59:59", DateUtil.FORMAT_ALL);
        	boolean result=com.qding.api.util.DateUtil.between(start, end, new Date());
        	if(result){
        		return DateUtils.addHours(new Date(), buffer).getTime();
        	}
        	start=DateUtil.parseDate(current+" 21:00:00", DateUtil.FORMAT_ALL);
        	end=DateUtil.parseDate(current+" 23:59:59", DateUtil.FORMAT_ALL);
        	result=com.qding.api.util.DateUtil.between(start, end, new Date());
        	if(result){
        		Date temp=DateUtils.addDays(new Date(), 1);
        		String temp1=com.qding.api.util.DateUtil.formatDate(temp);
        		temp=DateUtil.parseDate(temp1+" 10:00:00", DateUtil.FORMAT_ALL);
        		return temp.getTime();
        	}
        	start=DateUtil.parseDate(current+" 00:00:00", DateUtil.FORMAT_ALL);
        	end=DateUtil.parseDate(current+" 07:59:59", DateUtil.FORMAT_ALL);
        	result= com.qding.api.util.DateUtil.between(start, end, new Date());
        	if(result){
        		String temp1= com.qding.api.util.DateUtil.formatDate(new Date());
        		Date temp=DateUtil.parseDate(temp1+" 10:00:00", DateUtil.FORMAT_ALL);
        		return temp.getTime();
        	}
    	}catch(Exception ex){
    		return DateUtils.addDays(new Date(), 1).getTime();
    	}
    	return DateUtils.addDays(new Date(), 1).getTime();
    	
    }
	
    public List<String> getPuserIdByProject(String projectId){
    	List<String> list=new ArrayList<String>();
    	try{
    		HKRoleInfoRequest req=new HKRoleInfoRequest();
    		String menuId=DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_APP_MENU.getGroupName(), Constant.Dict_K_V_Enum.DICT_APP_MENU.getDictKey());
    		if(StringUtils.isBlank(menuId)){
    			logger.warn("字典缺少 app_menu----》app_menu_hk_shenpi_id 配置");
    			return list;
    		}
    		req.setRoleId(menuId);//按钮角色
    		req.setProjectId(projectId);
    		HKCommonResponse res=commonRPCService.getHKInfoByRoleInfo(req);
    		if(res!=null && res.getReturnInfo().getCode()==200 && res.getHkAuthorInfoDtoList()!=null){
    			for(HkAuthorInfoDto dto:res.getHkAuthorInfoDtoList()){
    				if(dto!=null && StringUtils.isNotBlank(dto.getId())){
    					list.add(dto.getId());
    				}
    			}
    		}else{
    			logger.warn("获取指定社区管家puserid错误projectId="+projectId);
    		}
    	}catch(Exception ex){
    		logger.error("获取指定社区管家puserid异常projectId="+projectId,ex);
    	}
    	return list;
    }
    
	
	
}
