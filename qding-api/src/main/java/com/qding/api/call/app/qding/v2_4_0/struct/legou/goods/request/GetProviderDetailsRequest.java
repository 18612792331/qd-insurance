package com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/6/16.
 */
@Validate
public class GetProviderDetailsRequest extends BaseRequest {

    private static final long serialVersionUID = -406200047631047150L;

    @NotNullValidate
    @ExplainAnnotation(explain = "供方ID")
    private Long providerId;

    public Long getProviderId() {
        return providerId;
    }

    public void setProviderId(Long providerId) {
        this.providerId = providerId;
    }

    @Override
    public String toString() {
        return "GetProviderDetailsRequest{" +
                "providerId='" + providerId + '\'' +
                '}';
    }
}


