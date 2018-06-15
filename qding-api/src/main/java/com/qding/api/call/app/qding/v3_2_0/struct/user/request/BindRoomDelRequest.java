package com.qding.api.call.app.qding.v3_2_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2017/3/3.
 */
public class BindRoomDelRequest extends BaseRequest {

    private static final long serialVersionUID = -4421369124588834287L;
    
    @ExplainAnnotation (explain = "房间ID[审核通过房屋解绑时传递]")
    private String roomId;
    
    @ExplainAnnotation (explain = "申请ID[删除审核未通过时传递]")
    private String applyId;

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	
    
    

    
}
