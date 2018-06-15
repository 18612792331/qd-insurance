package com.qding.api.call.app.qding.v4_0_0.struct.stage.request;

import lombok.Getter;
import lombok.Setter;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 
 * @author wang.cheng.liang 
 * time 2018.05.11
 *
 */
@Validate
public class GetAbleExpressInfoRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8496735557605616364L;
	
	@Getter
	@Setter
	@ExplainAnnotation(explain="社区ID")
	@NotNullValidate
	private Long projectId;

}
