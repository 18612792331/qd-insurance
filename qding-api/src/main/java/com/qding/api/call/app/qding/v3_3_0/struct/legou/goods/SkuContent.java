package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class SkuContent implements Serializable {

    private static final long serialVersionUID = -5725424480439695716L;

    @ExplainAnnotation(explain = "货品ID")
    @Getter
    @Setter
    private String skuId;

    @ExplainAnnotation(explain = "货品名称")
    @Getter
    @Setter
    private String skuName;

    @ExplainAnnotation(explain = "销售价格")
    @Getter
    @Setter
    private String price;

    @ExplainAnnotation(explain = "市场价格")
    @Getter
    @Setter
    private String marketPrice;

    @ExplainAnnotation(explain = "销售数量")
    @Getter
    @Setter
    private Long salesCount;
}
