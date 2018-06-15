package com.qding.insurance.domain;

import java.io.Serializable;
import java.util.Date;

public class InsuranceOrder implements Serializable{
    private static final long serialVersionUID = -6133361811583828165L;

    private String id;

    private String orderNo;

    private String wareId;

    private String wareName;

    private String skuId;

    private String skuName;

    private String orderMoney;

    private String paidMoney;

    private Integer status;

    private Integer payStatus;

    private Date createAt;

    private Date paidAt;

    private String payType;

    private Date finishAt;

    private Date cancelAt;

    private String cancelReason;

    private String memberId;

    private String memberPhone;

    private String memberName;

    private String memberIdcard;

    private String insurantRelation;

    private String insurantPhone;

    private String insurantName;

    private String insurantIdcard;

    private String benefitPhone;

    private String benefitName;

    private String benefitIdcard;

    private String cityId;

    private String cityName;

    private String projectId;

    private String projectName;

    private String roomId;

    private String roomAddress;

    private Date policyActAt;
    
    private String memberEmail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo == null ? null : orderNo.trim();
    }

    public String getWareId() {
        return wareId;
    }

    public void setWareId(String wareId) {
        this.wareId = wareId == null ? null : wareId.trim();
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName == null ? null : wareName.trim();
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public String getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(String orderMoney) {
        this.orderMoney = orderMoney == null ? null : orderMoney.trim();
    }

    public String getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(String paidMoney) {
        this.paidMoney = paidMoney == null ? null : paidMoney.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(Integer payStatus) {
        this.payStatus = payStatus;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(Date paidAt) {
        this.paidAt = paidAt;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Date getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(Date finishAt) {
        this.finishAt = finishAt;
    }

    public Date getCancelAt() {
        return cancelAt;
    }

    public void setCancelAt(Date cancelAt) {
        this.cancelAt = cancelAt;
    }

    public String getCancelReason() {
        return cancelReason;
    }

    public void setCancelReason(String cancelReason) {
        this.cancelReason = cancelReason == null ? null : cancelReason.trim();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId == null ? null : memberId.trim();
    }

    public String getMemberPhone() {
        return memberPhone;
    }

    public void setMemberPhone(String memberPhone) {
        this.memberPhone = memberPhone == null ? null : memberPhone.trim();
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName == null ? null : memberName.trim();
    }

    public String getMemberIdcard() {
        return memberIdcard;
    }

    public void setMemberIdcard(String memberIdcard) {
        this.memberIdcard = memberIdcard == null ? null : memberIdcard.trim();
    }

    public String getInsurantRelation() {
        return insurantRelation;
    }

    public void setInsurantRelation(String insurantRelation) {
        this.insurantRelation = insurantRelation == null ? null : insurantRelation.trim();
    }

    public String getInsurantPhone() {
        return insurantPhone;
    }

    public void setInsurantPhone(String insurantPhone) {
        this.insurantPhone = insurantPhone == null ? null : insurantPhone.trim();
    }

    public String getInsurantName() {
        return insurantName;
    }

    public void setInsurantName(String insurantName) {
        this.insurantName = insurantName == null ? null : insurantName.trim();
    }

    public String getInsurantIdcard() {
        return insurantIdcard;
    }

    public void setInsurantIdcard(String insurantIdcard) {
        this.insurantIdcard = insurantIdcard == null ? null : insurantIdcard.trim();
    }

    public String getBenefitPhone() {
        return benefitPhone;
    }

    public void setBenefitPhone(String benefitPhone) {
        this.benefitPhone = benefitPhone == null ? null : benefitPhone.trim();
    }

    public String getBenefitName() {
        return benefitName;
    }

    public void setBenefitName(String benefitName) {
        this.benefitName = benefitName == null ? null : benefitName.trim();
    }

    public String getBenefitIdcard() {
        return benefitIdcard;
    }

    public void setBenefitIdcard(String benefitIdcard) {
        this.benefitIdcard = benefitIdcard == null ? null : benefitIdcard.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId == null ? null : roomId.trim();
    }

    public String getRoomAddress() {
        return roomAddress;
    }

    public void setRoomAddress(String roomAddress) {
        this.roomAddress = roomAddress == null ? null : roomAddress.trim();
    }

    public Date getPolicyActAt() {
        return policyActAt;
    }

    public void setPolicyActAt(Date policyActAt) {
        this.policyActAt = policyActAt;
    }
    
    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail == null ? null : memberEmail.trim();
    }
}