package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 根据用户id查询新消息（由客户端定时拉取）				
 * @author lichao
 *
 */
public class GetNotifyByUserIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6304738760559294515L;

	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 是否需要重置未读消息
	 * 重置会删除消息
	 */
	private Integer reset = 1;
	
	public GetNotifyByUserIdRequest() {

	}
	
	
	public GetNotifyByUserIdRequest(String userId, Integer reset) {
		super();
		this.userId = userId;
		this.reset = reset;
	}

	public Integer getReset() {
		return reset;
	}


	public void setReset(Integer reset) {
		this.reset = reset;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
}
