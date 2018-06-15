package com.qding.api.call.app.qding.v3_1_0.struct.entrancecard;

import java.io.Serializable;
import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;

@ExplainAnnotation(explain = "社区信息")
public class ProjectDto implements Serializable {

    private static final long serialVersionUID = -7770487005752101081L;

    @ExplainAnnotation(explain = "社区ID", desc = "")
    private String projectId;

    @ExplainAnnotation(explain = "社区名称", desc = "")
    private String projectName;

    @ExplainAnnotation(explain = "城市ID", desc = "")
    private String cityId;

    @ExplainAnnotation(explain = "城市名称", desc = "")
    private String cityName;

    @ExplainAnnotation(explain = "业主在此社区的房屋列表", desc = "")
    private List<RoomBindDto> roomBindList;

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

    public List<RoomBindDto> getRoomBindList() {
        return roomBindList;
    }

    public void setRoomBindList(List<RoomBindDto> roomBindList) {
        this.roomBindList = roomBindList;
    }

}
