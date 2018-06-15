package com.qding.api.call.app.qding.v2_3_0.struct.watch.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/5/16.
 */
@Validate
public class GetWatchPhoneByIMEIRequest extends BaseRequest {

    private static final long serialVersionUID = -5180819932070113171L;

    @ExplainAnnotation(explain = "手表设备码")
    @NotNullValidate
    private String imei;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    @Override
    public String toString() {
        return "GetWatchPhoneByIMEIRequest{" +
                "imei='" + imei + '\'' +
                '}';
    }
}
