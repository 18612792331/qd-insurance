package com.qding.api.call.app.qding.v1_4_0.struct.auction.response.data;

import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/9/17.
 */
public class GetIntegralByMemberIdResponseData   extends ResponseData {


    private static final long serialVersionUID = 271773159234550750L;

    private Long memberIntegral;

    public Long getMemberIntegral() {
        return memberIntegral;
    }

    public void setMemberIntegral(Long memberIntegral) {
        this.memberIntegral = memberIntegral;
    }

    @Override
    public String toString() {
        return "GetIntegralByMemberIdResponseData [memberIntegral="+memberIntegral+",toString()=" + super.toString() + "]";
    }
}
