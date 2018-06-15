package com.qding.insurance.domain;

import java.io.Serializable;
import java.util.Date;

public class CompensateRecord implements Serializable{
    private static final long serialVersionUID = -2682477628505039840L;

    private String id;

    private String policyId;

    private String piccNo;

    private String guaranteeItemId;

    private String roomId;

    private String roomAddress;

    private Date happenAt;

    private Date reportAt;

    private String contactName;

    private String contactPhone;

    private String estimateMoney;

    private String accidentMemo;

    private String accidentImgs;

    private String reportNo;

    private String paidMoney;

    private Integer status;

    private Integer dataStatus;

    private Integer paidType;

    private Date createAt;

    private String createBy;

    private Date finishAt;

    private String finishBy;
    
    private String orderNo;//理赔订单号
    
    private String objectId;//保障商品ID
    
    private String objectName;//保障商品名称

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId == null ? null : policyId.trim();
    }

    public String getPiccNo() {
        return piccNo;
    }

    public void setPiccNo(String piccNo) {
        this.piccNo = piccNo == null ? null : piccNo.trim();
    }

    public String getGuaranteeItemId() {
        return guaranteeItemId;
    }

    public void setGuaranteeItemId(String guaranteeItemId) {
        this.guaranteeItemId = guaranteeItemId == null ? null : guaranteeItemId.trim();
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

    public Date getHappenAt() {
        return happenAt;
    }

    public void setHappenAt(Date happenAt) {
        this.happenAt = happenAt;
    }

    public Date getReportAt() {
        return reportAt;
    }

    public void setReportAt(Date reportAt) {
        this.reportAt = reportAt;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone == null ? null : contactPhone.trim();
    }

    public String getEstimateMoney() {
        return estimateMoney;
    }

    public void setEstimateMoney(String estimateMoney) {
        this.estimateMoney = estimateMoney == null ? null : estimateMoney.trim();
    }

    public String getAccidentMemo() {
        return accidentMemo;
    }

    public void setAccidentMemo(String accidentMemo) {
        this.accidentMemo = accidentMemo == null ? null : accidentMemo.trim();
    }

    public String getAccidentImgs() {
        return accidentImgs;
    }

    public void setAccidentImgs(String accidentImgs) {
        this.accidentImgs = accidentImgs == null ? null : accidentImgs.trim();
    }

    public String getReportNo() {
        return reportNo;
    }

    public void setReportNo(String reportNo) {
        this.reportNo = reportNo == null ? null : reportNo.trim();
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

    public Integer getDataStatus() {
        return dataStatus;
    }

    public void setDataStatus(Integer dataStatus) {
        this.dataStatus = dataStatus;
    }

    public Integer getPaidType() {
        return paidType;
    }

    public void setPaidType(Integer paidType) {
        this.paidType = paidType;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getFinishAt() {
        return finishAt;
    }

    public void setFinishAt(Date finishAt) {
        this.finishAt = finishAt;
    }

    public String getFinishBy() {
        return finishBy;
    }

    public void setFinishBy(String finishBy) {
        this.finishBy = finishBy == null ? null : finishBy.trim();
    }

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
    
    
}