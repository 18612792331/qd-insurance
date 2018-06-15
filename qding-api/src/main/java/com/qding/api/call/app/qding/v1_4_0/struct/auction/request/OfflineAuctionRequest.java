package com.qding.api.call.app.qding.v1_4_0.struct.auction.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2015/8/28.
 */
@Validate
public class OfflineAuctionRequest extends BaseRequest {

    private static final long serialVersionUID = -5195699269821301485L;

    @MinValueValidate(value="1")
    private Integer pageNo = 1;

    @RangeValueValidate(max="20", min="1")
    private Integer pageSize = 5;

    @NotNullValidate
    private Integer auctionType= 1;

    public Integer getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(Integer auctionType) {
        this.auctionType = auctionType;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "OfflineAuctionRequest [pageNo=" + pageNo + ",pageSize="+pageSize+",auctionType="+auctionType+", toString()=" + super.toString() + "]";
    }
}
