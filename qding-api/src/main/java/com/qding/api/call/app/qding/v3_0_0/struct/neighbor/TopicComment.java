package com.qding.api.call.app.qding.v3_0_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.CommentBriefMember;

import java.io.Serializable;

/**
 * Created by qd on 2016/9/9.
 */
public class TopicComment implements Serializable {


    @ExplainAnnotation (explain = "评论ID")
    private String id;

    @ExplainAnnotation (explain = "被回复评论ID")
    private String pid;

    @ExplainAnnotation (explain = "话题ID")
    private String topicId;

    @ExplainAnnotation (explain = "评论发布人信息")
    private CommentBriefMember sendMember;

    @ExplainAnnotation (explain = "评论接收人信息")
    private CommentBriefMember receiveMember;

    @ExplainAnnotation (explain = "评论内容")
    private String content;

    @ExplainAnnotation (explain = "评论时间")
    private long createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public CommentBriefMember getSendMember() {
        return sendMember;
    }

    public void setSendMember(CommentBriefMember sendMember) {
        this.sendMember = sendMember;
    }

    public CommentBriefMember getReceiveMember() {
        return receiveMember;
    }

    public void setReceiveMember(CommentBriefMember receiveMember) {
        this.receiveMember = receiveMember;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
