package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/27.
 */
@Validate
public class HotcycleHomePageRequest extends BaseRequest {

    private static final long serialVersionUID = 1674400993460831856L;

    @NotNullValidate
    private String projectId;

    private String userId;//可以为空

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

    @Override
    public String toString() {
        return "HotcycleHomePageRequest [projectId=" + projectId +",userId="+userId+"," +
                " super.toString() ]";
    }


}
