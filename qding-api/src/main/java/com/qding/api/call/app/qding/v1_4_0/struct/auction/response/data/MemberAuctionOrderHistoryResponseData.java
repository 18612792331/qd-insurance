package com.qding.api.call.app.qding.v1_4_0.struct.auction.response.data;

import com.qding.api.call.app.qding.v1_4_0.struct.auction.AuctionOrder;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/8/28.
 */
public class MemberAuctionOrderHistoryResponseData  extends ResponseData {

    private static final long serialVersionUID = 7915460177305467039L;
    private List<AuctionOrder> list;

    public List<AuctionOrder> getList() {
        return list;
    }

    public void setList(List<AuctionOrder> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MemberAuctionOrderHistoryResponseData [list="+list+",,toString()=" + super.toString() + "]";
    }
}
