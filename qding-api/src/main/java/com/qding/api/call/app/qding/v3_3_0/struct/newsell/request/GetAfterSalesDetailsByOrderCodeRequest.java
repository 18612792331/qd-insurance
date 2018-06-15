package com.qding.api.call.app.qding.v3_3_0.struct.newsell.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import lombok.Getter;
import lombok.Setter;

@Validate
public class GetAfterSalesDetailsByOrderCodeRequest extends BaseRequest {

    private static final long serialVersionUID = -7836104674709206672L;


    @Setter
    @Getter
    @ExplainAnnotation(explain = "订单号")
    @NotNullValidate
    private String orderCode;


    @Override
    public String toString() {
        return "GetAfterSalesDetailsByOrderCodeRequest{" +
                "orderCode='" + orderCode + '\'' +
                '}';
    }



}
