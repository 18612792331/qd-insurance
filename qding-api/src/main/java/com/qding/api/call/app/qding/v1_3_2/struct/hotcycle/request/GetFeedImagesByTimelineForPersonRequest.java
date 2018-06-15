package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by jiawenzheng on 2015/7/29.
 */
@Validate
public class GetFeedImagesByTimelineForPersonRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2308540059839490307L;
	
	/**
	 * 距当前日期天数
	 */
	@MinValueValidate(value="1")
	private Integer pageNo =1;

	/**
	 * 当前用户ID
	 */
	@NotNullValidate
	private String userId;

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public GetFeedImagesByTimelineForPersonRequest(Integer pageNo, String userId) {
		this.pageNo = pageNo;
		this.userId = userId;
	}

	public GetFeedImagesByTimelineForPersonRequest(){

	}
}
