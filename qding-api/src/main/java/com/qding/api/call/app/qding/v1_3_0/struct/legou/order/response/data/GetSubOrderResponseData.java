package com.qding.api.call.app.qding.v1_3_0.struct.legou.order.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.legou.order.OrderGoods;
import com.qding.api.struct.ResponseData;

public class GetSubOrderResponseData extends ResponseData{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9011959941030437931L;
	
	private List<OrderGoods> list;
	
	private int totalCount;
	
	private int recordCount;
	
	public GetSubOrderResponseData() {

	}
	
	public int getTotalCount() {
		return totalCount;
	}


	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}


	public int getRecordCount() {
		return recordCount;
	}


	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}


	public List<OrderGoods> getList() {
		return list;
	}
	
	public void setList(List<OrderGoods> list) {
		this.list = list;
	}
	
	
}
