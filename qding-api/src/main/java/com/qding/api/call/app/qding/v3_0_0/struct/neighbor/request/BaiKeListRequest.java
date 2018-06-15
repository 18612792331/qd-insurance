package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;


/**
 * Created by qd on 2017/3/2.
 */
@Validate
public class BaiKeListRequest extends BaseRequest  {

    private static final long serialVersionUID = -3535380267779528496L;

    @ExplainAnnotation(explain = "每页显示数")
    @RangeValueValidate(max="20", min="1")
    private Integer pageSize = Integer.valueOf(20);

    @ExplainAnnotation(explain = "排序规则字段")
    private String orderByRule;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderByRule() {
        return orderByRule;
    }

    public void setOrderByRule(String orderByRule) {
        this.orderByRule = orderByRule;
    }
}
