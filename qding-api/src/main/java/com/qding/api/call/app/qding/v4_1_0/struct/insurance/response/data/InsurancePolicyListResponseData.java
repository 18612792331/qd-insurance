package com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data;

import java.util.List;

import com.qding.api.struct.ResponseData;
/**
 * Created by panghaiwen on 2018/6/8.
 */
public class InsurancePolicyListResponseData extends ResponseData {

	private static final long serialVersionUID = 8025940538459200482L;
	
	private List<PolicyListResponseData> list;

	public List<PolicyListResponseData> getList() {
		return list;
	}

	public void setList(List<PolicyListResponseData> list) {
		this.list = list;
	}

	

	

}
