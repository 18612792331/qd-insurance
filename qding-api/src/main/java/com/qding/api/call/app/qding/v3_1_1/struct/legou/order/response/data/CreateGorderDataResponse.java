package com.qding.api.call.app.qding.v3_1_1.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/7/17.
 */
public class CreateGorderDataResponse extends ResponseData {

    private static final long serialVersionUID = 7763666239398823607L;

    @ExplainAnnotation(explain = "订单号")
    private String orderCode;

    @ExplainAnnotation (explain = "应付金额")
    private String shouldPay;

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getShouldPay() {
        return shouldPay;
    }

    public void setShouldPay(String shouldPay) {
        this.shouldPay = shouldPay;
    }

    @Override
    public String toString() {
        return "CreateGorderDataResponse{" +
                "orderCode='" + orderCode + '\'' +
                ", shouldPay='" + shouldPay + '\'' +
                '}';
    }
}
