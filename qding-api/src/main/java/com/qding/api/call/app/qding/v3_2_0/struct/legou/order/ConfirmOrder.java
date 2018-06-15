package com.qding.api.call.app.qding.v3_2_0.struct.legou.order;

import java.io.Serializable;
import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.ProjectConcat;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.CouponsPromotion;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.GoodsPromotion;
import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.OrderPromotion;
import com.qding.api.call.app.qding.v1_3_0.struct.user.Addresses;
import com.qding.api.call.app.qding.v3_2_0.struct.coupon.CouponDto;
import com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.SkuGrouponInfo;

public class ConfirmOrder implements Serializable{

	private static final long serialVersionUID = 7995773763035547534L;

	@ExplainAnnotation (explain = "订单总金额")
    private String totalPrice;

	@ExplainAnnotation (explain = "总折扣金额")
    private String totalDiscountPrice;

	@ExplainAnnotation (explain = "应付金额")
    private String shouldPay;

	@ExplainAnnotation (explain = "快递费")
    private String expressPrice = "0";

	@ExplainAnnotation (explain = "订单优惠")
    private List<OrderPromotion> orderPromotiones;
    
	@ExplainAnnotation (explain = "商品优惠")
    private List<GoodsPromotion> goodsPromotiones;
    
	@ExplainAnnotation (explain = "千丁券优惠")
    private CouponsPromotion couponCodePromotion;

	@ExplainAnnotation (explain = "可用可用优惠券")
    private List<CouponDto> commonCoupons;

	@ExplainAnnotation (explain = "不可用优惠券")
	private List<CouponDto> unavailableCommonCoupons;

	@ExplainAnnotation (explain = "用户所有可用优惠券总价值")
	private String totalCouponsPrice = "0";

	@ExplainAnnotation (explain = "物业地址联系方式")
    private ProjectConcat projectConcat;

	@ExplainAnnotation (explain = "收件人地址")
	private Addresses deliveryAddress;

	@ExplainAnnotation (explain = "优惠券是否可用")
	private Boolean isCanUseCoupon ;

	@ExplainAnnotation (explain = "优惠券金额是否超出实际金额")
	private Boolean isShowNotice;

	@ExplainAnnotation (explain = "备注")
	private String notice;

	@ExplainAnnotation (explain = "是否有多个定点自提地址",desc ="1:有，0:无" )
	private Integer isMoreProjectAddr = 0 ;
	
	@ExplainAnnotation(explain = "阶梯团购信息  3.3新增")
	private SkuGrouponInfo groupon;


	public Boolean getCanUseCoupon() {
		return isCanUseCoupon;
	}

	public void setCanUseCoupon(Boolean canUseCoupon) {
		isCanUseCoupon = canUseCoupon;
	}

	public Boolean getShowNotice() {
		return isShowNotice;
	}

	public void setShowNotice(Boolean showNotice) {
		isShowNotice = showNotice;
	}

	public Integer getIsMoreProjectAddr() {
		return isMoreProjectAddr;
	}

	public void setIsMoreProjectAddr(Integer isMoreProjectAddr) {
		this.isMoreProjectAddr = isMoreProjectAddr;
	}

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

	public List<CouponDto> getUnavailableCommonCoupons() {
		return unavailableCommonCoupons;
	}

	public void setUnavailableCommonCoupons(List<CouponDto> unavailableCommonCoupons) {
		this.unavailableCommonCoupons = unavailableCommonCoupons;
	}

	public ConfirmOrder() {

    }

	public ConfirmOrder(String totalPrice, String totalDiscountPrice,
						String shouldPay, String expressPrice,
						List<OrderPromotion> orderPromotiones,
						List<GoodsPromotion> goodsPromotiones,
						CouponsPromotion couponCodePromotion, List<CouponDto> commonCoupons,
						 String totalCouponsPrice,
						ProjectConcat projectConcat, Addresses deliveryAddress,Boolean isCanUseCoupon,Boolean isShowNotice,String notice) {
		super();
		this.totalPrice = totalPrice;
		this.totalDiscountPrice = totalDiscountPrice;
		this.shouldPay = shouldPay;
		this.expressPrice = expressPrice;
		this.orderPromotiones = orderPromotiones;
		this.goodsPromotiones = goodsPromotiones;
		this.couponCodePromotion = couponCodePromotion;
		this.commonCoupons = commonCoupons;
		this.totalCouponsPrice = totalCouponsPrice;
		this.projectConcat = projectConcat;
		this.deliveryAddress = deliveryAddress;
		this.isCanUseCoupon = isCanUseCoupon;
		this.isShowNotice = isShowNotice;
		this.notice = notice;
	}

	public Boolean getIsCanUseCoupon() {
		return isCanUseCoupon;
	}

	public void setIsCanUseCoupon(Boolean isCanUseCoupon) {
		this.isCanUseCoupon = isCanUseCoupon;
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

	public List<CouponDto> getCommonCoupons() {
		return commonCoupons;
	}

	public void setCommonCoupons(List<CouponDto> commonCoupons) {
		this.commonCoupons = commonCoupons;
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

	public SkuGrouponInfo getGroupon() {
		return groupon;
	}

	public void setGroupon(SkuGrouponInfo groupon) {
		this.groupon = groupon;
	}
	
	
	
	
	
	
	

}
