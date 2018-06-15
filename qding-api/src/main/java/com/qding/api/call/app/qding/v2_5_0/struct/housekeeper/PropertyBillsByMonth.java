package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.io.Serializable;

/**
 * 物业账单
 *
 * @author lichao
 */
@XStreamAlias(value = "propertyBills")
public class PropertyBillsByMonth implements Serializable {

    @ExplainAnnotation(explain = "当月应缴费用合计")
    private String dueAmount;


    @ExplainAnnotation(explain = "当月未缴费用合计")
    private String debtsAmount;

    @ExplainAnnotation(explain = "月份Long型")
    private Long feeDueDate;

    @ExplainAnnotation(explain = "月份")
    private String feeDueDateStrMonth;

    @ExplainAnnotation(explain = "状态", desc = "已缴 | 未缴")
    private String feeStatus;


    public PropertyBillsByMonth() {

    }

    public String getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }

    public String getDebtsAmount() {
        return debtsAmount;
    }

    public void setDebtsAmount(String debtsAmount) {
        this.debtsAmount = debtsAmount;
    }

    public String getFeeDueDateStrMonth() {
        return feeDueDateStrMonth;
    }

    public void setFeeDueDateStrMonth(String feeDueDateStrMonth) {
        this.feeDueDateStrMonth = feeDueDateStrMonth;
    }

    public String getFeeStatus() {
        return feeStatus;
    }

    public void setFeeStatus(String feeStatus) {
        this.feeStatus = feeStatus;
    }

    public Long getFeeDueDate() {
        return feeDueDate;
    }

    public void setFeeDueDate(Long feeDueDate) {
        this.feeDueDate = feeDueDate;
    }
}
