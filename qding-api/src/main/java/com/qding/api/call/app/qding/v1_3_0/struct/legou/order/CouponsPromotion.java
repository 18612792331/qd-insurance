package com.qding.api.call.app.qding.v1_3_0.struct.legou.order;

import java.io.Serializable;

public class CouponsPromotion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8010747950053130079L;

	 /**
     * 优惠活动ID
     */
    private String promotionId;
    
	 /**
     * 优惠活动名称
     */
    private String promotionName;
    
    /**
     * 折扣额
     */
    private String discount;

	/**
	 * 使用的优惠券总额
	 */
	private String totalCouponPrice;
    
    public CouponsPromotion() {

    }

	public CouponsPromotion(String promotionId, String promotionName,
			String discount) {
		super();
		this.promotionId = promotionId;
		this.promotionName = promotionName;
		this.discount = discount;
	}

	public CouponsPromotion(String promotionId, String promotionName,
							String discount,String totalCouponPrice) {
		super();
		this.promotionId = promotionId;
		this.promotionName = promotionName;
		this.discount = discount;
		this.totalCouponPrice =totalCouponPrice;
	}

	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getTotalCouponPrice() {
		return totalCouponPrice;
	}

	public void setTotalCouponPrice(String totalCouponPrice) {
		this.totalCouponPrice = totalCouponPrice;
	}

	@Override
	public String toString() {
		return "ConfirmOrderPromotion [promotionId=" + promotionId
				+ ", promotionName=" + promotionName + ", discount=" + discount
				+ ", toString()=" + super.toString() + "]";
	}
    
}
