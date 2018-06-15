package com.qding.api.call.app.qding.v1_3_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

public class AfterCreateOrder implements Serializable{

	private static final long serialVersionUID = 7995773763035547534L;

	@ExplainAnnotation(explain = "订单号")
	private String orderCode;

	@ExplainAnnotation (explain = "会员ID")
	private String memberId;

	@ExplainAnnotation (explain = "总金额")
	private String totalPrice;

	@ExplainAnnotation (explain = "应付金额")
	private String shouldPay;

	@ExplainAnnotation (explain = "支付状态")
	private Integer paymentStatus;

	@ExplainAnnotation (explain = "订单状态")
	private Integer orderStatus;

	@ExplainAnnotation (explain = "下单时间")
	private Long creatOrderAt;
    
    public AfterCreateOrder() {

    }


	public Integer getPaymentStatus() {
		return paymentStatus;
	}



	public void setPaymentStatus(Integer paymentStatus) {
		this.paymentStatus = paymentStatus;
	}



	public Integer getOrderStatus() {
		return orderStatus;
	}



	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
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

	public Long getCreatOrderAt() {
		return creatOrderAt;
	}

	public void setCreatOrderAt(Long creatOrderAt) {
		this.creatOrderAt = creatOrderAt;
	}

	@Override
	public String toString() {
		return "AfterCreateOrder{" +
				"orderCode='" + orderCode + '\'' +
				", memberId='" + memberId + '\'' +
				", totalPrice='" + totalPrice + '\'' +
				", shouldPay='" + shouldPay + '\'' +
				", paymentStatus=" + paymentStatus +
				", orderStatus=" + orderStatus +
				", creatOrderAt=" + creatOrderAt +
				'}';
	}
}
