package com.qding.api.call.app.qding.v3_2_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/3/3.
 */
public class BindRoomRequest extends BaseRequest {

    private static final long serialVersionUID = -4421369124588834287L;
    
    @ExplainAnnotation (explain = "房间id")
    @NotNullValidate
    private String roomId;
    
    @ExplainAnnotation (explain = "房屋角色类型")
    private Integer roleType;
    

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public Integer getRoleType() {
		return roleType;
	}

	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
    
    
    
}
