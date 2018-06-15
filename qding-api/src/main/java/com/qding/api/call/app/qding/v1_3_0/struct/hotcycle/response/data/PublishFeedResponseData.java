package com.qding.api.call.app.qding.v1_3_0.struct.hotcycle.response.data;

import com.qding.api.struct.ResponseData;

public class PublishFeedResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3711060300996242737L;

	/**
	 * feedId
	 */
	private String feedId;

	public String getFeedId() {
		return feedId;
	}

	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}

	@Override
	public String toString() {
		return "CreateFeedResponse [toString()=" + super.toString() + "]";
	}

	public PublishFeedResponseData() {

	}
}
