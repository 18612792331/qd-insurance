package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.FeedComment;
import com.qding.api.struct.ResponseData;

/**
 * 根据图文消息id获取该条消息的评论列表，按时间排序，带分页					
 * @author lichao
 *
 */
public class GetCommentByFeedIdResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5936086395491536688L;

	/**
	 * 总记录数
	 */
	private int totalCount;
	
	/**
	 * 当前页返回记录数
	 */
	private int recordCount;
	
	/**
	 * 评论列表
	 */
	private List<FeedComment> list;
	
	public GetCommentByFeedIdResponseData() {

	}
	
	
	public GetCommentByFeedIdResponseData(int totalCount, int recordCount,
			List<FeedComment> list) {
		super();
		this.totalCount = totalCount;
		this.recordCount = recordCount;
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


	public void setList(List<FeedComment> list) {
		this.list = list;
	}
	
	public List<FeedComment> getList() {
		return list;
	}
	

	@Override
	public String toString() {
		return "GetCommentByFeedIdResponse [totalCount=" + totalCount
				+ ", recordCount=" + recordCount + ", list=" + list
				+ ", toString()=" + super.toString() + "]";
	}

	
}
