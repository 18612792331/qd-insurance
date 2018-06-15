package com.qding.api.call.app.qding.v2_4_0.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MaxValueValidate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/8/12.
 */
@Validate
public class GetRedeemCodeOrdersRequest extends BaseRequest {

    private static final long serialVersionUID = 6660292602996660669L;

    @NotNullValidate
    @ExplainAnnotation(explain = "会员ID")
    private String memberId;

    @NotNullValidate
    @ExplainAnnotation(explain = "业态类型")
    private String businessType;

    @MinValueValidate(value="1")
    @ExplainAnnotation(explain = "当前查询页码")
    private Integer pageNo = 1;

    @MaxValueValidate(value="20")
    @ExplainAnnotation(explain = "每页查询数")
    private Integer pageSize = 10;


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

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    @Override
    public String toString() {
        return "GetRedeemCodeOrdersRequest{" +
                "memberId='" + memberId + '\'' +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
