package com.qding.api.call.app.qding.v1_4_1.struct.notify.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/11/2.
 */
@Validate
public class GetNotifyRemindRequest extends BaseRequest {

    private static final long serialVersionUID = -5460814246475703004L;

    @NotNullValidate
    private String userId;

    private Integer queryType;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getQueryType() {
        return queryType;
    }

    public void setQueryType(Integer queryType) {
        this.queryType = queryType;
    }

    @Override
    public String toString() {
        return "GetNotifyRemindRequest [userId=" + userId +",queryType="+queryType+
                " toString()="+super.toString() +"]";
    }
}
