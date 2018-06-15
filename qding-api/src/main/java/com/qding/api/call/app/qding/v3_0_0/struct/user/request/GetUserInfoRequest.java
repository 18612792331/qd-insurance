package com.qding.api.call.app.qding.v3_0_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2017/3/3.
 */
public class GetUserInfoRequest extends BaseRequest {

    private static final long serialVersionUID = 2421522758375545810L;

    @ExplainAnnotation (explain = "特定会员ID",desc = "只针对需要查询非当前用户的会员信息")
    private String selMemberId;

    public String getSelMemberId() {
        return selMemberId;
    }

    public void setSelMemberId(String selMemberId) {
        this.selMemberId = selMemberId;
    }

    @Override
    public String toString() {
        return "GetUserInfoRequest{" +
                "selMemberId='" + selMemberId + '\'' +
                '}';
    }
}
