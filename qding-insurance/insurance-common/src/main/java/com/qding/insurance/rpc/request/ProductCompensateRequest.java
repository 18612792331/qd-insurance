package com.qding.insurance.rpc.request;

import java.util.Date;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class ProductCompensateRequest extends BaseRequest {

    private static final long serialVersionUID = -3864718395809030684L;

    // 房屋ID
    private String roomID;

    // 订单号
    private String orderNo;

    // 申请理赔金额
    private String applyMoney;

    // 订单商品ID
    private String productId;

    // 商品名称
    private String productName;

    // 联系人姓名
    private String contactName;

    // 联系人电话
    private String contactPhone;

    // 出险时间
    private Date reportAt;

    // 事故描述
    private String accidentMemo;

    // 现场照片,多张以英文逗号分隔
    private String accidentImgs;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getApplyMoney() {
        return applyMoney;
    }

    public void setApplyMoney(String applyMoney) {
        this.applyMoney = applyMoney;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Date getReportAt() {
        return reportAt;
    }

    public void setReportAt(Date reportAt) {
        this.reportAt = reportAt;
    }

    public String getAccidentMemo() {
        return accidentMemo;
    }

    public void setAccidentMemo(String accidentMemo) {
        this.accidentMemo = accidentMemo;
    }

    public String getAccidentImgs() {
        return accidentImgs;
    }

    public void setAccidentImgs(String accidentImgs) {
        this.accidentImgs = accidentImgs;
    }

}
