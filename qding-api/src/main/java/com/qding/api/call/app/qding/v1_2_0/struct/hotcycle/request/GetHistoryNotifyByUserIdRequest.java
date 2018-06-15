package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 根据用户id查询历史消息				
 * @author lichao
 *
 */
public class GetHistoryNotifyByUserIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6304738760559294515L;

	/**
	 * 用户ID
	 */
	private String userId;
	
	private Integer pageNo = 1;
	
	private Integer pageSize = 5;
	
	public GetHistoryNotifyByUserIdRequest() {

	}
	

	public GetHistoryNotifyByUserIdRequest(String userId, Integer pageNo,
			Integer pageSize) {
		super();
		this.userId = userId;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}


	public Integer getPageNo() {
		return pageNo;
	}


	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}


	public Integer getPageSize() {
		return pageSize;
	}


	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}


	@Override
	public String toString() {
		return "GetHistoryNotifyByUserIdRequest [userId=" + userId
				+ ", pageNo=" + pageNo + ", pageSize=" + pageSize
				+ ", toString()=" + super.toString() + "]";
	}

}
