package com.qding.api.call.app.qding.v1_4_0.struct.auction.response.data;

import com.qding.api.call.app.qding.v1_4_0.struct.auction.AuctionDetail;
import com.qding.api.call.app.qding.v1_4_0.struct.auction.AuctionInfo;
import com.qding.api.struct.ResponseData;

/**
 * Created by jiawenzheng on 2015/8/28.
 */
public class OnlineAuctionResponseData  extends ResponseData {

    private static final long serialVersionUID = 3149513023955888180L;

    private AuctionInfo auctionInfo;

    private AuctionDetail auctionDetail;

    public AuctionInfo getAuctionInfo() {
        return auctionInfo;
    }

    public void setAuctionInfo(AuctionInfo auctionInfo) {
        this.auctionInfo = auctionInfo;
    }

    public AuctionDetail getAuctionDetail() {
        return auctionDetail;
    }

    public void setAuctionDetail(AuctionDetail auctionDetail) {
        this.auctionDetail = auctionDetail;
    }

    @Override
    public String toString() {
        return "OfflineAuctionResponseData [auctionInfo="+auctionInfo+",auctionDetail="+auctionDetail+",toString()=" + super.toString() + "]";
    }
}
