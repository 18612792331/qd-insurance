package com.qding.api.call.app.qding.v1_3_2.struct.popularize.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by Administrator on 2015/7/28.
 */
@Validate
public class GetPopularizeQuickMarkInfoRequest   extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2427548438213422643L;
	
	/**
	 * 用户ID
	 */
	@NotNullValidate
	private String memberId;

	
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public GetPopularizeQuickMarkInfoRequest(String userId) {
		super();
		this.memberId = userId;
	}
	
	public GetPopularizeQuickMarkInfoRequest(){
		
	}
	
	
}
