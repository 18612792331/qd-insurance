package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.PropertyBills;
import com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.PropertyFeeOrderDetailByMonthInfo;
import com.qding.api.struct.ResponseData;

import java.math.BigDecimal;
import java.util.List;

public class GetOrderDetailResponseData extends ResponseData {

    private static final long serialVersionUID = 6806038544150193190L;

    @ExplainAnnotation(explain = "订单(支付)状态", desc = "101：待缴 | 105：已缴 | 108：已退款")
    private String payStatus;

    @ExplainAnnotation(explain = "时间跨度")
    private String timeSpan;

    @ExplainAnnotation(explain = "待缴合计")
    private String totalPrice;

    @ExplainAnnotation(explain = "物业券抵扣")
    private String totalDiscount;

    @ExplainAnnotation(explain = "折扣后应缴")
    private String totalRealpay;

    @ExplainAnnotation(explain = "是否绑定过当前房屋")
    private boolean bind;

    @ExplainAnnotation(explain = "付款时间Long型")
    private String payeeAt;

    @ExplainAnnotation(explain = "收据号")
    private String receiptId;

    @ExplainAnnotation(explain = "是否是代缴的订单", desc = "0：不是 1：是")
    private Integer isReplacement;

    @ExplainAnnotation(explain = "费用所属的人的姓名")
    private String feeOwnersName;

    @ExplainAnnotation(explain = "费用所属的人的类型")
    private String feeOwnersType;

    @ExplainAnnotation(explain = "订单详情列表")
    private List<PropertyFeeOrderDetailByMonthInfo> list;

    public List<PropertyFeeOrderDetailByMonthInfo> getList() {
        return list;
    }

    public void setList(List<PropertyFeeOrderDetailByMonthInfo> list) {
        this.list = list;
    }

    public String getFeeOwnersType() {
        return feeOwnersType;
    }

    public void setFeeOwnersType(String feeOwnersType) {
        this.feeOwnersType = feeOwnersType;
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

    public boolean isBind() {
        return bind;
    }

    public void setBind(boolean bind) {
        this.bind = bind;
    }

    public String getPayeeAt() {
        return payeeAt;
    }

    public void setPayeeAt(String payeeAt) {
        this.payeeAt = payeeAt;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
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

    @Override
    public String toString() {
        return "GetOrderDetailResponseData{" + "payStatus='" + payStatus + '\'' + ", timeSpan='" + timeSpan + '\''
                + ", totalPrice='" + totalPrice + '\'' + ", totalDiscount='" + totalDiscount + '\'' + ", totalRealpay='"
                + totalRealpay + '\'' + ", bind=" + bind + ", payeeAt='" + payeeAt + '\'' + ", receiptId='" + receiptId
                + '\'' + ", isReplacement=" + isReplacement + ", feeOwnersName='" + feeOwnersName + '\'' + ", list="
                + list + '}';
    }
}
