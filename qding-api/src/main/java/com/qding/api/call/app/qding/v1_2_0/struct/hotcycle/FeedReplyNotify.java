package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 信息通知
 * @author lichao
 *
 */
@XStreamAlias(value="feedReplyNotify")
public class FeedReplyNotify implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 726241925490277096L;

	/**
	 * 信息ID
	 */
	private String feedId;
	
	/**
	 * 信息所属用户ID
	 */
	private String feedUserId;
	
	/**
	 * 触发通知用户
	 */
	private User triggerUser;
	
	/**
	 * 通知图片内容
	 */
	private FeedImage feedImage;
	
	/**
	 * 通知文字内容
	 */
	private String replyContent;
	
	/**
	 * 通知时间
	 */
	private long replyTime;
	
	/**
	 * 通知类型 1评论 2回复
	 */
	private int replyType;

	public FeedReplyNotify() {

	}


	public FeedReplyNotify(String feedId, String feedUserId, User triggerUser,
			FeedImage feedImage, String replyContent, long replyTime,
			int replyType) {
		super();
		this.feedId = feedId;
		this.feedUserId = feedUserId;
		this.triggerUser = triggerUser;
		this.feedImage = feedImage;
		this.replyContent = replyContent;
		this.replyTime = replyTime;
		this.replyType = replyType;
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

	public User getTriggerUser() {
		return triggerUser;
	}

	public void setTriggerUser(User triggerUser) {
		this.triggerUser = triggerUser;
	}


	public FeedImage getFeedImage() {
		return feedImage;
	}


	public void setFeedImage(FeedImage feedImage) {
		this.feedImage = feedImage;
	}


	public String getReplyContent() {
		return replyContent;
	}


	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}


	public long getReplyTime() {
		return replyTime;
	}


	public void setReplyTime(long replyTime) {
		this.replyTime = replyTime;
	}


	public int getReplyType() {
		return replyType;
	}


	public void setReplyType(int replyType) {
		this.replyType = replyType;
	}


	@Override
	public String toString() {
		return "FeedReplyNotify [feedId=" + feedId + ", feedUserId="
				+ feedUserId + ", triggerUser=" + triggerUser + ", feedImage="
				+ feedImage + ", replyContent=" + replyContent + ", replyTime="
				+ replyTime + ", replyType=" + replyType + ", toString()="
				+ super.toString() + "]";
	}

	
	
}
