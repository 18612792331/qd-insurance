package com.qding.api.call.app.qding.v3_1_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

public class GetDaiKouRoomListRequest extends BaseRequest {

    private static final long serialVersionUID = -7119413715411419655L;
    
    @ExplainAnnotation(explain = "社区Id")
    @NotNullValidate
    private String projectId;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
    
    

}
