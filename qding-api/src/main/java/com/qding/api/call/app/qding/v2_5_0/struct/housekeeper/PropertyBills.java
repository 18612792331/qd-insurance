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
public class PropertyBills implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2956761655759771703L;
    @ExplainAnnotation(explain = "社区名称")
    private String projectName;

    @ExplainAnnotation(explain = "客户姓名")
    private String custName;

    @ExplainAnnotation(explain = "费项")
    private String costName;

    @ExplainAnnotation(explain = "应收金额")
    private String dueAmount;

    @ExplainAnnotation(explain = "应收时间")
    private Long feeDueDate;

    @ExplainAnnotation(explain = "减免金额")
    private String waivAmount;

    @ExplainAnnotation(explain = "欠收金额")
    private String debtsAmount;

    @ExplainAnnotation(explain = "滞纳金")
    private String lateFeeAmount;

    @ExplainAnnotation(explain = "费用状态")
    private String feeStatus;

    @ExplainAnnotation(explain = "应收时间字符串")
    private String feeDueDateStr;

    @ExplainAnnotation(explain = "应收时间字符串-月份")
    private String feeDueDateStrMonth;

    @ExplainAnnotation(explain = "费用开始日期")
    private String feeStartDate = "";

    @ExplainAnnotation(explain = "费用结束日期")
    private String feeEndDate = "";


    public PropertyBills() {

    }

    public String getFeeStartDate() {
        return feeStartDate;
    }

    public void setFeeStartDate(String feeStartDate) {
        this.feeStartDate = feeStartDate;
    }

    public String getFeeEndDate() {
        return feeEndDate;
    }

    public void setFeeEndDate(String feeEndDate) {
        this.feeEndDate = feeEndDate;
    }

    public String getFeeDueDateStr() {
        return feeDueDateStr;
    }

    public void setFeeDueDateStr(String feeDueDateStr) {
        this.feeDueDateStr = feeDueDateStr;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public String getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(String dueAmount) {
        this.dueAmount = dueAmount;
    }

    public Long getFeeDueDate() {
        return feeDueDate;
    }

    public void setFeeDueDate(Long feeDueDate) {
        this.feeDueDate = feeDueDate;
    }

    public String getWaivAmount() {
        return waivAmount;
    }

    public void setWaivAmount(String waivAmount) {
        this.waivAmount = waivAmount;
    }

    public String getDebtsAmount() {
        return debtsAmount;
    }

    public void setDebtsAmount(String debtsAmount) {
        this.debtsAmount = debtsAmount;
    }

    public String getLateFeeAmount() {
        return lateFeeAmount;
    }

    public void setLateFeeAmount(String lateFeeAmount) {
        this.lateFeeAmount = lateFeeAmount;
    }

    public String getFeeStatus() {
        return feeStatus;
    }

    public void setFeeStatus(String feeStatus) {
        this.feeStatus = feeStatus;
    }

    public String getFeeDueDateStrMonth() {
        return feeDueDateStrMonth;
    }

    public void setFeeDueDateStrMonth(String feeDueDateStrMonth) {
        this.feeDueDateStrMonth = feeDueDateStrMonth;
    }
}
