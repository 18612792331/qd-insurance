package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.ActivityBean;
import com.qding.api.struct.ResponseData;
import com.qding.hotcycle.struct.Feed;

import java.util.List;

/**
 * 根据活动id获取此活动的feed列表，按发布时间排序，带分页
 * @author jiawenzheng
 *
 */
public class GetFeedsByActivityIdResponseData extends ResponseData {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8585385044133193280L;

	/**
	 * 总记录数
	 */
	private int totalCount;

	/**
	 * 当前页返回记录数
	 */
	private int recordCount;

	/**
	 * 活动相关信息
	 */
	private ActivityBean entity;

	/**
	 * feed信息结果集
	 */
	private List<Feed> feeds;

	public GetFeedsByActivityIdResponseData() {

	}

	public GetFeedsByActivityIdResponseData(int totalCount, int recordCount, List<Feed> feeds) {
		super();
		this.totalCount = totalCount;
		this.recordCount = recordCount;
		this.feeds = feeds;
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


	public List<Feed> getFeeds() {
		return feeds;
	}

	public void setFeeds(List<Feed> feeds) {
		this.feeds = feeds;
	}

	public ActivityBean getEntity() {
		return entity;
	}

	public void setEntity(ActivityBean entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "GetFeedsByActivityIdResponseData [totalCount=" + totalCount
				+ ", recordCount=" + recordCount + ", totalUserCount="
				+", feeds=" + feeds + ",entity="+entity+", toString()=" + super.toString() + "]";
	}




	
}
