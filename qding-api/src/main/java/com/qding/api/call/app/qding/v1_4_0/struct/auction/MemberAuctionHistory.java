package com.qding.api.call.app.qding.v1_4_0.struct.auction;

import java.io.Serializable;

/**
 * 会员竞拍历史
 * Created by qd on 2015/8/26.
 */
public class MemberAuctionHistory implements Serializable {


    private static final long serialVersionUID = 8402707453187839076L;

    private AuctionInfo auctionInfo;

    private AuctionOrder auctionOrder;

    public MemberAuctionHistory(){}

    public MemberAuctionHistory(AuctionInfo auctionInfo,AuctionOrder auctionOrder){
        this.auctionInfo = auctionInfo;
        this.auctionOrder = auctionOrder;
    }

    public AuctionInfo getAuctionInfo() {
        return auctionInfo;
    }

    public void setAuctionInfo(AuctionInfo auctionInfo) {
        this.auctionInfo = auctionInfo;
    }

    public AuctionOrder getAuctionOrder() {
        return auctionOrder;
    }

    public void setAuctionOrder(AuctionOrder auctionOrder) {
        this.auctionOrder = auctionOrder;
    }
}
