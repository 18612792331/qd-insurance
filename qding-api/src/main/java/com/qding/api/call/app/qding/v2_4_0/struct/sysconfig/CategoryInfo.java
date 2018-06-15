package com.qding.api.call.app.qding.v2_4_0.struct.sysconfig;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

public class CategoryInfo implements Serializable {

    private static final long serialVersionUID = 7010249364185515909L;

    public CategoryInfo() {
    }

    @ExplainAnnotation(explain = "id")
    private String id;

    @ExplainAnnotation(explain = "品类id")
    private Long categoryId;

    @ExplainAnnotation(explain = "品类name")
    private String categoryName;

    @ExplainAnnotation(explain = "品类编码")
    private String categoryCode;

    @ExplainAnnotation(explain = "品类图标")
    private String categoryIcon;

    @ExplainAnnotation(explain = "状态", desc = "1:启用 | 0:停用")
    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "MallProjectCategoryRecommendInfo{" +
                "id='" + id + '\'' +
                ", categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", categoryCode='" + categoryCode + '\'' +
                ", categoryIcon='" + categoryIcon + '\'' +
                ", status=" + status +
                '}';
    }
}