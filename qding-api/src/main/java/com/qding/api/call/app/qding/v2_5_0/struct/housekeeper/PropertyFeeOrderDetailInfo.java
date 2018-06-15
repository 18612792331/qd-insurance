package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.util.List;

@XStreamAlias(value = "propertyFeeOrderDetailInfo")
public class PropertyFeeOrderDetailInfo implements Serializable {

    private static final long serialVersionUID = 6635484720501561099L;

    @ExplainAnnotation(explain = "id")
    private String id;

    @ExplainAnnotation(explain = "订单号")
    private String orderCode;

    @ExplainAnnotation(explain = "账单日期")
    private String feeDate;

    @ExplainAnnotation(explain = "房间号")
    private String roomCode;

    @ExplainAnnotation(explain = "费项ID")
    private String feeId;

    @ExplainAnnotation(explain = "账单金额")
    private String totalFee;

    @ExplainAnnotation(explain = "费项描述")
    private String detail;

    @ExplainAnnotation(explain = "创建时间")
    private Long createAt;

    @ExplainAnnotation(explain = "月份")
    private String month;

    @ExplainAnnotation(explain = "缴费开始时间")
    private String feeStartDate = "";

    @ExplainAnnotation(explain = "缴费结束时间")
    private String feeEndDate = "";

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

    public String getFeeDate() {
        return feeDate;
    }

    public void setFeeDate(String feeDate) {
        this.feeDate = feeDate;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getFeeStartDate() {
        return feeStartDate;
    }

    public void setFeeStartDate(String feeStartDate) {
        this.feeStartDate = feeStartDate;
    }

    public String getFeeEndDate() {
        return feeEndDate;
    }

    public void setFeeEndDate(String feeEndDate) {
        this.feeEndDate = feeEndDate;
    }
}