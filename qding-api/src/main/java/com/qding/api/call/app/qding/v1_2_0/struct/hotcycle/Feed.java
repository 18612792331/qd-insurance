package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle;

import java.io.Serializable;
import java.util.List;


import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 信息
 * @author lichao
 *
 */
@XStreamAlias(value="feed")
public class Feed implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5959183835366175984L;

	/**
	 * 信息ID
	 */
	private String feedId;
	
	/**
	 * 用户ID
	 */
	private String userId;
	
	/**
	 * 商圈ID 
	 */
	private String businessId;
	
	/**
	 * 社区ID
	 */
	private String communityId;
	
	/**
	 * 社区名称
	 */
	private String communityName;
	
	/**
	 * 图片描述
	 */
	private String feedContent;
	
	/**
	 * 发布时间
	 */
	private long publishTime;
	
	/**
	 * 是否置顶
	 */
	private boolean isUp;
	
	/**
	 * 置顶下架时间
	 */
	private long downTime;
	
	/**
	 * 赞数
	 */
	private int praiseCount;
	
	/**
	 * 评论数
	 */
	private int commentCount;
	
	/**
	 * 信息所属用户
	 */
	private User feedUser;
	
	/**
	 * 信息图片
	 */
	private FeedImage feedImage; 
	
	/**
	 * 信息标签列表
	 */
	private List<FeedTag> feedTags;
	
	/**
	 * 信息赞列表
	 */
	private List<FeedPraise> feedPraises;
	
	/**
	 * 信息评论列表
	 */
	private List<FeedComment> feedComments;

	/**
	 * 分享后的合成图
	 */
	private String shareImgUrl;

	public String getShareImgUrl() {
		return shareImgUrl;
	}

	public void setShareImgUrl(String shareImgUrl) {
		this.shareImgUrl = shareImgUrl;
	}

	public Feed() {
	}

	

	public Feed(String feedId, String userId, String businessId,
			String communityId, String communityName, String feedContent,
			long publishTime, boolean isUp, long downTime, int praiseCount,
			int commentCount, User feedUser, FeedImage feedImage,
			List<FeedTag> feedTags, List<FeedPraise> feedPraises,
			List<FeedComment> feedComments,String shareImgUrl) {
		super();
		this.feedId = feedId;
		this.userId = userId;
		this.businessId = businessId;
		this.communityId = communityId;
		this.communityName = communityName;
		this.feedContent = feedContent;
		this.publishTime = publishTime;
		this.isUp = isUp;
		this.downTime = downTime;
		this.praiseCount = praiseCount;
		this.commentCount = commentCount;
		this.feedUser = feedUser;
		this.feedImage = feedImage;
		this.feedTags = feedTags;
		this.feedPraises = feedPraises;
		this.feedComments = feedComments;
		this.shareImgUrl = shareImgUrl;
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

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getFeedContent() {
		return feedContent;
	}

	public void setFeedContent(String feedContent) {
		this.feedContent = feedContent;
	}

	public long getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(long publishTime) {
		this.publishTime = publishTime;
	}

	public boolean isUp() {
		return isUp;
	}

	public void setUp(boolean isUp) {
		this.isUp = isUp;
	}

	public long getDownTime() {
		return downTime;
	}

	public void setDownTime(long downTime) {
		this.downTime = downTime;
	}

	public int getPraiseCount() {
		return praiseCount;
	}
	
	public void setPraiseCount(int praiseCount) {
		this.praiseCount = praiseCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public User getFeedUser() {
		return feedUser;
	}

	public void setFeedUser(User feedUser) {
		this.feedUser = feedUser;
	}

	public List<FeedTag> getFeedTags() {
		return feedTags;
	}

	public void setFeedTags(List<FeedTag> feedTags) {
		this.feedTags = feedTags;
	}

	public List<FeedPraise> getFeedPraises() {
		return feedPraises;
	}

	public void setFeedPraises(List<FeedPraise> feedPraises) {
		this.feedPraises = feedPraises;
	}

	public List<FeedComment> getFeedComments() {
		return feedComments;
	}

	public void setFeedComments(List<FeedComment> feedComments) {
		this.feedComments = feedComments;
	}

	public FeedImage getFeedImage() {
		return feedImage;
	}
	
	public void setFeedImage(FeedImage feedImage) {
		this.feedImage = feedImage;
	}



	@Override
	public String toString() {
		return "Feed [feedId=" + feedId + ", userId=" + userId
				+ ", businessId=" + businessId + ", communityId=" + communityId
				+ ", communityName=" + communityName + ", feedContent="
				+ feedContent + ", publishTime=" + publishTime + ", isUp="
				+ isUp + ", downTime=" + downTime + ", praiseCount="
				+ praiseCount + ", commentCount=" + commentCount
				+ ", feedUser=" + feedUser + ", feedImage=" + feedImage
				+ ", feedTags=" + feedTags + ", feedPraises=" + feedPraises
				+ ", feedComments=" + feedComments + "]";
	}

	
}
