package com.qding.api.call.app.qding.v1_3_0.struct.wallet;

import java.io.Serializable;

public class AccountDetail implements Serializable{
	
	

	private static final long serialVersionUID = 284768936828344183L;
	

	/**
     *  订单号
     */
    private String orderCode;
    
    /**
     *  会员id
     */
    private String memberId;
    
    /**
     *  操作预存款数(单位元)
     */
    private String optPredepositCash;


	/**
	 * 当前时间点的预存款
	 */
	private String availablePredepositCash;
    
    
    /**
     *  业态名称
     */
    private String productName;
    
    /**
     *  操作时间
     */
    private Long optTime;

    
    /**
     * 业态编号
     */
    private String productNo;

	/**
	 * 支付或收入类型
	 */
	private Integer optTypeValue;

	/**
	 * 是否是亲情支付
	 */
	private Integer isFamilyPay = 0;


	/**
	 *  支付方式
	 */
	private String payTypeName;

	public String getPayTypeName() {
		return payTypeName;
	}

	public void setPayTypeName(String payTypeName) {
		this.payTypeName = payTypeName;
	}

	public Integer getOptTypeValue() {
		return optTypeValue;
	}

	public String getAvailablePredepositCash() {
		return availablePredepositCash;
	}

	public void setAvailablePredepositCash(String availablePredepositCash) {
		this.availablePredepositCash = availablePredepositCash;
	}

	public String getOrderCode() {
		return orderCode;
	}


	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	public String getOptPredepositCash() {
		return optPredepositCash;
	}


	public void setOptPredepositCash(String optPredepositCash) {
		this.optPredepositCash = optPredepositCash;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Long getOptTime() {
		return optTime;
	}


	public void setOptTime(Long optTime) {
		this.optTime = optTime;
	}


	public String getProductNo() {
		return productNo;
	}


	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}


	public void setOptTypeValue(Integer optTypeValue) {
		this.optTypeValue = optTypeValue;
	}

	public Integer getIsFamilyPay() {
		return isFamilyPay;
	}

	public void setIsFamilyPay(Integer isFamilyPay) {
		this.isFamilyPay = isFamilyPay;
	}

	public AccountDetail () {
		
	}
    
    

}
