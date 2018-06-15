package com.qding.api.call.app.qding.v1_3_2.struct.popularize.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;

/**
 * Created by Administrator on 2015/7/28.
 */
@Validate
public class GetPopularizeQuickMarkRequest  extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6079488495297616579L;
	/**
	 * 用户ID
	 */
	private String memberId;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public GetPopularizeQuickMarkRequest(String memberId) {
		super();
		this.memberId = memberId;
	}
	
	public GetPopularizeQuickMarkRequest(){
		
	}
}
