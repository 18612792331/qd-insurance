package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;

@XStreamAlias(value = "propertyFeeOrderInfo")
public class PropertyFeeOrderInfo implements Serializable {

    private static final long serialVersionUID = 6635484720501561099L;

    @ExplainAnnotation(explain = "id")
    private String id;

    @ExplainAnnotation(explain = "订单号")
    private String orderCode;

    @ExplainAnnotation(explain = "订单生成时间")
    private String createAt;

    @ExplainAnnotation(explain = "订单(支付)状态", desc = "101：待缴 | 105：已缴 | 108：已退款 | 200:已取消")
    private String payStatus;

    @ExplainAnnotation(explain = "时间跨度")
    private String timeSpan;

    @ExplainAnnotation(explain = "待缴合计")
    private String totalPrice;

    @ExplainAnnotation(explain = "物业券抵扣")
    private String totalDiscount;

    @ExplainAnnotation(explain = "折扣后应缴")
    private String totalRealpay;

    @ExplainAnnotation(explain = "是否是代缴的订单",desc = "0：不是 1：是")
    private Integer isReplacement;

    @ExplainAnnotation(explain = "费用所属的人的姓名")
    private String feeOwnersName;

    /*@ExplainAnnotation(explain = "明细")
    private List<PropertyFeeOrderDetailByMonthInfo> detailList;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(String timeSpan) {
        this.timeSpan = timeSpan;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getTotalRealpay() {
        return totalRealpay;
    }

    public void setTotalRealpay(String totalRealpay) {
        this.totalRealpay = totalRealpay;
    }

    public Integer getIsReplacement() {
        return isReplacement;
    }

    public void setIsReplacement(Integer isReplacement) {
        this.isReplacement = isReplacement;
    }

    public String getFeeOwnersName() {
        return feeOwnersName;
    }

    public void setFeeOwnersName(String feeOwnersName) {
        this.feeOwnersName = feeOwnersName;
    }
}