package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/9/12.
 */
public class BriefActivity implements Serializable{

    private static final long serialVersionUID = 8830637118503091307L;

    @ExplainAnnotation (explain = "报名截止时间")
    private Long endTime;

    @ExplainAnnotation (explain = "名额总数")
    private Integer  activityTotalCount;

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getActivityTotalCount() {
        return activityTotalCount;
    }

    public void setActivityTotalCount(Integer activityTotalCount) {
        this.activityTotalCount = activityTotalCount;
    }


}
