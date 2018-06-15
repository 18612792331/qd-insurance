package com.qding.api.call.app.qding.v1_3_0.struct.hotcycle.request;

import java.util.List;

import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.PublishTag;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 发布消息接口		
 * @author lichao
 *
 */
public class PublishFeedRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4192244304708636458L;
	
	private String userId;

	private String communityId;
	
	private String communityName;

	private String imageUrl;

	private String feedContent;

	private List<PublishTag> tags;
	
	public PublishFeedRequest() {

	}

	public PublishFeedRequest(String userId, String communityId,
			String communityName, String imageUrl, String feedContent,
			List<PublishTag> tags) {
		super();
		this.userId = userId;
		this.communityId = communityId;
		this.communityName = communityName;
		this.imageUrl = imageUrl;
		this.feedContent = feedContent;
		this.tags = tags;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getFeedContent() {
		return feedContent;
	}

	public void setFeedContent(String feedContent) {
		this.feedContent = feedContent;
	}

	public List<PublishTag> getTags() {
		return tags;
	}

	public void setTags(List<PublishTag> tags) {
		this.tags = tags;
	}


}
