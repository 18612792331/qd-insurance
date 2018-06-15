package com.qding.api.call.app.qding.v3_3_0.struct.realestate.response;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.EvaluateLable;
import com.qding.api.struct.ResponseData;

public class LableListResponseData extends ResponseData {

    private static final long serialVersionUID = 2108010926604691864L;

    @ExplainAnnotation (explain = "评价标签列表")
    private List<EvaluateLable> list;

	public List<EvaluateLable> getList() {
		return list;
	}

	public void setList(List<EvaluateLable> list) {
		this.list = list;
	}

	
	
    
    
    
}
