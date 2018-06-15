package com.qding.api.call.app.qding.v1_4_0.struct.auction.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/9/9.
 */
@Validate
public class WinningOrderRequest  extends BaseRequest {

    private static final long serialVersionUID = -8415606097674396221L;

    @NotNullValidate
    private String auctionId;

    private String memberId = "";

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
        return "OfflineAuctionRequest [auctionId=" + auctionId + ",memberId="+memberId+", toString()=" + super.toString() + "]";
    }
}
