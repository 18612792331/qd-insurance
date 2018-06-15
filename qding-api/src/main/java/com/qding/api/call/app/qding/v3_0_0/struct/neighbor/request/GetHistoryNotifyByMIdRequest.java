package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;

/**
 * Created by qd on 2016/9/22.
 */
@Validate
public class GetHistoryNotifyByMIdRequest extends BaseRequest {


    @MinValueValidate(value="1")
    @ExplainAnnotation (explain = "当前查询页码")
    private int pageNo = 1;

    @RangeValueValidate(max="20", min="1")
    @ExplainAnnotation (explain = "当前查询每页显示数")
    private int pageSize = 10;


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
        return "GetHistoryNotifyByMIdRequest{" +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
