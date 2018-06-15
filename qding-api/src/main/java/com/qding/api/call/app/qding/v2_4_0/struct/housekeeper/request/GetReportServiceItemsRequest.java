package com.qding.api.call.app.qding.v2_4_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/7/25.
 */
@Validate
public class GetReportServiceItemsRequest extends BaseRequest {

    private static final long serialVersionUID = -8744972042279402789L;


    @NotNullValidate
    @ExplainAnnotation(explain = "社区ID")
    private String projectId;


    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "GetReportServiceItemsRequest{" +
                "projectId='" + projectId + '\'' +
                '}';
    }
}
