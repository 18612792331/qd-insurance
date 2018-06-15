package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * 根据活动id返回此活动的用户列表，带分页			
 * @author jiawenzheng
 *
 */
@Validate
public class GetUsersByActivityIdRequest extends BaseRequest{

	/**
	 * 标签ID
	 */
	@NotNullValidate
	private String activityId;
	
	/**
	 * 当前页
	 */
	@MinValueValidate(value="1")
	private long pageNo = 1;
	
	/**
	 * 页大小
	 */
	@RangeValueValidate(max="20", min="1")
	private long pageSize = 5;
	
	public GetUsersByActivityIdRequest() {

	}

	public GetUsersByActivityIdRequest(String activityId, long pageNo, long pageSize) {
		super();
		this.activityId = activityId;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
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
		return "GetUsersByActivityIdRequest [activityId=" + activityId + ", pageNo=" + pageNo
				+ ", pageSize=" + pageSize + ", toString()=" + super.toString()
				+ "]";
	}
}
