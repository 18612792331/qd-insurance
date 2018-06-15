package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.response.data;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedImage;
import com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.ActivityDetail;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * 根据活动id获取照片列表，按时间排序，带分页
 * @author jiawenzheng
 *
 */
public class GetFeedImagesByActivityIdResponseData extends ResponseData{

	/**
	 *
	 */
	private static final long serialVersionUID = -5506336599681176925L;

	/**
	 * 总记录数
	 */
	private int totalCount;

	/**
	 * 当前页返回记录数
	 */
	private int recordCount;

	/**
	 * 用户数
	 */
	private int userCount;

	/**
	 * 活动详情
	 */
	private ActivityDetail entity;


	/**
	 * 照片列表
	 */
	private List<FeedImage> list;

	public GetFeedImagesByActivityIdResponseData() {

	}

	public GetFeedImagesByActivityIdResponseData(int totalCount, int recordCount, int userCount, ActivityDetail entity, List<FeedImage> list) {
		this.totalCount = totalCount;
		this.recordCount = recordCount;
		this.userCount = userCount;
		this.entity = entity;
		this.list = list;
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


	public int getUserCount() {
		return userCount;
	}

	public void setUserCount(int userCount) {
		this.userCount = userCount;
	}

	public List<FeedImage> getList() {
		return list;
	}
	
	public void setList(List<FeedImage> list) {
		this.list = list;
	}

	public ActivityDetail getEntity() {
		return entity;
	}

	public void setEntity(ActivityDetail entity) {
		this.entity = entity;
	}

	@Override
	public String toString() {
		return "GetFeedsByUserIdResponse [totalCount=" + totalCount
				+ ", recordCount=" + recordCount + ", userCount=" + userCount
				+ ", list=" + list + ",entity="+entity+" toString()="
				+ super.toString() + "]";
	}
}
