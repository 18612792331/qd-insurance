package com.qding.insurance.vo;

import java.util.List;

import com.qding.insurance.domain.InsurancePolicy;

public class InsurancePolicyDetailVo extends InsurancePolicy {

    private static final long serialVersionUID = 6177343048821120152L;

    // 商品名
    private String wareName;

    // 货品名
    private String skuName;

    // 实付金额
    private String paidMoney;

    // 保单权益列表
    private List<PolicyPlanVo> policyPlanList;

    // 保单理赔记录
    private List<CompensateRecordResult> compensateList;

    public List<PolicyPlanVo> getPolicyPlanList() {
        return policyPlanList;
    }

    public void setPolicyPlanList(List<PolicyPlanVo> policyPlanList) {
        this.policyPlanList = policyPlanList;
    }

    public List<CompensateRecordResult> getCompensateList() {
        return compensateList;
    }

    public void setCompensateList(List<CompensateRecordResult> compensateList) {
        this.compensateList = compensateList;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public String getSkuName() {
        return skuName;
    }

    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }

    public String getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(String paidMoney) {
        this.paidMoney = paidMoney;
    }

}
