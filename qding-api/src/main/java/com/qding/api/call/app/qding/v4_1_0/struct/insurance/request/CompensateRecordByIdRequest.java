package com.qding.api.call.app.qding.v4_1_0.struct.insurance.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class CompensateRecordByIdRequest extends BaseRequest{

	private static final long serialVersionUID = -8008923518092727757L;
	
	private String id; //理赔id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

}
