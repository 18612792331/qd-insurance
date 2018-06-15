package com.qding.api.call.app.qding.v2_4_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.CouponsPromotion;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.GoodsPromotion;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.OrderPromotion;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Addresses;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderBase;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.OrderInvoice;
import com.qding.api.call.app.qding.v2_3_0.struct.legou.order.OrderGroup;
import java.io.Serializable;
import java.util.List;

public class Order implements Serializable{

	private static final long serialVersionUID = 6566766289133702835L;

	@ExplainAnnotation(explain = "订单详情")
	private OrderBase orderBase;

	@ExplainAnnotation(explain = "发票信息")
	private OrderInvoice orderInvoice;

	@ExplainAnnotation (explain = "物业地址联系方式")
	private ProjectConcat projectConcat;

	@ExplainAnnotation (explain = "收件人地址")
	private Addresses deliveryAddress;

/*	@ExplainAnnotation(explain = "送货地址")
	private OrderDelivery orderDelivery;*/

	@ExplainAnnotation(explain = "物流配送组")
	private OrderGroup logisticsDis;

	@ExplainAnnotation(explain = "物业自提组")
	private OrderGroup propertySelf;

	@ExplainAnnotation(explain = "订单优惠")
	private List<OrderPromotion> orderPromotiones;

	@ExplainAnnotation(explain = "商品优惠")
	private List<GoodsPromotion> goodsPromotiones;

	@ExplainAnnotation(explain = "千丁券优惠")
	private CouponsPromotion couponCodePromotion;

	@ExplainAnnotation(explain = "物流相关信息")
	private List<LogisticsInfo> logisticsInfoList;

	@ExplainAnnotation(explain = "乐购货品状态",desc = "用于判断是否可签收")
	private Integer legouStatus = 0;

	@ExplainAnnotation(explain = "支付时效提醒",desc = "eg:请于下单后48小时内完成支付")
	private String payRemind;


	public List<LogisticsInfo> getLogisticsInfoList() {
		return logisticsInfoList;
	}

	public void setLogisticsInfoList(List<LogisticsInfo> logisticsInfoList) {
		this.logisticsInfoList = logisticsInfoList;
	}

	public Integer getLegouStatus() {
		return legouStatus;
	}

	public void setLegouStatus(Integer legouStatus) {
		this.legouStatus = legouStatus;
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

/*	public OrderDelivery getOrderDelivery() {
		return orderDelivery;
	}

	public void setOrderDelivery(OrderDelivery orderDelivery) {
		this.orderDelivery = orderDelivery;
	}*/


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

	public OrderGroup getLogisticsDis() {
		return logisticsDis;
	}

	public void setLogisticsDis(OrderGroup logisticsDis) {
		this.logisticsDis = logisticsDis;
	}

	public OrderGroup getPropertySelf() {
		return propertySelf;
	}

	public void setPropertySelf(OrderGroup propertySelf) {
		this.propertySelf = propertySelf;
	}

	public ProjectConcat getProjectConcat() {
		return projectConcat;
	}

	public void setProjectConcat(ProjectConcat projectConcat) {
		this.projectConcat = projectConcat;
	}

	public Addresses getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(Addresses deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getPayRemind() {
		return payRemind;
	}

	public void setPayRemind(String payRemind) {
		this.payRemind = payRemind;
	}

}
