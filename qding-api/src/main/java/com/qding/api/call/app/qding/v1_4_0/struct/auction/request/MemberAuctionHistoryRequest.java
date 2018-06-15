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
public class MemberAuctionHistoryRequest extends BaseRequest {


    private static final long serialVersionUID = 7475846191595674341L;

    @NotNullValidate
    private String memberId = "";

    private String isGet="";

    @RangeValueValidate(max="20", min="1")
    private Integer pageSize=5;

    @MinValueValidate(value="1")
    private Integer pageNo = 1;

    public String getIsGet() {
        return isGet;
    }

    public void setIsGet(String isGet) {
        this.isGet = isGet;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

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


    @Override
    public String toString() {
        return "MemberAuctionHistoryRequest{" +
                "memberId=" + memberId  +
                "isGet=" + isGet  +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                '}';
    }
}
