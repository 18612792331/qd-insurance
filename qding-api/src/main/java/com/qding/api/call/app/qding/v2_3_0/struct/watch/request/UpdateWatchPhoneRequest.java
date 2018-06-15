package com.qding.api.call.app.qding.v2_3_0.struct.watch.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/5/16.
 */
@Validate
public class UpdateWatchPhoneRequest extends BaseRequest {

    private static final long serialVersionUID = -2004915148212198963L;

    @ExplainAnnotation(explain = "会员ID")
    @NotNullValidate
    private String memberId;

    @ExplainAnnotation(explain = "手表设备号")
    @NotNullValidate
    private String imei;

    @ExplainAnnotation(explain = "手表手机号")
    @NotNullValidate
    private String watchPhone;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getWatchPhone() {
        return watchPhone;
    }

    public void setWatchPhone(String watchPhone) {
        this.watchPhone = watchPhone;
    }

    @Override
    public String toString() {
        return "UpdateWatchMobileRequest{" +
                "memberId='" + memberId + '\'' +
                ", imei='" + imei + '\'' +
                ", watchPhone='" + watchPhone + '\'' +
                '}';
    }
}
