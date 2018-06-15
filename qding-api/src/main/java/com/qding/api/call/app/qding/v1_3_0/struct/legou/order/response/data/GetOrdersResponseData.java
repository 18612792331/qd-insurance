package com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.Order;
import com.qding.api.struct.ResponseData;

public class GetOrdersResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5865375817476963729L;
 
	private int recordCount;
	
	private int totalCount;
	
	private List<Order> list;
	
	public GetOrdersResponseData() {

	}

	public GetOrdersResponseData(int recordCount, int totalCount,
			List<Order> list) {
		super();
		this.recordCount = recordCount;
		this.totalCount = totalCount;
		this.list = list;
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

	public List<Order> getList() {
		return list;
	}

	public void setList(List<Order> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetOrdersResponseData [recordCount=" + recordCount
				+ ", totalCount=" + totalCount + ", list=" + list
				+ ", toString()=" + super.toString() + "]";
	}
	
}
