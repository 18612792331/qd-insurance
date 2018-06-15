package com.qding.insurance.rpc.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qding.framework.common.api.struct.ReturnInfo;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.insurance.rpc.IPolicyGuaranteeItemRpc;
import com.qding.insurance.rpc.response.PolicyGuaranteeItemResponse;
import com.qding.insurance.service.IPolicyGuaranteeItemService;
@Service("guaranteeItemRpc")
public class PolicyGuaranteeItemRpcImpl implements IPolicyGuaranteeItemRpc {

	private static final Logger logger = Logger.getLogger(PolicyGuaranteeItemRpcImpl.class);

	@Autowired
	private IPolicyGuaranteeItemService policyGuaranteeItemService;

	@Override
	public PolicyGuaranteeItemResponse getPolicyGuaranteeItemList(String policyId) {

		PolicyGuaranteeItemResponse response = new PolicyGuaranteeItemResponse();
		ReturnInfo returnInfo = new ReturnInfo();

		try {
			response.setPolicyGuaranteeItemList(policyGuaranteeItemService.getByPolicyId(policyId));
			returnInfo.setCode(HttpStatus.OK.getStatusCode());
			returnInfo.setMessage("查询成功");

		} catch (Exception e) {
			logger.error("getPolicyGuaranteeItemList异常", e);
			returnInfo.setCode(HttpStatus.INTERNAL_SERVER_ERROR.getStatusCode());
			returnInfo.setMessage("查询失败");
		}

		response.setReturnInfo(returnInfo);
		return response;

	}

}
