package com.qding.api.call.app.qding.v4_0_0.struct.sharecoupon;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by xuxiaoxing on 2018/5/5.
 */
public class SkuDTO implements Serializable{

    @ExplainAnnotation(explain = "skuId")
    private Long skuId;

    @ExplainAnnotation(explain = "商品名称")
    private String name;

    @ExplainAnnotation(explain = "商品图片")
    private String[] wareImgUrl;

    @ExplainAnnotation(explain = "货品图片")
    private String[] skuImgUrl;

    @ExplainAnnotation(explain = "售价")
    private String price;

    @ExplainAnnotation(explain = "市场价")
    private String marketPrice;


    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getWareImgUrl() {
        return wareImgUrl;
    }

    public void setWareImgUrl(String[] wareImgUrl) {
        this.wareImgUrl = wareImgUrl;
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

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }
}
