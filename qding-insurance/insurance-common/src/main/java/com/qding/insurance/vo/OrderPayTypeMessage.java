package com.qding.insurance.vo;

import java.io.Serializable;

/**
 * 订单支付方式消息体
 */
public class OrderPayTypeMessage implements Serializable {

    private static final long serialVersionUID = 6092238220138146457L;

    private String orderPayType;

    private String payAmount;

    private String payType;

    public String getOrderPayType() {
        return orderPayType;
    }

    public void setOrderPayType(String orderPayType) {
        this.orderPayType = orderPayType;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

}
