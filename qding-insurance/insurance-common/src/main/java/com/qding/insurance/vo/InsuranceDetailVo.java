package com.qding.insurance.vo;

import java.io.Serializable;
import java.util.List;

import com.qding.insurance.domain.GuaranteePlan;
import com.qding.insurance.domain.InsuranceProject;
import com.qding.insurance.domain.InsuranceSku;
import com.qding.insurance.domain.InsuranceWareExtWithBLOBs;

public class InsuranceDetailVo implements Serializable {

    private static final long serialVersionUID = -8672812311239213434L;

    private InsuranceWareExtWithBLOBs ware; // 商品信息

    private List<SpecVo> specList; // 规格选项

    private List<InsuranceSku> skuList; // sku列表

    private List<GuaranteeItemVo> guaranteeList; // 保障内容列表

    private List<GuaranteePlan> planList; // 保障计划列表

    private List<InsuranceProject> projectList; // 社区列表

    public List<InsuranceProject> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<InsuranceProject> projectList) {
        this.projectList = projectList;
    }

    public InsuranceWareExtWithBLOBs getWare() {
        return ware;
    }

    public void setWare(InsuranceWareExtWithBLOBs ware) {
        this.ware = ware;
    }

    public List<SpecVo> getSpecList() {
        return specList;
    }

    public void setSpecList(List<SpecVo> specList) {
        this.specList = specList;
    }

    public List<InsuranceSku> getSkuList() {
        return skuList;
    }

    public void setSkuList(List<InsuranceSku> skuList) {
        this.skuList = skuList;
    }

    public List<GuaranteeItemVo> getGuaranteeList() {
        return guaranteeList;
    }

    public void setGuaranteeList(List<GuaranteeItemVo> guaranteeList) {
        this.guaranteeList = guaranteeList;
    }

    public List<GuaranteePlan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<GuaranteePlan> planList) {
        this.planList = planList;
    }

}
