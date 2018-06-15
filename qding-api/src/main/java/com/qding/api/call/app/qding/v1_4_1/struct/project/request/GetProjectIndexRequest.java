package com.qding.api.call.app.qding.v1_4_1.struct.project.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/30.
 */
public class GetProjectIndexRequest   extends BaseRequest {

    private static final long serialVersionUID = 5804155572926539372L;


    /**
     * 社区ID
     */
    @NotNullValidate
    private String projectId;

    public GetProjectIndexRequest() {

    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
}
