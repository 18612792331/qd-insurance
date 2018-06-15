package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class AddUserTagRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4159646278193673580L;

	private String tagName;

	private String userId;
	
	public AddUserTagRequest() {
	}

	public AddUserTagRequest(String tagName, String userId) {
		super();
		this.tagName = tagName;
		this.userId = userId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "AddUserTagRequest [tagName=" + tagName + ", userId=" + userId
				+ ", toString()=" + super.toString() + "]";
	}
	
}
