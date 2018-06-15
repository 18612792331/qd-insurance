package com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.brick.AppVersionCheck;
import com.qding.api.struct.ResponseData;

/**
 * APP版本验证					
 * @author lichao
 *
 */
public class AppVersionCheckResponseData extends ResponseData{

	private static final long serialVersionUID = 5164867130762556539L;

	private AppVersionCheck entity;


	public AppVersionCheckResponseData() {

	}

	public AppVersionCheckResponseData(AppVersionCheck entity) {
		super();
		this.entity = entity;
	}

	public AppVersionCheck getEntity() {
		return entity;
	}

	public void setEntity(AppVersionCheck entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "AppVersionCheckResponseData [entity=" + entity
				+ ",toString()=" + super.toString() + "]";
	}
	
	
}
