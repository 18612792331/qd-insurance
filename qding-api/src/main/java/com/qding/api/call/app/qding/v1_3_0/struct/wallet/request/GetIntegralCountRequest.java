package com.qding.api.call.app.qding.v1_3_0.struct.wallet.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/12/2.
 */
@Validate
public class GetIntegralCountRequest  extends BaseRequest {

    @NotNullValidate
    @ExplainAnnotation (explain = "会员ID")
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "GetIntegralCountRequest{" +
                "memberId='" + memberId + '\'' +
                '}';
    }
}
