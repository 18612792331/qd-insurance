package com.qding.api.call.app.qding.v3_3_0.struct.newsell.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import lombok.Getter;
import lombok.Setter;

@Validate
public class GetSubOrdersByPcodeRequest extends BaseRequest {

    private static final long serialVersionUID = -3721702357434289381L;


    @ExplainAnnotation(explain = "主订单号")
    @Setter
    @Getter
    @NotNullValidate
    private String orderCode;


    @Override
    public String toString() {
        return "GetSubOrdersByPcodeRequest{" +
                "orderCode='" + orderCode + '\'' +
                '}';
    }
}
