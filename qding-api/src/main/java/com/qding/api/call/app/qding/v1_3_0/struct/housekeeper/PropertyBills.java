package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 物业账单
 * @author lichao
 *
 */
@XStreamAlias(value="propertyBills")
public class PropertyBills implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -2956761655759771703L;

	private String projectName;
	private String custName;
	private String costName;
	private String dueAmount;
	private Long feeDueDate;
	private String waivAmount;
	private String debtsAmount;
	private String lateFeeAmount;
	private String feeStatus;
    private String feeDueDateStr;
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

	public PropertyBills(String projectName, String custName, String costName,
						 String dueAmount, Long feeDueDate, String waivAmount,
						 String debtsAmount, String lateFeeAmount, String feeStatus) {
		super();
		this.projectName = projectName;
		this.custName = custName;
		this.costName = costName;
		this.dueAmount = dueAmount;
		this.feeDueDate = feeDueDate;
		this.waivAmount = waivAmount;
		this.debtsAmount = debtsAmount;
		this.lateFeeAmount = lateFeeAmount;
		this.feeStatus = feeStatus;
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
