package com.qding.api.call.app.qding.v4_0_0;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.ExpressInfoDTO;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.ParcelInfoDTO;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.request.CancelExpressRequest;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.request.CommitSendRequest;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.request.ConfirmSendRequest;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.request.GetAbleExpressInfoRequest;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.request.GetReceiveDetailsRequest;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.request.GetReceiveRecordRequest;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.request.GetSendDetailsRequest;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.request.GetSendRecordRequest;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.request.ModifyExpressRequest;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.response.CancelExpressResponseData;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.response.CommitSendResponseData;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.response.ConfirmSendResponseData;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.response.GetAbleExpressInfoResponseData;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.response.GetReceiveDetailsResponseData;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.response.GetReceiveRecordResponseData;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.response.GetSendDetailsResponseData;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.response.GetSendRecordResponseData;
import com.qding.api.call.app.qding.v4_0_0.struct.stage.response.ModifyExpressResponseData;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.logistics.platform.client.remote.LogisticsPlatformOrderRpcService;
import com.qding.logistics.platform.client.remote.struct.request.QueryKD100Request;
import com.qding.logistics.platform.client.remote.struct.response.QueryKD100Response;
import com.qding.order.service.IRemoteOrderSavingOptimizerService;
import com.qding.order.struct.response.GetOrderPriceResponse;
import com.qding.stage.domain.ExpressInfo;
import com.qding.stage.domain.ParcelInfo;
import com.qding.stage.dto.ExpressInfoDto;
import com.qding.stage.dto.LogisticsDto;
import com.qding.stage.dto.Mailing;
import com.qding.stage.enums.ExpressInfoStatus;
import com.qding.stage.service.IExpressInfoRpcService;
import com.qding.stage.service.IParcelInfoRpcService;
import com.qding.stage.struct.request.ExpressInfoRequest;
import com.qding.stage.struct.request.ParcelInfoRequest;
import com.qding.stage.struct.request.QdCompanyListRequest;
import com.qding.stage.struct.response.ExpressInfoResponse;
import com.qding.stage.struct.response.ParcelInfoResponse;
import com.qding.stage.struct.response.QdCompanyListResponse;

@ExplainAnnotation(explain = "驿站")
public class CallStage extends Callable {	

	@Autowired
	private IExpressInfoRpcService expressInfoRpc;
	
	@Autowired
	private IParcelInfoRpcService parcelInfoRpc;
	
	@Autowired
    private LogisticsPlatformOrderRpcService logisticsPlatformOrderRpc;
	
	@Autowired
	private IRemoteOrderSavingOptimizerService remoteOrderRpc;
	
	private static final Logger logger = Logger.getLogger(CallStage.class);

	@ExplainAnnotation(explain = "提交寄件信息")
	@HTTP(alias = "commitSendInfo", isNeadToken = true, isRequireAuth = true)
	public Response<CommitSendResponseData> commitSendInfo(CommitSendRequest request, UserToken userToken) {
		Response<CommitSendResponseData> response = new Response<CommitSendResponseData>();
		CommitSendResponseData data = new CommitSendResponseData();
		response.setCode(HttpStatus.OK.getStatusCode());
		try {
			ExpressInfoRequest express = new ExpressInfoRequest();
			ExpressInfo info = transfor(ExpressInfo.class, request);
			info.setMemberId(userToken.getMemberId());
			express.setExpressInfo(info);
			express.setThirdId(request.getThirdId());
			checkAndContinue(expressInfoRpc.saveExpressInfoRpc(express));
			// 返回消息
			response.setData(data);
		} catch (Exception e) {
			return handleException(CommitSendResponseData.class, e);
		}
		return response;
	}

	@ExplainAnnotation(explain = "获取包裹信息列表")
	@HTTP(alias = "getReceiveList", isNeadToken = true, isRequireAuth = true)
	public Response<GetReceiveRecordResponseData> getReceiveList(GetReceiveRecordRequest request, UserToken userToken) {
		Response<GetReceiveRecordResponseData> response = new Response<GetReceiveRecordResponseData>();
		GetReceiveRecordResponseData data = new GetReceiveRecordResponseData();
		response.setCode(HttpStatus.OK.getStatusCode());
		try {
			ParcelInfoRequest parcel = new ParcelInfoRequest();
			ParcelInfo info = new ParcelInfo();
			info.setReceiverMid(userToken.getMemberId());
			parcel.setParcelInfo(info);
			userToken.getAccountId();
			ParcelInfoResponse result = parcelInfoRpc.getParceInfoByParams(parcel);
			checkAndContinue(result);
			List<ParcelInfo> parcelInfoList = result.getParcelInfoList();
			data.setParcels(transforList(ParcelInfoDTO.class, parcelInfoList));
			response.setData(data);
		} catch (Exception e) {
			return handleException(GetReceiveRecordResponseData.class, e);
		}

		return response;
	}

