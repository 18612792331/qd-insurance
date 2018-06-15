package com.qding.api.call.app.qding.v3_0_0.struct.user.request;

import com.qding.api.call.app.qding.v1_3_0.struct.user.Member;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/3/3.
 */
@Validate
public class UpdateUserInfoRequest extends BaseRequest {

    private static final long serialVersionUID = -2407839231837027614L;

    @NotNullValidate
    private Member memberInfo;

    public Member getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(Member memberInfo) {
        this.memberInfo = memberInfo;
    }
}
