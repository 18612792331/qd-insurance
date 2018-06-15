package com.qding.api.call.app.qding.v2_0_0.struct.project.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2016/7/6.
 */
@Validate
public class GetProjectAroundInfoByIdRequest extends BaseRequest {

    private static final long serialVersionUID = -5467582968548601167L;

    @NotNullValidate
    @ExplainAnnotation (explain = "周边商铺ID")
    private String projectAroundDataId;

    public String getProjectAroundDataId() {
        return projectAroundDataId;
    }

    public void setProjectAroundDataId(String projectAroundDataId) {
        this.projectAroundDataId = projectAroundDataId;
    }

    @Override
    public String toString() {
        return "GetProjectAroundInfoByIdRequest{" +
                "projectAroundDataId='" + projectAroundDataId + '\'' +
                '}';
    }
}
