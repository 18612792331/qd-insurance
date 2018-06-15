package com.qding.api.call.app.qding.v3_2_0.struct.user.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.user.ProjectRoom;
import com.qding.api.call.app.qding.v3_2_0.struct.user.MemberBindRoomGroupDTO;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/3.
 */
public class GetRoomBymidResponseData extends ResponseData {

	private static final long serialVersionUID = -3048088629820103021L;

	@ExplainAnnotation(explain = "社区分组已绑定房间列表")
	private List<MemberBindRoomGroupDTO> list;

	public List<MemberBindRoomGroupDTO> getList() {
		return list;
	}

	public void setList(List<MemberBindRoomGroupDTO> list) {
		this.list = list;
	}


	@Override
	public String toString() {
		return "GetRoomBymidResponseData{" +
				"list=" + list +
				'}';
	}
}
