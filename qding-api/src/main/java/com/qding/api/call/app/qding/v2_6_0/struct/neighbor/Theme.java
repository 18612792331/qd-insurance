package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/9/13.
 */
public class Theme implements Serializable {

    private static final long serialVersionUID = -2936741513765014694L;


    @ExplainAnnotation (explain = "是否支持发投票 1支持 0不支持")
    private Integer isSupportVote;

    @ExplainAnnotation (explain = "发布规则 1.先发后审 2.先审后发")
    private Integer publishRule;

    @ExplainAnnotation (explain = "用户投票时间规则 1每月 2每周")
    private Integer voteTimeRule;

    @ExplainAnnotation (explain = "投票上限数")
    private Integer voteNumRule;

    @ExplainAnnotation (explain = "是否支持报名活动 1支持 0不支持")
    private Integer isSupportActivity;

    @ExplainAnnotation (explain = "用户报名时间规则 1每月 2每周")
    private Integer supportTimeRule;

    @ExplainAnnotation (explain = "用户报名上限数")
    private Integer supportNumRule;


    public Integer getIsSupportVote() {
        return isSupportVote;
    }

    public void setIsSupportVote(Integer isSupportVote) {
        this.isSupportVote = isSupportVote;
    }

    public Integer getPublishRule() {
        return publishRule;
    }

    public void setPublishRule(Integer publishRule) {
        this.publishRule = publishRule;
    }

    public Integer getVoteTimeRule() {
        return voteTimeRule;
    }

    public void setVoteTimeRule(Integer voteTimeRule) {
        this.voteTimeRule = voteTimeRule;
    }

    public Integer getVoteNumRule() {
        return voteNumRule;
    }

    public void setVoteNumRule(Integer voteNumRule) {
        this.voteNumRule = voteNumRule;
    }

    public Integer getIsSupportActivity() {
        return isSupportActivity;
    }

    public void setIsSupportActivity(Integer isSupportActivity) {
        this.isSupportActivity = isSupportActivity;
    }

    public Integer getSupportTimeRule() {
        return supportTimeRule;
    }

    public void setSupportTimeRule(Integer supportTimeRule) {
        this.supportTimeRule = supportTimeRule;
    }

    public Integer getSupportNumRule() {
        return supportNumRule;
    }

    public void setSupportNumRule(Integer supportNumRule) {
        this.supportNumRule = supportNumRule;
    }
}
