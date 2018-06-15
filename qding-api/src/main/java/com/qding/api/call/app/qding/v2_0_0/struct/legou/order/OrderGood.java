package com.qding.api.call.app.qding.v2_0_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.Arrays;

public class OrderGood implements Serializable{

	private static final long serialVersionUID = -5666698559560822176L;

	@ExplainAnnotation(explain = "商品名称")
    private String goodsName;

	@ExplainAnnotation(explain = "货品图集")
    private String skuImgUrl[];

	@ExplainAnnotation(explain = "货品描述")
	private String goodsDesc;

	@ExplainAnnotation(explain = "商品ID")
	private Long wareId;

	@ExplainAnnotation(explain = "货品ID")
	private String skuId;

	@ExplainAnnotation(explain = "商品产品线Code")
	private String markingCode = "";

	@ExplainAnnotation(explain = "商品产品线名称")
	private String markingName = "";

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

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public OrderGood() {

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

	public Long getWareId() {
		return wareId;
	}

	public void setWareId(Long wareId) {
		this.wareId = wareId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	@Override
	public String toString() {
		return "OrderGood{" +
				"goodsName='" + goodsName + '\'' +
				", skuImgUrl=" + Arrays.toString(skuImgUrl) +
				", goodsDesc='" + goodsDesc + '\'' +
				", wareId=" + wareId +
				", skuId=" + skuId +
				", markingCode='" + markingCode + '\'' +
				", markingName='" + markingName + '\'' +
				'}';
	}
}
