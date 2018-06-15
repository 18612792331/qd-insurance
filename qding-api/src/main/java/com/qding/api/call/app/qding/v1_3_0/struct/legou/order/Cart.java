package com.qding.api.call.app.qding.v1_3_0.struct.legou.order;

import java.io.Serializable;

public class Cart implements Serializable{

	private static final long serialVersionUID = -4974685189319220664L;

	private String goodsId;
	
	private String skuId;
    
    private String goodsName;
    
    private String skuImgUrl[];
    
    //销售价
    private String price;
    
    //市场价
    private String originalPrice;
    
    private Integer buyNum;
    
    private Integer deliveryType;
    
    /**
	 * 优惠活动
	 */
	private String[] activityInfo;
	
    public Cart() {

    }

    public String[] getActivityInfo() {
		return activityInfo;
	}

	public void setActivityInfo(String[] activityInfo) {
		this.activityInfo = activityInfo;
	}

	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}
    
    public Integer getDeliveryType() {
		return deliveryType;
	}
    
    
	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String[] getSkuImgUrl() {
		return skuImgUrl;
	}
	
	public void setSkuImgUrl(String[] skuImgUrl) {
		this.skuImgUrl = skuImgUrl;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}
	
	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	
	public Cart(String goodsId, String skuId, String goodsName,
			String[] skuImgUrl, String price, String originalPrice,
			Integer buyNum, Integer deliveryType, String[] activityInfo) {
		super();
		this.goodsId = goodsId;
		this.skuId = skuId;
		this.goodsName = goodsName;
		this.skuImgUrl = skuImgUrl;
		this.price = price;
		this.originalPrice = originalPrice;
		this.buyNum = buyNum;
		this.deliveryType = deliveryType;
		this.activityInfo = activityInfo;
	}
}
