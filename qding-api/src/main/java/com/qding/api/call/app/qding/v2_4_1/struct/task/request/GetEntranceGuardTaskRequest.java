package com.qding.api.call.app.qding.v2_4_1.struct.task.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/3/24.
 */
@Validate
public class GetEntranceGuardTaskRequest extends BaseRequest {

    private static final long serialVersionUID = -5479319726423086605L;

    @NotNullValidate
    @ExplainAnnotation (explain = "会员ID")
    private String memberId;

    @NotNullValidate
    @ExplainAnnotation (explain = "门禁社区ID")
    private String projectId;

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
        return "GetEntranceGuardTaskRequest{" +
                "memberId='" + memberId + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
