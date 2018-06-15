package com.qding.api.call.app.qding.v1_4_0.struct.auction.response.data;

import com.qding.api.call.app.qding.v1_4_0.struct.auction.MemberAuctionHistory;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/8/28.
 */
public class MemberAuctionHistoryResponseData  extends ResponseData {

    private static final long serialVersionUID = -3780664198512786733L;

    private List<MemberAuctionHistory> list;

    private int totalCount;

    public List<MemberAuctionHistory> getList() {
        return list;
    }

    public void setList(List<MemberAuctionHistory> list) {
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
        return "MemberAuctionHistoryResponseData [list="+list+",totalCount="+totalCount+",toString()=" + super.toString() + "]";
    }
}
