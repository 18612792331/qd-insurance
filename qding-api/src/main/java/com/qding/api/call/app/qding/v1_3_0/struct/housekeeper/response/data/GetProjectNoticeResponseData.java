package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.Notice;
import com.qding.api.struct.ResponseData;
 
public class GetProjectNoticeResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1165260432081781669L;

	private int recordCount;
	
	private int totalCount;
	
	private List<Notice> list;
	
	public GetProjectNoticeResponseData() {

	}

	public GetProjectNoticeResponseData(int recordCount, int totalCount,
			List<Notice> list) {
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

	public List<Notice> getList() {
		return list;
	}

	public void setList(List<Notice> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetProjectBannerResponseData [recordCount=" + recordCount
				+ ", totalCount=" + totalCount + ", list=" + list
				+ ", toString()=" + super.toString() + "]";
	}
	
}
