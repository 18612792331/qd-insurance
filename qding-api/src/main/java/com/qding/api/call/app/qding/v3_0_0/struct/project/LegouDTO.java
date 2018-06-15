package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;


/**
 * Created by qd on 2017/9/7.
 */
public class LegouDTO extends SkipUrl{

    @ExplainAnnotation(explain = "运营位置")
    private Integer placeIndex;

    @ExplainAnnotation(explain = "活动标签")
    private String activityTag;

    @ExplainAnnotation(explain = "标题")
    private String title;

    @ExplainAnnotation(explain = "活动描述",desc = "副标题")
    private String desc;

    @ExplainAnnotation(explain = "商品图片")
    private String goodsImg;

    @ExplainAnnotation(explain = "市场价格")
    private String originalPrice;

    @ExplainAnnotation(explain = "当前价格")
    private String price;

    public String getActivityTag() {
        return activityTag;
    }

    public void setActivityTag(String activityTag) {
        this.activityTag = activityTag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Integer getPlaceIndex() {
        return placeIndex;
    }

    public void setPlaceIndex(Integer placeIndex) {
        this.placeIndex = placeIndex;
    }
}
