package com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetHKIndexRequest extends BaseRequest {

    /**
     *
     */
    private static final long serialVersionUID = 5595487871608106040L;

    @NotNullValidate
    @ExplainAnnotation(explain = "社区ID")
    private String projectId;

    public GetHKIndexRequest() {

    }


    public GetHKIndexRequest(String projectId) {
        super();
        this.projectId = projectId;
    }


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }


    @Override
    public String toString() {
        return "GetHKIndexRequest [projectId=" + projectId + ", toString()="
                + super.toString() + "]";
    }

}
