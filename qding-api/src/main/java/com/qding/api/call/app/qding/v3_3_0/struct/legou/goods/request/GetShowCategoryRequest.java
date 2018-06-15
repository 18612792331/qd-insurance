package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetShowCategoryRequest  extends BaseRequest {

    private static final long serialVersionUID = 6648667214624058240L;
    
    
    @NotNullValidate
    @ExplainAnnotation(explain = "社区ID")
    public Long projectId;


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }
}
