package com.qding.api.call.app.qding.v1_3_0.struct.user.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetProjectRoomsRequest extends BaseRequest{

	private static final long serialVersionUID = -7984683678342356499L;
	
	/**
	 * 社区ID
	 */
	@NotNullValidate
	private String projectId;
	
	/**
	 * 会员ID
	 */
	@NotNullValidate
	private String memberId;
	
	public GetProjectRoomsRequest() {

	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public GetProjectRoomsRequest(String projectId, String memberId) {
		super();
		this.projectId = projectId;
		this.memberId = memberId;
	}
	
	@Override
    public String toString() {
        return "GetProjectRoomsRequest [projectId="+projectId+",memberId="+memberId+",toString()=" + super.toString() + "]";
    }
	
}
