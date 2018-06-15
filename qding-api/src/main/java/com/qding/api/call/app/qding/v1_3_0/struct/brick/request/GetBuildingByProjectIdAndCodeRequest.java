package com.qding.api.call.app.qding.v1_3_0.struct.brick.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 根据社区id和组团code获取楼栋列表					
 * @author lichao
 *
 */
@Validate
public class GetBuildingByProjectIdAndCodeRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6126400822733272476L;

	@NotNullValidate
	private String projectId;
	
	private String groupCode;

	public GetBuildingByProjectIdAndCodeRequest() {

	}
	
	public GetBuildingByProjectIdAndCodeRequest(String projectId,
			String groupCode) {
		super();
		this.projectId = projectId;
		this.groupCode = groupCode;
	}

	@Override
	public String toString() {
		return "GetBuildingByProjectIdAndCodeRequest [projectId=" + projectId
				+ ", groupCode=" + groupCode + ", toString()="
				+ super.toString() + "]";
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}
	
	
}
