package com.qding.insurance.domain;

import java.io.Serializable;

public class PolicyGuaranteeItem implements Serializable{
    private static final long serialVersionUID = 6152900505298450963L;

    private String id;

    private String policyId;

    private String itemTitle;

    private Integer itemType;

    private Integer compensateType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId == null ? null : policyId.trim();
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle == null ? null : itemTitle.trim();
    }

    public Integer getItemType() {
        return itemType;
    }

    public void setItemType(Integer itemType) {
        this.itemType = itemType;
    }

    public Integer getCompensateType() {
        return compensateType;
    }

    public void setCompensateType(Integer compensateType) {
        this.compensateType = compensateType;
    }
}