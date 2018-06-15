package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 根据用户id获取照片列表，按时间排序，带分页				
 * @author lichao
 *
 */
public class GetFeedImagesByUserIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7453556467478697056L;

	public GetFeedImagesByUserIdRequest(long pageNo, long pageSize,
			String userId) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.userId = userId;
	}

	/**
	 * 当前页
	 */
	private long pageNo = 1;
	
	/**
	 * 信息页大小
	 */
	private long pageSize = 5;
	
	
	/**
	 * 用户ID
	 */
	private String userId;

	public GetFeedImagesByUserIdRequest() {

	}

	@Override
	public String toString() {
		return "GetFeedsByUserIdRequest [pageNo=" + pageNo + ", pageSize="
				+ pageSize + ", userId=" + userId + ", toString()="
				+ super.toString() + "]";
	}

	public long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
