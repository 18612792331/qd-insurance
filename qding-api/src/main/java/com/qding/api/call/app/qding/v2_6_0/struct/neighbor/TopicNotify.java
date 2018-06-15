package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2016/9/22.
 */
public class TopicNotify extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 6503314659212402785L;

    @ExplainAnnotation (explain = "话题ID")
    private String topicId;

    @ExplainAnnotation (explain = "话题图片")
    private String topicImg;

    @ExplainAnnotation (explain = "话题简要内容")
    private String topicContent;

    @ExplainAnnotation (explain = "评论或回复内容")
    private String commentContent;

    @ExplainAnnotation (explain = "发出者ID(点赞人|评论人)")
    private BriefMember sMember;

    @ExplainAnnotation (explain = "接受者会员ID (被回复人)")
    private BriefMember rMember;

    @ExplainAnnotation (explain = "通知时间")
    private long replyTime;

    @ExplainAnnotation (explain = "通知类型 1评论 2评论回复 3,点赞")
    private int type;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getTopicImg() {
        return topicImg;
    }

    public void setTopicImg(String topicImg) {
        this.topicImg = topicImg;
    }

    public String getTopicContent() {
        return topicContent;
    }

    public void setTopicContent(String topicContent) {
        this.topicContent = topicContent;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public BriefMember getsMember() {
        return sMember;
    }

    public void setsMember(BriefMember sMember) {
        this.sMember = sMember;
    }

    public BriefMember getrMember() {
        return rMember;
    }

    public void setrMember(BriefMember rMember) {
        this.rMember = rMember;
    }

    public long getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(long replyTime) {
        this.replyTime = replyTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
