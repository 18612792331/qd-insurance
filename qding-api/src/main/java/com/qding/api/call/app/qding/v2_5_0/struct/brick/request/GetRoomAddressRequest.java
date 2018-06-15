package com.qding.api.call.app.qding.v2_5_0.struct.brick.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/11/22.
 */
public class GetRoomAddressRequest extends BaseRequest {

    private static final long serialVersionUID = 6189848555095339889L;
    
    @ExplainAnnotation (explain = "社区id")
    @NotNullValidate
    private String projectId;
    
    @ExplainAnnotation (explain = "组团名称")
    @NotNullValidate
    private String groupName;
    
    @ExplainAnnotation (explain = "会员id")
    @NotNullValidate
    private String memeberId;
    
    
    public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getMemeberId() {
		return memeberId;
	}
	public void setMemeberId(String memeberId) {
		this.memeberId = memeberId;
	}
	
	@Override
	public String toString() {
		return "GetRoomAddressRequest [projectId=" + projectId
				+ ", groupName=" + groupName + ", memeberId=" + memeberId
				+ "]";
	}
	
	
	 
	
	
	 
}
