package com.qding.api.call.app.qding.v3_1_0.struct.neighbor;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;

public class InterestLableDto implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ExplainAnnotation(explain = "标签id")
	private String lableId;
	
	@ExplainAnnotation(explain = "标签名称")
	private String lableName;
	
	public InterestLableDto() {
		super();
	}

	public InterestLableDto(String lableId, String lableName) {
		super();
		this.lableId = lableId;
		this.lableName = lableName;
	}

	public String getLableId() {
		return lableId;
	}

	public void setLableId(String lableId) {
		this.lableId = lableId;
	}

	public String getLableName() {
		return lableName;
	}

	public void setLableName(String lableName) {
		this.lableName = lableName;
	}
	
	
	
}
