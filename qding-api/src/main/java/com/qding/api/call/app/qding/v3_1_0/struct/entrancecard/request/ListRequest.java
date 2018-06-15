package com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;

@Validate
public class ListRequest extends BaseRequest {

    private static final long serialVersionUID = -8977459602323133315L;

    @ExplainAnnotation(explain = "页码")
    private Integer pageNo;

    @ExplainAnnotation(explain = "页容量")
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

}