	@ExplainAnnotation(explain = "获取包裹详细信息")
	@HTTP(alias = "getReceiveDetails", isNeadToken = true, isRequireAuth = true)
	public Response<GetReceiveDetailsResponseData> getReceiveDetails(GetReceiveDetailsRequest request,
			UserToken userToken) {
		Response<GetReceiveDetailsResponseData> response = new Response<GetReceiveDetailsResponseData>();
		GetReceiveDetailsResponseData data = new GetReceiveDetailsResponseData();
		response.setCode(HttpStatus.OK.getStatusCode());
		try {
			ParcelInfoRequest parcel = new ParcelInfoRequest();
			ParcelInfo info = new ParcelInfo();
			info.setId(request.getParcelId());
			parcel.setParcelInfo(info);
			ParcelInfoResponse result = parcelInfoRpc.getParcelInfoById(parcel);
			checkAndContinue(result);
			info = result.getParcelInfo();
			data.setParcelId(info.getId());
			data.setCreateTime(info.getCreateTime());
			data.setLogisticsCode(info.getLogisticsCode());
			data.setLogisticsName(info.getLogisticsCompanyName());
			data.setPhone(info.getDistrictId());
			data.setUserName(info.getSenderName());
			data.setPhone(info.getSenderPhone());
			data.setStatus(info.getStatus());
			//查询物流信息
			QueryKD100Request kd = new QueryKD100Request();
			kd.setCompanyName(info.getLogisticsCompanyName());
			kd.setLogisticsCode(info.getLogisticsCode());
			QueryKD100Response logisticsInfo = logisticsPlatformOrderRpc.queryKD100(kd);
			data.setLogisticsInfo(logisticsInfo.getLogs());
			response.setData(data);
		} catch (Exception e) {
			return handleException(GetReceiveDetailsResponseData.class, e);
		}
		return response;
	}

	@ExplainAnnotation(explain = "获取寄件信息列表")
	@HTTP(alias = "getExpressInfoList", isNeadToken = true, isRequireAuth = true)
	public Response<GetSendRecordResponseData> getExpressInfoList(GetSendRecordRequest request, UserToken userToken) {
		Response<GetSendRecordResponseData> response = new Response<GetSendRecordResponseData>();
		GetSendRecordResponseData data = new GetSendRecordResponseData();
		response.setCode(HttpStatus.OK.getStatusCode());
		try {
			ExpressInfoRequest express = new ExpressInfoRequest();
			ExpressInfoDto info = new ExpressInfoDto();
			info.setMemberId(userToken.getMemberId());
			express.setExpressInfo(info);
			ExpressInfoResponse result = expressInfoRpc.getExpressInfoListByParams(express);
			checkAndContinue(result);
			//取出数据后转换成前台需要数据
			List<ExpressInfoDto> list= result.getExpressInfoList();
			data.setExpressList(transforList(ExpressInfoDTO.class, list));
			response.setData(data);
		} catch (Exception e) {
			return handleException(GetSendRecordResponseData.class, e);
		}

		return response;
	}

	@ExplainAnnotation(explain = "获取寄件信息详情")
	@HTTP(alias = "getExpressDetails", isNeadToken = true, isRequireAuth = true)
	public Response<GetSendDetailsResponseData> getExpressDetails(GetSendDetailsRequest request, UserToken userToken) {
		Response<GetSendDetailsResponseData> response = new Response<GetSendDetailsResponseData>();
		response.setCode(HttpStatus.OK.getStatusCode());
		try {
			ExpressInfoRequest express = new ExpressInfoRequest();
			ExpressInfo info = new ExpressInfo();
			info.setId(request.getExpressId());
			express.setExpressInfo(info);
			ExpressInfoResponse result = expressInfoRpc.getExpressInfoById(express);
			checkAndContinue(result);
			ExpressInfoDto infoDto = result.getExpressInfo();
			GetSendDetailsResponseData data = transfor(GetSendDetailsResponseData.class, infoDto);
			//查询物流信息
			QueryKD100Request kd = new QueryKD100Request();
			kd.setCompanyName(infoDto.getQdCompanyName());
			kd.setLogisticsCode(infoDto.getLogisticsCode());
			QueryKD100Response logisticsInfo = logisticsPlatformOrderRpc.queryKD100(kd);
			
			data.setLogisticsInfo(logisticsInfo.getLogs());
			response.setData(data);
		} catch (Exception e) {
			return handleException(GetSendDetailsResponseData.class, e);
		}
		return response;
	}

