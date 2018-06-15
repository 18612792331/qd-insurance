package com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.brick.Location;
import com.qding.api.struct.ResponseData;

public class LocationResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2462430406456875018L;

	private Location entity;
	
	public LocationResponseData() {

	}

	public LocationResponseData(Location entity) {
		super();
		this.entity = entity;
	}

	public Location getEntity() {
		return entity;
	}

	public void setEntity(Location entity) {
		this.entity = entity;
	}
	
}
