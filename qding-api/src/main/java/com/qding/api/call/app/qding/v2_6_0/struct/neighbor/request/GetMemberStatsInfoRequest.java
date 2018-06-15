package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/9/9.
 */
@Validate
public class GetMemberStatsInfoRequest extends BaseRequest {

    private static final long serialVersionUID = -23898966494461095L;

    @NotNullValidate
    @ExplainAnnotation(explain = "账号ID")
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "GetMemberStatsInfoRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }
}
