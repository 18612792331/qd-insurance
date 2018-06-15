package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/22.
 */
public class FeeModel implements Serializable {

    private static final long serialVersionUID = -2270138288113936815L;


    @ExplainAnnotation (explain = "费用名称")
    private String costName;

    @ExplainAnnotation (explain = "费用流水ID")
    private long feeId;

    @ExplainAnnotation (explain = "总金额")
    private float dueAmount;

    @ExplainAnnotation (explain = "月份")
    private Long feeDueDate;

    @ExplainAnnotation (explain = "未缴金额")
    private float debtsAmount;

    @ExplainAnnotation (explain = "违约金")
    private float lateFeeAmount;

    @ExplainAnnotation (explain = "费项所属的月份，如果是年缴的此字段为全年")
    private String feeDueDateStrMonth;

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public long getFeeId() {
        return feeId;
    }

    public void setFeeId(long feeId) {
        this.feeId = feeId;
    }

    public float getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(float dueAmount) {
        this.dueAmount = dueAmount;
    }

    public Long getFeeDueDate() {
        return feeDueDate;
    }

    public void setFeeDueDate(Long feeDueDate) {
        this.feeDueDate = feeDueDate;
    }

    public float getDebtsAmount() {
        return debtsAmount;
    }

    public void setDebtsAmount(float debtsAmount) {
        this.debtsAmount = debtsAmount;
    }

    public float getLateFeeAmount() {
        return lateFeeAmount;
    }

    public void setLateFeeAmount(float lateFeeAmount) {
        this.lateFeeAmount = lateFeeAmount;
    }

    public String getFeeDueDateStrMonth() {
        return feeDueDateStrMonth;
    }

    public void setFeeDueDateStrMonth(String feeDueDateStrMonth) {
        this.feeDueDateStrMonth = feeDueDateStrMonth;
    }
}
