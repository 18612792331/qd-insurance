package com.qding.api.call.app.qding.v1_4_1.struct.legou.goods.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/11/4.
 */
@Validate
public class GetLgKeyWordForSysRequest extends BaseRequest {

    private static final long serialVersionUID = -3461892442265508797L;

    @NotNullValidate
    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "GetLgKeyWordForSysRequest [projectId=" + projectId +
                " ,super.toString() ]";
    }
}
