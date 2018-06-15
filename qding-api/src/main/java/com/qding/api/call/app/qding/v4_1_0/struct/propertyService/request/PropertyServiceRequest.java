package com.qding.api.call.app.qding.v4_1_0.struct.propertyService.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

public class PropertyServiceRequest extends BaseRequest{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ExplainAnnotation (explain = "社区ID")
	private String projectId;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
}
