package com.qding.api.call.app.qding.v3_2_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2017/3/3.
 */
public class BindRoomCancelRequest extends BaseRequest {

    private static final long serialVersionUID = -4421369124588834287L;
    
    @ExplainAnnotation (explain = "申请id")
    private String applyId;

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}
    
    

    
}
