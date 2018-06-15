package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.UserInfo;
import com.qding.api.struct.ResponseData;

public class GetUserByIdResponseData extends ResponseData{

	private static final long serialVersionUID = 8150153039822311693L;

	private UserInfo entity;
	
	private String lastCommunity;

	public GetUserByIdResponseData() {

	}
	
	public GetUserByIdResponseData(UserInfo entity, String lastCommunity) {
		super();
		this.entity = entity;
		this.lastCommunity = lastCommunity;
	}

	public UserInfo getEntity() {
		return entity;
	}

	public void setEntity(UserInfo entity) {
		this.entity = entity;
	}

	public String getLastCommunity() {
		return lastCommunity;
	}

	public void setLastCommunity(String lastCommunity) {
		this.lastCommunity = lastCommunity;
	}

	@Override
	public String toString() {
		return "GetUserByIdResponseData [entity=" + entity + ", lastCommunity="
				+ lastCommunity + ", toString()=" + super.toString() + "]";
	}
	
}
