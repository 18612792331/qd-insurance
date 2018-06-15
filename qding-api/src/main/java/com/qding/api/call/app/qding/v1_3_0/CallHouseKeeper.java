package com.qding.api.call.app.qding.v1_3_0;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.util.*;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.*;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.Notice;
import com.qding.api.imessage.IntegralMessage;

import com.qding.api.imessage.IntegralMessageBeanT;
import com.qding.brick.pojo.biz.*;
import com.qding.brick.remote.biz.AppConfigRemote;

import com.qding.hk.domain.*;
import com.qding.member.rpc.IMemberRoomRPC;
import com.qding.member.rpc.response.memberroom.GetSelfRoomResponse;
import com.qding.passport.service.IProfileService;

import com.qding.passport.struct.request.GetMemberByAccountIdRequest;

import com.qding.passport.struct.response.GetMemberResponse;

import com.qding.profee.rpc.model.fee.FeeModel;
import com.qding.profee.rpc.request.fee.FeeRequest;
import com.qding.profee.rpc.response.fee.FeeResponse;
import com.qding.profee.rpc.response.fee.SumFeeResponse;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.HTTP;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request.ApplyAccessControlRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request.ApplyReportRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request.GetAccessControlsRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request.GetMyReportRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request.GetNoticeByIdRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request.GetProjectNoticeRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request.GetPropertyBillsIndexRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request.GetReportReceiptRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request.GetRoomPropertyBillsRequest;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data.ApplyAccessControlResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data.ApplyReportResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data.GetAccessControlsResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data.GetMyReportResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data.GetNoticeByIdResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data.GetProjectNoticeResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data.GetPropertyBillsIndexResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data.GetReportReceiptResponseData;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data.GetRoomPropertyBillsResponseData;
import com.qding.api.constant.Constant;
import com.qding.api.struct.Response;
import com.qding.api.util.QDMemberRemote;
import com.qding.brick.remote.biz.BuildingRemote;
import com.qding.brick.remote.biz.ProjectReadRemote;
import com.qding.brick.remote.biz.RoomReadRemote;
import com.qding.framework.common.basemodel.ResultPage;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.hk.rpc.request.matter.FindMatterReplyRequest;
import com.qding.hk.rpc.request.matter.MatterApplyRequest;
import com.qding.hk.rpc.request.matter.UserSearchMatterApplyRequest;
import com.qding.hk.rpc.request.notice.GetNoticeInfoRequest;
import com.qding.hk.rpc.request.notice.GetNoticeListRequest;
import com.qding.hk.rpc.request.past.ApplyPastReleaseRequest;
import com.qding.hk.rpc.request.past.SearchUserPastReleaseRequest;
import com.qding.hk.rpc.response.matter.MatterApplyResponse;
import com.qding.hk.rpc.response.matter.MatterReplyResultResponse;
import com.qding.hk.rpc.response.matter.UserMatterApplyResultResponse;
import com.qding.hk.rpc.response.notice.GetNoticeInfoResponse;
import com.qding.hk.rpc.response.notice.GetNoticeListResponse;
import com.qding.hk.rpc.response.past.ApplyPastReleaseResponse;
import com.qding.hk.rpc.response.past.UserPastReleaseResultResponse;
import com.qding.profee.rpc.service.IFeeRpcService;
import com.qding.hk.rpc.service.IMatterRpcService;
import com.qding.hk.rpc.service.INoticeRpcService;
import com.qding.hk.rpc.service.IPastReleaseRpcService;
import com.qding.member.model.MemberRoom;
import com.qding.passport.service.IPassportService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetAccountRequest;
import com.qding.passport.struct.response.GetAccountResponse;


/**
 * 管家 version 1.3.0
 * @author lichao
 *
 */
public class CallHouseKeeper extends com.qding.api.call.app.qding.v1_2_1.CallHouseKeeper{

	
	@Autowired
	private INoticeRpcService noticeService;
	
	@Autowired
	private IMatterRpcService matterService;
	
	@Autowired
	private IFeeRpcService feeService;
	
	@Autowired
	private IPastReleaseRpcService pastService;
	
	@Autowired
	private ProjectReadRemote projectService;

	@Autowired
	private IntegralMessage integralMessage;

	@Autowired
	private AppConfigRemote appConfigRemote;

