package com.qding.api.call.app.qding.v1_3_0.struct.activity.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class GetActivityByIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7911334064675853520L;

	@NotNullValidate
	private String activityId;
	
	public GetActivityByIdRequest() {

	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	public String getActivityId() {
		return activityId;
	}
	
}
