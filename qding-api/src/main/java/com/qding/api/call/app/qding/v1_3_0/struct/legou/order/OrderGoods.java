package com.qding.api.call.app.qding.v1_3_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.Arrays;

public class OrderGoods implements Serializable{

	private static final long serialVersionUID = -5666698559560822176L;

	@ExplainAnnotation(explain = "主订单号")
	private String orderCode;

	@ExplainAnnotation(explain = "子订单号")
	private String subOrderCode;

	@ExplainAnnotation(explain = "商品ID")
	private String goodsId;

	@ExplainAnnotation(explain = "货品ID")
	private String skuId;

	@ExplainAnnotation(explain = "商品名称")
    private String goodsName;

	@ExplainAnnotation(explain = "商品图片")
    private String skuImgUrl[];

	@ExplainAnnotation(explain = "商品价格")
    private String price;

	@ExplainAnnotation(explain = "购买数量")
    private Integer buyNum;

	@ExplainAnnotation(explain = "收货方式")
    private Integer deliveryType;
    
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

	@Override
	public String toString() {
		return "OrderGoods [goodsId=" + goodsId + ", skuId=" + skuId
				+ ", goodsName=" + goodsName + ", goodsImg="
				+ Arrays.toString(skuImgUrl) + ", price=" + price + ", buyNum="
				+ buyNum + "]";
	}

}
