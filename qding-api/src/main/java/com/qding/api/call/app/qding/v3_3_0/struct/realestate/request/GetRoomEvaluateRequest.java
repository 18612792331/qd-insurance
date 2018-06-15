package com.qding.api.call.app.qding.v3_3_0.struct.realestate.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;

@Validate
public class GetRoomEvaluateRequest extends BaseRequest {

    private static final long serialVersionUID = 1699350228064434666L;
    
    @ExplainAnnotation (explain = "入住签约房屋评价主体id")
    private String bodyId;
    
	public String getBodyId() {
		return bodyId;
	}

	public void setBodyId(String bodyId) {
		this.bodyId = bodyId;
	}

	@Override
	public String toString() {
		return "GetRoomEvaluateRequest [bodyId=" + bodyId + "]";
	}
	
    
    
    
    
     
}
