package com.qding.api.call.app.qding.v1_4_0.struct.auction.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2015/9/9.
 */
@Validate
public class AuctionOrderHistoryRequest extends BaseRequest {

    private static final long serialVersionUID = 5629199463783627007L;

    @NotNullValidate
    private String auctionId = "";

    @RangeValueValidate(max="20", min="1")
    private Integer pageSize=5;

    @MinValueValidate(value="1")
    private Integer pageNo = 1;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    @Override
    public String toString() {
        return "AuctionOrderHistoryRequest [auctionId=" + auctionId + ", pageSize=" + pageSize
                + ",pageNo="+pageNo+", toString()=" + super.toString() + "]";
    }

}
