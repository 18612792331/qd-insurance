package com.qding.api.call.app.qding.v2_0_0.struct.project.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/12/15.
 */

@Validate
public class GetProjectIndexRequest extends BaseRequest {

    private static final long serialVersionUID = -579256725439075690L;

    @NotNullValidate
    @ExplainAnnotation(explain = "社区ID")
    private String projectId;

    @ExplainAnnotation(explain = "会员ID")
    private String memberId;

    public GetProjectIndexRequest() {

    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "GetProjectIndexRequest [memberId="+memberId+" , projectId=" + projectId +",toString="+ super.toString()+"]";
    }
}
