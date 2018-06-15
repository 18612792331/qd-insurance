package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.PropertyBills;
import com.qding.api.struct.ResponseData;

public class GetPropertyBillsIndexResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6806038544150193190L;

	private int recordCount;
	
	private int totalCount;
	
	private String sumDebt;
	
	private String sumPrePay;
	
	private List<PropertyBills> list;
	
	public GetPropertyBillsIndexResponseData() {

	}

	public GetPropertyBillsIndexResponseData(int recordCount, int totalCount,
			String sumDebt, String sumPrePay, List<PropertyBills> list) {
		super();
		this.recordCount = recordCount;
		this.totalCount = totalCount;
		this.sumDebt = sumDebt;
		this.sumPrePay = sumPrePay;
		this.list = list;
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

	@Override
	public String toString() {
		return "GetPropertyBillsIndexResponseData [recordCount=" + recordCount
				+ ", totalCount=" + totalCount + ", sumDebt=" + sumDebt
				+ ", sumPrePay=" + sumPrePay + ", list=" + list
				+ ", toString()=" + super.toString() + "]";
	}

}
