package com.qding.api.call.app.qding.v1_3_2.struct.im.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by jiawenzheng on 2015/7/27.
 */
@Validate
public class GetImUserTokenRequest  extends BaseRequest {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4817542988566147681L;
	/**
     * 用户ID
     */
    @NotNullValidate
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GetImUserTokenRequest() {
    }
}
