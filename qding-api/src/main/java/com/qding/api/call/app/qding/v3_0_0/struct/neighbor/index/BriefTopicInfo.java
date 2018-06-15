package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2017/2/24.
 */
public class BriefTopicInfo extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -6084658326495761015L;

    @ExplainAnnotation(explain = "发帖人简要信息")
    private BriefMember memberInfo;

    @ExplainAnnotation(explain = "常态标签简要信息")
    private BriefInteractionTagInfo  tagInfo;

    @ExplainAnnotation(explain = "帖子简述")
    private String topicDesc;

    @ExplainAnnotation(explain = "发布时间")
    private Long publishTime;

    @ExplainAnnotation(explain = "图片")
    private String topicImg;

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public BriefMember getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(BriefMember memberInfo) {
        this.memberInfo = memberInfo;
    }

    public BriefInteractionTagInfo getTagInfo() {
        return tagInfo;
    }

    public void setTagInfo(BriefInteractionTagInfo tagInfo) {
        this.tagInfo = tagInfo;
    }

    public String getTopicDesc() {
        return topicDesc;
    }

    public void setTopicDesc(String topicDesc) {
        this.topicDesc = topicDesc;
    }

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }
}
