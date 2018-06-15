package com.qding.api.call.app.qding.v1_3_0.struct.notify.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.notify.LegouNotify;
import com.qding.api.struct.ResponseData;
 
public class GetLegouNotifiesResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2444099428601076311L;

	private List<LegouNotify> list;
	
	private int recordCount;
	
	private int totalCount;

	public List<LegouNotify> getList() {
		return list;
	}

	public void setList(List<LegouNotify> list) {
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
	
	public GetLegouNotifiesResponseData() {

	}
}