	@Autowired
	private RoomReadRemote roomReadService;

	@Autowired
	private IProfileService profileAPI;

	@Autowired
	private BuildingRemote buildingRemoteService;


	@Autowired
	private IMemberRoomRPC memberRoomAPI;

	@Autowired
	private IPassportService passportService;

	private org.apache.log4j.Logger logger =  org.apache.log4j.Logger.getLogger(CallHouseKeeper.class);
	/**
	 * 根据公告id查询公告信息					
	 * @param request
	 * @return
	 */
	@HTTP(alias="getNotice")
	@Deprecated
	public Response<GetNoticeByIdResponseData> getNotice(GetNoticeByIdRequest request) {
		
		try {
			Response<GetNoticeByIdResponseData> response = new Response<GetNoticeByIdResponseData>();
			
			GetNoticeInfoRequest noticeInfoRequest = transfor(GetNoticeInfoRequest.class, request);
			
			GetNoticeInfoResponse noticeInfoResponse = noticeService.getNoticeInfoById(noticeInfoRequest);
			
			checkAndContinue(noticeInfoResponse);
			
			GetNoticeByIdResponseData data = transfor(GetNoticeByIdResponseData.class, noticeInfoResponse);
			
			response.setData(data);
			
			return response;
			
		} catch (Exception e) {
			
			return handleException(GetNoticeByIdResponseData.class, e);
		}
	}
	
	/**
	 * 根据社区id查询公告列表					
	 * @param request
	 * @return
	 */
	@HTTP(alias="getProjectNotices")
	@Deprecated
	public Response<GetProjectNoticeResponseData> getProjectNotices(GetProjectNoticeRequest request) {
		
		try {
			Response<GetProjectNoticeResponseData> response = new Response<GetProjectNoticeResponseData>();

			GetNoticeListRequest noticeRequest = transfor(GetNoticeListRequest.class, request);
			//管家
			noticeRequest.setPosition(1);

			GetNoticeListResponse noticeResponse = noticeService.getNoticeList(noticeRequest);
			
			checkAndContinue(noticeResponse);

			ResultPage<ResNotice> resultPage = noticeResponse.getResultPage();
			
			List<ResNotice> noticeList = resultPage.getItems();
			
			if(noticeList == null) {
				
				noticeList = new ArrayList<ResNotice>();
			}
			
			List<Notice> banners = new ArrayList<>();
			
			transfor(banners, noticeList);
			
			GetProjectNoticeResponseData data = new GetProjectNoticeResponseData(
					noticeList.size(),
					resultPage.getTotalCount(),
					banners
					);
			response.setData(data);
			
			return response;
			
		} catch (Exception e) {
			
			return handleException(GetProjectNoticeResponseData.class, e);
		}
	}

	
	/**
	 * 物业缴费信息首页					
	 * @return
	 */
	@HTTP(alias="getPropertyBillIndex")
	@Deprecated
	public Response<GetPropertyBillsIndexResponseData> getPropertyBillIndex(GetPropertyBillsIndexRequest request) {
		
		try {
			Response<GetPropertyBillsIndexResponseData> response = new Response<GetPropertyBillsIndexResponseData>();
			
			Room room = roomReadService.get(Long.parseLong(request.getRoomId()));
			
			if(room == null) {
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "房间不存在");
			}

			boolean isLongHu = isLongHuForFee(room.getProjectId());

			logger.info("callHousekeeper isLongHu return : projectId"+room.getProjectId()+"  , isLongHu:"+isLongHu);

			SumFeeResponse sumFeeResponse = null ;

			if (isLongHu) {
				sumFeeResponse = feeService.getSumFee(room.getCode());
			} else {
				sumFeeResponse = feeService.getNotLongForSumFee(room.getId());
			}

			checkAndContinue(sumFeeResponse);
			
			//未交
			Float debts = sumFeeResponse.getSumFee().getDebts();
			//预付
			Float prePay = sumFeeResponse.getSumFee().getPrePay();
			
			FeeRequest feeRequest = transfor(FeeRequest.class, request);
			feeRequest.setCode(room.getCode());
			feeRequest.setLongHu(isLongHu);

			FeeResponse feeByMonth;
			
			//未缴
			//龙湖接口不支持按缴费状态查询 -- 内存分页
			if(request.getFeeStatus() == 1) {
				
				Integer pageNo = request.getPageNo();
				Integer pageSize = request.getPageSize();

				feeRequest.setPageNo(pageNo);
				feeRequest.setPageSize(pageSize);
				feeByMonth = feeService.getFeeByMonth(feeRequest);

				checkAndContinue(feeByMonth);
				
				List<FeeModel> unPayFees = new ArrayList<>();
				Iterator<FeeModel> iterator = feeByMonth.getFeeModelList().iterator();
				while(iterator.hasNext()) {
					FeeModel fee = iterator.next();
					if("未缴".equals(fee.getFeeStatus())) {
						unPayFees.add(fee);
					}
				}
				
				int form = (pageNo - 1) * pageSize;
				int to = (pageNo * pageSize);
				int max = unPayFees.size();
				
				if(to > max) {
					to = max;
				}
				
				feeByMonth.setFeeModelList(
						unPayFees.subList(form, to)
				);

			}
			else if(request.getFeeStatus() == 0){

				feeByMonth = feeService.getFeeByMonth(feeRequest);
			
				checkAndContinue(feeByMonth);
			}
			else {
				
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "错误的feeStatus");

			}
			
