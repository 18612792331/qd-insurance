package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/1/8.
 */
@Validate
public class GetCategoryRequest extends BaseRequest {

    private static final long serialVersionUID = 3893214300477748700L;

    @NotNullValidate
    public Long projectId;


    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "GetCategoryRequest{" +
                "projectId=" + projectId +
                '}';
    }
}
