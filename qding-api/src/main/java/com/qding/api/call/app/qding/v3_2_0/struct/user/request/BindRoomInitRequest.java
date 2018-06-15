package com.qding.api.call.app.qding.v3_2_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2017/3/3.
 */
public class BindRoomInitRequest extends BaseRequest {

    private static final long serialVersionUID = -4421369124588834287L;
    
    @ExplainAnnotation (explain = "房间id")
    private String roomId;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
    
    
    
}
