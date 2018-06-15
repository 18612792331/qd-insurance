package com.qding.api.call.app.qding.v3_2_0.struct.user;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.user.ProjectRoom;
import com.qding.brick.pojo.biz.Project;

import java.util.List;

/**
 * Created by qd on 2017/7/21.
 */
public class MemberBindRoomGroupDTO {

    @ExplainAnnotation (explain = "社区ID")
    private String projectId;

    @ExplainAnnotation (explain = "社区信息名称")
    private String projectName;

    @ExplainAnnotation (explain = "房间列表")
    private List<ProjectRoom> roomList;

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

    public List<ProjectRoom> getRoomList() {
        return roomList;
    }

    public void setRoomList(List<ProjectRoom> roomList) {
        this.roomList = roomList;
    }
}
