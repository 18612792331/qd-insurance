package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2015/12/30.
 */
public class RecommendGood extends SkipUrl  implements Serializable {

    private static final long serialVersionUID = 2411968631210851107L;

    @ExplainAnnotation(explain = "商品ID")
    private String goodsId;

    @ExplainAnnotation(explain = "货品ID")
    private String skuId;

    @ExplainAnnotation(explain = "商品名称")
    private String goodsName;

    @ExplainAnnotation(explain = "商品图片")
    private String goodsImg;

    @ExplainAnnotation(explain = "市场价格")
    private String originalPrice;

    @ExplainAnnotation(explain = "当前价格")
    private String price;

    @ExplainAnnotation(explain = "卖出数量")
    private Integer sellCount;

    public RecommendGood(){
    }

    public RecommendGood(String goodsId, String skuId, String goodsName, String goodsImg, String originalPrice, String price, Integer sellCount) {
        this.goodsId = goodsId;
        this.skuId = skuId;
        this.goodsName = goodsName;
        this.goodsImg = goodsImg;
        this.originalPrice = originalPrice;
        this.price = price;
        this.sellCount = sellCount;
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

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
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

    public Integer getSellCount() {
        return sellCount;
    }

    public void setSellCount(Integer sellCount) {
        this.sellCount = sellCount;
    }
}
