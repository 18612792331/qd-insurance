package com.qding.api.call.app.qding.v1_3_1.struct.project.response.data;

import com.qding.api.call.app.qding.v1_3_1.struct.project.ProjectIndex;
import com.qding.api.struct.ResponseData;

public class GetProjectIndexResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -524879115164153895L;

	private ProjectIndex entity;
	
	public GetProjectIndexResponseData() {

	}

	public GetProjectIndexResponseData(ProjectIndex entity) {
		super();
		this.entity = entity;
	}

	public ProjectIndex getEntity() {
		return entity;
	}

	public void setEntity(ProjectIndex entity) {
		this.entity = entity;
	}
	
}
