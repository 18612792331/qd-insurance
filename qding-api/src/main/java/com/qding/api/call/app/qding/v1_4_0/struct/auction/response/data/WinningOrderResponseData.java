package com.qding.api.call.app.qding.v1_4_0.struct.auction.response.data;

import com.qding.api.call.app.qding.v1_4_0.struct.auction.AuctionInfo;
import com.qding.api.call.app.qding.v1_4_0.struct.auction.AuctionOrder;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/9/9.
 */
public class WinningOrderResponseData extends ResponseData {

    private static final long serialVersionUID = -4796190920259930128L;

    private AuctionInfo auctionInfo;

    private AuctionOrder auctionOrder;

    private Integer bidCount = 0;

    private Integer participateCount = 0;

    public Integer getParticipateCount() {
        return participateCount;
    }

    public void setParticipateCount(Integer participateCount) {
        this.participateCount = participateCount;
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

    public Integer getBidCount() {
        return bidCount;
    }

    public void setBidCount(Integer bidCount) {
        this.bidCount = bidCount;
    }

    @Override
    public String toString() {
        return "WinningOrderResponseData [auctionInfo="+auctionInfo+",auctionOrder="+auctionOrder+",bidCount="+bidCount+",participateCount="+participateCount+",toString()=" + super.toString() + "]";
    }
}
