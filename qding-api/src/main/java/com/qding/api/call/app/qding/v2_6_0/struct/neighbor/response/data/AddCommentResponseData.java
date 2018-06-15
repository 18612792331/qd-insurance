package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_6_0.struct.neighbor.TopicComment;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/9/9.
 */
public class AddCommentResponseData extends ResponseData {

    private static final long serialVersionUID = 4451363617010728625L;

    @ExplainAnnotation(explain = "话题信息")
    private TopicComment topicComment;

    public TopicComment getTopicComment() {
        return topicComment;
    }

    public void setTopicComment(TopicComment topicComment) {
        this.topicComment = topicComment;
    }

    @Override
    public String toString() {
        return "AddCommentResponseData{" +
                "topicComment=" + topicComment +
                '}';
    }
}
