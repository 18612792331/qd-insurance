package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias(value="feedImage")
public class FeedImage implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9132424332358385523L;

	/**
	 * 信息Id
	 */
	private String feedId;
	
	/**
	 * 图片地址
	 */
	private String imageUrl;
	
	
	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}
	
	public String getFeedId() {
		return feedId;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public FeedImage() {

	}

	public FeedImage(String feedId, String imageUrl) {
		super();
		this.feedId = feedId;
		this.imageUrl = imageUrl;
	}

	@Override
	public String toString() {
		return "FeedImage [feedId=" + feedId + ", imageUrl=" + imageUrl + "]";
	}



}
