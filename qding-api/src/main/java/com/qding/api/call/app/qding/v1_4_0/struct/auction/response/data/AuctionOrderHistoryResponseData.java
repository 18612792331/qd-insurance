package com.qding.api.call.app.qding.v1_4_0.struct.auction.response.data;

import com.qding.api.call.app.qding.v1_4_0.struct.auction.AuctionOrder;
import com.qding.api.struct.ResponseData;

import java.util.List;


/**
 * Created by qd on 2015/9/9.
 */
public class AuctionOrderHistoryResponseData   extends ResponseData {


    private static final long serialVersionUID = -8180052647026893892L;

    private List<AuctionOrder> list;

    private int totalCount;

    public List<AuctionOrder> getList() {
        return list;
    }

    public void setList(List<AuctionOrder> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "AuctionOrderHistoryResponseData [list="+list+",totalCount="+totalCount+",toString()=" + super.toString() + "]";
    }
}
