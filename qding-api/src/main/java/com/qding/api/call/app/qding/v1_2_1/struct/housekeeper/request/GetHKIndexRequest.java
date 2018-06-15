package com.qding.api.call.app.qding.v1_2_1.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class GetHKIndexRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5595487871608106040L;

	private String projectId;
	
	public GetHKIndexRequest() {

	}


	public GetHKIndexRequest(String projectId) {
		super();
		this.projectId = projectId;
	}


	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}


	@Override
	public String toString() {
		return "GetHKIndexRequest [projectId=" + projectId + ", toString()="
				+ super.toString() + "]";
	}

}
