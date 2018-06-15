package com.qding.insurance.domain;

import java.io.Serializable;

public class InsuranceSku implements Serializable {

    private static final long serialVersionUID = 3574321377940941472L;

    private String id;

    private String skuName;

    private String wareId;

    private String projectType;

    private String styleType;

    private String timeType;

    private String price;

    private Long brickSkuId;

    private String piccCode; // 险种代码

    public String getPiccCode() {
        return piccCode;
    }

    public void setPiccCode(String piccCode) {
        this.piccCode = piccCode;
    }

    public Long getBrickSkuId() {
        return brickSkuId;
    }

    public void setBrickSkuId(Long brickSkuId) {
        this.brickSkuId = brickSkuId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName == null ? null : skuName.trim();
    }

    public String getWareId() {
        return wareId;
    }

    public void setWareId(String wareId) {
        this.wareId = wareId == null ? null : wareId.trim();
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType == null ? null : projectType.trim();
    }

    public String getStyleType() {
        return styleType;
    }

    public void setStyleType(String styleType) {
        this.styleType = styleType == null ? null : styleType.trim();
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType == null ? null : timeType.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }
}