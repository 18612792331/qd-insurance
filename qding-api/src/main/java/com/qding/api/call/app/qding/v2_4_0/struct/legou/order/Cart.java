package com.qding.api.call.app.qding.v2_4_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsPromotion;

import java.io.Serializable;
import java.util.List;

public class Cart implements Serializable{

	private static final long serialVersionUID = -4974685189319220664L;

	@ExplainAnnotation(explain = "乐购产品线Code")
	private String markingCode;

	@ExplainAnnotation(explain = "乐购产品线名称")
	private String markingName;

	@ExplainAnnotation(explain = "商品ID")
	private String goodsId;

	@ExplainAnnotation(explain = "货品ID")
	private String skuId;

	@ExplainAnnotation(explain = "商品名称")
    private String goodsName;

	@ExplainAnnotation(explain = "货品图")
    private String skuImgUrl[];
    
	@ExplainAnnotation(explain = "销售价")
    private String price;
    
	@ExplainAnnotation(explain = "市场价")
    private String originalPrice;

	@ExplainAnnotation(explain = "促销价")
	private String promotionPrice;

	@ExplainAnnotation(explain = "购买数")
    private Integer buyNum;

	@ExplainAnnotation(explain = "剩余可购买数")
	private Integer surplusBuyNum;

	@ExplainAnnotation(explain = "限购总数")
	private Integer restrictionBuyNum;

	@ExplainAnnotation(explain = "送货方式")
    private Integer deliveryType;

	@ExplainAnnotation(explain = "规格")
	private String[] spe;

	@ExplainAnnotation(explain = "规格属性")
	private String[] speInfo;

	@ExplainAnnotation(explain = "乐购商品促销标签集合")
	private List<GoodsPromotion> goodsPromotionList;


	@ExplainAnnotation(explain = "是否支持超出限购原价购买",desc = "0：不支持 1：支持")
	private Integer isSupportSell = 0;
	
	@ExplainAnnotation(explain = "无效状态 有效, 失效 ,限购,售罄, 阶梯团购 ",desc = "3.3新增")
	private String statusTag;

	public Integer getIsSupportSell() {
		return isSupportSell;
	}

	public void setIsSupportSell(Integer isSupportSell) {
		this.isSupportSell = isSupportSell;
	}

	public Cart() {

    }

	public Integer getSurplusBuyNum() {
		return surplusBuyNum;
	}

	public void setSurplusBuyNum(Integer surplusBuyNum) {
		this.surplusBuyNum = surplusBuyNum;
	}

	public Integer getRestrictionBuyNum() {
		return restrictionBuyNum;
	}

	public void setRestrictionBuyNum(Integer restrictionBuyNum) {
		this.restrictionBuyNum = restrictionBuyNum;
	}

	public String[] getSpe() {
		return spe;
	}

	public void setSpe(String[] spe) {
		this.spe = spe;
	}

	public String[] getSpeInfo() {
		return speInfo;
	}

	public void setSpeInfo(String[] speInfo) {
		this.speInfo = speInfo;
	}

	public String getMarkingCode() {
		return markingCode;
	}

	public void setMarkingCode(String markingCode) {
		this.markingCode = markingCode;
	}

	public String getMarkingName() {
		return markingName;
	}

	public void setMarkingName(String markingName) {
		this.markingName = markingName;
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

	public String getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(String originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

	public Integer getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(Integer deliveryType) {
		this.deliveryType = deliveryType;
	}

	public List<GoodsPromotion> getGoodsPromotionList() {
		return goodsPromotionList;
	}

	public void setGoodsPromotionList(List<GoodsPromotion> goodsPromotionList) {
		this.goodsPromotionList = goodsPromotionList;
	}

	public String getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(String promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public Cart(String markingCode, String makingName, String goodsId, String skuId, String goodsName, String[] skuImgUrl, String price,
				String originalPrice,String promotionPrice, Integer buyNum, Integer deliveryType, List<GoodsPromotion> goodsPromotionList, String[] spe,
				String[] speInfo, Integer surplusBuyNum, Integer restrictionBuyNum, Integer isSupportSell) {
		this.markingCode = markingCode;
		this.markingName = makingName;
		this.goodsId = goodsId;
		this.skuId = skuId;
		this.goodsName = goodsName;
		this.skuImgUrl = skuImgUrl;
		this.price = price;
		this.originalPrice = originalPrice;
		this.promotionPrice = promotionPrice;
		this.buyNum = buyNum;
		this.deliveryType = deliveryType;
		this.goodsPromotionList = goodsPromotionList;
		this.spe = spe;
		this.speInfo = speInfo;
		this.surplusBuyNum = surplusBuyNum;
		this.restrictionBuyNum = restrictionBuyNum;
		this.isSupportSell = isSupportSell;
	}

	public String getStatusTag() {
		return statusTag;
	}

	public void setStatusTag(String statusTag) {
		this.statusTag = statusTag;
	}

	
	
	


}
