package com.qding.api.call.app.qding.v3_1_0.struct.user;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;

public class ProjectDto implements Serializable{

	@ExplainAnnotation(explain = "社区名称")
	private String projectName;
	
	@ExplainAnnotation(explain = "城市")
	private String cityName;
	
	@ExplainAnnotation(explain = "社区ID）")
	private String projectId;
	
	@ExplainAnnotation(explain = "图片")
	private String img;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	
	
}
