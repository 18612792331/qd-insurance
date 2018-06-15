package com.qding.api.call.app.qding.v2_5_0.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/8/22.
 */
@Validate
public class GetProjectListByDistrictIdRequest extends BaseRequest {

    private static final long serialVersionUID = -5910188709677665693L;

    @ExplainAnnotation (explain = "城市ID")
    private Long regionId;

    @ExplainAnnotation (explain = "类型",desc = "1.WX  2.APP")
    @NotNullValidate
    private Integer type;

    @ExplainAnnotation (explain = "区县ID")
    private Long districtId;

    @ExplainAnnotation (explain = "是否包含虚拟社区",desc = "0:不包含，1：包含")
    private int isIncludeVirtual = Integer.valueOf(0);

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public int getIsIncludeVirtual() {
        return isIncludeVirtual;
    }

    public void setIsIncludeVirtual(int isIncludeVirtual) {
        this.isIncludeVirtual = isIncludeVirtual;
    }
}
