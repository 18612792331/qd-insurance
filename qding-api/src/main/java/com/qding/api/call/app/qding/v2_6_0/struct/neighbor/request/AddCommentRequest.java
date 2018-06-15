package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/9/9.
 */
@Validate
public class AddCommentRequest extends BaseRequest {

    private static final long serialVersionUID = 2443759120059478062L;

    @ExplainAnnotation (explain = "话题ID")
    private String topicId;

    @ExplainAnnotation (explain = "被回复的评论ID")
    private String pCommentId;

    @ExplainAnnotation (explain = "发送者会员ID")
    private String sendMemberId;

    @ExplainAnnotation (explain = "评论内容")
    private String content;

    @NotNullValidate
    @ExplainAnnotation (explain = "账户ID")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getpCommentId() {
        return pCommentId;
    }

    public void setpCommentId(String pCommentId) {
        this.pCommentId = pCommentId;
    }

    public String getSendMemberId() {
        return sendMemberId;
    }

    public void setSendMemberId(String sendMemberId) {
        this.sendMemberId = sendMemberId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AddCommentRequest{" +
                "topicId='" + topicId + '\'' +
                ", pCommentId='" + pCommentId + '\'' +
                ", sendMemberId='" + sendMemberId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
