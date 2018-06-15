package com.qding.api.call.app.qding.v1_2_1.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * 根据社区id获取首页信息
 * @author lichao
 *
 */
public class GetCommunityIndexByIdRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7921866261700432001L;
	
	/**
	 * 社区ID
	 */
	private String communityId;
	
	public GetCommunityIndexByIdRequest() {

	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}

	@Override
	public String toString() {
		return "GetCommunityIndexByIdRequest [communityId=" + communityId
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
