package com.qding.api.call.app.qding.v2_0_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/12/29.
 */
public class OrderBean extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 8834317392559211883L;

    @ExplainAnnotation(explain = "按钮级跳转模型")
    private List<BtnSkip> btnSkipList;

    @ExplainAnnotation(explain = "业态图标")
    private String businessIco;

    @ExplainAnnotation(explain = "业态名称")
    private String businessName;

    @ExplainAnnotation(explain = "业态分类")
    private String businessType;

    @ExplainAnnotation(explain = "订单下商品信息列表")
    private List<OrderGood> orderGoods;

    @ExplainAnnotation(explain = "订单总额")
    private String totalPrice;

    @ExplainAnnotation(explain = "应付金额")
    private String shouldPay;

    @ExplainAnnotation(explain = "实际支付总金额")
    private String totalRealPay;

    @ExplainAnnotation(explain = "总折扣额")
    private String totalDiscount;

    @ExplainAnnotation(explain = "订单来源",desc = "0:App,1:代客下单,2：微信下单,3:客服下单,4:支付宝服务窗")
    private Integer sourceType;

    @ExplainAnnotation(explain = "下单时间")
    private Long createTime;

    @ExplainAnnotation(explain = "支付状态")
    private Integer paymentStatus;

    @ExplainAnnotation(explain = "支付类型")
    private Integer paymentType;

    @ExplainAnnotation(explain = "订单状态")
    private Integer orderStatus;

    @ExplainAnnotation(explain = "订单状态名称")
    private String orderStatusName;

    @ExplainAnnotation(explain = "订单号")
    private String orderCode;

    @ExplainAnnotation(explain = "订单来源",desc = "1:点滴,2:千丁")
    private Integer orderSourceType =0;

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<BtnSkip> getBtnSkipList() {
        return btnSkipList;
    }

    public void setBtnSkipList(List<BtnSkip> btnSkipList) {
        this.btnSkipList = btnSkipList;
    }

    public List<OrderGood> getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(List<OrderGood> orderGoods) {
        this.orderGoods = orderGoods;
    }

    public String getBusinessIco() {
        return businessIco;
    }

    public void setBusinessIco(String businessIco) {
        this.businessIco = businessIco;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatusName() {
        return orderStatusName;
    }

    public void setOrderStatusName(String orderStatusName) {
        this.orderStatusName = orderStatusName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getShouldPay() {
        return shouldPay;
    }

    public void setShouldPay(String shouldPay) {
        this.shouldPay = shouldPay;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getTotalRealPay() {
        return totalRealPay;
    }

    public void setTotalRealPay(String totalRealPay) {
        this.totalRealPay = totalRealPay;
    }

    public String getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getOrderSourceType() {
        return orderSourceType;
    }

    public void setOrderSourceType(Integer orderSourceType) {
        this.orderSourceType = orderSourceType;
    }
}
