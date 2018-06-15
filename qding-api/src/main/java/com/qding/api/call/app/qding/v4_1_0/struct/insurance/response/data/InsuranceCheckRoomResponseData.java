package com.qding.api.call.app.qding.v4_1_0.struct.insurance.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by zhangxiaojun on 2018/6/7.
 */
public class InsuranceCheckRoomResponseData extends ResponseData {
	private static final long serialVersionUID = 9153545829841806312L;
	
	@ExplainAnnotation(explain = "是否已投保； true:已投保，false:未投保")
	private boolean hasInsured;

	public boolean isHasInsured() {
		return hasInsured;
	}

	public void setHasInsured(boolean hasInsured) {
		this.hasInsured = hasInsured;
	}

}
