package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request;

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
    @NotNullValidate
    private String topicId;

    @ExplainAnnotation (explain = "被回复的评论ID")
    private String pcommentId;

    @ExplainAnnotation (explain = "评论内容")
    @NotNullValidate
    private String content;


    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getPcommentId() {
        return pcommentId;
    }

    public void setPcommentId(String pcommentId) {
        this.pcommentId = pcommentId;
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
                ", content='" + content + '\'' +
                '}';
    }
}
