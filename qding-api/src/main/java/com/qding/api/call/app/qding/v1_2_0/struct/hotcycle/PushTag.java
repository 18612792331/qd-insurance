package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="pushTag")
public class PushTag implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2034025114081663691L;

	
	private String tagId;
	
	private String tagImageUrl;
	
	private String tagName;
	
	private int tagCount;
	
	private String tagDesc;
	
	public PushTag() {
	}


	public PushTag(String tagId, String tagImageUrl, String tagName,
			int tagCount, String tagDesc) {
		super();
		this.tagId = tagId;
		this.tagImageUrl = tagImageUrl;
		this.tagName = tagName;
		this.tagCount = tagCount;
		this.tagDesc = tagDesc;
	}

	public String getTagDesc() {
		return tagDesc;
	}
	
	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}

	public String getTagId() {
		return tagId;
	}


	public void setTagId(String tagId) {
		this.tagId = tagId;
	}


	public String getTagImageUrl() {
		return tagImageUrl;
	}


	public void setTagImageUrl(String tagImageUrl) {
		this.tagImageUrl = tagImageUrl;
	}


	public String getTagName() {
		return tagName;
	}


	public void setTagName(String tagName) {
		this.tagName = tagName;
	}


	public int getTagCount() {
		return tagCount;
	}


	public void setTagCount(int tagCount) {
		this.tagCount = tagCount;
	}


	@Override
	public String toString() {
		return "PushTag [tagId=" + tagId + ", tagImageUrl=" + tagImageUrl
				+ ", tagName=" + tagName + ", tagCount=" + tagCount
				+ ", tagDesc=" + tagDesc + "]";
	}


	
	
}
