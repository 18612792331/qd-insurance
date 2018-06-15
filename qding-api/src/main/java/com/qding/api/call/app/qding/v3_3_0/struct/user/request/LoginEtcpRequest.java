package com.qding.api.call.app.qding.v3_3_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

public class LoginEtcpRequest extends BaseRequest{

	private static final long serialVersionUID = 1L;
	
	@ExplainAnnotation (explain = "客户是否同意授权",desc = "1同意")
	private Integer state=0;

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	
	
	

}
