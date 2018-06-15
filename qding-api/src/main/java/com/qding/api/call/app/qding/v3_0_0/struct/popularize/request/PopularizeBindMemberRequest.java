package com.qding.api.call.app.qding.v3_0_0.struct.popularize.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/3/25.
 */
@Validate
public class PopularizeBindMemberRequest extends BaseRequest {

    private static final long serialVersionUID = 8401555068635232844L;



    @NotNullValidate
    @ExplainAnnotation(explain = "邀请码")
    private String inviteCode;

    private String memberId;

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
