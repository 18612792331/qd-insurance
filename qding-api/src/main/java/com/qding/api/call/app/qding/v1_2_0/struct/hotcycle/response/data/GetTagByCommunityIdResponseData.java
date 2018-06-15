package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedCommunityTag;
import com.qding.api.struct.ResponseData;

/**
 * 根据社区id显示此商圈的标签，带分页，按最新发布标签排序
 * @author lichao
 *
 */
public class GetTagByCommunityIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = 683863177652940242L;

	
	/**
	 * 总记录数
	 */
	private int totalCount;
	
	/**
	 * 当前页返回记录数	
	 */
	private int recordCount;
	
	/**
	 * 标签
	 */
	private List<FeedCommunityTag> list;
	
	public GetTagByCommunityIdResponseData() {

	}

	public GetTagByCommunityIdResponseData(int totalCount, int recordCount,
			List<FeedCommunityTag> list) {
		super();
		this.totalCount = totalCount;
		this.recordCount = recordCount;
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetTagByBussinessIdResponse [totalCount=" + totalCount
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

	public List<FeedCommunityTag> getList() {
		return list;
	}
	
	public void setList(List<FeedCommunityTag> list) {
		this.list = list;
	}
	
}
