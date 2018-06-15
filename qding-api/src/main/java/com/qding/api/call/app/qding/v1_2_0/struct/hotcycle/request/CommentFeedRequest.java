package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 发布评论	
 * @author lichao
 *
 */
public class CommentFeedRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6446150178209958989L;

	private String feedId;
	
	private String content;
	
	private String userId;
	
	private String parentCommentId;
	
	public CommentFeedRequest() {

	}

	public String getFeedId() {
		return feedId;
	}

	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}

	public String getContent() {
		return content;
	}

	public CommentFeedRequest(String feedId, String content, String userId,
			String parentCommentId) {
		super();
		this.feedId = feedId;
		this.content = content;
		this.userId = userId;
		this.parentCommentId = parentCommentId;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "CommentFeedRequest [feedId=" + feedId + ", content=" + content
				+ ", userId=" + userId + ", parentCommentId=" + parentCommentId
				+ ", toString()=" + super.toString() + "]";
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getParentCommentId() {
		return parentCommentId;
	}

	public void setParentCommentId(String parentCommentId) {
		this.parentCommentId = parentCommentId;
	}
}
