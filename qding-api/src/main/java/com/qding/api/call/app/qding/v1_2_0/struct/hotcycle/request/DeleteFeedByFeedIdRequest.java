package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 根据用户id和 图文消息id删除图文消息
 * @author lichao
 *
 */
public class DeleteFeedByFeedIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -758218700837756114L;

	public DeleteFeedByFeedIdRequest(String feedId, String userId) {
		super();
		this.feedId = feedId;
		this.userId = userId;
	}

	private String feedId;
	
	private String userId;
	
	public DeleteFeedByFeedIdRequest() {

	}

	@Override
	public String toString() {
		return "DeleteFeedByFeedIdRequest [feedId=" + feedId + ", userId="
				+ userId + ", toString()=" + super.toString() + "]";
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
