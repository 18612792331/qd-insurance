package com.qding.api.call.app.qding.v2_4_0.struct.sysconfig;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;


public class ModuleInfo implements Serializable {

    private static final long serialVersionUID = -8610728924446516741L;

    public ModuleInfo() {
    }

    @ExplainAnnotation(explain = "id")
    private String id;

    @ExplainAnnotation(explain = "标题")
    private String title;

    @ExplainAnnotation(explain = "内容类型", desc = "0:商品 | 1:url链接 | 2:秒杀 | 3:阶梯团购")
    private Integer contentType;

    @ExplainAnnotation(explain = "简介")
    private String introduction;

    @ExplainAnnotation(explain = "标签")
    private String tag;

    @ExplainAnnotation(explain = "16进制色值")
    private String colorValue;

    @ExplainAnnotation(explain = "banner图标")
    private String bannerIcon;

    @ExplainAnnotation(explain = "按钮名称")
    private String buttonName;

    @ExplainAnnotation(explain = "排序值")
    private Integer sort;

    @ExplainAnnotation(explain = "商品列表")
    private List<WareInfo> wareList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getColorValue() {
        return colorValue;
    }

    public void setColorValue(String colorValue) {
        this.colorValue = colorValue;
    }

    public String getBannerIcon() {
        return bannerIcon;
    }

    public void setBannerIcon(String bannerIcon) {
        this.bannerIcon = bannerIcon;
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public List<WareInfo> getWareList() {
        return wareList;
    }

    public void setWareList(List<WareInfo> wareList) {
        this.wareList = wareList;
    }

    @Override
    public String toString() {
        return "ItemInfo{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", contentType=" + contentType +
                ", introduction='" + introduction + '\'' +
                ", tag='" + tag + '\'' +
                ", colorValue='" + colorValue + '\'' +
                ", bannerIcon='" + bannerIcon + '\'' +
                ", buttonName='" + buttonName + '\'' +
                ", sort=" + sort +
                ", wareList=" + wareList +
                '}';
    }
}