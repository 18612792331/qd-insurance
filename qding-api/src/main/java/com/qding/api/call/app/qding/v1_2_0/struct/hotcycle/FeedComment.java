package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 评论
 * @author lichao
 *
 */
@XStreamAlias(value="feedComment")
public class FeedComment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6805555912288189479L;

	/**
	 * 评论信息ID
	 */
	private String commentId;
	
	/**
	 * 信息ID
	 */
	private String feedId;
	
	/**
	 * 信息用户ID
	 */
	private String feedUserId;
	
	/**
	 * 评论接收用户
	 */
	private String receiveUserId;
	
	/**
	 * 评论发布用户
	 */
	private User sendUser;
	
	/**
	 * 评论内容
	 */
	private String commentContent;
	
	/**
	 * 评论时间
	 */
	private long commentTime;
	
	/**
	 * 回复对应的评论ID
	 */
	private String parentCommentId;
	
	
	/**
	 * 回复对应评论用户ID
	 */
	private User parentUser;
	
	public FeedComment() {

	}

	public FeedComment(String commentId, String feedId, String feedUserId,
			String receiveUserId, User sendUser, String commentContent,
			long commentTime, String parentCommentId, User parentUser) {
		super();
		this.commentId = commentId;
		this.feedId = feedId;
		this.feedUserId = feedUserId;
		this.receiveUserId = receiveUserId;
		this.sendUser = sendUser;
		this.commentContent = commentContent;
		this.commentTime = commentTime;
		this.parentCommentId = parentCommentId;
		this.parentUser = parentUser;
	}

	public User getParentUser() {
		return parentUser;
	}
	
	public void setParentUser(User parentUser) {
		this.parentUser = parentUser;
	}

	public String getCommentId() {
		return commentId;
	}


	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}


	public String getFeedId() {
		return feedId;
	}


	public void setFeedId(String feedId) {
		this.feedId = feedId;
	}


	public String getFeedUserId() {
		return feedUserId;
	}


	public void setFeedUserId(String feedUserId) {
		this.feedUserId = feedUserId;
	}


	public String getReceiveUserId() {
		return receiveUserId;
	}


	public void setReceiveUserId(String receiveUserId) {
		this.receiveUserId = receiveUserId;
	}


	public User getSendUser() {
		return sendUser;
	}


	public void setSendUser(User sendUser) {
		this.sendUser = sendUser;
	}


	public String getCommentContent() {
		return commentContent;
	}


	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}


	public long getCommentTime() {
		return commentTime;
	}


	public void setCommentTime(long commentTime) {
		this.commentTime = commentTime;
	}


	public String getParentCommentId() {
		return parentCommentId;
	}


	public void setParentCommentId(String parentCommentId) {
		this.parentCommentId = parentCommentId;
	}


	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", feedId=" + feedId
				+ ", feedUserId=" + feedUserId + ", receiveUserId="
				+ receiveUserId + ", sendUser=" + sendUser
				+ ", commentContent=" + commentContent + ", commentTime="
				+ commentTime + ", parentCommentId=" + parentCommentId + "]";
	}
	
}
