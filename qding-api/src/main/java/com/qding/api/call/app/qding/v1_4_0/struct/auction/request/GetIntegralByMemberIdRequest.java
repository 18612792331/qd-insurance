package com.qding.api.call.app.qding.v1_4_0.struct.auction.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/9/17.
 */
@Validate
public class GetIntegralByMemberIdRequest extends BaseRequest {


    private static final long serialVersionUID = -185133473733482029L;

    /**
     * 会员ID
     */
    @NotNullValidate
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "GetIntegralByMemberIdRequest [memberId=" + memberId + ", toString()=" + super.toString() + "]";
    }
}
