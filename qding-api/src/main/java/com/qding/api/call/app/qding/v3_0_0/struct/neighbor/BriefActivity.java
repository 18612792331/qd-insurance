package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2016/9/12.
 */
public class BriefActivity extends SkipUrl implements Serializable{

    private static final long serialVersionUID = 8830637118503091307L;

    @ExplainAnnotation (explain = "活动开始时间",desc = "即报名截止时间")
    private  Long startTime;

    @ExplainAnnotation (explain = "活动结束时间")
    private Long endTime;

    @ExplainAnnotation (explain = "名额总数")
    private Integer  activityTotalCount;

    @ExplainAnnotation (explain = "集合地点")
    private String addr;


    @ExplainAnnotation (explain = "经度")
    private String longitude;

    @ExplainAnnotation (explain = "纬度")
    private String latitude;

    @ExplainAnnotation (explain = "受邀人")
    private String invitedPerson;

    @ExplainAnnotation (explain = "预计消费")
    private String consumes;

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getInvitedPerson() {
        return invitedPerson;
    }

    public void setInvitedPerson(String invitedPerson) {
        this.invitedPerson = invitedPerson;
    }

    public String getConsumes() {
        return consumes;
    }

    public void setConsumes(String consumes) {
        this.consumes = consumes;
    }

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

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
