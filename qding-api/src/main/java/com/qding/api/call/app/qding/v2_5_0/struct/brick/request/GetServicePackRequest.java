package com.qding.api.call.app.qding.v2_5_0.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/10/26.
 */
@Validate
public class GetServicePackRequest extends BaseRequest {

    private static final long serialVersionUID = -3872592000555064589L;

    @ExplainAnnotation (explain = "版本code")
    @NotNullValidate
    private String versionCode;

    @ExplainAnnotation (explain = "APP类型",desc = "1:android,2:IOS")
    @NotNullValidate
    private Integer appType;

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }

    @Override
    public String toString() {
        return "GetServicePackRequest{" +
                "versionCode='" + versionCode + '\'' +
                ", appType=" + appType +
                '}';
    }
}
