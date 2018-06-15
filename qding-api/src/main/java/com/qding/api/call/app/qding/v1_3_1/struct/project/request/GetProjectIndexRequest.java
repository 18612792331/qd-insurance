package com.qding.api.call.app.qding.v1_3_1.struct.project.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 根据社区id获取首页信息
 * @author lichao
 *
 */
@Validate
public class GetProjectIndexRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7921866261700432001L;
	
	/**
	 * 社区ID
	 */
	@NotNullValidate
	private String projectId;
	
	public GetProjectIndexRequest() {

	}

	public String getProjectId() {
		return projectId;
	}
	
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}
