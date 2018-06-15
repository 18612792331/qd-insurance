package com.qding.insurance.vo;

import java.io.Serializable;

import com.qding.insurance.domain.CompensateRecord;

public class CompensateRecordResult extends CompensateRecord implements Serializable {

    private static final long serialVersionUID = -324570145209776690L;

    private String itemTitle;

    private Integer compensateType;// 理赔方式，1：按金额，2：按次数（次）

    public Integer getCompensateType() {
        return compensateType;
    }

    public void setCompensateType(Integer compensateType) {
        this.compensateType = compensateType;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

}