package com.qding.insurance.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 订单支付完成消息体
 */
public class OrderPayMessage implements Serializable {

    private static final long serialVersionUID = 5359097237470514911L;

    private String orderCode;

    private String paySourceType;

    private String realpay;

    private String tradeType;

    private String payAt;

    private List<OrderPayTypeMessage> payTypeList;

    public String getPayAt() {
        return payAt;
    }

    public void setPayAt(String payAt) {
        this.payAt = payAt;
    }

    public String getPaySourceType() {
        return paySourceType;
    }

    public void setPaySourceType(String paySourceType) {
        this.paySourceType = paySourceType;
    }

    public String getRealpay() {
        return realpay;
    }

    public void setRealpay(String realpay) {
        this.realpay = realpay;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public List<OrderPayTypeMessage> getPayTypeList() {
        return payTypeList;
    }

    public void setPayTypeList(List<OrderPayTypeMessage> payTypeList) {
        this.payTypeList = payTypeList;
    }

}
