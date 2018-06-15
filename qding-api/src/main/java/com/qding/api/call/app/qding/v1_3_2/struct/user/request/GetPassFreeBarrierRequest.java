package com.qding.api.call.app.qding.v1_3_2.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.smart.validate.rule.NotNullValidate;

/**
 * Created by andy on 16-4-20.
 */
@Validate
public class GetPassFreeBarrierRequest  extends BaseRequest {


    private static final long serialVersionUID = 6225657633011628435L;

    @NotNullValidate
    @ExplainAnnotation (explain = "会员ID")
    private String memberId;

    @ExplainAnnotation (explain = "社区ID")
    private Long projectId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "GetPassFreeBarrierRequest{" +
                "memberId='" + memberId + '\'' +
                ", projectId=" + projectId +
                '}';
    }
}
