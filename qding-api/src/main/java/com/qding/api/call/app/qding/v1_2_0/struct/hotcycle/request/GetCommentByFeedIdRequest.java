package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 根据图文消息id获取该条消息的评论列表，按时间排序，带分页					
 * @author lichao
 *
 */
public class GetCommentByFeedIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4581482942514486390L;

	public GetCommentByFeedIdRequest(String feedId, int pageNo, int pageSize) {
		super();
		this.feedId = feedId;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	/**
	 * 消息ID
	 */
	private String feedId;
	
	private int pageNo = 1;
	
	private	int pageSize = 5;
	
	public GetCommentByFeedIdRequest() {

	}
	
	@Override
	public String toString() {
		return "GetCommentByFeedIdRequest [feedId=" + feedId + ", pageNo="
				+ pageNo + ", pageSize=" + pageSize + ", toString()="
				+ super.toString() + "]";
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}
	
	public String getFeedId() {
		return feedId;
	}
}
