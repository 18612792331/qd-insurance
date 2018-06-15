package com.qding.api.call.app.qding.v1_4_0.struct.auction.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/26.
 */

@Validate
public class UpdateAuctionShareStatusRequest  extends BaseRequest {

    private static final long serialVersionUID = -5169530168253631675L;

    @NotNullValidate
    private String auctionId;

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    @Override
    public String toString() {
        return "UpdateAuctionShareStatusRequest [auctionId="+auctionId+", toString()=" + super.toString() + "]";
    }
}
