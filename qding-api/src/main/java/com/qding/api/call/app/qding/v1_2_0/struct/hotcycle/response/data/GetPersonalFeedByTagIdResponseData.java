package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Feed;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Tag;
import com.qding.api.struct.ResponseData;

/**
 * 个人标签页 图片列表
 * @author lichao
 *
 */
public class GetPersonalFeedByTagIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2000506762674573516L;

	/**
	 * 总记录数
	 */
	private int totalCount;
	
	/**
	 * 当前页返回记录数	
	 */
	private int recordCount;
	
	/**
	 * 个人信息列表
	 */
	private List<Feed> feeds;
	
	/**
	 * 用户所选的标签
	 */
	private Tag tag;
	
	public GetPersonalFeedByTagIdResponseData() {

	}

	public GetPersonalFeedByTagIdResponseData(int totalCount, int recordCount,
			List<Feed> feeds, Tag tag) {
		super();
		this.totalCount = totalCount;
		this.recordCount = recordCount;
		this.feeds = feeds;
		this.tag = tag;
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

	public Tag getTag() {
		return tag;
	}

	public void setTag(Tag tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		return "GetPersonalFeedByTagIdResponse [totalCount=" + totalCount
				+ ", recordCount=" + recordCount + ", feeds=" + feeds
				+ ", tag=" + tag + ", toString()=" + super.toString() + "]";
	}
}
