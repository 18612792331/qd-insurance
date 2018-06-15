package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Feed;
import com.qding.api.struct.ResponseData;

/**
 * 根据社区id返回所属商圈的图文信息，带分页	
 * @author lichao
 *
 */
public class GetFeedByCommunityIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7898966692532634318L;

	private int totalCount;
	
	private int recordCount;
	
	private List<Feed> list;
	
	public GetFeedByCommunityIdResponseData() {

	}

	public GetFeedByCommunityIdResponseData(int totalCount, int recordCount,
			List<Feed> list) {
		super();
		this.totalCount = totalCount;
		this.recordCount = recordCount;
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetFeedByCommunityIdResponse [totalCount=" + totalCount
				+ ", recordCount=" + recordCount + ", list=" + list
				+ ", toString()=" + super.toString() + "]";
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

	public List<Feed> getList() {
		return list;
	}
	
	public void setList(List<Feed> list) {
		this.list = list;
	}
}
