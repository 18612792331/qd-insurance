package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import java.io.Serializable;

/**
 * Created by qd on 2016/9/20.
 */
public class ActivityTopicStruct implements Serializable {

    private static final long serialVersionUID = 8464294691189436820L;

    private Long startTime;

    private Long endTime;

    private Integer maxPepole;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Integer getMaxPepole() {
        return maxPepole;
    }

    public void setMaxPepole(Integer maxPepole) {
        this.maxPepole = maxPepole;
    }
}
