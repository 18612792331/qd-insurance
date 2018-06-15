package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 根据标签id返回此标签的用户列表，带分页			
 * @author lichao
 *
 */
public class GetUsersByTagIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3325640531330139835L;
	
	/**
	 * 标签ID
	 */
	private String tagId;
	
	/**
	 * 当前页
	 */
	private long pageNo = 1;
	
	/**
	 * 页大小
	 */
	private long pageSize = 5;
	
	public GetUsersByTagIdRequest() {

	}

	public GetUsersByTagIdRequest(String tagId, long pageNo, long pageSize) {
		super();
		this.tagId = tagId;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
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

	@Override
	public String toString() {
		return "GetUsersByTagIdRequest [tagId=" + tagId + ", pageNo=" + pageNo
				+ ", pageSize=" + pageSize + ", toString()=" + super.toString()
				+ "]";
	}
}
