package com.qding.api.call.app.qding.v3_0_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/3/8.
 */

@Validate
public class CheckMobileRequest extends BaseRequest {

    private static final long serialVersionUID = 32881870837347639L;

    @ExplainAnnotation(explain = "纬度")
    private String latitude;

    @ExplainAnnotation (explain = "经度")
    private String longitude;

    @ExplainAnnotation (explain = "手机号")
    @NotNullValidate
    private String mobile;

    @ExplainAnnotation (explain = "来源类型")
    @NotNullValidate
    private Integer sourceType;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }
}
