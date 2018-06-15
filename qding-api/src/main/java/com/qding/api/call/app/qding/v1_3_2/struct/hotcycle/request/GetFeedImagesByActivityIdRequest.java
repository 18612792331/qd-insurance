package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * 根据活动id获取照片列表，按时间排序，带分页
 * @author jiawenzheng
 *
 */
@Validate
public class GetFeedImagesByActivityIdRequest extends BaseRequest{

	/**
	 *
	 */
	private static final long serialVersionUID = -7453556467478697056L;

	public GetFeedImagesByActivityIdRequest(long pageNo, long pageSize,
											String activityId) {
		super();
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.activityId = activityId;
	}

	/**
	 * 当前页
	 */
	@MinValueValidate(value="1")
	private long pageNo = 1;

	/**
	 * 信息页大小
	 */
	@RangeValueValidate(max="20", min="1")
	private long pageSize = 10;


	/**
	 * 用户ID
	 */
	@NotNullValidate
	private String activityId;

	public GetFeedImagesByActivityIdRequest() {

	}

	@Override
	public String toString() {
		return "GetFeedsByUserIdRequest [pageNo=" + pageNo + ", pageSize="
				+ pageSize + ", activityId=" + activityId + ", toString()="
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

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
}
