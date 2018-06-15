package com.qding.api.call.app.qding.v2_3_0.struct.watch.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/7/25.
 */
@Validate
public class UnbindingRelationsRequest extends BaseRequest {

    private static final long serialVersionUID = -7758060939048848039L;

    @ExplainAnnotation(explain = "手表设备码")
    @NotNullValidate
    private String imei;

    @ExplainAnnotation(explain = "账号ID")
    @NotNullValidate
    private String userId;

    @ExplainAnnotation(explain = "会员ID")
    @NotNullValidate
    private String memberId;

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "UnbindingRelationsRequest{" +
                "imei='" + imei + '\'' +
                ", userId='" + userId + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
