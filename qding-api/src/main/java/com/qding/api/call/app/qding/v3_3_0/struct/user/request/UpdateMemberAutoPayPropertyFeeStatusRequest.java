package com.qding.api.call.app.qding.v3_3_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/5/10.
 */
@Validate
public class UpdateMemberAutoPayPropertyFeeStatusRequest extends BaseRequest {

    private static final long serialVersionUID = 6330270835571682937L;

    @ExplainAnnotation (explain = "物业费代扣状态",desc = "1:开通，0：关闭")
    @NotNullValidate
    private Integer payStatus;

    @ExplainAnnotation (explain = "要开通的社区ID")
    @NotNullValidate
    private String projectId;

    public Integer getPayStatus() {
        return payStatus;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    @Override
    public String toString() {
        return "UpdateMemberAutoPayPropertyFeeStatusRequest{" +
                "payStatus=" + payStatus +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
