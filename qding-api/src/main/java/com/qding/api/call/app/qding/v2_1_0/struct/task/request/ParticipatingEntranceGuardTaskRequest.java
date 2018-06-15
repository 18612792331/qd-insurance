package com.qding.api.call.app.qding.v2_1_0.struct.task.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/3/24.
 */
public class ParticipatingEntranceGuardTaskRequest extends BaseRequest {

    private static final long serialVersionUID = 3711142128364604038L;

    @NotNullValidate
    @ExplainAnnotation (explain = "用户和任务流关联Id")
    private String tmfId;

    @NotNullValidate
    @ExplainAnnotation (explain = "任务ID")
    private String taskId;

    @NotNullValidate
    @ExplainAnnotation (explain = "参与方式",desc = "0：放弃 1：执行")
    private Integer executeType;

    @NotNullValidate
    @ExplainAnnotation (explain = "会员ID")
    private String memberId;

    @NotNullValidate
    @ExplainAnnotation (explain = "所属社区ID")
    private String projectId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Integer getExecuteType() {
        return executeType;
    }

    public void setExecuteType(Integer executeType) {
        this.executeType = executeType;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTmfId() {
        return tmfId;
    }

    public void setTmfId(String tmfId) {
        this.tmfId = tmfId;
    }


    @Override
    public String toString() {
        return "ParticipatingEntranceGuardTaskRequest{" +
                "tmfId='" + tmfId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", executeType=" + executeType +
                ", memberId='" + memberId + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }
}
