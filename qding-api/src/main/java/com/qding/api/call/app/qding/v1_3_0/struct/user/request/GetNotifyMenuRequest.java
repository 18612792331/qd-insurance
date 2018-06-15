package com.qding.api.call.app.qding.v1_3_0.struct.user.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetNotifyMenuRequest extends BaseRequest{

	private static final long serialVersionUID = -3817154795899252717L;
	
	/**
	 * 社区ID
	 */
	@NotNullValidate
	private String projectId;
	
	public GetNotifyMenuRequest() {

	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
    @Override
    public String toString() {
        return "GetNotifyMenuRequest [projectId="+projectId+",toString()=" + super.toString() + "]";
    }
}
