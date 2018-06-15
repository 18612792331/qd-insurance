package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.response.data;

import com.qding.api.struct.ResponseData;

/**
 * Created by Administrator on 2015/8/5.
 */
public class GetActivityStatusResponseData  extends ResponseData {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7583849445021099983L;
	/**
     * 活动状态
     */
    private Integer activityStatus;

    public Integer getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(Integer activityStatus) {
        this.activityStatus = activityStatus;
    }

    @Override
    public String toString() {
        return "GetActivityStatusResponseData [activityStatus=" + activityStatus+
              ", toString()=" + super.toString() + "]";
    }
}
