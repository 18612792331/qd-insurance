package com.qding.api.call.app.qding.v2_0_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.CouponsPromotion;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.GoodsPromotion;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.OrderPromotion;

import java.io.Serializable;
import java.util.List;

public class Order  implements Serializable{

	private static final long serialVersionUID = 6566766289133702835L;

	@ExplainAnnotation(explain = "订单详情")
	private OrderBase orderBase;

	@ExplainAnnotation(explain = "收货地址",desc = "前端展示用地址")
	private String receiveAddr;

	@ExplainAnnotation(explain = "发票信息")
	private OrderInvoice orderInvoice;

	@ExplainAnnotation(explain = "送货地址")
	private OrderDelivery orderDelivery;

	@ExplainAnnotation(explain = "订单商品列表")
	private List<OrderGoods> orderGoods;

	@ExplainAnnotation(explain = "订单优惠")
	private List<OrderPromotion> orderPromotiones;

	@ExplainAnnotation(explain = "商品优惠")
	private List<GoodsPromotion> goodsPromotiones;

	@ExplainAnnotation(explain = "千丁券优惠")
	private CouponsPromotion couponCodePromotion;

	@ExplainAnnotation(explain = "物流相关信息")
	private LogisticsInfo logisticsInfo;

	@ExplainAnnotation(explain = "乐购货品状态",desc = "用于判断是否可签收")
	private Integer legouStatus = 0;

	public Integer getLegouStatus() {
		return legouStatus;
	}

	public void setLegouStatus(Integer legouStatus) {
		this.legouStatus = legouStatus;
	}

	public LogisticsInfo getLogisticsInfo() {
		return logisticsInfo;
	}

	public void setLogisticsInfo(LogisticsInfo logisticsInfo) {
		this.logisticsInfo = logisticsInfo;
	}

	public OrderBase getOrderBase() {
		return orderBase;
	}

	public void setOrderBase(OrderBase orderBase) {
		this.orderBase = orderBase;
	}

	public OrderInvoice getOrderInvoice() {
		return orderInvoice;
	}

	public void setOrderInvoice(OrderInvoice orderInvoice) {
		this.orderInvoice = orderInvoice;
	}

	public OrderDelivery getOrderDelivery() {
		return orderDelivery;
	}

	public void setOrderDelivery(OrderDelivery orderDelivery) {
		this.orderDelivery = orderDelivery;
	}

	public List<OrderGoods> getOrderGoods() {
		return orderGoods;
	}

	public void setOrderGoods(List<OrderGoods> orderGoods) {
		this.orderGoods = orderGoods;
	}

	public List<OrderPromotion> getOrderPromotiones() {
		return orderPromotiones;
	}

	public void setOrderPromotiones(List<OrderPromotion> orderPromotiones) {
		this.orderPromotiones = orderPromotiones;
	}

	public List<GoodsPromotion> getGoodsPromotiones() {
		return goodsPromotiones;
	}

	public void setGoodsPromotiones(List<GoodsPromotion> goodsPromotiones) {
		this.goodsPromotiones = goodsPromotiones;
	}

	public CouponsPromotion getCouponCodePromotion() {
		return couponCodePromotion;
	}

	public void setCouponCodePromotion(CouponsPromotion couponCodePromotion) {
		this.couponCodePromotion = couponCodePromotion;
	}

	public String getReceiveAddr() {
		return receiveAddr;
	}

	public void setReceiveAddr(String receiveAddr) {
		this.receiveAddr = receiveAddr;
	}

}
