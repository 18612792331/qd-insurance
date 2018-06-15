package com.qding.api.call.app.qding.v2_0_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.Arrays;

public class OrderGoods implements Serializable{

	private static final long serialVersionUID = -5666698559560822176L;

	@ExplainAnnotation(explain = "订单号")
	private String orderCode;

	@ExplainAnnotation(explain = "子订单号")
	private String subOrderCode;

	@ExplainAnnotation(explain = "商品ID")
	private String goodsId;

	@ExplainAnnotation(explain = "货品ID")
	private String skuId;

	@ExplainAnnotation(explain = "商品名称")
    private String goodsName;

	@ExplainAnnotation(explain = "货品图片组地址")
    private String skuImgUrl[];

	@ExplainAnnotation(explain = "金额")
    private String price;

	@ExplainAnnotation(explain = "购买数")
    private Integer buyNum;

	@ExplainAnnotation(explain = "送货类型")
    private Integer deliveryType;

	@ExplainAnnotation(explain = "商品描述")
	private String goodsDesc;

	@ExplainAnnotation(explain = "是否支持七天限购",desc = " 1支持 0不支持")
	private Integer isSdnrtr;

	@ExplainAnnotation(explain = "第三方订单号")
	private String thirdOrderCode;

	@ExplainAnnotation(explain = "第三方货品编号")
	private String thirdSkuBn;

	public Integer getIsSdnrtr() {
		return isSdnrtr;
	}

	public void setIsSdnrtr(Integer isSdnrtr) {
		this.isSdnrtr = isSdnrtr;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public OrderGoods() {

    }

    public String getSubOrderCode() {
		return subOrderCode;
	}
    
    public void setSubOrderCode(String subOrderCode) {
		this.subOrderCode = subOrderCode;
	}
    
    public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
    
    public String getOrderCode() {
		return orderCode;
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

	public String getThirdOrderCode() {
		return thirdOrderCode;
	}

	public void setThirdOrderCode(String thirdOrderCode) {
		this.thirdOrderCode = thirdOrderCode;
	}

	public String getThirdSkuBn() {
		return thirdSkuBn;
	}

	public void setThirdSkuBn(String thirdSkuBn) {
		this.thirdSkuBn = thirdSkuBn;
	}

	public OrderGoods(String orderCode, String subOrderCode, String goodsId, String skuId, String goodsName, String[] skuImgUrl, String price, Integer buyNum, Integer deliveryType, String goodsDesc) {
		this.orderCode = orderCode;
		this.subOrderCode = subOrderCode;
		this.goodsId = goodsId;
		this.skuId = skuId;
		this.goodsName = goodsName;
		this.skuImgUrl = skuImgUrl;
		this.price = price;
		this.buyNum = buyNum;
		this.deliveryType = deliveryType;
		this.goodsDesc = goodsDesc;
	}

	public OrderGoods( String goodsId, String skuId, String goodsName, String[] skuImgUrl, String price, Integer buyNum, Integer deliveryType, String goodsDesc) {
		this.goodsId = goodsId;
		this.skuId = skuId;
		this.goodsName = goodsName;
		this.skuImgUrl = skuImgUrl;
		this.price = price;
		this.buyNum = buyNum;
		this.deliveryType = deliveryType;
		this.goodsDesc = goodsDesc;
	}

	@Override
	public String toString() {
		return "OrderGoods{" +
				"orderCode='" + orderCode + '\'' +
				", subOrderCode='" + subOrderCode + '\'' +
				", goodsId='" + goodsId + '\'' +
				", skuId='" + skuId + '\'' +
				", goodsName='" + goodsName + '\'' +
				", skuImgUrl=" + Arrays.toString(skuImgUrl) +
				", price='" + price + '\'' +
				", buyNum=" + buyNum +
				", deliveryType=" + deliveryType +
				", goodsDesc='" + goodsDesc + '\'' +
				", isSdnrtr=" + isSdnrtr +
				", thirdOrderCode='" + thirdOrderCode + '\'' +
				", thirdSkuBn='" + thirdSkuBn + '\'' +
				'}';
	}
}
