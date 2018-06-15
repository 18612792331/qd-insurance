package com.qding.api.call.app.qding.v1_4_1.struct.notify.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2015/10/29.
 */
@Validate
public class GeSysNotifiesRequest extends BaseRequest {

    private static final long serialVersionUID = 5870845472385762586L;

    @MinValueValidate(value="1")
    private Integer pageNo;

    @RangeValueValidate(min="1", max="20")
    private Integer pageSize;

    @NotNullValidate
    private String memberId;

    public GeSysNotifiesRequest() {

    }

    public GeSysNotifiesRequest(Integer pageNo, Integer pageSize,
                                   String memberId) {
        super();
        this.pageNo = pageNo;
        this.pageSize = pageSize;
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

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "GeSysNotifiesRequest [memberId=" + memberId +",pageNo="+pageNo+",pageSize="+pageSize+
                " super.toString() ]";
    }
}
