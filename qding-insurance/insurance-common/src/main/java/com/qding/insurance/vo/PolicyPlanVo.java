package com.qding.insurance.vo;

import com.qding.insurance.domain.PolicyGuaranteePlan;

public class PolicyPlanVo extends PolicyGuaranteePlan {

    private static final long serialVersionUID = 3816492810019192120L;

    private Integer compensateType;// 理赔方式，1：按金额，2：按次数（次）

    private String itemName;

    public Integer getCompensateType() {
        return compensateType;
    }

    public void setCompensateType(Integer compensateType) {
        this.compensateType = compensateType;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

}
