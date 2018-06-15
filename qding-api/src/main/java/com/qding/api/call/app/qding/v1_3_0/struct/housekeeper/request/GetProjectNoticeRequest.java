package com.qding.api.call.app.qding.v1_3_0.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

@Validate
public class GetProjectNoticeRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7032662147287592925L;

	@RangeValueValidate(max="20", min="1")
	private Integer pageSize = 10;
	
	@MinValueValidate(value="1")
	private Integer pageNo = 1;
	
	@NotNullValidate
	private String projectId;
	
	public GetProjectNoticeRequest() {

	}


	public GetProjectNoticeRequest(Integer pageSize, Integer pageNo, String projectId) {
		super();
		this.pageSize = pageSize;
		this.pageNo = pageNo;
		this.projectId = projectId;
	}


	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	@Override
	public String toString() {
		return "GetProjectBannerRequest [pageSize=" + pageSize + ", pageNo="
				+ pageNo + ", projectId="
				+ projectId + ", toString()=" + super.toString() + "]";
	}
	
}
