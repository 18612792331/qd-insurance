package com.qding.api.call.app.qding.v1_4_0.struct.auction.response.data;

import com.qding.api.call.app.qding.v1_4_0.struct.auction.OfflineAuction;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/8/28.
 */
public class OfflineAuctionResponseData  extends ResponseData {

    private static final long serialVersionUID = -4160457700083225956L;

    private List<OfflineAuction> list;

    private int totalCount;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<OfflineAuction> getList() {
        return list;
    }

    public void setList(List<OfflineAuction> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "OfflineAuctionResponseData [list="+list+",totalCount="+totalCount+",toString()=" + super.toString() + "]";
    }
}
