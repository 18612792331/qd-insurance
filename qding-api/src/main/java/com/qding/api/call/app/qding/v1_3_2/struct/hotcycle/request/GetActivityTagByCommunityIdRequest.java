package com.qding.api.call.app.qding.v1_3_2.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by Administrator on 2015/7/27.
 */
@Validate
public class GetActivityTagByCommunityIdRequest  extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2941511450111429882L;

	@NotNullValidate
	private String communityId;


	public String getCommunityId() {
		return communityId;
	}


	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	
	
}
