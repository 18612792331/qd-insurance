package com.qding.insurance.rpc.request;

import java.io.Serializable;
import java.util.Date;

public class ValidatePolicyByOrderIDRequest implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3920940452294827349L;
	// 房屋ID
	private String roomID;
	// 订单号
	private String orderNo;
	// 理赔金额
	private String paidMoney;
	// 保障对象ID
	private String policyGuaranteeObjectID;

	// 联系人姓名 电话
	private String contactName;
	private String contactPhone;
	// 出险时间
	private Date reportAt;
	// 事故描述
	private String accidentMemo;
	

	// 现场照片
	 private String accidentImgs;
	// 报案号
	private String reportNo;

	
	public String getAccidentImgs() {
		return accidentImgs;
	}

	public void setAccidentImgs(String accidentImgs) {
		this.accidentImgs = accidentImgs;
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

	public String getPaidMoney() {
		return paidMoney;
	}

	public void setPaidMoney(String paidMoney) {
		this.paidMoney = paidMoney;
	}

	public String getPolicyGuaranteeObjectID() {
		return policyGuaranteeObjectID;
	}

	public void setPolicyGuaranteeObjectID(String policyGuaranteeObjectID) {
		this.policyGuaranteeObjectID = policyGuaranteeObjectID;
	}

	public String getReportNo() {
		return reportNo;
	}

	public void setReportNo(String reportNo) {
		this.reportNo = reportNo;
	}

}
