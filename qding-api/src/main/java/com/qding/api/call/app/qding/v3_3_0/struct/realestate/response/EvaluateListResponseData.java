package com.qding.api.call.app.qding.v3_3_0.struct.realestate.response;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.EvaluateBody;
import com.qding.api.struct.ResponseData;

public class EvaluateListResponseData extends ResponseData {

    private static final long serialVersionUID = 2108010926604691864L;

    @ExplainAnnotation (explain = "房屋入住签约列表")
    private List<EvaluateBody> list;

	public List<EvaluateBody> getList() {
		return list;
	}

	public void setList(List<EvaluateBody> list) {
		this.list = list;
	}
    
	
    
    
    
}
