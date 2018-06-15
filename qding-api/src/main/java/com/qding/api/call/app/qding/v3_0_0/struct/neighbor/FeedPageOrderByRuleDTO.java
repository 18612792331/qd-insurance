package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/9.
 */
public class FeedPageOrderByRuleDTO implements Serializable {

    private static final long serialVersionUID = -6508829522057050019L;
    //系统热度
    private Double cursorSystemHot;
    //人工热度
    private Double cursorArtificialHot;
    //创建发布时间
    private Long lastTime;
    //针对活动
    private Long lastStartTime;
    //针对活动
    private Long lastEndTime;

    public Double getCursorSystemHot() {
        return cursorSystemHot;
    }

    public void setCursorSystemHot(Double cursorSystemHot) {
        this.cursorSystemHot = cursorSystemHot;
    }

    public Double getCursorArtificialHot() {
        return cursorArtificialHot;
    }

    public void setCursorArtificialHot(Double cursorArtificialHot) {
        this.cursorArtificialHot = cursorArtificialHot;
    }

    public Long getLastTime() {
        return lastTime;
    }

    public Long getLastStartTime() {
        return lastStartTime;
    }

    public void setLastStartTime(Long lastStartTime) {
        this.lastStartTime = lastStartTime;
    }

    public Long getLastEndTime() {
        return lastEndTime;
    }

    public void setLastEndTime(Long lastEndTime) {
        this.lastEndTime = lastEndTime;
    }

    public void setLastTime(Long lastTime) {
        this.lastTime = lastTime;
    }
}
