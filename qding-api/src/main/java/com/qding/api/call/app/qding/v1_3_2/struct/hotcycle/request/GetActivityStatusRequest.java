package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by Administrator on 2015/8/5.
 */
@Validate
public class GetActivityStatusRequest   extends BaseRequest {

    /**
	 * 
	 */
	private static final long serialVersionUID = 304089702672397881L;
	/**
     * 活动ID
     */
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
        return "GetActivityStatusRequest [ activityId=" + activityId + ", toString()="
                + super.toString() + "]";
    }
}
