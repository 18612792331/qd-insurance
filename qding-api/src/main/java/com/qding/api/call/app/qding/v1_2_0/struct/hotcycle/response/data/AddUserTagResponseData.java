package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.response.data;

import com.qding.api.struct.ResponseData;

public class AddUserTagResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -608748554123617495L;

	private String tagId;

	private String tagName;

	public AddUserTagResponseData() {

	}
	
	public AddUserTagResponseData(String tagId, String tagName) {
		super();
		this.tagId = tagId;
		this.tagName = tagName;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	
	public String getTagId() {
		return tagId;
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public String getTagName() {
		return tagName;
	}

	@Override
	public String toString() {
		return "AddUserTagResponseData [tagId=" + tagId + ", tagName="
				+ tagName + ", toString()=" + super.toString() + "]";
	}
}