	@ExplainAnnotation(explain = "确认寄件信息")
	@HTTP(alias = "confirmSendInfo", isNeadToken = true, isRequireAuth = true)
	public Response<ConfirmSendResponseData> confirmSendInfo(ConfirmSendRequest request, UserToken userToken) {
		Response<ConfirmSendResponseData> response = new Response<ConfirmSendResponseData>();
		response.setCode(HttpStatus.OK.getStatusCode());
		try {
			//根据订单号获取寄件价格
			GetOrderPriceResponse orderPrice = remoteOrderRpc.getOrderPrice(request.getOrderId(), null);
			// 根据订单号获取寄件信息
			ExpressInfoRequest express = new ExpressInfoRequest();
			ExpressInfo info = new ExpressInfo();
			info.setId(request.getExpressId());
			express.setExpressInfo(info);
			ExpressInfoResponse result = expressInfoRpc.getExpressInfoById(express);
			checkAndContinue(result);
			ConfirmSendResponseData data = transfor(ConfirmSendResponseData.class, result.getExpressInfo());
			data.setCash(orderPrice.getTotalPrice());
			data.setRealCash(orderPrice.getTotalRealpay());
			response.setData(data);
		} catch (Exception e) {
			return handleException(ConfirmSendResponseData.class, e);
		}
		return response;
	}
	
	@ExplainAnnotation(explain = "获取社区物流信息")
	@HTTP(alias = "getProjectLogisiscInfo", isNeadToken = true, isRequireAuth = true)
	public Response<GetAbleExpressInfoResponseData> getProjectLogisiscInfo(GetAbleExpressInfoRequest request, UserToken userToken) {
		Response<GetAbleExpressInfoResponseData> response = new Response<GetAbleExpressInfoResponseData>();
		GetAbleExpressInfoResponseData data = new GetAbleExpressInfoResponseData();
		response.setCode(HttpStatus.OK.getStatusCode());
		try {
			QdCompanyListRequest qdCompanyListRequest = new QdCompanyListRequest();
			qdCompanyListRequest.setProjectId(request.getProjectId());
			QdCompanyListResponse result = expressInfoRpc.getLogisticsListByProjectId(qdCompanyListRequest);
			checkAndContinue(result);
			List<LogisticsDto> logisticsList = result.getLogisticsList();
			List<Map<String, String>> reLogisticsList = Lists.newArrayList();
			for (LogisticsDto logisticsDto : logisticsList) {
				Map<String, String> logistics = Maps.newHashMap();
				logistics.put("value", String.valueOf(logisticsDto.getQdCompanyId()));
				logistics.put("label", logisticsDto.getQdCompanyName());
				logistics.put("thirdId", logisticsDto.getThirdId());
				reLogisticsList.add(logistics);
			}
			List<Map<String, String>> reMailingList = Lists.newArrayList();
			List<Mailing> mailingList = result.getMailingList();
			for (Mailing mailing : mailingList) {
				Map<String, String> goodsType = Maps.newHashMap();
				goodsType.put("value", mailing.getId());
				goodsType.put("label", mailing.getMailingType());
				reMailingList.add(goodsType);
			}
			data.setLogisticsList(reLogisticsList);
			data.setGoodsTypeList(reMailingList);
			response.setData(data);
			logger.info("getProjectLogisiscInfo response :"+JSON.toJSONString(response));
		} catch (Exception e) {
			return handleException(GetAbleExpressInfoResponseData.class, e);
		}
		return response;
	}
	
	@ExplainAnnotation(explain = "取消寄件")
	@HTTP(alias = "cancelExpress", isNeadToken = true, isRequireAuth = true)
	public Response<CancelExpressResponseData> cancelExpress(CancelExpressRequest request, UserToken userToken) {
		Response<CancelExpressResponseData> response = new Response<CancelExpressResponseData>();
		CancelExpressResponseData data = new CancelExpressResponseData();
		response.setCode(HttpStatus.OK.getStatusCode());
		try {
			ExpressInfoRequest express = new ExpressInfoRequest();
			ExpressInfo info = new ExpressInfo();
			info.setId(request.getExpressId());
			info.setProjectId(request.getProjectId());
			info.setStatus(ExpressInfoStatus.CANCELLED.getCode());
			express.setExpressInfo(info);
			checkAndContinue(expressInfoRpc.updateExpressInfoRpc(express));
			response.setData(data);
		} catch (Exception e) {
			return handleException(CancelExpressResponseData.class, e);
		}
		return response;
	}
	
	@ExplainAnnotation(explain = "修改寄件信息")
	@HTTP(alias = "modifyExpress", isNeadToken = true, isRequireAuth = true)
	public Response<ModifyExpressResponseData> modifyExpress(ModifyExpressRequest request, UserToken userToken) {
		Response<ModifyExpressResponseData> response = new Response<ModifyExpressResponseData>();
		ModifyExpressResponseData data = new ModifyExpressResponseData();
		response.setCode(HttpStatus.OK.getStatusCode());
		try {
			ExpressInfoRequest express = new ExpressInfoRequest();
			ExpressInfo info = transfor(ExpressInfo.class, request);
			express.setExpressInfo(info);
			checkAndContinue(expressInfoRpc.updateExpressInfoRpc(express));
			response.setData(data);
		} catch (Exception e) {
			return handleException(ModifyExpressResponseData.class, e);
		}
		return response;
	}
	
}
