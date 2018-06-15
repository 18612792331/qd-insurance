package com.qding.insurance.vo;

import java.io.Serializable;
import java.util.Date;

//用户保单列表
public class InsurancePolicyInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6137023060874580862L;
	private String id;
	private String piccNo;
	private Integer status;
	// 商品名
	private String wareName;
	// 货品名
	private String skuName;
	private String insurantName;
	private String roomAddress;
	private Date actAt;
	private Date expireAt;
	
	private String memberId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPiccNo() {
		return piccNo;
	}

	public void setPiccNo(String piccNo) {
		this.piccNo = piccNo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getWareName() {
		return wareName;
	}

	public void setWareName(String wareName) {
		this.wareName = wareName;
	}

	public String getSkuName() {
		return skuName;
	}

	public void setSkuName(String skuName) {
		this.skuName = skuName;
	}

	public String getInsurantName() {
		return insurantName;
	}

	public void setInsurantName(String insurantName) {
		this.insurantName = insurantName;
	}

	public String getRoomAddress() {
		return roomAddress;
	}

	public void setRoomAddress(String roomAddress) {
		this.roomAddress = roomAddress;
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

}
