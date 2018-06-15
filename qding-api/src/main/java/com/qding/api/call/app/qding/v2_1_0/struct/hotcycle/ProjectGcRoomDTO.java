package com.qding.api.call.app.qding.v2_1_0.struct.hotcycle;


import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;


public class ProjectGcRoomDTO implements Serializable {
    private static final long serialVersionUID = 4318313758463570297L;

    @ExplainAnnotation(explain = "社区ID")
    private String projectId;

    @ExplainAnnotation(explain = "社区名")
    private String projectName;

    @ExplainAnnotation(explain = "群组列表")
    private List<GcRoomDTO> groupList;

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

    public List<GcRoomDTO> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<GcRoomDTO> groupList) {
        this.groupList = groupList;
    }

    @Override
    public String toString() {
        return "ProjectGcRoomDTO{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", groupList=" + groupList +
                '}';
    }
}
