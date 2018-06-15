package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * 根据活动id获取此活动的feed列表，按发布时间排序，带分页	获取图片列表部分
 * @author jiawenzheng
 *
 */
@Validate
public class GetFeedsByActivityIdRequest extends BaseRequest {

	/**
	 *
	 */
	private static final long serialVersionUID = -7719947626493980278L;


	/**
	 * 活动ID
	 */
	@NotNullValidate
	private String activityId;

	/**
	 * 当前页
	 */
	@MinValueValidate(value="1")
	private int pageNo = 1;

	/**
	 * 页大小
	 */
	@RangeValueValidate(max="20", min="1")
	private int pageSize = 10;



	public GetFeedsByActivityIdRequest() {
	}

	public GetFeedsByActivityIdRequest(String activityId, int pageNo, int pageSize) {
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
		return "GetFeedsByActivityIdRequest [activityId=" + activityId + ", pageNo="
				+ pageNo + ", pageSize=" + pageSize + ", toString()=" + super.toString() + "]";
	}
}
