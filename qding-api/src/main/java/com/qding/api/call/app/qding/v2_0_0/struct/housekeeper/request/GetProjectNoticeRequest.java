package com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
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
	@ExplainAnnotation(explain = "每页查询数")
	private Integer pageSize = 10;
	
	@MinValueValidate(value="1")
	@ExplainAnnotation(explain = "当前查询页码")
	private Integer pageNo = 1;
	
	@NotNullValidate
	@ExplainAnnotation(explain = "社区ID")
	private String projectId;

	//公告类型
	@ExplainAnnotation(explain = "公告类型",desc = "1：紧急通知,2：通知,3：社区活动,6:温馨提示,7：新闻")
	private Integer noticeType;

	public GetProjectNoticeRequest() {

	}

	public Integer getNoticeType() {
		return noticeType;
	}

	public void setNoticeType(Integer noticeType) {
		this.noticeType = noticeType;
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
		return "GetProjectBannerRequest [noticeType="+noticeType+" ,pageSize=" + pageSize + ", pageNo="
				+ pageNo + ", projectId="
				+ projectId + ", toString()=" + super.toString() + "]";
	}
	
}
