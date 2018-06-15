package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class GetUserByIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -811503133663924855L;

	private String userId;

	public GetUserByIdRequest() {

	}
	
	public GetUserByIdRequest(String userId) {
		super();
		this.userId = userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "GetUserByIdRequest [userId=" + userId + ", toString()="
				+ super.toString() + "]";
	}
	
}
