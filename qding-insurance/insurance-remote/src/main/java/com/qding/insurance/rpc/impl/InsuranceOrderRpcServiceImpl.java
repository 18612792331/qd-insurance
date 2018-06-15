package com.qding.insurance.rpc.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qding.framework.common.api.struct.ReturnInfo;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.insurance.domain.InsuranceOrder;
import com.qding.insurance.rpc.IInsuranceOrderRpcService;
import com.qding.insurance.rpc.request.InsuranceOrderInsureRequest;
import com.qding.insurance.rpc.response.InsuranceOrderDetailResponse;
import com.qding.insurance.service.IInsuranceOrderService;
import com.qding.insurance.vo.GuaranteePlanResultVo;
import com.qding.insurance.vo.InsuranceDetailByOrderNoVo;

@Service("insuranceOrderRpc")
public class InsuranceOrderRpcServiceImpl implements IInsuranceOrderRpcService {
	private static final Logger logger = Logger.getLogger(InsuranceOrderRpcServiceImpl.class);

	@Autowired
	private IInsuranceOrderService orderService;
	
	@Override
	public boolean checkWheatherInsured(String roomId) {
		logger.info("参数roomID:" + roomId);
		return orderService.checkWheatherInsured(roomId);
	}

	@Override
	public GuaranteePlanResultVo getGuaranteePlanBySkuInfo(String projectType,
			String styleType, String timeType) {
		GuaranteePlanResultVo planResultVo = orderService.getGuaranteePlanBySkuInfo(projectType, styleType, timeType);
		return planResultVo;
	}

	@Override
	public void insure(InsuranceOrderInsureRequest request) throws Exception {
		orderService.insure(request);
	}


	@Override
	public InsuranceOrderDetailResponse getInsOderDetailByOrderNo(String orderNo) {
		InsuranceOrderDetailResponse response = new InsuranceOrderDetailResponse();
		ReturnInfo returnInfo = new ReturnInfo();
		if(StringUtils.isNotBlank(orderNo)){
			try {
				InsuranceDetailByOrderNoVo order = orderService.getInsOderDetailByOrderNo(orderNo);
				response.setInsuranceOrderVo(order);
				returnInfo.setCode(HttpStatus.OK.getStatusCode());
				response.setReturnInfo(returnInfo);
				
				if(order == null){
					returnInfo.setMessage("无此订单信息");
					response.setReturnInfo(returnInfo);
				}
				
			} catch (Exception e) {
				logger.error("getInsOderDetailByOrderNo异常", e);
				returnInfo.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
				returnInfo.setMessage("查询失败：内部错误");
				response.setReturnInfo(returnInfo);
			}
			return response;
			
		}
		returnInfo.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
		returnInfo.setMessage("传递参数为空");
		response.setReturnInfo(returnInfo);
		return response;
	}





}
