package com.qding.api.call.app.qding.v1_3_0.struct.brick;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="building")
public class Building implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3103444036647618107L;

	private String id;
	private String name;
	private String projectId;
    private String projectName;
    private String groupCode;
	private String groupName;
	private String cityId;
	private String cityName;

	public Building() {

	}

	public Building(String id, String name, String projectId,
			String projectName, String groupCode, String groupName,
			String cityId, String cityName) {
		super();
		this.id = id;
		this.name = name;
		this.projectId = projectId;
		this.projectName = projectName;
		this.groupCode = groupCode;
		this.groupName = groupName;
		this.cityId = cityId;
		this.cityName = cityName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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

	@Override
	public String toString() {
		return "Building [id=" + id + ", name=" + name + ", projectId="
				+ projectId + ", projectName=" + projectName + ", groupCode="
				+ groupCode + ", groupName=" + groupName + ", cityId=" + cityId
				+ ", cityName=" + cityName + "]";
	}
}
