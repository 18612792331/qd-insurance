package com.qding.api.call.app.qding.v1_3_0.struct.brick.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetProjectConcatsRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7673039574297725668L;
 
	@NotNullValidate
	private String projectId;
	
	public GetProjectConcatsRequest() {

	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public GetProjectConcatsRequest(String projectId) {
		super();
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "GetProjectConcatsRequest [projectId=" + projectId
				+ ", toString()=" + super.toString() + "]";
	}
	
}
