package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2015/12/16.
 */
public class SimplFeedBean implements Serializable {

    private static final long serialVersionUID = -7082442294646561386L;

    @ExplainAnnotation(explain = "信息ID")
    private String feedId;

    @ExplainAnnotation(explain = "feed内容")
    private String feedContent;

    @ExplainAnnotation(explain = "feed图片")
    private String feedImage;

    @ExplainAnnotation(explain = "社区地址")
    private String communityName;

    @ExplainAnnotation(explain = "发布时间")
    private String interval;

    @ExplainAnnotation(explain = "用户头像")
    private String userHeadImageUrl;

    public String getFeedId() {
        return feedId;
    }

    public void setFeedId(String feedId) {
        this.feedId = feedId;
    }

    public String getFeedContent() {
        return feedContent;
    }

    public void setFeedContent(String feedContent) {
        this.feedContent = feedContent;
    }

    public String getFeedImage() {
        return feedImage;
    }

    public void setFeedImage(String feedImage) {
        this.feedImage = feedImage;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getInterval() {
        return interval;
    }

    public void setInterval(String interval) {
        this.interval = interval;
    }

    public String getUserHeadImageUrl() {
        return userHeadImageUrl;
    }

    public void setUserHeadImageUrl(String userHeadImageUrl) {
        this.userHeadImageUrl = userHeadImageUrl;
    }
}
