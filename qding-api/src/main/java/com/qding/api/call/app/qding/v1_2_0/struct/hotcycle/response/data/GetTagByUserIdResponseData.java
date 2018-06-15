package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedCommunityTag;
import com.qding.api.struct.ResponseData;

/**
 * 根据用户id获取该用户的标签列表，带分页，按最新发布标签排序						
 * @author lichao
 *
 */
public class GetTagByUserIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5714584642567608696L;

	/**
	 * 总记录数
	 */
	private int totalCount;
	
	/**
	 * 当前页返回记录数	
	 */
	private int recordCount;
	
	/**
	 * 照片总数
	 */
	private int imageCount;
	
	
	/**
	 * 标签列表
	 */
	private List<FeedCommunityTag> list;
	
	public GetTagByUserIdResponseData() {

	}

	public GetTagByUserIdResponseData(int totalCount, int recordCount,
			int imageCount, List<FeedCommunityTag> list) {
		super();
		this.totalCount = totalCount;
		this.recordCount = recordCount;
		this.imageCount = imageCount;
		this.list = list;
	}

	public void setImageCount(int imageCount) {
		this.imageCount = imageCount;
	}
	
	public int getImageCount() {
		return imageCount;
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

	public void setList(List<FeedCommunityTag> list) {
		this.list = list;
	}
	
	public List<FeedCommunityTag> getList() {
		return list;
	}

	@Override
	public String toString() {
		return "GetTagByUserIdResponse [totalCount=" + totalCount
				+ ", recordCount=" + recordCount + ", imageCount="
				+ imageCount + ", list=" + list + ", toString()="
				+ super.toString() + "]";
	}
}
