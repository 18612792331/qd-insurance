package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import java.io.Serializable;


import com.qding.api.annotation.ExplainAnnotation;

/**
 * Created by qd on 2016/9/9.
 */
public class TopicDetail  implements Serializable {

    private static final long serialVersionUID = 4762608030286375401L;

    @ExplainAnnotation(explain = "通用帖子信息")
    private TopicCommon commonInfo;

    @ExplainAnnotation(explain = "投票话题信息")
    private VoteInfo voteInfo;

    @ExplainAnnotation(explain = "报名话题信息")
    private ActivityInfo activityInfo;

    @ExplainAnnotation(explain = "新闻附属信息")
    private BriefNews newsInfo;

    @ExplainAnnotation(explain = "百科附属信息")
    private EncyclopediaDTO  encyclopediaInfo;

    public TopicCommon getCommonInfo() {
        return commonInfo;
    }

    public void setCommonInfo(TopicCommon commonInfo) {
        this.commonInfo = commonInfo;
    }

    public VoteInfo getVoteInfo() {
        return voteInfo;
    }

    public void setVoteInfo(VoteInfo voteInfo) {
        this.voteInfo = voteInfo;
    }

    public ActivityInfo getActivityInfo() {
        return activityInfo;
    }

    public void setActivityInfo(ActivityInfo activityInfo) {
        this.activityInfo = activityInfo;
    }

    public BriefNews getNewsInfo() {
        return newsInfo;
    }

    public void setNewsInfo(BriefNews newsInfo) {
        this.newsInfo = newsInfo;
    }

    public EncyclopediaDTO getEncyclopediaInfo() {
        return encyclopediaInfo;
    }

    public void setEncyclopediaInfo(EncyclopediaDTO encyclopediaInfo) {

        this.encyclopediaInfo = encyclopediaInfo;
    }


}
