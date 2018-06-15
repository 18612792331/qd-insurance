package com.qding.api.call.app.qding.v1_3_0.struct.legou.order;

import java.io.Serializable;
import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_3_0.struct.coupon.Coupon;
import com.qding.api.call.app.qding.v1_3_0.struct.coupon.CouponBatch;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Addresses;

public class ConfirmOrder implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7995773763035547534L;

    private String totalPrice;
    
    private String totalDiscountPrice;
    
    private String shouldPay;
    
    private String expressPrice = "0";
    
    //订单优惠
    private List<OrderPromotion> orderPromotiones;
    
    //商品优惠
    private List<GoodsPromotion> goodsPromotiones;
    
    //千丁券优惠
    private CouponsPromotion couponCodePromotion;
    
    private List<Coupon> commonCoupons;
	
	private List<CouponBatch> specialCoupons;
	
	//用户所有可用优惠券总价值
	private String totalCouponsPrice = "0";
	
    private ProjectConcat projectConcat;

	private Addresses deliveryAddress;

	//优惠券金额是否超出实际金额
	private Boolean isShowNotice;

	//备注
	private String notice;

	public Boolean getIsShowNotice() {
		return isShowNotice;
	}

	public void setIsShowNotice(Boolean isShowNotice) {
		this.isShowNotice = isShowNotice;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public ConfirmOrder() {

    }

	public ConfirmOrder(String totalPrice, String totalDiscountPrice,
			String shouldPay, String expressPrice,
			List<OrderPromotion> orderPromotiones,
			List<GoodsPromotion> goodsPromotiones,
			CouponsPromotion couponCodePromotion, List<Coupon> commonCoupons,
			List<CouponBatch> specialCoupons, String totalCouponsPrice,
			ProjectConcat projectConcat, Addresses deliveryAddress,Boolean isShowNotice,String notice) {
		super();
		this.totalPrice = totalPrice;
		this.totalDiscountPrice = totalDiscountPrice;
		this.shouldPay = shouldPay;
		this.expressPrice = expressPrice;
		this.orderPromotiones = orderPromotiones;
		this.goodsPromotiones = goodsPromotiones;
		this.couponCodePromotion = couponCodePromotion;
		this.commonCoupons = commonCoupons;
		this.specialCoupons = specialCoupons;
		this.totalCouponsPrice = totalCouponsPrice;
		this.projectConcat = projectConcat;
		this.deliveryAddress = deliveryAddress;
		this.isShowNotice = isShowNotice;
		this.notice = notice;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getTotalDiscountPrice() {
		return totalDiscountPrice;
	}

	public void setTotalDiscountPrice(String totalDiscountPrice) {
		this.totalDiscountPrice = totalDiscountPrice;
	}

	public String getShouldPay() {
		return shouldPay;
	}

	public void setShouldPay(String shouldPay) {
		this.shouldPay = shouldPay;
	}

	public String getExpressPrice() {
		return expressPrice;
	}

	public void setExpressPrice(String expressPrice) {
		this.expressPrice = expressPrice;
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

	public List<Coupon> getCommonCoupons() {
		return commonCoupons;
	}

	public void setCommonCoupons(List<Coupon> commonCoupons) {
		this.commonCoupons = commonCoupons;
	}

	public List<CouponBatch> getSpecialCoupons() {
		return specialCoupons;
	}

	public void setSpecialCoupons(List<CouponBatch> specialCoupons) {
		this.specialCoupons = specialCoupons;
	}

	public String getTotalCouponsPrice() {
		return totalCouponsPrice;
	}

	public void setTotalCouponsPrice(String totalCouponsPrice) {
		this.totalCouponsPrice = totalCouponsPrice;
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

}
