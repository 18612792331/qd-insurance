package com.qding.api.call.app.qding.v4_1_0.struct.propertyService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;

public class PropertyServiceResultDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1769790102505582148L;

	@ExplainAnnotation(explain = "主分类ID")
	private Long mainId;
	
	@ExplainAnnotation(explain = "主分类名称")
    private String mainName;
    
	@ExplainAnnotation(explain = "子分类列表")
    private List<PropertyServiceSubClazResultDTO> subList;

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	public String getMainName() {
		return mainName;
	}

	public void setMainName(String mainName) {
		this.mainName = mainName;
	}

	public List<PropertyServiceSubClazResultDTO> getSubList() {
		return subList;
	}

	public void setSubList(List<PropertyServiceSubClazResultDTO> subList) {
		this.subList = subList;
	}

}
