package com.qding.insurance.param;

import java.io.Serializable;
import java.util.List;

import com.qding.insurance.domain.GuaranteePlan;

public class SaveGuaranteePlanParam implements Serializable {

    private static final long serialVersionUID = -7275592118437958837L;

    private String wareId;

    private List<GuaranteePlan> planList;

    public String getWareId() {
        return wareId;
    }

    public void setWareId(String wareId) {
        this.wareId = wareId;
    }

    public List<GuaranteePlan> getPlanList() {
        return planList;
    }

    public void setPlanList(List<GuaranteePlan> planList) {
        this.planList = planList;
    }

}
