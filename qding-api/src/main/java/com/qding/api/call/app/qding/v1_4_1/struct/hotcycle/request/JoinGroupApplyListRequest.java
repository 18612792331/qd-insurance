package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/29.
 */
@Validate
public class JoinGroupApplyListRequest  extends BaseRequest {

    private static final long serialVersionUID = -804162892188408577L;

    @NotNullValidate
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
