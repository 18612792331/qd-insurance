package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MaxValueValidate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import lombok.Getter;
import lombok.Setter;

@Validate
public class GetSeckillListRequest extends BaseRequest {

    private static final long serialVersionUID = 473392438880097673L;

    @MinValueValidate(value="1")
    @ExplainAnnotation(explain = "当前查询页码")
    @Getter
    @Setter
    private Integer pageNo = 1;

    @MaxValueValidate(value="20")
    @ExplainAnnotation(explain = "每页查询数")
    @Getter
    @Setter
    private Integer pageSize = 10;


    @Override
    public String toString() {
        return "GetSeckillListRequest{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                '}';
    }
}
