package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2016/9/9.
 */
public class DelTopicRequest extends BaseRequest {

    private static final long serialVersionUID = 1882548539421707640L;

    @ExplainAnnotation(explain = "会员ID")
    private String memberId;

    @ExplainAnnotation(explain = "话题ID")
    private String topicId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    @Override
    public String toString() {
        return "DelTopicRequest{" +
                "memberId='" + memberId + '\'' +
                ", topicId='" + topicId + '\'' +
                '}';
    }
}
