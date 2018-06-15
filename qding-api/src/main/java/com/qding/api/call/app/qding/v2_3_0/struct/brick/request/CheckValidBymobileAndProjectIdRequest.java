package com.qding.api.call.app.qding.v2_3_0.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/8/16.
 */
@Validate
public class CheckValidBymobileAndProjectIdRequest extends BaseRequest {

    private static final long serialVersionUID = 3822539535951805832L;

    @ExplainAnnotation (explain = "社区ID")
    @NotNullValidate
    private String projectId;

    @ExplainAnnotation (explain = "手机号")
    @NotNullValidate(message="请输入手机号")
    private String mobile;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    @Override
    public String toString() {
        return "CheckValidBymobileAndProjectIdRequest{" +
                "projectId='" + projectId + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
