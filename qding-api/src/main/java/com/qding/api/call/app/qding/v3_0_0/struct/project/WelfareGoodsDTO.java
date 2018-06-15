package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/5.
 */
public class WelfareGoodsDTO extends BtnSkip implements Serializable {

    private static final long serialVersionUID = 2390854874948985102L;

    @ExplainAnnotation(explain = "标题")
    private String title ;

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

    @ExplainAnnotation(explain = "数量")
    private Integer sellCount;

    @ExplainAnnotation(explain = "促销开始时间",desc = "例如：秒杀")
    private Long promotionStartTime;

    @ExplainAnnotation(explain = "类型",desc = "1:乐购，2:秒杀，3:通用业态,4:其他")
    private Integer type;

    @ExplainAnnotation (explain = "剩余数量")
    private Integer surplusCount;

    @ExplainAnnotation (explain = "简介")
    private String desc;

    @ExplainAnnotation (explain = "UI渲染类型",desc = "1:url ,2:其他")
    private Integer uiType;

    @ExplainAnnotation (explain = "秒杀状态",desc = "-1:未开始，0:进行，1：已结束")
    private Integer status;

    @ExplainAnnotation(explain = "是否为有限库存",desc = "1:无限库存，0：有限库存")
    private int unlimitedStock;

    public int getUnlimitedStock() {
        return unlimitedStock;
    }

    public void setUnlimitedStock(int unlimitedStock) {
        this.unlimitedStock = unlimitedStock;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUiType() {
        return uiType;
    }

    public void setUiType(Integer uiType) {
        this.uiType = uiType;
    }

    public Integer getSurplusCount() {
        return surplusCount;
    }

    public void setSurplusCount(Integer surplusCount) {
        this.surplusCount = surplusCount;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Long getPromotionStartTime() {
        return promotionStartTime;
    }

    public void setPromotionStartTime(Long promotionStartTime) {
        this.promotionStartTime = promotionStartTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
