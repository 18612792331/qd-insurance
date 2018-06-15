package com.qding.api.call.app.qding.v3_1_1.struct.legou.order.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2017/7/17.
 */
@Validate
public class GetCrabsDeliverResultPageRequest extends BaseRequest {

    private static final long serialVersionUID = 4223736644994217957L;

    @ExplainAnnotation (explain = "当前请求页码")
    @MinValueValidate(value="1")
    private Integer pageNo;

    @ExplainAnnotation (explain = "每页显示数")
    @RangeValueValidate(max="20", min="1")
    private Integer pageSize;


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
        return "GetCrabsDeliverResultPageRequest{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
