package com.qding.api.call.app.qding.v1_3_0.struct.legou.order;

import java.io.Serializable;
import java.util.List;

public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6566766289133702835L;

	private OrderBase orderBase;
    
	private OrderInvoice orderInvoice;
	
	private OrderDelivery orderDelivery;
	
    private List<OrderGoods> orderGoods;
    
    //订单优惠
    private List<OrderPromotion> orderPromotiones;
    
    //商品优惠
    private List<GoodsPromotion> goodsPromotiones;
    
    //千丁券优惠
    private CouponsPromotion couponCodePromotion;
    
    public Order() {

    }

	public Order(OrderBase orderBase, OrderInvoice orderInvoice,
			OrderDelivery orderDelivery, List<OrderGoods> orderGoods,
			List<OrderPromotion> orderPromotiones,
			List<GoodsPromotion> goodsPromotiones,
			CouponsPromotion couponCodePromotion) {
		super();
		this.orderBase = orderBase;
		this.orderInvoice = orderInvoice;
		this.orderDelivery = orderDelivery;
		this.orderGoods = orderGoods;
		this.orderPromotiones = orderPromotiones;
		this.goodsPromotiones = goodsPromotiones;
		this.couponCodePromotion = couponCodePromotion;
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

}
