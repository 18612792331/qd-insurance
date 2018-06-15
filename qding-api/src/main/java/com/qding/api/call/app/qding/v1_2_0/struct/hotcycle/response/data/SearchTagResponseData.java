package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Tag;
import com.qding.api.struct.ResponseData;

public class SearchTagResponseData extends ResponseData{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -797829166215421411L;

	

	/**
	 * 总记录数
	 */
	private int totalCount;
	
	/**
	 * 当前页返回记录数	
	 */
	private int recordCount;
	
	private List<Tag> list;

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

	public void setList(List<Tag> list) {
		this.list = list;
	}
	
	public List<Tag> getList() {
		return list;
	}
	
	@Override
	public String toString() {
		return "SearchTagResponse [totalCount=" + totalCount + ", recordCount="
				+ recordCount + ", list=" + list + ", toString()="
				+ super.toString() + "]";
	}
	
}
