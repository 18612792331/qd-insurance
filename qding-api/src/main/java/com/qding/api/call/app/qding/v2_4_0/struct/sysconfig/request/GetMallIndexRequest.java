package com.qding.api.call.app.qding.v2_4_0.struct.sysconfig.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.smart.validate.rule.NotNullValidate;


@Validate
public class GetMallIndexRequest extends BaseRequest {

    private static final long serialVersionUID = 1674400993460831856L;

    @ExplainAnnotation(explain = "当前社区ID")
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
        return "GetMallIndexRequest{" +
                "projectId='" + projectId + '\'' +
                '}';
    }
}
