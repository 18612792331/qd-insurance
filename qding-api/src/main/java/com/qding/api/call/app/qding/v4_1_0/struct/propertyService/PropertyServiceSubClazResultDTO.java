package com.qding.api.call.app.qding.v4_1_0.struct.propertyService;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;

public class PropertyServiceSubClazResultDTO implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 4784745184703973996L;

	@ExplainAnnotation(explain = "子分类ID")
	private Long subId;

	@ExplainAnnotation(explain = "子分类名称")
    private String subName;
	
	@ExplainAnnotation(explain = "子分类图片")
    private String imgUrl;

	public Long getSubId() {
		return subId;
	}

	public void setSubId(Long subId) {
		this.subId = subId;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
