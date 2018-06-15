package com.qding.api.call.app.qding.v1_3_0.struct.brick.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.brick.Project;
import com.qding.api.struct.ResponseData;

/**
 * 根据社区id获取社区信息					
 * @author lichao
 *
 */
public class GetProjectByIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3823386424329259277L;

	private Project entity;
	
	public GetProjectByIdResponseData() {

	}

	public GetProjectByIdResponseData(Project entity) {
		super();
		this.entity = entity;
	}

	public Project getEntity() {
		return entity;
	}

	public void setEntity(Project entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "GetProjectByIdResponseData [entity=" + entity + ", toString()="
				+ super.toString() + "]";
	}
	
	
}
