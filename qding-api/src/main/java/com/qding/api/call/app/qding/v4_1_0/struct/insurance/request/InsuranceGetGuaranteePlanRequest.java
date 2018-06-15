package com.qding.api.call.app.qding.v4_1_0.struct.insurance.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

public class InsuranceGetGuaranteePlanRequest extends BaseRequest{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ExplainAnnotation (explain = "社区Id")
	@NotNullValidate
	private String projectId;
	
	@ExplainAnnotation (explain = "款式类型")
	@NotNullValidate
	private String styleType;
	
	@ExplainAnnotation (explain = "保障年限")
	@NotNullValidate
	private String timeType;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getStyleType() {
		return styleType;
	}

	public void setStyleType(String styleType) {
		this.styleType = styleType;
	}

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
}
