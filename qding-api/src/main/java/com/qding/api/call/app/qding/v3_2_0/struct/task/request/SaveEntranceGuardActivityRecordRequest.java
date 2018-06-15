package com.qding.api.call.app.qding.v3_2_0.struct.task.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/8/12.
 */
@Validate
public class SaveEntranceGuardActivityRecordRequest extends BaseRequest {

    @ExplainAnnotation (explain = "活动ID")
    @NotNullValidate
    private String activityId;

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    @Override
    public String toString() {
        return "SaveEntranceGuardActivityRecordRequest{" +
                "activityId='" + activityId + '\'' +
                '}';
    }
}
