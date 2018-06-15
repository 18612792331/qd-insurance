package com.qding.api.call.app.qding.v2_1_0.struct.sysconfig.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/7/22.
 */
@Validate
public class GetAccessControAnimationRequest extends BaseRequest {

    private static final long serialVersionUID = -4089403360318637747L;

    @ExplainAnnotation (explain = "社区ID")
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
        return "GetAccessControAnimationRequest{" +
                "projectId='" + projectId + '\'' +
                '}';
    }
}
