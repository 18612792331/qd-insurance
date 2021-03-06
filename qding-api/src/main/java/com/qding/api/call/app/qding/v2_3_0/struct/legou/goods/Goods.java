package com.qding.api.call.app.qding.v2_3_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

public class Goods implements Serializable{
	
	private static final long serialVersionUID = 3194082312753700992L;

	@ExplainAnnotation(explain = "商品ID")
	private String goodsId;
	
	@ExplainAnnotation(explain = "货品ID")
	private String skuId;
	
	@ExplainAnnotation(explain = "商品描述")
	private String goodsDesc;

	@ExplainAnnotation(explain = "商品名称")
	private String goodsName;
	
	@ExplainAnnotation(explain = "商品图片")
	private String[] goodsImg;
	
	@ExplainAnnotation(explain = "市场价格")
	private String originalPrice;
	
	@ExplainAnnotation(explain = "当前价格")
	private String price;
	
	@ExplainAnnotation(explain = "收藏数")
	private String praiseCount;
	
	@ExplainAnnotation(explain = "优惠活动")
	private String[] activityInfo;

	@ExplainAnnotation(explain = "规则缩略图")
	private String[] skuImgUrl;

	@ExplainAnnotation(explain = "货品已售数量")
	private Long countSkuSellNum = 0L;

	@ExplainAnnotation(explain = "商品已售数量")
	private Long countWareSellNum = 0L;

	@ExplainAnnotation(explain = "是否送货上门或自提",desc = "1：上门  2：自取")
	private Integer deliveryType ;

	@ExplainAnnotation(explain = "是否可加入购物车",desc = " 1：可以 0：不可以")
	private Integer supportCart=1;

	public Integer getSupportCart() {
		return supportCart;
	}

	public void setSupportCart(Integer supportCart) {
		this.supportCart = supportCart;
	}

	public Integer getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}

	public Long getCountWareSellNum() {
		return countWareSellNum;
	}

	public void setCountWareSellNum(Long countWareSellNum) {
		this.countWareSellNum = countWareSellNum;
	}

	public Long getCountSkuSellNum() {
		return countSkuSellNum;
	}

	public void setCountSkuSellNum(Long countSkuSellNum) {
		this.countSkuSellNum = countSkuSellNum;
	}

	public String[] getSkuImgUrl() {
		return skuImgUrl;
	}

	public void setSkuImgUrl(String[] skuImgUrl) {
		this.skuImgUrl = skuImgUrl;
	}

	/**
	 * @return the activityInfo
	 */
	public String[] getActivityInfo() {
		return activityInfo;
	}

	/**
	 * @param activityInfo the activityInfo to set
	 */
	public void setActivityInfo(String[] activityInfo) {
		this.activityInfo = activityInfo;
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

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String[] getGoodsImg() {
		return goodsImg;
	}

	public void setGoodsImg(String[] goodsImg) {
		this.goodsImg = goodsImg;
	}

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPraiseCount() {
		return praiseCount;
	}

	public void setPraiseCount(String praiseCount) {
		this.praiseCount = praiseCount;
	}

	
	public Goods(){
		
	}

}
