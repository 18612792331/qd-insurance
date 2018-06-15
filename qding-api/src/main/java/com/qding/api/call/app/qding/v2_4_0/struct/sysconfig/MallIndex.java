package com.qding.api.call.app.qding.v2_4_0.struct.sysconfig;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

public class MallIndex implements Serializable {

    @ExplainAnnotation(explain = "品类")
    private List<CategoryInfo> categoryInfoList;

    @ExplainAnnotation(explain = "banner")
    private List<BannerInfo> bannerInfoList;

    @ExplainAnnotation(explain = "栏目")
    private List<ItemInfo> itemInfoList;

    @ExplainAnnotation(explain = "推荐")
    private List<RecommendInfo> recommendInfoList;

    public List<CategoryInfo> getCategoryInfoList() {
        return categoryInfoList;
    }

    public void setCategoryInfoList(List<CategoryInfo> categoryInfoList) {
        this.categoryInfoList = categoryInfoList;
    }

    public List<BannerInfo> getBannerInfoList() {
        return bannerInfoList;
    }

    public void setBannerInfoList(List<BannerInfo> bannerInfoList) {
        this.bannerInfoList = bannerInfoList;
    }

    public List<ItemInfo> getItemInfoList() {
        return itemInfoList;
    }

    public void setItemInfoList(List<ItemInfo> itemInfoList) {
        this.itemInfoList = itemInfoList;
    }

    public List<RecommendInfo> getRecommendInfoList() {
        return recommendInfoList;
    }

    public void setRecommendInfoList(List<RecommendInfo> recommendInfoList) {
        this.recommendInfoList = recommendInfoList;
    }

    @Override
    public String toString() {
        return "MallIndex{" +
                "categoryInfoList=" + categoryInfoList +
                ", bannerInfoList=" + bannerInfoList +
                ", itemInfoList=" + itemInfoList +
                ", recommendInfoList=" + recommendInfoList +
                '}';
    }
}
