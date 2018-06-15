package com.qding.api.call.app.qding.v2_4_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;

/**
 * Created by qd on 2016/6/14.
 */
public class EmpLocationBean extends HkLocationBean {


    @ExplainAnnotation(explain = "工单ID")
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }


}
