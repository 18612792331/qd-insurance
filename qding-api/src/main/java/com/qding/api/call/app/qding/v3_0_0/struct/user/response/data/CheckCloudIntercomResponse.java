package com.qding.api.call.app.qding.v3_0_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 *报事
 */
public class CheckCloudIntercomResponse extends ResponseData {

    private static final long serialVersionUID = -378342396745401337L;

    @ExplainAnnotation(explain = "是否设置了云对讲")
    private Boolean   intercomFlag;

    private String projectId;

    private String projectName;

    public Boolean getIntercomFlag() {
        return intercomFlag;
    }

    public void setIntercomFlag(Boolean intercomFlag) {
        this.intercomFlag = intercomFlag;
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

    @Override
    public String toString() {
        return "CheckCloudIntercomResponse{" +
                "intercomFlag=" + intercomFlag +
                ", projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
