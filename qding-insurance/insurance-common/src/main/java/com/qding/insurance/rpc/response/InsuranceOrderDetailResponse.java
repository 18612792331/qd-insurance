package com.qding.insurance.rpc.response;

import com.qding.framework.common.api.struct.response.BaseResponse;
import com.qding.insurance.domain.InsuranceOrder;
import com.qding.insurance.vo.InsuranceDetailByOrderNoVo;
//保险订单详情
public class InsuranceOrderDetailResponse extends BaseResponse{

	private static final long serialVersionUID = 7044784169954946437L;
	
	private InsuranceDetailByOrderNoVo insuranceOrderVo;

	public InsuranceDetailByOrderNoVo getInsuranceOrderVo() {
		return insuranceOrderVo;
	}

	public void setInsuranceOrderVo(InsuranceDetailByOrderNoVo insuranceOrder) {
		this.insuranceOrderVo = insuranceOrder;
	}

}
