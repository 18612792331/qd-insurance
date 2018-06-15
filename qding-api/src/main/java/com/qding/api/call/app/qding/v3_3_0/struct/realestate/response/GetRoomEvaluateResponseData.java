package com.qding.api.call.app.qding.v3_3_0.struct.realestate.response;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.realestate.EvaluateLable;
import com.qding.api.struct.ResponseData;

public class GetRoomEvaluateResponseData extends ResponseData {

    private static final long serialVersionUID = 2108010926604691864L;
    
    @ExplainAnnotation (explain = "是否已经评价 0 未评价，1 已经评价")
    private int status;
    
    @ExplainAnnotation (explain = "评价星级")
    private int starLevel;
    
    @ExplainAnnotation (explain = "评价标签列表")
    private List<EvaluateLable> list;
    
    @ExplainAnnotation (explain = "评价内容")
    private String content;

	public int getStarLevel() {
		return starLevel;
	}

	public void setStarLevel(int starLevel) {
		this.starLevel = starLevel;
	}

	public List<EvaluateLable> getList() {
		return list;
	}

	public void setList(List<EvaluateLable> list) {
		this.list = list;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
    
	
    
    
}
