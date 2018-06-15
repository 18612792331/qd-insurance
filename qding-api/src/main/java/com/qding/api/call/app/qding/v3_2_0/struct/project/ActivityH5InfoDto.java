package com.qding.api.call.app.qding.v3_2_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by jinhaishan on 17/7/17.
 */
public class ActivityH5InfoDto implements Serializable{
    private static final long serialVersionUID = -3120644111053569815L;

    @ExplainAnnotation(explain = "活动标题")
    private String activityTitle;

    @ExplainAnnotation(explain = "活动时间")
    private String activityTime;

    @ExplainAnnotation(explain = "活动url")
    private String activityUrl;

    @ExplainAnnotation(explain = "活动发布时间", desc = "服务端排序使用")
    private Long publishTime;

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }
}
