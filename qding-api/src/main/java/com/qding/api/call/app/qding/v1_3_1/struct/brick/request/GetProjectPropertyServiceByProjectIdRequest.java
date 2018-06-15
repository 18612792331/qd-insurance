package com.qding.api.call.app.qding.v1_3_1.struct.brick.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/9/17.
 */
@Validate
public class GetProjectPropertyServiceByProjectIdRequest extends BaseRequest {


    private static final long serialVersionUID = 3371292736659241004L;

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
        return "GetServiceItemsByProjectIdRequest [projectId=" + projectId
                + ", toString()=" + super.toString() + "]";
    }
}
