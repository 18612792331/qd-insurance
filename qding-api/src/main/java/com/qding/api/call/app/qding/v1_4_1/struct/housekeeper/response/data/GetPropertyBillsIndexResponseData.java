package com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.PropertyBills;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.OwnerInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetPropertyBillsIndexResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6806038544150193190L;

	private int recordCount;
	
	private int totalCount;
	
	private String sumDebt;
	
	private String sumPrePay;

	private boolean bind;
	
	private List<PropertyBills> list;

	private List<OwnerInfo> ownerInfos;

	public List<OwnerInfo> getOwnerInfos() {
		return ownerInfos;
	}

	@ExplainAnnotation(explain = "1:清账阶段 0：非清账阶段")
	private int  canPayFee;

	@ExplainAnnotation (explain = "提示信息")
	private String remindMsg;

	@ExplainAnnotation (explain = "应付金额")
	private String shouldPay;

	@ExplainAnnotation (explain = "优惠金额")
	private String discountAmount;

	public String getRemindMsg() {
		return remindMsg;
	}

	public void setRemindMsg(String remindMsg) {
		this.remindMsg = remindMsg;
	}

	public String getShouldPay() {
		return shouldPay;
	}

	public void setShouldPay(String shouldPay) {
		this.shouldPay = shouldPay;
	}

	public String getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(String discountAmount) {
		this.discountAmount = discountAmount;
	}

	public void setOwnerInfos(List<OwnerInfo> ownerInfos) {
		this.ownerInfos = ownerInfos;
	}

	public boolean isBind() {
		return bind;
	}

	public void setBind(boolean bind) {
		this.bind = bind;
	}

	public GetPropertyBillsIndexResponseData() {

	}

	public GetPropertyBillsIndexResponseData(int recordCount, int totalCount,
											 String sumDebt, String sumPrePay, List<PropertyBills> list, List<OwnerInfo> ownerInfos,boolean bind) {
		super();
		this.recordCount = recordCount;
		this.totalCount = totalCount;
		this.sumDebt = sumDebt;
		this.sumPrePay = sumPrePay;
		this.list = list;
		this.ownerInfos = ownerInfos;
		this.bind =bind;
	}

	public String getSumDebt() {
		return sumDebt;
	}

	public void setSumDebt(String sumDebt) {
		this.sumDebt = sumDebt;
	}

	public String getSumPrePay() {
		return sumPrePay;
	}

	public void setSumPrePay(String sumPrePay) {
		this.sumPrePay = sumPrePay;
	}

	public int getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<PropertyBills> getList() {
		return list;
	}

	public void setList(List<PropertyBills> list) {
		this.list = list;
	}

	public int getCanPayFee() {
		return canPayFee;
	}

	public void setCanPayFee(int canPayFee) {
		this.canPayFee = canPayFee;
	}

	@Override
	public String toString() {
		return "GetPropertyBillsIndexResponseData{" +
				"recordCount=" + recordCount +
				", totalCount=" + totalCount +
				", sumDebt='" + sumDebt + '\'' +
				", sumPrePay='" + sumPrePay + '\'' +
				", bind=" + bind +
				", list=" + list +
				", ownerInfos=" + ownerInfos +
				", canPayFee=" + canPayFee +
				", remindMsg='" + remindMsg + '\'' +
				", shouldPay='" + shouldPay + '\'' +
				", discountAmount='" + discountAmount + '\'' +
				'}';
	}
}
