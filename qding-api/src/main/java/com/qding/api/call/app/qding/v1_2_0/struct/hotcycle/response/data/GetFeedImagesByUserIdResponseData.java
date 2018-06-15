package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedImage;
import com.qding.api.struct.ResponseData;

/**
 * 根据用户id获取照片列表，按时间排序，带分页				
 * @author lichao
 *
 */
public class GetFeedImagesByUserIdResponseData extends ResponseData{

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
	 * 标签数
	 */
	private int tagCount;
	
	/**
	 * 照片列表
	 */
	private List<FeedImage> list;
	
	public GetFeedImagesByUserIdResponseData() {
		
	}

	public GetFeedImagesByUserIdResponseData(int totalCount, int recordCount,
			int tagCount, List<FeedImage> list) {
		super();
		this.totalCount = totalCount;
		this.recordCount = recordCount;
		this.tagCount = tagCount;
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

	public int getTagCount() {
		return tagCount;
	}

	public void setTagCount(int tagCount) {
		this.tagCount = tagCount;
	}

	public List<FeedImage> getList() {
		return list;
	}
	
	public void setList(List<FeedImage> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "GetFeedsByUserIdResponse [totalCount=" + totalCount
				+ ", recordCount=" + recordCount + ", tagCount=" + tagCount
				+ ", list=" + list + ", toString()="
				+ super.toString() + "]";
	}
}
