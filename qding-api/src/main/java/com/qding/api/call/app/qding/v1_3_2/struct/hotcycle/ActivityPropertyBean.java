package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle;

import java.io.Serializable;

/**
 * Created by jiawenzheng on 2015/7/26.
 */
public class ActivityPropertyBean implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5204734802496044959L;

	/**活动ID
	 * 
	 */
	private String activityId;

	 /**
     * 关联名称
     */
    private String relevancyName;

    /**
     * 活动关联配置
     */
    private String relevancyProperty;

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

	public String getRelevancyName() {
		return relevancyName;
	}

	public void setRelevancyName(String relevancyName) {
		this.relevancyName = relevancyName;
	}

	public String getRelevancyProperty() {
		return relevancyProperty;
	}

	public void setRelevancyProperty(String relevancyProperty) {
		this.relevancyProperty = relevancyProperty;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

    
}
