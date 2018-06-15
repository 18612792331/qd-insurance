package com.qding.api.call.app.qding.v2_5_0.struct.brick;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;

/**
 * Created by qd on 2016/11/22.
 */
public class RoomAddress implements Serializable {

	private static final long serialVersionUID = -1744125012432453224L;

	@ExplainAnnotation(explain = "房间流水号id")
    private String id;

    @ExplainAnnotation (explain = "房间名称")
    private String name;
    
    @ExplainAnnotation (explain = "楼栋id")
    private String buildingId;
    
    @ExplainAnnotation (explain = "楼栋名称")
    private String buildingName;

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


	@Override
	public String toString() {
		return "RoomAddress [id=" + id + ", name=" + name + ", buildingId="
				+ buildingId + ", buildingName=" + buildingName + "]";
	}

    
    
    
}
