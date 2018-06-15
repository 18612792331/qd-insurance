package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.response.data;

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

	@ExplainAnnotation (explain = "1:不能缴费 0：能缴费")
	private int  canPayFee;
	
	private List<PropertyBills> list;

	private List<OwnerInfo> ownerInfos;

	public List<OwnerInfo> getOwnerInfos() {
		return ownerInfos;
	}

	public void setOwnerInfos(List<OwnerInfo> ownerInfos) {
		this.ownerInfos = ownerInfos;
	}

	public GetPropertyBillsIndexResponseData() {

	}

	public GetPropertyBillsIndexResponseData(int recordCount, int totalCount,
											 String sumDebt, String sumPrePay, List<PropertyBills> list, List<OwnerInfo> ownerInfos) {
		super();
		this.recordCount = recordCount;
		this.totalCount = totalCount;
		this.sumDebt = sumDebt;
		this.sumPrePay = sumPrePay;
		this.list = list;
		this.ownerInfos = ownerInfos;
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
				", canPayFee=" + canPayFee +
				", list=" + list +
				", ownerInfos=" + ownerInfos +
				'}';
	}
}
