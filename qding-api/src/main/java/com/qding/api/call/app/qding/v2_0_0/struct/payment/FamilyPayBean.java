package com.qding.api.call.app.qding.v2_0_0.struct.payment;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by qd on 2016/1/6.
 */
public class FamilyPayBean implements Serializable {

    private static final long serialVersionUID = 7749270142859601545L;

    @ExplainAnnotation(explain = "开通者ID")
    private String midFrom;

    @ExplainAnnotation(explain = "使用者ID")
    private String midTo;

    @ExplainAnnotation(explain = "使用者设定的限额")
    private BigDecimal quotaTo;

    @ExplainAnnotation(explain = "使用者可用的限额")
    private BigDecimal availableQuotaTo;

    @ExplainAnnotation(explain = "开通者名")
    private String nameFrom;

    @ExplainAnnotation(explain = "开通者手机号")
    private String mobileFrom;

    @ExplainAnnotation(explain = "是否是默认支付账号")
    private Integer defaultFlag;

    public String getMidFrom() {
        return midFrom;
    }

    public void setMidFrom(String midFrom) {
        this.midFrom = midFrom;
    }

    public String getMidTo() {
        return midTo;
    }

    public void setMidTo(String midTo) {
        this.midTo = midTo;
    }

    public BigDecimal getQuotaTo() {
        return quotaTo;
    }

    public void setQuotaTo(BigDecimal quotaTo) {
        this.quotaTo = quotaTo;
    }

    public BigDecimal getAvailableQuotaTo() {
        return availableQuotaTo;
    }

    public void setAvailableQuotaTo(BigDecimal availableQuotaTo) {
        this.availableQuotaTo = availableQuotaTo;
    }

    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public String getMobileFrom() {
        return mobileFrom;
    }

    public void setMobileFrom(String mobileFrom) {
        this.mobileFrom = mobileFrom;
    }

    public Integer getDefaultFlag() {
        return defaultFlag;
    }

    public void setDefaultFlag(Integer defaultFlag) {
        this.defaultFlag = defaultFlag;
    }
}
