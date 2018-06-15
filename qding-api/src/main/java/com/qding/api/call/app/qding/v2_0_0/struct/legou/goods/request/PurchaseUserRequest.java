package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2015/12/22.
 */

@Validate
public class PurchaseUserRequest extends BaseRequest {

    private static final long serialVersionUID = -518480237963266263L;


    @NotNullValidate
    @ExplainAnnotation(explain = "货品ID")
    private Long skuId;

    @MinValueValidate(value="1")
    @ExplainAnnotation(explain = "当前查询页码")
    private Integer pageNo = 1;

    @RangeValueValidate(max="20", min="1")
    @ExplainAnnotation(explain = "每页查询条数")
    private Integer pageSize = 10;

    public Long getSkuId() {
        return skuId;
    }

    public void setSkuId(Long skuId) {
        this.skuId = skuId;
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
