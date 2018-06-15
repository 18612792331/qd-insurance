package com.qding.api.call.app.qding.v1_3_0.struct.hotcycle;

import java.io.Serializable;

public class PublishTagForFeed implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 3818430148485025678L;

	private String tagName;
	
	private String tagLeft;
	
	private String tagTop;

	public PublishTagForFeed() {

	}


	public PublishTagForFeed(String tagName, String tagLeft,
			String tagTop) {
		super();
		this.tagName = tagName;
		this.tagLeft = tagLeft;
		this.tagTop = tagTop;
	}


	public String getTagLeft() {
		return tagLeft;
	}


	public void setTagLeft(String tagLeft) {
		this.tagLeft = tagLeft;
	}


	public String getTagTop() {
		return tagTop;
	}


	public void setTagTop(String tagTop) {
		this.tagTop = tagTop;
	}


	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}


	@Override
	public String toString() {
		return "PublishTag [ tagName=" + tagName
				+ ", tagLeft=" + tagLeft + ", tagTop=" + tagTop + "]";
	}

}