			GetPropertyBillsIndexResponseData data = transfor(GetPropertyBillsIndexResponseData.class, feeByMonth);
			data.setRecordCount(feeByMonth.getFeeModelList().size());
			data.setTotalCount(feeByMonth.getFeeModelList().size());
			data.setSumDebt(String.valueOf(debts));
			data.setSumPrePay(String.valueOf(prePay));
			response.setData(data);
			return response;
			
		} catch (Exception e) {
			
			return handleException(GetPropertyBillsIndexResponseData.class, e);
		} 
	}
	
	/**
	 * 根据roomId查询物业费缴费详情					
	 * @param request
	 * @return
	 */
	@HTTP(alias="getRoomPropertyBills")
	@Deprecated
	public Response<GetRoomPropertyBillsResponseData> getRoomPropertyBills(GetRoomPropertyBillsRequest request) {
		
		try {
			Response<GetRoomPropertyBillsResponseData> response = new Response<GetRoomPropertyBillsResponseData>();
			
			Room room = roomReadService.get(Long.parseLong(request.getRoomId()));
			
			if(room == null) {
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "房间不存在");
			}
			
			FeeRequest feeRequest = transfor(FeeRequest.class, request);
			
			feeRequest.setCode(room.getCode());
            //为避免时区问题，客户端传过来日期字符串，由服务端转成时间戳
            if(QDStringUtil.isNotEmpty(request.getDate())){
                feeRequest.setDate(DateFormat.getDateInstance().parse(request.getDate()).getTime());
            }else {
                //兼容老版本
                feeRequest.setDate(request.getMonth());
            }

			boolean isLongHu =  isLongHuForFee(room.getProjectId());

			feeRequest.setLongHu(isLongHu);

			FeeResponse roomBillsResponse = feeService.getOweFeeByMonth(feeRequest);
			
			checkAndContinue(roomBillsResponse);

			GetRoomPropertyBillsResponseData data = new GetRoomPropertyBillsResponseData();// transfor(GetRoomPropertyBillsResponseData.class, roomBillsResponse);
			List<PropertyBills> list = Lists.newArrayList();
			if (QDStringUtil.isNotNull(roomBillsResponse.getFeeModelList()) && roomBillsResponse.getFeeModelList().size()>0 ) {
				Iterator<FeeModel> iterator = roomBillsResponse.getFeeModelList().iterator();
				float unpaidNum =0;
				while(iterator.hasNext()) {
					FeeModel fee = iterator.next();
					PropertyBills propertyBills =transfor(PropertyBills.class,fee);
					list.add(propertyBills);
					if("未缴".equals(fee.getFeeStatus())) {
						if(!data.isUnpaid()){
							data.setUnpaid(true);
						}
						unpaidNum +=fee.getDebtsAmount();
					}
				}
				BigDecimal  unpaidCount = new BigDecimal(String.valueOf(unpaidNum));
				data.setUnpaidCount(unpaidCount);
				data.setList(list);
			}
			response.setData(data);
			
			return response;
		} catch (Exception e) {
			
			return handleException(GetRoomPropertyBillsResponseData.class, e);
		}
	}
	
	/**
	 * 根据用户id和社区id查询门禁列表					
	 * @param request
	 * @return
	 */
	@HTTP(alias="getAccessControls")
	public Response<GetAccessControlsResponseData> getAccessControls(GetAccessControlsRequest request) {
		
		try {
			Response<GetAccessControlsResponseData> response = new Response<GetAccessControlsResponseData>();
			
			SearchUserPastReleaseRequest pastRequest = transfor(SearchUserPastReleaseRequest.class, request);
			
			UserPastReleaseResultResponse pastResponse = pastService.searchUserPastReleaseList(pastRequest);
			
			checkAndContinue(pastResponse);
			
			GetAccessControlsResponseData data = transfor(GetAccessControlsResponseData.class, pastResponse);
			
			data.setRecordCount(data.getList().size());

			List<AccessControl> list = data.getList();

			for (AccessControl accessControl : list){

				if (QDStringUtil.isNotEmpty(accessControl.getQrcodeUrl())){

					accessControl.setAccessPassWord("");

					accessControl.setAccessType(Constant.HK_PAST_QRCODE);//二维码

				} else {

					accessControl.setAccessType(Constant.HK_PAST_BLUETOOH);//蓝牙
				}
			}
			
			response.setData(data);

			return response;

		} catch (Exception e) {
			return handleException(GetAccessControlsResponseData.class, e);
		}
		
	}
	


	private Calendar getStartCalendar() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		return c;
	}
	
	private Calendar getEndCalendar() {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		return c;
	}

	private void addDay(Calendar c, int day) {
		c.add(Calendar.DATE, day);
	}
	/**
	 * 门禁预约申请					
	 * @param request
	 * @return
	 */
	@HTTP(alias="applyAccessControl")
	public Response<ApplyAccessControlResponseData> applyAccessControl(ApplyAccessControlRequest request) {
	
		try {
			Response<ApplyAccessControlResponseData> response = new Response<ApplyAccessControlResponseData>();
			
			ApplyPastReleaseRequest applyRequest = new ApplyPastReleaseRequest();

			ResPastRelease pastRelease = new ResPastRelease();
			
			int purposeDateType = request.getPurposeDateType();
			Calendar effectiveStart = getStartCalendar();
			Calendar effectiveEnd;
			
			//今天
			if(purposeDateType == 1) {
				effectiveEnd = getEndCalendar();
			}
			//明天
			else if(purposeDateType == 2) {
				
				effectiveEnd = getEndCalendar();
				addDay(effectiveEnd, 1);
				addDay(effectiveStart, 1);
			}
			//后天
			else if(purposeDateType == 3) {
				
				effectiveEnd = getEndCalendar();
				addDay(effectiveEnd, 2);
				addDay(effectiveStart, 2);
			}
			else {
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "无效的purposeDateType");
			}
			
			Room room = roomReadService.get(Long.parseLong(request.getRoomId()));
			
			if(room == null) {
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "房间不存在");
			}
			
			Project project = projectService.get(room.getProjectId());

			Building building = buildingRemoteService.get(room.getBuildingId());
			pastRelease.setGroupId(QDStringUtil.isNotNull(building)&&QDStringUtil.isNotNull(building.getRegionCode()) ?String.valueOf(building.getRegionCode()): "-1");
			pastRelease.setUserId(request.getAccountId());
			pastRelease.setRoomId(String.valueOf(room.getId()));
			pastRelease.setRoomNumber(room.getCsmRoomId());
			pastRelease.setProjectId(room.getProjectId());
			pastRelease.setName(request.getName());
			pastRelease.setPhone(request.getPhone());
			pastRelease.setPurpose(request.getPurposeType().shortValue());
			pastRelease.setEffectiveStart(effectiveStart.getTimeInMillis());
			pastRelease.setEffectiveEnd(effectiveEnd.getTimeInMillis());
			pastRelease.setReleaseNum(Constant.getReleaseNum(request.getPurposeType().toString()));
            String roomName = QDStringUtil.isNotNull(room.getGroupName()) ? (room.getGroupName()+"-"):"";
            roomName+=QDStringUtil.isNotNull(building) && QDStringUtil.isNotNull(building.getName()) ? (building.getName()+"-"):"";
			roomName+=room.getName();
			pastRelease.setBuilding(roomName);
			pastRelease.setProjectName(project.getName());
			pastRelease.setProjectNumber(project.getCsmProjectId());
			pastRelease.setRole(1);
			try {
				GetMemberByAccountIdRequest getMemberByAccountIdRequest = new GetMemberByAccountIdRequest();
				getMemberByAccountIdRequest.setAccountId(request.getAccountId());
				GetMemberResponse getMemberResponse = profileAPI.getMemberByAccountId(getMemberByAccountIdRequest);
				if (HttpStatus.OK.getStatusCode() == getMemberResponse.getReturnInfo().getCode()){
					String memberId = getMemberResponse.getMemberInfo().getId();
					GetSelfRoomResponse selfRoom = memberRoomAPI.getSelfRoom(memberId,String.valueOf(room.getId()));
					checkAndContinue(selfRoom);
					MemberRoom memberRoom = selfRoom.getMemberRoom();
					pastRelease.setRole(memberRoom.getRole().intValue());
				}
			} catch (Exception ex) {

			}

			Integer hkPastReleaseSource = Constant.transforPlatformByAppDevice(Constant.hkPastReleaseSourceMap, request.getAppDevice());
			
			pastRelease.setSource(hkPastReleaseSource);
			
			applyRequest.setPastRelease(pastRelease);
			
			applyRequest.setLongHu(isLongHu(room.getProjectId()));

			ApplyPastReleaseResponse applyPastReleaseResponse = null;

			ApplyAccessControlResponseData data = null;

            if(QDStringUtil.isNull(request.getAccessType()) || request.getAccessType()==-1){
                //通行类型为空，再获取一次
                request.setAccessType(project.getGateType());
            }

			if(Constant.HK_PAST_QRCODE ==request.getAccessType()|| Constant.HK_PAST_COMMON ==request.getAccessType()){//如果是二维码
				applyPastReleaseResponse = pastService.applyPastRelease(applyRequest);
				checkAndContinue(applyPastReleaseResponse);
				data = transfor(ApplyAccessControlResponseData.class, applyPastReleaseResponse);
				data.getEntity().setAccessPassWord("");
			} else if(Constant.HK_PAST_BLUETOOH ==request.getAccessType()){
				applyPastReleaseResponse = pastService.applyBluetoothPwd(applyRequest);
				checkAndContinue(applyPastReleaseResponse);
				data =transfor(ApplyAccessControlResponseData.class,applyPastReleaseResponse);
			}else{
                throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "无效的通行类型");
            }

			com.qding.api.call.app.qding.v1_3_0.struct.brick.Room r =
					transfor(com.qding.api.call.app.qding.v1_3_0.struct.brick.Room.class, room);

			data.getEntity().setRoom(r);

			//1.4.1增加
			if (QDStringUtil.isNotNull(request.getAccessType())){
				data.getEntity().setAccessType(request.getAccessType());
			}

			response.setData(data);

			//加入积分规则（生成访客通行）
			if (QDStringUtil.isNotNull(request.getAccountId()) && QDStringUtil.isNotEmpty(request.getAccountId()) && Constant.HK_PAST_BLUETOOH !=request.getAccessType()) {
				String businessType = "";
				if ( 5 == request.getPurposeType() ){//如果是为当前业主生成方可通行通行证
					businessType =  Constant.INTEGRAL_VISITOR_ACCESS_M;
				} else {//如果是为来访者生成方可通行通行证
					businessType =  Constant.INTEGRAL_VISITOR_ACCESS;
				}
				IntegralMessageBeanT integralMessageBeanT = new IntegralMessageBeanT(request.getAccountId(), businessType, request.getAccountId(), QDStringUtil.isNotNull(room.getProjectId())?room.getProjectId():null, System.currentTimeMillis(), Constant.INCOME_OPT_TYPE, null,null);
				integralMessage.assembleIntegralMessage(integralMessageBeanT);
			}

			return response;
			
		} catch (Exception e) {
			
			return handleException(ApplyAccessControlResponseData.class, e);
		}
	}

	
	/**
	 * 报事申请					
	 * @param request
	 * @return
	 */
	@HTTP(alias="applyReport")
	@Deprecated
	public Response<ApplyReportResponseData> applyReport(ApplyReportRequest request) {

		try {
			Response<ApplyReportResponseData> response = new Response<ApplyReportResponseData>();
			
			Room room = roomReadService.get(Long.parseLong(request.getRoomId()));

			if(room == null) {
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "房间不存在");
			}
			
			GetAccountResponse getAccountResponse = passportService.getAccountByAccountId(transfor(GetAccountRequest.class, request));
			
			checkAndContinue(getAccountResponse);
			
			MemberInfo memberInfo = getAccountResponse.getMemberInfo();
			
			if(memberInfo == null) {

				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "请先绑定手机号");

			}

			// ==================以下是判断该社区是否支持报事报修的临时解决方案，下一版本完善后，这里需要删除================== start
			Integer brickSourceType = Constant.transforPlatformByAppDevice(Constant.brickSourceTypeMap, request.getAppDevice());
			/**
			 * 社区首页业态
			 */
			String version = appConfigRemote.getCurVersion(request.getAppDevice().getQdVersion());
			List<ServiceItem> serviceItems = appConfigRemote.getBindServiceItemListbyProjectId(Long.parseLong(request.getProjectId()),
					Constant.SERVICE_ITEM_MANAGER,
					version,
					brickSourceType
			);

			Boolean enableApply = false;
			for ( int i=0;i<serviceItems.size();i++ ) {
				if ("MATTER_APPLY".equals(serviceItems.get(i).getContant())) {
					enableApply = true;
					break;
				}
			}

			if (!enableApply) {
				ApplyReportResponseData data = new ApplyReportResponseData();
				data.setMessage("当前社区暂不支持报事报修");
				response.setCode(HttpStatus.BAD_REQUEST.getStatusCode());
				response.setData(data);
				return  response;
			}
		// ==================以下是判断该社区是否支持报事报修的临时解决方案，下一版本完善后，这里需要删除================== end

			GetSelfRoomResponse selfRoom = memberRoomAPI.getSelfRoom(memberInfo.getId(), request.getRoomId());
			
			checkAndContinue(selfRoom);
			
			MemberRoom memberRoom = selfRoom.getMemberRoom();
			
			Project project = projectService.get(room.getProjectId());
			
			MatterApplyRequest applyRequest = new MatterApplyRequest();
			
			ResMatterApply apply = transfor(ResMatterApply.class, request);
			apply.setRoomNumber(room.getCsmRoomId());
			apply.setProjectName(room.getProjectName());
			apply.setProjectNumber(project.getCsmProjectId());
			apply.setIdentity(memberRoom.getRole());
			apply.setCityId((int)project.getRegionId());
			apply.setCityName(project.getRegionName());
			if(QDStringUtil.isEmpty(room.getGroupName())) {
				apply.setBuilding(String.format("%s-%s", 
						room.getBuildingName(), 
						room.getName()));
			} else {
				apply.setBuilding(String.format("%s-%s-%s", 
						room.getGroupName(),
						room.getBuildingName(),
						room.getName()));
			}
			String[] pics = request.getPics();
			if(pics != null) {
				int picsSize = pics.length;
				if(picsSize > 0) {
					apply.setPic1(pics[0]);
				}
				if(picsSize > 1) {
					apply.setPic2(pics[1]);
				}
				if(picsSize > 2) {
					apply.setPic3(pics[2]);
				}
			}
			applyRequest.setResMatterApply(apply);
			applyRequest.setLongHu(isLongHuForFee(apply.getProjectId()));
			
			MatterApplyResponse applyResponse = matterService.matterApply(applyRequest);

			checkAndContinue(applyResponse);

			response.setData(new ApplyReportResponseData());

			return response;

		} catch (Exception e) {
			
			return handleException(ApplyReportResponseData.class, e);
		}
		
	}


	/**
	 *  临时添加判断是否是龙湖的接口
	 * @param projectId
	 * @return
	 */
	public boolean isLongHuForFee(Long projectId){

		try {
			boolean isLongHu =  projectService.isLongForProject(projectId);
			logger.info("callHousekeeper tmpIsLongHu : projectId"+projectId+"==》"+isLongHu);
			return isLongHu;
		}catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	private boolean isLongHu(Long projectId) throws Exception {


		Project project = projectService.get(projectId);

		logger.info("callHousekeeper isLongHu : projectId"+projectId+"  , PropInfoId:"+project.getPropInfoId());

		if (QDStringUtil.isNull(project.getPropInfoId()) || 0==project.getPropInfoId() ) {

			return  false;
		}

		PropertyInfo propertyInfo = projectService.getPropertyInfoById(project.getPropInfoId());

		if ( QDStringUtil.isNotNull(propertyInfo)) {
			logger.info("callHousekeeper isLongHu : BelongTo:"+propertyInfo.getBelongTo());
		}

		if ( QDStringUtil.isNotNull(propertyInfo) && 1== propertyInfo.getBelongTo()) {
			return  true;
		}

		return false;
	}

	/**
	 * 我的报事查询					
	 * @param request
	 * @return
	 */
	@HTTP(alias="getMyReports")
	public Response<GetMyReportResponseData> getMyReports(GetMyReportRequest request) {
		
		try {
			Response<GetMyReportResponseData> response = new Response<GetMyReportResponseData>();
			
			UserSearchMatterApplyRequest applyRequest = transfor(UserSearchMatterApplyRequest.class, request);

			UserMatterApplyResultResponse applyResponse = matterService.findMatterApplyByUserId(applyRequest);
			
			checkAndContinue(applyResponse);
			
			List<ResMatter> items = applyResponse.getResultPage().getItems();
			List<Report> rs = new ArrayList<Report>();
			
			for(ResMatter rm : items) {
				
				Report report = transfor(Report.class, rm);
				
				List<String> pics = new ArrayList<>();
				
				if(QDStringUtil.isNotBlank(rm.getPic1())) {
					pics.add(rm.getPic1());
				}
				if(QDStringUtil.isNotBlank(rm.getPic2())) {
					pics.add(rm.getPic2());
				}
				if(QDStringUtil.isNotBlank(rm.getPic3())) {
					pics.add(rm.getPic3());
				}
				report.setPics(pics.toArray(new String[]{}));
				boolean isLongHu =  projectService.isLongForProject(rm.getProjectId());
				if (isLongHu){
					report.setIsLongHu(1);
				}else {
					report.setIsLongHu(2);
				}
				rs.add(report);

			}

			GetMyReportResponseData data = new GetMyReportResponseData();
			
			data.setList(rs);
			data.setTotalCount(applyResponse.getResultPage().getTotalCount());
			data.setRecordCount(data.getList().size());
			
			response.setData(data);
			
			return response;
			
		} catch (Exception e) {
			
			return handleException(GetMyReportResponseData.class, e);
		}
	}

	
	/**
	 * 通过报事ID获取报事回执列表					
	 * @param request
	 * @return
	 */
	@HTTP(alias="getReportReceipts")
	@Deprecated
	public Response<GetReportReceiptResponseData> getReportReceipts(GetReportReceiptRequest request) {
		
		try {
			Response<GetReportReceiptResponseData> response = new Response<GetReportReceiptResponseData>();
			FindMatterReplyRequest replyRequest = transfor(FindMatterReplyRequest.class, request);
			MatterReplyResultResponse replyResponse = matterService.findUserMatterReply(replyRequest);
			checkAndContinue(replyResponse);
			List<MatterReply> matterReplyList = replyResponse.getMatterReplyList();
			List<ReportReceipt> reportReceiptList = Lists.newArrayList();

			if (CollectionUtils.isNotEmpty(matterReplyList)) {
				for (MatterReply matterReply : matterReplyList) {
					try {
						ReportReceipt reportReceipt = new ReportReceipt();
						String replyContent = matterReply.getContent();
						if( QDStringUtil.isEmpty(replyContent) ) continue;
						boolean isContains = replyContent.contains("fullContext");
						if( isContains ) {
							JSONObject contentJson = JSONObject.parseObject(replyContent);
							replyContent = contentJson.getString("fullContext");
						}
						reportReceipt.setContent(replyContent);
						reportReceipt.setCreateTime(matterReply.getCreateTime());
						reportReceiptList.add(reportReceipt);

					}catch (Exception ex) {
						continue;
					}
				}
			}

			GetReportReceiptResponseData data = new GetReportReceiptResponseData();
			data.setList(reportReceiptList);
			response.setCode(HttpStatus.OK.getStatusCode());
			response.setData(data);
			return response;
			
		} catch (Exception e) {
			return handleException(GetReportReceiptResponseData.class, e);
		}
		
	}


}
