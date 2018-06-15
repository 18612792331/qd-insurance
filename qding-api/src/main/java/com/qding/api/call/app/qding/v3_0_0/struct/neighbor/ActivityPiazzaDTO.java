package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;

/**
 * Created by qd on 2017/3/4.
 */
public class ActivityPiazzaDTO extends BriefActivity implements Serializable {

    @ExplainAnnotation(explain = "活动ID", desc = "即帖子ID")
    private String id;

    @ExplainAnnotation(explain = "活动名称")
    private String activityName;

    @ExplainAnnotation(explain = "是否名额已满", desc = "0:名额未满，1：名额已满")
    private Integer enrollStatus;

    @ExplainAnnotation(explain = "活动状态", desc = "0:报名中-未开始，1:进行中-已开始，2:已结束")
    private Integer step;

    @ExplainAnnotation(explain = "头图")
    private String topImg;

    @ExplainAnnotation(explain = "剩余时间")
    private Long surplusTime;

    @ExplainAnnotation(explain = "报名人数")
    private Integer enrollCount;

    @ExplainAnnotation(explain = "参与状态", desc = "0:未参加，1:已参加")
    private Integer joinStatus;

    @ExplainAnnotation(explain = "当前用户是否报名")
    private boolean haveSignUp;

    @ExplainAnnotation(explain = "支持人数,点赞人数")
    private Integer likeCount;

    public String getActivityName() {
        return activityName;
    }

    public boolean isHaveSignUp() {
        return haveSignUp;
    }

    public void setHaveSignUp(boolean haveSignUp) {
        this.haveSignUp = haveSignUp;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Integer getEnrollStatus() {
        return enrollStatus;
    }

    public void setEnrollStatus(Integer enrollStatus) {
        this.enrollStatus = enrollStatus;
    }

    public String getTopImg() {
        return topImg;
    }

    public void setTopImg(String topImg) {
        this.topImg = topImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Integer getJoinStatus() {
        return joinStatus;
    }

    public void setJoinStatus(Integer joinStatus) {
        this.joinStatus = joinStatus;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }
}
