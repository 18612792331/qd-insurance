package com.qding.api.call.app.qding.v2_3_0.struct.watch.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/5/17.
 */
@Validate
public class GetWatchLocationByIMEIRequest extends BaseRequest {

    private static final long serialVersionUID = -1862991276667031315L;

    @ExplainAnnotation(explain = "会员ID")
    @NotNullValidate
    private String memberId;

    @ExplainAnnotation(explain = "手表设备码")
    @NotNullValidate
    private String imei;

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

    @Override
    public String toString() {
        return "GetWatchLocationByIMEIRequest{" +
                "memberId='" + memberId + '\'' +
                ", imei='" + imei + '\'' +
                '}';
    }
}
