package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/9/9.
 */
@Validate
public class EnrollRequest extends BaseRequest {

    private static final long serialVersionUID = 5472674636040321087L;

    @ExplainAnnotation(explain = "会员ID")
    @NotNullValidate
    private String memberId;

    @ExplainAnnotation(explain = "话题ID")
    @NotNullValidate
    private String topicId;

    @NotNullValidate
    @ExplainAnnotation(explain = "账户ID")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

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
        return "EnrollRequest{" +
                "memberId='" + memberId + '\'' +
                ", topicId='" + topicId + '\'' +
                ", userId='" + userId + '\'' +
                '}';
    }
}
