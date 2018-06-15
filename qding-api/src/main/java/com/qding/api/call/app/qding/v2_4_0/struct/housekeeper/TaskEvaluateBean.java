package com.qding.api.call.app.qding.v2_4_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.io.Serializable;

/**
 * Created by qd on 2016/6/14.
 */
public class TaskEvaluateBean  extends  ReportEvaluateBean implements Serializable {

    @NotNullValidate
    @ExplainAnnotation (explain = "工单ID")
    private String taskId;

    @ExplainAnnotation (explain = "差评标签",desc = "多个中间用逗号隔开，要求传字典key")
    private String evaluateLables;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }


    public String getEvaluateLables() {
        return evaluateLables;
    }

    public void setEvaluateLables(String evaluateLables) {
        this.evaluateLables = evaluateLables;
    }
}
