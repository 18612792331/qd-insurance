package com.qding.api.call.app.qding.v3_2_0.struct.user.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_2_0.struct.user.RoomRoleDto;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/3.
 */
public class BindRoomInitResponseData extends ResponseData {

	private static final long serialVersionUID = -3048088629820103021L;
	
	@ExplainAnnotation (explain = "房间id")
    private String roomId;
	@ExplainAnnotation (explain = "角色列表")
    private List<RoomRoleDto> roleList;
	@ExplainAnnotation (explain = "信息提示")
	private String infotip;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public List<RoomRoleDto> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoomRoleDto> roleList) {
		this.roleList = roleList;
	}

	public String getInfotip() {
		return infotip;
	}

	public void setInfotip(String infotip) {
		this.infotip = infotip;
	}
    
    
    
    
    
     
}
