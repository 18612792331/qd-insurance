package com.qding.api.call.app.qding.v1_3_0.struct.user.response.data;

import com.qding.api.struct.ResponseData;

public class AuthorizeResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2788795288166972638L;

	private String userToken;
	
	
	public String getUserToken() {
		return userToken;
	}

	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}


	public AuthorizeResponseData(String userToken) {
		super();
		this.userToken = userToken;
	}

	public AuthorizeResponseData() {

	}
}
