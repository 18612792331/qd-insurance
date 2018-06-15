package com.qding.insurance.domain;

import java.io.Serializable;

public class GuaranteePlan implements Serializable{
    private static final long serialVersionUID = -617921411685412556L;

    private String id;

    private String wareId;

    private String skuId;

    private String guaranteeItemId;

    private String rightValue;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getWareId() {
        return wareId;
    }

    public void setWareId(String wareId) {
        this.wareId = wareId == null ? null : wareId.trim();
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId == null ? null : skuId.trim();
    }

    public String getGuaranteeItemId() {
        return guaranteeItemId;
    }

    public void setGuaranteeItemId(String guaranteeItemId) {
        this.guaranteeItemId = guaranteeItemId == null ? null : guaranteeItemId.trim();
    }

    public String getRightValue() {
        return rightValue;
    }

    public void setRightValue(String rightValue) {
        this.rightValue = rightValue == null ? null : rightValue.trim();
    }
}