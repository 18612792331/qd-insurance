package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle;

import java.io.Serializable;

import com.qding.hotcycle.struct.cache.ActivityPropertyBean;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="publishTag")
public class PublishTag implements Serializable {

	private static final long serialVersionUID = -153255780241472214L;

	private String tagId;
	
	private String tagName;
	
	private String tagLeft;
	
	private String tagTop;
	
	private String  activityId;

	
	public PublishTag() {

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

	public String getTagId() {
		return tagId;
	}


	public void setTagId(String tagId) {
		this.tagId = tagId;
	}


	public String getTagName() {
		return tagName;
	}


	public void setTagName(String tagName) {
		this.tagName = tagName;
	}


	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public PublishTag(String tagId, String tagName, String tagLeft, String tagTop, String activityId) {
		this.tagId = tagId;
		this.tagName = tagName;
		this.tagLeft = tagLeft;
		this.tagTop = tagTop;
		this.activityId = activityId;
	}

	@Override
	public String toString() {
		return "PublishTag [tagId=" + tagId + ", tagName=" + tagName
				+ ", tagLeft=" + tagLeft + ", tagTop=" + tagTop + ",activityId="+activityId+"]";
	}


}
