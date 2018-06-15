package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2015/10/10.
 */
@Validate
public class FindBlueToothLogRequest  extends BaseRequest {


    private static final long serialVersionUID = 8897092730212097748L;


    @NotNullValidate
    private String accountId;

    /**
     * 当前页
     */
    @MinValueValidate(value="1")
    private Integer pageNo = 1;

    /**
     * 信息页大小
     */
    @RangeValueValidate(max="20", min="1")
    private Integer pageSize = 10;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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
        return "FindBlueToothLogRequest [ accountId=" + accountId + ", pageNo="+pageNo+",pageSize="+pageSize+",toString()="
                + super.toString() + "]";
    }
}
