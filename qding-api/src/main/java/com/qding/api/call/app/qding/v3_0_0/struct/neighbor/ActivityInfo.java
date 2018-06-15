package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefMember;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/9/9.
 * 报名信息模型
 */
public class ActivityInfo extends BriefActivity implements Serializable {

    private static final long serialVersionUID = 839003568403239617L;

    @ExplainAnnotation (explain = "已报名总数")
    private  Integer enrollCount;

    @ExplainAnnotation (explain = "单人限报数")
    private  Integer limitCountPer;

    @ExplainAnnotation (explain = "活动状态",desc = "0:报名中-未开始，1:进行中-已开始，2:已结束")
    private Integer step;

    @ExplainAnnotation (explain = "是否名额已满",desc = "0:名额未满，1：名额已满")
    private Integer enrollStatus;

    @ExplainAnnotation (explain = "参与状态",desc = "0：未参与，1：已参与")
    private Integer joinStatus;

    @ExplainAnnotation (explain = "群状态",desc = "0：解散，1：正常")
    private Integer gcRoomStatus;

    @ExplainAnnotation (explain = "已报名用户列表")
    private List<BriefMember> memberList;

    @ExplainAnnotation (explain = "场次列表", desc = "场次列表为空说明不区分场次")
    private List<ActivitySessionDto> activitySessionDtoList;

 /*   @ExplainAnnotation (explain = "跟帖列表",desc = "具体看默认是最新，还是最热")
    private List<TopicCommon> followTopicList;*/

    @ExplainAnnotation (explain = "报名结束剩余时间",desc = "单位:秒")
    private Long surplusTime;

    public Long getSurplusTime() {
        return surplusTime;
    }

    public void setSurplusTime(Long surplusTime) {
        this.surplusTime = surplusTime;
    }

    public Integer getEnrollCount() {
        return enrollCount;
    }

    public void setEnrollCount(Integer enrollCount) {
        this.enrollCount = enrollCount;
    }

    public Integer getEnrollStatus() {
        return enrollStatus;
    }

    public void setEnrollStatus(Integer enrollStatus) {
        this.enrollStatus = enrollStatus;
    }

    public List<BriefMember> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<BriefMember> memberList) {
        this.memberList = memberList;
    }

    public Integer getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(Integer joinStatus) {
        this.joinStatus = joinStatus;
    }

    public Integer getGcRoomStatus() {
        return gcRoomStatus;
    }

    public void setGcRoomStatus(Integer gcRoomStatus) {
        this.gcRoomStatus = gcRoomStatus;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public List<ActivitySessionDto> getActivitySessionDtoList() {
        return activitySessionDtoList;
    }

    public void setActivitySessionDtoList(List<ActivitySessionDto> activitySessionDtoList) {
        this.activitySessionDtoList = activitySessionDtoList;
    }

    public Integer getLimitCountPer() {
        return limitCountPer;
    }

    public void setLimitCountPer(Integer limitCountPer) {
        this.limitCountPer = limitCountPer;
    }
}
