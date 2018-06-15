package com.qding.api.call.app.qding.v2_4_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderGood;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/8/12.
 */
public class RedeemCodeOrderBean implements Serializable {

    private static final long serialVersionUID = 4026110131506624731L;

    @ExplainAnnotation(explain = "业态名称")
    private String businessName;

    @ExplainAnnotation(explain = "按钮级跳转模型")
    private List<BtnSkip> btnSkipList;

    @ExplainAnnotation(explain = "兑换码商品信息")
    private OrderGood orderGood;

    @ExplainAnnotation(explain = "兑换码")
    private RedeemCode redeemCode;

    @ExplainAnnotation(explain = "订单类型", desc = "0:大闸蟹，1:高德唯斯")
    private String orderType;



    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public List<BtnSkip> getBtnSkipList() {
        return btnSkipList;
    }

    public void setBtnSkipList(List<BtnSkip> btnSkipList) {
        this.btnSkipList = btnSkipList;
    }

    public OrderGood getOrderGood() {
        return orderGood;
    }

    public void setOrderGood(OrderGood orderGood) {
        this.orderGood = orderGood;
    }

    public RedeemCode getRedeemCode() {
        return redeemCode;
    }

    public void setRedeemCode(RedeemCode redeemCode) {
        this.redeemCode = redeemCode;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
