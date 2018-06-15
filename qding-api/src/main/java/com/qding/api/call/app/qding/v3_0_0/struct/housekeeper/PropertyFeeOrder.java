package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/22.
 */
public class PropertyFeeOrder implements Serializable {

    private static final long serialVersionUID = -3457612579547896491L;

    @ExplainAnnotation (explain = "")
    private String orderCode;

    @ExplainAnnotation (explain = "")
    private String totalPrice;

    @ExplainAnnotation (explain = "")
    private String totalDiscount;

    @ExplainAnnotation (explain = "")
    private String qdingDiscount;

    @ExplainAnnotation (explain = "")
    private String wuyeDiscount;

    @ExplainAnnotation (explain = "")
    private String totalRealpay;

    @ExplainAnnotation (explain = "")
    private Integer payType;

    @ExplainAnnotation (explain = "")
    private Long payAt;

    @ExplainAnnotation (explain = "")
    private Integer payStatus;

    @ExplainAnnotation (explain = "")
    private String roomCode;

    @ExplainAnnotation (explain = "")
    private Long roomId;

    @ExplainAnnotation (explain = "")
    private Long projectId;

    @ExplainAnnotation (explain = "")
    private String commid;

    @ExplainAnnotation (explain = "")
    private Long payeeAt;

    @ExplainAnnotation (explain = "")
    private String projectName;

    @ExplainAnnotation (explain = "")
    private String memberId;

    @ExplainAnnotation (explain = "")
    private String memberName;

    @ExplainAnnotation (explain = "")
    private String memberMobile;

    @ExplainAnnotation (explain = "")
    private Long propertyId;

    @ExplainAnnotation (explain = "")
    private String propertyName;

    @ExplainAnnotation (explain = "")
    private Long skuId;

    @ExplainAnnotation (explain = "")
    private Integer isdel;

    @ExplainAnnotation (explain = "")
    private Long createAt;

    @ExplainAnnotation (explain = "")
    private String createBy;

    @ExplainAnnotation (explain = "")
    private String receiptId;

    @ExplainAnnotation (explain = "")
    private Integer notifyStatus;

    @ExplainAnnotation (explain = "")
    private Long regionId;

    @ExplainAnnotation (explain = "")
    private String regionName;

    @ExplainAnnotation (explain = "")
    private String roomName;

    @ExplainAnnotation (explain = "")
    private Integer sourceType;

    @ExplainAnnotation (explain = "")
    private Integer isIncludeOwnerFee;

    @ExplainAnnotation (explain = "")
    private Integer isReplacement;

    @ExplainAnnotation (explain = "")
    private String feeOwnersName;

    @ExplainAnnotation (explain = "")
    private String feeOwnersCustid;

    @ExplainAnnotation (explain = "")
    private String orderType;


    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
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

    public String getQdingDiscount() {
        return qdingDiscount;
    }

    public void setQdingDiscount(String qdingDiscount) {
        this.qdingDiscount = qdingDiscount;
    }

    public String getWuyeDiscount() {
        return wuyeDiscount;
    }

    public void setWuyeDiscount(String wuyeDiscount) {
        this.wuyeDiscount = wuyeDiscount;
    }

    public String getTotalRealpay() {
        return totalRealpay;
    }

    public void setTotalRealpay(String totalRealpay) {
        this.totalRealpay = totalRealpay;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getPayAt() {
        return payAt;
    }

    public void setPayAt(Long payAt) {
        this.payAt = payAt;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getCommid() {
        return commid;
    }

    public void setCommid(String commid) {
        this.commid = commid;
    }

    public Long getPayeeAt() {
        return payeeAt;
    }

    public void setPayeeAt(Long payeeAt) {
        this.payeeAt = payeeAt;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberMobile() {
        return memberMobile;
    }

    public void setMemberMobile(String memberMobile) {
        this.memberMobile = memberMobile;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public Integer getIsdel() {
        return isdel;
    }

    public void setIsdel(Integer isdel) {
        this.isdel = isdel;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public Integer getNotifyStatus() {
        return notifyStatus;
    }

    public void setNotifyStatus(Integer notifyStatus) {
        this.notifyStatus = notifyStatus;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Integer getIsIncludeOwnerFee() {
        return isIncludeOwnerFee;
    }

    public void setIsIncludeOwnerFee(Integer isIncludeOwnerFee) {
        this.isIncludeOwnerFee = isIncludeOwnerFee;
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

    public String getFeeOwnersCustid() {
        return feeOwnersCustid;
    }

    public void setFeeOwnersCustid(String feeOwnersCustid) {
        this.feeOwnersCustid = feeOwnersCustid;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
}
