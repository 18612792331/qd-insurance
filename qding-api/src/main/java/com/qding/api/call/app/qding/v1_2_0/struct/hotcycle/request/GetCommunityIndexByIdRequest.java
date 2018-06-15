package com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.request;

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
	
	private String version1;
	
	private String code;

	
	public GetCommunityIndexByIdRequest(String communityId, String version1, String code) {
		super();
		this.communityId = communityId;
		this.version1 = version1;
		this.code = code;
	}

	public GetCommunityIndexByIdRequest() {
		
	}

	public String getVersion1() {
		return version1;
	}

	public void setVersion1(String version1) {
		this.version1 = version1;
	}

	public String getCommunityId() {
		return communityId;
	}

	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		return "GetCommunityIndexByIdRequest [communityId=" + communityId
				+ ", version1=" + version1
				+ ", code=" + code + ", toString()=" + super.toString() + "]";
	}
}
