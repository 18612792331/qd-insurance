package com.qding.api.call.app.qding.v1_3_2.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.smart.validate.rule.NotNullValidate;

/**
 * Created by andy on 16-4-20.
 */
@Validate
public class SetPassFreeBarrierStatusRequest extends BaseRequest {

    private static final long serialVersionUID = 3908405086437498871L;

    @NotNullValidate
    @ExplainAnnotation(explain = "会员ID")
    private String memberId;

    @ExplainAnnotation (explain = "社区ID")
    private Long projectId;

    @NotNullValidate
    @ExplainAnnotation (explain = "状态",desc = "1:开启，0:关闭")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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
        return "SetPassFreeBarrierStatusRequest{" +
                "memberId='" + memberId + '\'' +
                ", projectId=" + projectId +
                ", status=" + status +
                '}';
    }
}
