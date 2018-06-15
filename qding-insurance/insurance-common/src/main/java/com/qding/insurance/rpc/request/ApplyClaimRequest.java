package com.qding.insurance.rpc.request;

import java.io.Serializable;
import java.util.Date;

public class ApplyClaimRequest implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2885675709971724562L;
	// 单证ID,
	private String policyID;
	// 用户MID
	private String memberId;

	// 保障内容ID
	// 现场照片
	private String accidentImgs;

	private String guaranteeItemId;

	// 联系人姓名 电话
	private String contactName;
	private String contactPhone;
	// 出险时间

	private Date happenAt;
	// 估损金额
	private String paidMoney;
	// 事故描述
	private String accidentMemo;

	public String getAccidentImgs() {
		return accidentImgs;
	}

	public void setAccidentImgs(String accidentImgs) {
		this.accidentImgs = accidentImgs;
	}

	public String getPolicyID() {
		return policyID;
	}

	public void setPolicyID(String policyID) {
		this.policyID = policyID;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getGuaranteeItemId() {
		return guaranteeItemId;
	}

	public void setGuaranteeItemId(String guaranteeItemId) {
		this.guaranteeItemId = guaranteeItemId;
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

	public Date getHappenAt() {
		return happenAt;
	}

	public void setHappenAt(Date happenAt) {
		this.happenAt = happenAt;
	}

	public String getPaidMoney() {
		return paidMoney;
	}

	public void setPaidMoney(String paidMoney) {
		this.paidMoney = paidMoney;
	}

	public String getAccidentMemo() {
		return accidentMemo;
	}

	public void setAccidentMemo(String accidentMemo) {
		this.accidentMemo = accidentMemo;
	}

}
