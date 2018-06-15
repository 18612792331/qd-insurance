package com.qding.api.call.app.qding.v1_3_1.struct.project;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="recommendTag")
public class RecommendTag implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2034025114081663691L;

	
	private String id;
	
	private String imageUrl;
	
	private String name;
	
	private Integer feedCount;
	
	private Integer userCount;
	
	private String desc;

	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public RecommendTag() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFeedCount() {
		return feedCount;
	}

	public void setFeedCount(Integer feedCount) {
		this.feedCount = feedCount;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}


}
