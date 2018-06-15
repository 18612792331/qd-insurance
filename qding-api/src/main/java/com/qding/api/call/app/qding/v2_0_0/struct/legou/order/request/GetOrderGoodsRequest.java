package com.qding.api.call.app.qding.v2_0_0.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/1/16.
 */
public class GetOrderGoodsRequest extends BaseRequest {


    private static final long serialVersionUID = -7824957040236469792L;

    @NotNullValidate
    @ExplainAnnotation(explain = "会员ID")
    private String memberId;

    @NotNullValidate
    @ExplainAnnotation(explain = "订单号")
    private String orderCode;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    @Override
    public String toString() {
        return "GetOrderGoodsRequest{" +
                "memberId='" + memberId + '\'' +
                ", orderCode='" + orderCode + '\'' +
                '}';
    }
}
