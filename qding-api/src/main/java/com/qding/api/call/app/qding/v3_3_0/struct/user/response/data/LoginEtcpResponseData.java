package com.qding.api.call.app.qding.v3_3_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/5/10.
 */
public class LoginEtcpResponseData extends ResponseData {

    private static final long serialVersionUID = 1443500757465907055L;
    
    @ExplainAnnotation(explain = "重定向url")
    private String redirectUrl;
    
    @ExplainAnnotation(explain = "是否已经授权，0未授权记录，1已经授权")
    private Integer authorization;
    
    public Integer getAuthorization() {
		return authorization;
	}

	public void setAuthorization(Integer authorization) {
		this.authorization = authorization;
	}

	public String getRedirectUrl() {
		return redirectUrl;
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	@Override
    public String toString() {
        return "UpdateMemberPayStatusResponseData{}";
    }
	
	
	
}
