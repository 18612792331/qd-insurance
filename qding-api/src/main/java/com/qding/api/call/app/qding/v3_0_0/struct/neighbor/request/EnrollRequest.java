package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request;

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

    @ExplainAnnotation(explain = "话题ID")
    @NotNullValidate
    private String topicId;

    @ExplainAnnotation(explain = "场次ID")
    private String activitySessionId;

    @NotNullValidate
    @ExplainAnnotation(explain = "计划人数")
    private int personCount;

    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }

    public String getActivitySessionId() {
        return activitySessionId;
    }

    public void setActivitySessionId(String activitySessionId) {
        this.activitySessionId = activitySessionId;
    }

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    @Override
    public String toString() {
        return "EnrollRequest{" +
                "topicId='" + topicId + '\'' +
                ", activitySessionId='" + activitySessionId + '\'' +
                ", personCount='" + personCount + '\'' +
                '}';
    }
}
