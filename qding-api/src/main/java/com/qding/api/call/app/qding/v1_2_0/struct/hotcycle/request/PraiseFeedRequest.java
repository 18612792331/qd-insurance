package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 点赞
 * @author lichao
 *
 */
public class PraiseFeedRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5824671833590432494L;

	private String feedId;
	
	private String userId;
	
	public PraiseFeedRequest() {

	}
 
	public PraiseFeedRequest(String feedId, String userId) {
		super();
		this.feedId = feedId;
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "PraiseFeedRequest [feedId=" + feedId + ", userId=" + userId
				+ ", toString()=" + super.toString() + "]";
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
	
}
