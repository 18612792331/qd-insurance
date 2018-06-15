package com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.brick.Building;
import com.qding.api.struct.ResponseData;

/**
 * 根据楼栋id获取楼栋信息					
 * @author lichao
 *
 */
public class GetBuildingByIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3429704849190279602L;

	private Building entity;
	
	public GetBuildingByIdResponseData() {

	}

	public GetBuildingByIdResponseData(Building entity) {
		super();
		this.entity = entity;
	}

	public Building getEntity() {
		return entity;
	}

	public void setEntity(Building entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "GetBuildingByIdResponseData [entity=" + entity
				+ ", toString()=" + super.toString() + "]";
	}
	
}
