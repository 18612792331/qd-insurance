package com.qding.api.call.app.qding.v3_3_0.struct.realestate.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class LableListRequest extends BaseRequest {

    private static final long serialVersionUID = 1699350228064434666L;
    
    @ExplainAnnotation (explain = "评价主体类型  0 签约，1 入住")
    @NotNullValidate
	private String bodyType;

	public String getBodyType() {
		return bodyType;
	}

	public void setBodyType(String bodyType) {
		this.bodyType = bodyType;
	}
    
    
    
     
}
