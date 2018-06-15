package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.PropertyBills;
import com.qding.api.struct.ResponseData;

import java.math.BigDecimal;
import java.util.List;

/**
 * 根据roomId查询物业费缴费详情
 *
 * @author lichao
 */
public class GetRoomPropertyBillsResponseData extends ResponseData {

    private static final long serialVersionUID = 6806038544150193190L;

    private List<PropertyBills> list;

    @ExplainAnnotation(explain = "年月", desc = "示例：2016年1月")
    private String ym;

    @ExplainAnnotation(explain = "是否全部都缴费")
    private boolean unpaid = false;

    @ExplainAnnotation(explain = "应缴总额")
    private BigDecimal totalPrice;

    @ExplainAnnotation(explain = "待缴总额")
    private BigDecimal unpaidPrice;

    @ExplainAnnotation(explain = "已缴总额")
    private BigDecimal paidPrice;

    @ExplainAnnotation(explain = "订单(支付)状态", desc = "101：待缴 | 105：已缴 | 108：已退款")
    private String payStatus;

    @ExplainAnnotation(explain = "时间跨度")
    private String timeSpan;

    public GetRoomPropertyBillsResponseData() {

    }

    public GetRoomPropertyBillsResponseData(List<PropertyBills> list) {
        super();
        this.list = list;
    }

    public List<PropertyBills> getList() {
        return list;
    }

    public void setList(List<PropertyBills> list) {
        this.list = list;
    }

    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }

    public boolean isUnpaid() {
        return unpaid;
    }

    public void setUnpaid(boolean unpaid) {
        this.unpaid = unpaid;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getUnpaidPrice() {
        return unpaidPrice;
    }

    public void setUnpaidPrice(BigDecimal unpaidPrice) {
        this.unpaidPrice = unpaidPrice;
    }

    public BigDecimal getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(BigDecimal paidPrice) {
        this.paidPrice = paidPrice;
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

    @Override
    public String toString() {
        return "GetRoomPropertyBillsResponseData{" +
                "list=" + list +
                ", ym='" + ym + '\'' +
                ", unpaid=" + unpaid +
                ", totalPrice=" + totalPrice +
                ", unpaidPrice=" + unpaidPrice +
                ", paidPrice=" + paidPrice +
                ", payStatus='" + payStatus + '\'' +
                ", timeSpan='" + timeSpan + '\'' +
                '}';
    }
}
