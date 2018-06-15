package com.qding.api.call.app.qding.v2_1_0.struct.task;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;

import java.io.Serializable;

/**
 * Created by qd on 2016/3/24.
 */
public class EntranceGuardBean extends BtnSkip implements Serializable {

    private static final long serialVersionUID = 2490513290208029046L;

    @ExplainAnnotation(explain = "会员任务数据流关联ID")
    private String tmfId = "";

    @ExplainAnnotation(explain = "任务ID")
    private String taskId = "";

    @ExplainAnnotation(explain = "任务名称")
    private String taskName = "";

    @ExplainAnnotation(explain = "任务简介")
    private String taskDesc = "";

    @ExplainAnnotation(explain = "任务图片")
    private String taskImgUrl = "";

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public String getTaskImgUrl() {
        return taskImgUrl;
    }

    public void setTaskImgUrl(String taskImgUrl) {
        this.taskImgUrl = taskImgUrl;
    }

    public String getTmfId() {
        return tmfId;
    }

    public void setTmfId(String tmfId) {
        this.tmfId = tmfId;
    }
}
