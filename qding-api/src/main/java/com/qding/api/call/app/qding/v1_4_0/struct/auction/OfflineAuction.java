package com.qding.api.call.app.qding.v1_4_0.struct.auction;

import java.io.Serializable;

/**
 * Created by qd on 2015/8/26.
 */
public class OfflineAuction implements Serializable {


    private static final long serialVersionUID = -4292112056394998180L;

    private AuctionInfo auctionInfo;

    private AuctionOrder auctionOrder;


    public OfflineAuction() {}


    public OfflineAuction(AuctionInfo auctionInfo,AuctionOrder auctionOrder){
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
