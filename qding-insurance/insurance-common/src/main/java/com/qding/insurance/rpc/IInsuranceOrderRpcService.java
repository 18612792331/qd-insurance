package com.qding.insurance.rpc;

import com.qding.insurance.domain.InsuranceOrder;
import com.qding.insurance.rpc.request.InsuranceOrderInsureRequest;
import com.qding.insurance.rpc.response.InsuranceOrderDetailResponse;
import com.qding.insurance.vo.GuaranteePlanResultVo;



/**
 * Create by zhangxiaojun on 18/6/7
 **/
public interface IInsuranceOrderRpcService {
	/**
	 * 校验房屋是否已投保
	 * @param roomId
	 * @return
	 */
	public boolean checkWheatherInsured(String roomId);
	
	/**
	 * 根据货品信息查询保障计划相关信息
	 * @param projectType
	 * @param styleType
	 * @param timeType
	 * @return
	 */
	public GuaranteePlanResultVo getGuaranteePlanBySkuInfo(String projectType, String styleType, String timeType);
	
	/**
	 * 投保
	 * @param request
	 * @throws Exception 
	 */
	public void insure(InsuranceOrderInsureRequest request) throws Exception;
	
	/**
	 * 根据平台订单号查询保险订单详情
	 * @param orderNo
	 * @return
	 */
	public InsuranceOrderDetailResponse getInsOderDetailByOrderNo(String orderNo);
	
	
}
