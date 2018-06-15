package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 根据图文消息id获取点赞列表，按时间排序，带分页				
 * @author lichao
 *
 */
public class GetPraiseByFeedIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 734631261879464044L;

	/**
	 * 消息ID
	 */
	private String feedId;
	
	private int pageNo = 1;
	
	private	int pageSize = 5;
	
	public GetPraiseByFeedIdRequest() {

	}

	public GetPraiseByFeedIdRequest(String feedId, int pageNo, int pageSize) {
		super();
		this.feedId = feedId;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public String getFeedId() {
		return feedId;
	}

	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	@Override
	public String toString() {
		return "GetPraiseByFeedIdRequest [feedId=" + feedId + ", pageNo="
				+ pageNo + ", pageSize=" + pageSize + ", toString()="
				+ super.toString() + "]";
	}
}
