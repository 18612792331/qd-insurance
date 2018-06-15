package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 删除评论				
 * @author lichao
 *
 */
public class DeleteFeedCommentRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6227550012142437141L;

	/**
	 * 消息ID
	 */
	private String feedId;
	
	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 评论ID
	 */
	private String commentId;
	
	public DeleteFeedCommentRequest() {

	}

	public DeleteFeedCommentRequest(String feedId, String userId,
			String commentId) {
		super();
		this.feedId = feedId;
		this.userId = userId;
		this.commentId = commentId;
	}

	public String getFeedId() {
		return feedId;
	}

	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	@Override
	public String toString() {
		return "DeleteCommentRequest [feedId=" + feedId + ", userId=" + userId
				+ ", commentId=" + commentId + ", toString()="
				+ super.toString() + "]";
	}
}
