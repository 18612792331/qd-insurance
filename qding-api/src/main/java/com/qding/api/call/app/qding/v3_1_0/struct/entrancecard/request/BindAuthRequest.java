package com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;

@Validate
public class BindAuthRequest extends BaseRequest {

    private static final long serialVersionUID = -8977459602123133315L;

    @ExplainAnnotation(explain = "用户当前社区ID")
    private String projectId;

    
    public String getProjectId() {
        return projectId;
    }
    
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    
    
}
