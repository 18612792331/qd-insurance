package com.qding.api.call.app.qding.v2_5_0.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/11/22.
 */
public class GetGroupAddressRequest extends BaseRequest {

    private static final long serialVersionUID = 6189848555095339889L;
    
    @ExplainAnnotation (explain = "社区id")
    @NotNullValidate
    private String projectId;
    
    public String getProjectId() {
		return projectId;
	}
    
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
    public String toString() {
        return "GetGroupAddressRequest{}";
    }
}
