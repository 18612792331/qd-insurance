package com.qding.api.call.app.qding.v1_3_2.struct.im.response.data;

import com.qding.api.struct.ResponseData;

/**
 * Created by jiawenzheng on 2015/7/27.
 */
public class GetImUserTokenResponseData  extends ResponseData {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8885607271409783882L;
	private String  token;
    
    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
    public String toString() {
        return "GetImUserTokenResponseData [token=" + token
                + ", toString()=" + super.toString() + "]";
    }
}
