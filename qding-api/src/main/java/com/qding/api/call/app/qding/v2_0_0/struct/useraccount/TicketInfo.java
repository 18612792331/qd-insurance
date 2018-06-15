package com.qding.api.call.app.qding.v2_0_0.struct.useraccount;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;


@XStreamAlias(value = "ticketInfo")
public class TicketInfo implements Serializable {

    @ExplainAnnotation(explain = "开通者ID")
    private String midFrom;

    @ExplainAnnotation(explain = "使用者ID")
    private String midTo;

    @ExplainAnnotation(explain = "千丁券累计使用")
    private String totalPaymentAmount;

    @ExplainAnnotation(explain = "总张数")
    private int totalTicketNum;

    @ExplainAnnotation(explain = "本月千丁券已使用")
    private String usedPaymentAmount;
    @ExplainAnnotation(explain = "本月千丁券已使用数量")
    private int usedTicketNum;

    @ExplainAnnotation(explain = "可用千丁券")
    private String remainQuota;

    @ExplainAnnotation(explain = "可用千丁券数量")
    private int remainTicketNum;


    public int getTotalTicketNum() {
        return totalTicketNum;
    }

    public void setTotalTicketNum(int totalTicketNum) {
        this.totalTicketNum = totalTicketNum;
    }


    public int getUsedTicketNum() {
        return usedTicketNum;
    }

    public void setUsedTicketNum(int usedTicketNum) {
        this.usedTicketNum = usedTicketNum;
    }


    public int getRemainTicketNum() {
        return remainTicketNum;
    }

    public void setRemainTicketNum(int remainTicketNum) {
        this.remainTicketNum = remainTicketNum;
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

    public TicketInfo() {
    }

    public TicketInfo(String midFrom, String midTo, String totalPaymentAmount, int totalTicketNum, String usedPaymentAmount, int usedTicketNum, String remainQuota, int remainTicketNum) {
        this.midFrom = midFrom;
        this.midTo = midTo;
        this.totalPaymentAmount = totalPaymentAmount;
        this.totalTicketNum = totalTicketNum;
        this.usedPaymentAmount = usedPaymentAmount;
        this.usedTicketNum = usedTicketNum;
        this.remainQuota = remainQuota;
        this.remainTicketNum = remainTicketNum;
    }

    @Override
    public String toString() {
        return "TicketInfo{" +
                "midFrom='" + midFrom + '\'' +
                ", midTo='" + midTo + '\'' +
                ", totalPaymentAmount=" + totalPaymentAmount +
                ", totalTicketNum=" + totalTicketNum +
                ", usedPaymentAmount=" + usedPaymentAmount +
                ", usedTicketNum=" + usedTicketNum +
                ", remainQuota=" + remainQuota +
                ", remainTicketNum=" + remainTicketNum +
                '}';
    }
}