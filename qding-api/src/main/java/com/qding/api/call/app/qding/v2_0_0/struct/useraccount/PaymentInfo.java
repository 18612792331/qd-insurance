package com.qding.api.call.app.qding.v2_0_0.struct.useraccount;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;
import java.math.BigDecimal;

@XStreamAlias(value = "paymentInfo")
public class PaymentInfo implements Serializable {

    @ExplainAnnotation(explain = "开通者ID")
    private String midFrom;

    @ExplainAnnotation(explain = "使用者ID")
    private String midTo;

    @ExplainAnnotation(explain = "钱包累计支付")
    private String totalPaymentAmount;

    @ExplainAnnotation(explain = "本月钱包已使用")
    private String usedPaymentAmount;

    @ExplainAnnotation(explain = "本月钱包剩余额度")
    private String remainQuota;

    public String getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    public void setTotalPaymentAmount(String totalPaymentAmount) {
        this.totalPaymentAmount = totalPaymentAmount;
    }

    public String getUsedPaymentAmount() {
        return usedPaymentAmount;
    }

    public void setUsedPaymentAmount(String usedPaymentAmount) {
        this.usedPaymentAmount = usedPaymentAmount;
    }

    public String getRemainQuota() {
        return remainQuota;
    }

    public void setRemainQuota(String remainQuota) {
        this.remainQuota = remainQuota;
    }

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

    public PaymentInfo() {
    }

    public PaymentInfo(String midFrom, String midTo, String totalPaymentAmount, String usedPaymentAmount, String remainQuota) {
        this.midFrom = midFrom;
        this.midTo = midTo;
        this.totalPaymentAmount = totalPaymentAmount;
        this.usedPaymentAmount = usedPaymentAmount;
        this.remainQuota = remainQuota;
    }
}