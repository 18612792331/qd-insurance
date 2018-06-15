package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;

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

    @ExplainAnnotation (explain = "报名状态",desc = "0:结束，1：进行 2:名额已满")
    private Integer enrollStatus;

    @ExplainAnnotation (explain = "参与状态",desc = "0：未参与，1：已参与")
    private Integer joinStatus;

    @ExplainAnnotation (explain = "群状态",desc = "0：解散，1：正常")
    private Integer gcRoomStatus;

    @ExplainAnnotation (explain = "已报名用户列表")
    private List<BriefMember> memberList;


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
}
