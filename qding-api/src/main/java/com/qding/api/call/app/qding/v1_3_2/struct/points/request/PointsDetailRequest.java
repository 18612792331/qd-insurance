package com.qding.api.call.app.qding.v1_3_2.struct.points.request;

import com.qding.api.smart.validate.rule.WalletStatusValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by jiawenzheng on 2015/8/13.
 */
public class PointsDetailRequest   extends BaseRequest {

    private static final long serialVersionUID = -274634941873552870L;

    /**
     * 会员ID
     */
    @NotNullValidate
    @WalletStatusValidate
    private String memberId;

    /**
     * 当前 查询页码
     */
    @MinValueValidate(value="1")
    private Integer pageNo =1;

    /**
     * 每页显示数
     */
    @RangeValueValidate(max="20", min="1")
    private Integer pageSize =10;


    public PointsDetailRequest(){

    }


    public String getMemberId() {
        return memberId;
    }


    public void setMemberId(String memberId) {
        this.memberId = memberId;
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
}
