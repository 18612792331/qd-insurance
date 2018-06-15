package com.qding.api.call.app.qding.v1_3_0.struct.wallet;

import java.io.Serializable;

public class AfterChargeWallet implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7995773763035547534L;

	private String orderCode;
    
    private String memberId;
    
    private String totalPrice;
    
    private String shouldPay;
    
    public AfterChargeWallet() {

    }

	public AfterChargeWallet(String orderCode, String memberId,
			String totalPrice, String shouldPay) {
		super();
		this.orderCode = orderCode;
		this.memberId = memberId;
		this.totalPrice = totalPrice;
		this.shouldPay = shouldPay;
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

	@Override
	public String toString() {
		return "AfterCreateOrder [orderCode=" + orderCode + ", memberId="
				+ memberId + ", totalPrice=" + totalPrice + ", shouldPay="
				+ shouldPay + "]";
	}
    
    
}
