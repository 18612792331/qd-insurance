package com.qding.api.call.app.qding.v1_3_2.struct.user.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/9/28.
 */
@Validate
public class BindProjectRequest extends BaseRequest {


    private static final long serialVersionUID = 8239197029433786196L;

    /**
     * 社区ID
     */
    @NotNullValidate
    private String projectId;

    /**
     * 会员ID
     */
    @NotNullValidate
    private String memberId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "BindProjectRequest [projectId："+projectId+",memberId:"+memberId+",super.toString()]";
    }
}
