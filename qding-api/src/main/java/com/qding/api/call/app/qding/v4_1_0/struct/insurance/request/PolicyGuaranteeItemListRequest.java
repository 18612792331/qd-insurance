package com.qding.api.call.app.qding.v4_1_0.struct.insurance.request;

import lombok.Data;

import com.qding.framework.common.api.struct.request.BaseRequest;
@Data
public class PolicyGuaranteeItemListRequest extends BaseRequest{

	
	private static final long serialVersionUID = 3174917757434900526L;
		//保险单证ID（非PICC订单号）
		private String policyId;
}
