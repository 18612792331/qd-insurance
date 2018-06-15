package com.qding.api.call.app.qding.v2_0_0.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/1/4.
 */


@Validate
public class GetBusinessListRequest extends BaseRequest {

    private static final long serialVersionUID = 5600196625864817552L;

    @NotNullValidate
    @ExplainAnnotation(explain = "会员ID")
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "GetBusinessListRequest{" +
                "memberId='" + memberId + '\'' +
                '}';
    }
}
