package com.qding.api.call.app.qding.v2_0_0.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/1/4.
 */
public class BusinessBean implements Serializable {

    private static final long serialVersionUID = 4643521943394045649L;

    @ExplainAnnotation(explain = "业态类型")
    private String businessType;

    @ExplainAnnotation(explain = "业态名称")
    private String businessName;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }


}
