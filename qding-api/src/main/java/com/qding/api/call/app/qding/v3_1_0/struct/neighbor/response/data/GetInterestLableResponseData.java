package com.qding.api.call.app.qding.v3_1_0.struct.neighbor.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_1_0.struct.neighbor.InterestLableDto;
import com.qding.api.struct.ResponseData;

public class GetInterestLableResponseData extends ResponseData {

    private static final long serialVersionUID = 2409085836086098452L;
    
    @ExplainAnnotation(explain = "标签列表")
    private List<InterestLableDto> list;

	public List<InterestLableDto> getList() {
		return list;
	}

	public void setList(List<InterestLableDto> list) {
		this.list = list;
	}
    
    

}
