package com.qding.insurance.rpc;

import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.insurance.rpc.request.FinishCompensateRequest;
import com.qding.insurance.rpc.request.IsInsuranceProductRequest;
import com.qding.insurance.rpc.request.ProductCompensateRequest;
import com.qding.insurance.rpc.request.UserCompensateRequest;
import com.qding.insurance.rpc.response.IsInsuranceProductResponse;
import com.qding.insurance.rpc.response.ProductCompensateResponse;
import com.qding.insurance.rpc.response.UserPolicyListResponse;
import com.qding.insurance.vo.InsurancePolicyDetailVo;

public interface IInsurancePolicyRpc {

	/**
	 * 获取保单详情
	 */
	public InsurancePolicyDetailVo getPolicyDetailInfo(String policyId);

	/**
	 * 根据房屋ID获取房屋投保状态
	 */
	BaseResponse getRoomInsureStatusByRoomID(String roomID);

	/**
	 * 根据MID获取用户保单列表
	 */
	UserPolicyListResponse getUserInsurancePolicyList(String userMid);
	
	/**
	 * 业态理赔
	 */
	ProductCompensateResponse productCompensate(ProductCompensateRequest request);
	
	/**
	 * 用户理赔
	 */
	BaseResponse userCompensate(UserCompensateRequest request);
	
	/**
	 * 是否在保商品
	 */
	IsInsuranceProductResponse isInsuranceProduct(IsInsuranceProductRequest request);
	
	/**
	 * 取消理赔
	 */
	BaseResponse cancelCompensate(FinishCompensateRequest request);
	
	/**
	 * 完成理赔
	 */
	BaseResponse finishCompensate(FinishCompensateRequest request);
	
}
