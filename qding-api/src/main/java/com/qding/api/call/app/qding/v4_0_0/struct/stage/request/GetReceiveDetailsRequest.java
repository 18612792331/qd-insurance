package com.qding.api.call.app.qding.v4_0_0.struct.stage.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author wang.cheng.liang
 *	create time 2018.5.8
 */
@Validate
public class GetReceiveDetailsRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2234264557809883648L;
	
	@Setter
    @Getter	
    @ExplainAnnotation (explain = "包裹号")
    @NotNullValidate
    private String parcelId;
	
	
}
