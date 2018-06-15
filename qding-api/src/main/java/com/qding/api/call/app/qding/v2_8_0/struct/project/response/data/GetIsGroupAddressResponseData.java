package com.qding.api.call.app.qding.v2_8_0.struct.project.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/12/15.
 */
public class GetIsGroupAddressResponseData extends ResponseData {

    private static final long serialVersionUID = -2884008794120398607L;
    
    @ExplainAnnotation(explain = "本社区是否有组团0.否  1.是 ")
    private int isGroupAddress;
    
	public int getIsGroupAddress() {
		return isGroupAddress;
	}
	public void setIsGroupAddress(int isGroupAddress) {
		this.isGroupAddress = isGroupAddress;
	}
    
     
}
