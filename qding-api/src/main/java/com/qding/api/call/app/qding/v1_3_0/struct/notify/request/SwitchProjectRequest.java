package com.qding.api.call.app.qding.v1_3_0.struct.notify.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/1/20.
 */
public class SwitchProjectRequest extends BaseRequest {

    private static final long serialVersionUID = 5790711535752342897L;

    @NotNullValidate
    @ExplainAnnotation(explain = "用户账号ID")
    private String accountId;

    @NotNullValidate
    @ExplainAnnotation(explain = "切换社区ID")
    private String projectId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "SwitchProjectRequest{" +
                "accountId='" + accountId + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
