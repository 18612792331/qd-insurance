package com.qding.api.call.app.qding.v1_3_1.struct.payment.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2015/9/18.
 */
public class CardPayRequest extends BaseRequest {


    private static final long serialVersionUID = -4628720808907839481L;

    private Integer cardType;

    private String orderId;

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}
