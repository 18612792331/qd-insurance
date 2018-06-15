package com.qding.api.call.app.qding.v1_3_0.struct.wallet.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.wallet.ChargeSku;
import com.qding.api.struct.ResponseData;

public class GetChargeValuesResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6832663904473875980L;

	private List<ChargeSku> list;
	
	public GetChargeValuesResponseData() {

	}

	public List<ChargeSku> getList() {
		return list;
	}

	public void setList(List<ChargeSku> list) {
		this.list = list;
	}
	
}
