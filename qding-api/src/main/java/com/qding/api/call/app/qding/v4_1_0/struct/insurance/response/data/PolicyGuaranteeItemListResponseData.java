package com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data;

import java.util.List;

import lombok.Data;

import com.qding.api.struct.ResponseData;

/**
 * 单证保障内容列表response
 * @author panghaiwen
 *
 */
@Data
public class PolicyGuaranteeItemListResponseData extends ResponseData{

	
	private static final long serialVersionUID = -4858584240581140590L;
	//单证ID
	private String policyId;
	private List<PolicyDutyResponseData> list;
}
