package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.Feed;
import com.qding.api.struct.ResponseData;

/**
 * 根据标签id获取此标签的图片列表，按发布时间排序，带分页					
 * @author lichao
 *
 */
public class GetFeedsByTagIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8909532152543822214L;

	/**
	 * 总记录数
	 */
	private int totalCount;
	
	/**
	 * 当前页返回记录数	
	 */
	private int recordCount;
	
	/**
	 * 用户总数
	 */
	private int totalUserCount;
	
	
	/**
	 * 用户结果集
	 */
	private List<Feed> list;


	/**
	 * 共同标签名称
	 */
	private String tagName;

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public GetFeedsByTagIdResponseData() {

	}


	public GetFeedsByTagIdResponseData(int totalCount, int recordCount,
			int totalUserCount, List<Feed> list) {
		super();
		this.totalCount = totalCount;
		this.recordCount = recordCount;
		this.totalUserCount = totalUserCount;
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


	public int getTotalUserCount() {
		return totalUserCount;
	}


	public void setTotalUserCount(int totalUserCount) {
		this.totalUserCount = totalUserCount;
	}


	public List<Feed> getList() {
		return list;
	}


	public void setList(List<Feed> list) {
		this.list = list;
	}


	@Override
	public String toString() {
		return "GetFeedsByTagIdResponseData [totalCount=" + totalCount
				+ ", recordCount=" + recordCount + ", totalUserCount="
				+ totalUserCount + ", list=" + list + ", toString()="
				+ super.toString() + "]";
	}


}
