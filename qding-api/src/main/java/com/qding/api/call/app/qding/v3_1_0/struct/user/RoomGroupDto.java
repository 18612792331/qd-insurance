package com.qding.api.call.app.qding.v3_1_0.struct.user;

import java.io.Serializable;
import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;

public class RoomGroupDto implements Serializable{

	@ExplainAnnotation(explain = "所属社区 形如长楹天街住宅")
	private String projectName;
	
	@ExplainAnnotation(explain = "社区Id")
	private String projectId;
	
	@ExplainAnnotation(explain = "城市Id")
	private String cityId;
	
	@ExplainAnnotation(explain = "城市名称")
	private String cityName;
	
	@ExplainAnnotation(explain = "房屋列表")
	private List<RoomDto> list;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<RoomDto> getList() {
		return list;
	}

	public void setList(List<RoomDto> list) {
		this.list = list;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	
	
	
}
