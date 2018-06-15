package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 赞
 * @author lichao
 *
 */
@XStreamAlias(value="feedPraise")
public class FeedPraise implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3941025008431070470L;
	
	/**
	 * 信息ID
	 */
	private String feedId;
	
	/**
	 * 信息发布用户ID
	 */
	private String feedUserId;
	
	/**
	 * 点赞用户
	 */
	private User praiseUser;
	
	/**
	 * 点赞时间
	 */
	private long praiseTime;
	
	public FeedPraise() {

	}

	public FeedPraise(String feedId, String feedUserId, User praiseUser,
			long praiseTime) {
		super();
		this.feedId = feedId;
		this.feedUserId = feedUserId;
		this.praiseUser = praiseUser;
		this.praiseTime = praiseTime;
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

	public User getPraiseUser() {
		return praiseUser;
	}

	public void setPraiseUser(User praiseUser) {
		this.praiseUser = praiseUser;
	}

	public long getPraiseTime() {
		return praiseTime;
	}

	public void setPraiseTime(long praiseTime) {
		this.praiseTime = praiseTime;
	}

	@Override
	public String toString() {
		return "Praise [feedId=" + feedId + ", feedUserId=" + feedUserId
				+ ", praiseUser=" + praiseUser + ", praiseTime=" + praiseTime
				+ "]";
	}
}
