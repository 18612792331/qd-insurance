package com.qding.insurance.domain;

import java.io.Serializable;

public class PolicyGuaranteePlan implements Serializable{
    private static final long serialVersionUID = -7898121205871290729L;

    private String id;

    private String policyId;

    private String guaranteeItemId;

    private String rightValue;

    private String lockValue;

    private String balanceValue;

    private String paidValue;

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

    public String getLockValue() {
        return lockValue;
    }

    public void setLockValue(String lockValue) {
        this.lockValue = lockValue == null ? null : lockValue.trim();
    }

    public String getBalanceValue() {
        return balanceValue;
    }

    public void setBalanceValue(String balanceValue) {
        this.balanceValue = balanceValue == null ? null : balanceValue.trim();
    }

    public String getPaidValue() {
        return paidValue;
    }

    public void setPaidValue(String paidValue) {
        this.paidValue = paidValue == null ? null : paidValue.trim();
    }
}