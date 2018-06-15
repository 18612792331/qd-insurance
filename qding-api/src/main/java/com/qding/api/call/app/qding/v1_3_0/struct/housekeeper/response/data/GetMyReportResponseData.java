package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.Report;
import com.qding.api.struct.ResponseData;

/**
 * 我的报事查询					
 * @author lichao
 *
 */
public class GetMyReportResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2024704551546039842L;
 
	private int recordCount;
	
	private int totalCount;
	
	private List<Report> list;
	
	public GetMyReportResponseData() {

	}

	public GetMyReportResponseData(int recordCount, int totalCount,
			List<Report> list) {
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

	public List<Report> getList() {
		return list;
	}

	public void setList(List<Report> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetMyReportResponseData [recordCount=" + recordCount
				+ ", totalCount=" + totalCount + ", list=" + list
				+ ", toString()=" + super.toString() + "]";
	}
}
