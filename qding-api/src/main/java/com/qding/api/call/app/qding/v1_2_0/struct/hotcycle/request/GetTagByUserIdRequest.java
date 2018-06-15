package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 根据用户id获取该用户的标签列表，带分页，按最新发布标签排序						
 * @author lichao
 *
 */
public class GetTagByUserIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2159222227423837645L;

	private String userId;
	
	private String keyword;
	
	private int pageNo = 1;
	
	private int pageSize = 5;
	
	public GetTagByUserIdRequest() {

	}

	public GetTagByUserIdRequest(String userId, String keyword, int pageNo,
			int pageSize) {
		super();
		this.userId = userId;
		this.keyword = keyword;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
		return "GetTagByUserIdRequest [userId=" + userId + ", keyword="
				+ keyword + ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", toString()=" + super.toString() + "]";
	}
}
