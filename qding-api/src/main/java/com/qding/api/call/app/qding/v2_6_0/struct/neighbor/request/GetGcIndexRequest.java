package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/9/14.
 */
@Validate
public class GetGcIndexRequest extends BaseRequest {

    private static final long serialVersionUID = 2666647338759738499L;

    @NotNullValidate
    @ExplainAnnotation (explain = "社区ID")
    private String projectId;

    @ExplainAnnotation (explain = "账户ID")
    @NotNullValidate
    private String userId;

    @ExplainAnnotation (explain = "会员ID")
    @NotNullValidate
    private String memberId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "getGcIndexRequest{" +
                "projectId='" + projectId + '\'' +
                ", userId='" + userId + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
