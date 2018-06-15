package com.qding.api.call.app.qding.v2_0_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/1/8.
 */
public class LogisticsInfo implements Serializable {

    private static final long serialVersionUID = -7731055947910209050L;

    @ExplainAnnotation(explain = "物流单号")
    private String logisticsCode = "";

    @ExplainAnnotation(explain = "物流公司编号")
    private String companyCode = "";

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
}
