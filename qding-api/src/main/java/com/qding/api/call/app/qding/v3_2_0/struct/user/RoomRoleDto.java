package com.qding.api.call.app.qding.v3_2_0.struct.user;

import com.qding.api.annotation.ExplainAnnotation;

public class RoomRoleDto {
	
	@ExplainAnnotation (explain = "角色标示")
	private String roleId;
	
	@ExplainAnnotation (explain = "角色名称 1业主 2业主亲戚 3业主朋友 4租客")
	private String roleName;
	
	public RoomRoleDto() {
		super();
	}

	public RoomRoleDto(String roleId, String roleName) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
}
