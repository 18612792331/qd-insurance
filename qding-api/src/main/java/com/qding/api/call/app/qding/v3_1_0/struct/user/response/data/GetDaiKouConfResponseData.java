package com.qding.api.call.app.qding.v3_1_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;


public class GetDaiKouConfResponseData extends ResponseData {

    private static final long serialVersionUID = 2409085836086098452L;
    
    @ExplainAnnotation(explain = "物业费代扣状态1：开通，0：未开通")
    private Integer status=0;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
    
    

}
