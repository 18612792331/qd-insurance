package com.qding.api.call.app.qding.v1_4_1.struct.notify;

import java.io.Serializable;

/**
 * Created by jiawenzheng on 2015/7/26.
 */
public class SkipBean implements Serializable {

	private static final long serialVersionUID = 5204734802496044959L;


	 /**
     * 类型
     */
    private String serviceType;

    /**
     * 子类型
     */
    private String subType;

	/**
	 * 活动状态
	 */
	private String skipUrl;

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getSubType() {
		return subType;
	}

	public void setSubType(String subType) {
		this.subType = subType;
	}

	public String getSkipUrl() {
		return skipUrl;
	}

	public void setSkipUrl(String skipUrl) {
		this.skipUrl = skipUrl;
	}
}
