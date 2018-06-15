package com.qding.api.call.app.qding.v1_3_0.struct.legou.order;

import java.io.Serializable;

public class OrderBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7166693004333549028L;
	
	private String orderCode;
	private String totalPrice;
	private String shouldPay;
	private String totalRealPay;
	private Integer orderStatus;
	private Integer paymentStatus;
	private Integer paymentType;
	private Integer isPayOnline;
	private String memberId;
	private String memberName;
	private String memberMobile;
	private String paddress;
	private Long createTime;
	private Long paymentTime;

	public OrderBase() {

	}

	


	public OrderBase(String orderCode, String totalPrice, String shouldPay,
			String totalRealPay, Integer orderStatus, Integer paymentStatus,
			Integer paymentType, Integer isPayOnline, String memberId,
			String memberName, String memberMobile, String paddress,
			Long createTime, Long paymentTime) {
		super();
		this.orderCode = orderCode;
		this.totalPrice = totalPrice;
		this.shouldPay = shouldPay;
		this.totalRealPay = totalRealPay;
		this.orderStatus = orderStatus;
		this.paymentStatus = paymentStatus;
		this.paymentType = paymentType;
		this.isPayOnline = isPayOnline;
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberMobile = memberMobile;
		this.paddress = paddress;
		this.createTime = createTime;
		this.paymentTime = paymentTime;
	}



	public String getTotalRealPay() {
		return totalRealPay;
	}




	public void setTotalRealPay(String totalRealPay) {
		this.totalRealPay = totalRealPay;
	}




	public Integer getIsPayOnline() {
		return isPayOnline;
	}
	
	public void setIsPayOnline(Integer isPayOnline) {
		this.isPayOnline = isPayOnline;
	}
	
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

	public String getShouldPay() {
		return shouldPay;
	}

	public void setShouldPay(String shouldPay) {
		this.shouldPay = shouldPay;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Integer getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(Integer paymentType) {
		this.paymentType = paymentType;
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

	public String getPaddress() {
		return paddress;
	}

	public void setPaddress(String paddress) {
		this.paddress = paddress;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Long paymentTime) {
		this.paymentTime = paymentTime;
	}

}
