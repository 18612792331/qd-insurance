package com.qding.api.call.app.qding.v1_3_0.struct.activity.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.activity.Activity;
import com.qding.api.struct.ResponseData;

public class GetActivityByIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7606125918395101904L;

	private Activity entity;
	
	
	public GetActivityByIdResponseData() {

	}


	public Activity getEntity() {
		return entity;
	}


	public void setEntity(Activity entity) {
		this.entity = entity;
	}
	
}
