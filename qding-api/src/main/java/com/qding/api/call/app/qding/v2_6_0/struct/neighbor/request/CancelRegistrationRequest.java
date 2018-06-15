package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2016/9/9.
 */
public class CancelRegistrationRequest extends BaseRequest {

    private static final long serialVersionUID = 5630321389780127280L;

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
        return "CancelRegistrationRequest{" +
                "memberId='" + memberId + '\'' +
                ", topicId='" + topicId + '\'' +
                '}';
    }
}
