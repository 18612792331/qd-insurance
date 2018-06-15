package com.qding.insurance.domain;

import java.io.Serializable;
import java.util.Date;

public class InsurancePolicy implements Serializable{
    private static final long serialVersionUID = -4250536376785427570L;

    private String id;

    private String orderNo;

    private String piccNo;

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

    private String projectId;

    private String projectName;

    private String roomId;

    private String roomAddress;

    private String policyUrl;

    private Integer status;

    private Date insureAt;

    private Date actAt;

    private Date expireAt;

    private String endReason;

    private Date endAt;
    
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

    public String getPiccNo() {
        return piccNo;
    }

    public void setPiccNo(String piccNo) {
        this.piccNo = piccNo == null ? null : piccNo.trim();
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

    public String getPolicyUrl() {
        return policyUrl;
    }

    public void setPolicyUrl(String policyUrl) {
        this.policyUrl = policyUrl == null ? null : policyUrl.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getInsureAt() {
        return insureAt;
    }

    public void setInsureAt(Date insureAt) {
        this.insureAt = insureAt;
    }

    public Date getActAt() {
        return actAt;
    }

    public void setActAt(Date actAt) {
        this.actAt = actAt;
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }

    public String getEndReason() {
        return endReason;
    }

    public void setEndReason(String endReason) {
        this.endReason = endReason == null ? null : endReason.trim();
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }
    
    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail == null ? null : memberEmail.trim();
    }
}