package com.qding.api.call.app.qding.v2_0_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

public class OrderBase implements Serializable{

	private static final long serialVersionUID = -7166693004333549028L;

	@ExplainAnnotation(explain = "订单号")
	private String orderCode;

	@ExplainAnnotation(explain = "商品总金额",desc = "排除运费，目前只每日鲜使用")
	private String goodsPrice;

	@ExplainAnnotation(explain = "总金额")
	private String totalPrice;

	@ExplainAnnotation(explain = "应付金额")
	private String shouldPay;

	@ExplainAnnotation(explain = "实际支付总金额")
	private String totalRealPay;

	@ExplainAnnotation(explain = "总折扣额")
	private String totalDiscount;

	@ExplainAnnotation(explain = "订单来源",desc = "0:App,1:代客下单,2：微信下单,3:客服下单,4:支付宝服务窗")
	private Integer sourceType;

	@ExplainAnnotation(explain = "订单状态")
	private Integer orderStatus;

	@ExplainAnnotation(explain = "支付状态")
	private Integer paymentStatus;

	@ExplainAnnotation(explain = "支付类型")
	private Integer paymentType;

	@ExplainAnnotation(explain = "支付类型名称")
	private String paymentTypeName;

	@ExplainAnnotation(explain = "是否在线支付")
	private Integer isPayOnline;

	@ExplainAnnotation(explain = "会员ID")
	private String memberId;

	@ExplainAnnotation(explain = "会员名称")
	private String memberName;

	@ExplainAnnotation(explain = "会员手机号")
	private String memberMobile;

	@ExplainAnnotation(explain = "物业地址")
	private String paddress;

	@ExplainAnnotation(explain = "下单时间")
	private Long createTime;

	@ExplainAnnotation(explain = "支付时间")
	private Long paymentTime;

	@ExplainAnnotation(explain = "是否已经评价",desc = "1:已评价，0：未评价")
	private Integer evaluateStatus;


	public String getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(String totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public Integer getSourceType() {
		return sourceType;
	}

	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}

	public OrderBase() {

	}

	public Integer getEvaluateStatus() {
		return evaluateStatus;
	}

	public void setEvaluateStatus(Integer evaluateStatus) {
		this.evaluateStatus = evaluateStatus;
	}

	public String getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(String goodsPrice) {
		this.goodsPrice = goodsPrice;
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

	public String getPaymentTypeName() {
		return paymentTypeName;
	}

	public void setPaymentTypeName(String paymentTypeName) {
		this.paymentTypeName = paymentTypeName;
	}
}
