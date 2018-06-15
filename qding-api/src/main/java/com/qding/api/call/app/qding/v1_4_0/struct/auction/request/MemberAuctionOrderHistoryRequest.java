package com.qding.api.call.app.qding.v1_4_0.struct.auction.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/8/28.
 */
@Validate
public class MemberAuctionOrderHistoryRequest extends BaseRequest {

    private static final long serialVersionUID = -8332238226079090364L;

    @NotNullValidate
    private String memberId = "";

    @NotNullValidate
    private String auctionId = "";

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    @Override
    public String toString() {
        return "MemberAuctionOrderHistoryRequest{" +
                "memberId='" + memberId + '\'' +
                ", auctionId='" + auctionId + '\'' +
                '}';
    }
}
