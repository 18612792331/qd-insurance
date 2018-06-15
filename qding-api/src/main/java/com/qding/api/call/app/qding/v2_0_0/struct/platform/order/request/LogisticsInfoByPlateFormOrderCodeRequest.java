package com.qding.api.call.app.qding.v2_0_0.struct.platform.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import static com.qding.knowledge.common.MarkCodeEnum.No;

/**
 * Created by qd on 2017/9/15.
 */
@Validate
public class LogisticsInfoByPlateFormOrderCodeRequest extends BaseRequest {

    private static final long serialVersionUID = -35449818724306934L;

    @ExplainAnnotation(explain = "主订单号")
    @NotNullValidate
    private String orderCode;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public String toString() {
        return "LogisticsInfoByPlateFormOrderCodeRequest{" +
                "orderCode='" + orderCode + '\'' +
                '}';
    }
}
