package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.RangeValueValidate;
import lombok.Getter;
import lombok.Setter;

public class GetGoodsByProjectIdRequest extends BaseRequest {

    private static final long serialVersionUID = -1882976164376212067L;

    @MinValueValidate(value = "1")
    @ExplainAnnotation(explain = "当前页码")
    @Setter
    @Getter
    private Integer pageNo = 1;

    @RangeValueValidate(max = "20", min = "1")
    @ExplainAnnotation(explain = "每页查询显示数")
    @Setter
    @Getter
    private Integer pageSize = 10;

    @Override
    public String toString() {
        return "GetGoodsByProjectIdRequest{}";
    }
}
