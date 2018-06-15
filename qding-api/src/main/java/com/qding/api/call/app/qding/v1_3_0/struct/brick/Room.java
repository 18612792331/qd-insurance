package com.qding.api.call.app.qding.v1_3_0.struct.brick;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.util.PinYinUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;

public class Room implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8230255719582864496L;

	@ExplainAnnotation (explain = "房间ID")
	private String id;
	@ExplainAnnotation (explain = "房间名称")
	private String name;
	@ExplainAnnotation (explain = "社区ID")
	private String projectId;
	@ExplainAnnotation (explain = "社区名称")
	private String projectName;
	@ExplainAnnotation (explain = "建筑ID")
	private String buildingId;
	@ExplainAnnotation (explain = "建筑名称")
	private String buildingName;
	@ExplainAnnotation (explain = "描述")
	private String desc;
	//TODO fix bug 下一版本删除
	@ExplainAnnotation (explain = "业主手机号")
	private String[] mobiles;
	private String groupName;
	@ExplainAnnotation (explain = "是否售出",desc = "0 未售出 1 已售出")
	private Integer deliverStatus;
	@ExplainAnnotation (explain = "房间详情地址")
	private String roomAddr;
	@ExplainAnnotation (explain = "物业云|千丁房屋关联ID")
	private String csmRoomId;

	public Room() {

	}

	public Room(String id, String name, String projectId, String projectName,
			String buildingId, String buildingName, String desc,
			String[] mobiles, String groupName,Integer deliverStatus) {
		super();
		this.id = id;
		this.name = name;
		this.projectId = projectId;
		this.projectName = projectName;
		this.buildingId = buildingId;
		this.buildingName = buildingName;
		this.desc = desc;
		this.mobiles = mobiles;
		this.groupName = groupName;
		this.deliverStatus =deliverStatus;
	}

	public String getCsmRoomId() {
		return csmRoomId;
	}

	public void setCsmRoomId(String csmRoomId) {
		this.csmRoomId = csmRoomId;
	}

	public String getRoomAddr() {
		return roomAddr;
	}

	public void setRoomAddr(String roomAddr) {
		this.roomAddr = roomAddr;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getGroupName() {
		return groupName;
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


	public String getBuildingId() {
		return buildingId;
	}


	public void setBuildingId(String buildingId) {
		this.buildingId = buildingId;
	}


	public String getBuildingName() {
		return buildingName;
	}


	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public String[] getMobiles() {
		return mobiles;
	}


	public void setMobiles(String[] mobiles) {
		this.mobiles = mobiles;
	}

	public Integer getDeliverStatus() {
		return deliverStatus;
	}

	public void setDeliverStatus(Integer deliverStatus) {
		this.deliverStatus = deliverStatus;
	}
}
