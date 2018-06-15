package com.qding.api.call.app.qding.v2_5_0.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/8/22.
 */
@Validate
public class GetDistrictListByRegionIdRequest extends BaseRequest {

    private static final long serialVersionUID = -5436021105849462943L;

    @ExplainAnnotation (explain = "城市ID")
    @NotNullValidate
    private String regionId;

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }
}
