package com.qding.api.call.app.qding.v1_3_0.struct.brick.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 根据社区id获取社区信息					
 * @author lichao
 *
 */
@Validate
public class GetProjectByIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4344547108306854679L;

	@NotNullValidate
	private String projectId;
	
	public GetProjectByIdRequest() {
	}

	public GetProjectByIdRequest(String projectId) {
		super();
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "GetProjectByIdRequest [projectId=" + projectId
				+ ", toString()=" + super.toString() + "]";
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
}
