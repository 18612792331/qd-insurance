package com.qding.api.call.app.qding.v1_3_2.struct.im.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.util.List;

/**
 * Created by Administrator on 2015/7/27.
 */
@Validate
public class UnBlackUserRequest extends BaseRequest {

	/**
	 *
	 */
	private static final long serialVersionUID = -8219103462320755565L;

	/**
	 * 账户ID
	 */
	@NotNullValidate
	private String userId;

	/**
	 * 黑名单列表
	 */
	@NotNullValidate
	private List<String> blackUserIds;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public List<String> getBlackUserIds() {
		return blackUserIds;
	}

	public void setBlackUserIds(List<String> blackUserIds) {
		this.blackUserIds = blackUserIds;
	}

	public UnBlackUserRequest() {
	}
	
	
	
}
