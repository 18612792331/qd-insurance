package com.qding.api.call.app.qding.v1_3_0.struct.legou.order;

import java.io.Serializable;

public class OrderPromotion implements Serializable{

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
    
    public OrderPromotion() {

    }

	public OrderPromotion(String promotionId, String promotionName,
			String discount) {
		super();
		this.promotionId = promotionId;
		this.promotionName = promotionName;
		this.discount = discount;
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

	@Override
	public String toString() {
		return "ConfirmOrderPromotion [promotionId=" + promotionId
				+ ", promotionName=" + promotionName + ", discount=" + discount
				+ ", toString()=" + super.toString() + "]";
	}
    
}
