package com.qding.api.call.app.qding.v1_3_0.struct.user.request;


import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

@Validate
public class InviteUserHistoryRequest  extends BaseRequest  {

	
	private static final long serialVersionUID = -2889401198342341439L;

	public InviteUserHistoryRequest() {
		// TODO Auto-generated constructor stub
	}
	
	/**
     * 会员ID
     */
	@NotNullValidate
	private String memberId;
	
	/**
     * 社区ID
     */
	@NotNullValidate
	private String projectId;
	
	/**
     * 当前查询页码
     */
	@RangeValueValidate(max="20", min="1")
	private Integer pageSize = 10;
	
	/**
     * 每页显示条数
     */
	@MinValueValidate(value="1")
	private Integer pageNo = 1;
	

	

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
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

	@Override
    public String toString() {
        return "InviteUserHistoryRequest [memberId="+memberId+",projectId="+projectId+",pageSize="+pageSize+",pageNo="+pageNo+",toString()=" + super.toString() + "]";
    }

}
