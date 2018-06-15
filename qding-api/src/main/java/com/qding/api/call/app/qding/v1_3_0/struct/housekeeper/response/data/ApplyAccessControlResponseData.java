package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.AccessControl;
import com.qding.api.struct.ResponseData;

/**
 * 门禁预约申请					
 * @author lichao
 *
 */
public class ApplyAccessControlResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7627387255471484940L;

	private AccessControl entity;
	
	public ApplyAccessControlResponseData() {
	}

	public ApplyAccessControlResponseData(AccessControl entity) {
		super();
		this.entity = entity;
	}

	public AccessControl getEntity() {
		return entity;
	}

	public void setEntity(AccessControl entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "ApplyAccessControlResponseData [entity=" + entity
				+ ", toString()=" + super.toString() + "]";
	}
	
}
