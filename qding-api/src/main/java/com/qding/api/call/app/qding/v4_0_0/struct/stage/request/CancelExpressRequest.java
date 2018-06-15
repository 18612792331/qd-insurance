package com.qding.api.call.app.qding.v4_0_0.struct.stage.request;

import lombok.Getter;
import lombok.Setter;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class CancelExpressRequest  extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7820683543920862385L;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain = "寄件ID")
	@NotNullValidate
	private String expressId;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain = "社区ID")
	@NotNullValidate
	private Long projectId;
}
