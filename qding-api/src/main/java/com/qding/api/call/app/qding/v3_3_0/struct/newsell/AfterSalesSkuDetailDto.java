package com.qding.api.call.app.qding.v3_3_0.struct.newsell;

import com.qding.api.annotation.ExplainAnnotation;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class AfterSalesSkuDetailDto implements Serializable {

    private static final long serialVersionUID = -4819746819144371296L;

    @Getter
    @Setter
    @ExplainAnnotation(explain = "单价")
    private String skuPrice;



    @Getter
    @Setter
    @ExplainAnnotation(explain = "申请优惠金额")
    private String totalDiscount;


    @Getter
    @Setter
    @ExplainAnnotation(explain = "申请实付金额")
    private String totalRealpay;

    @Getter
    @Setter
    @ExplainAnnotation(explain = "申请数量")
    private Integer num;

    @Getter
    @Setter
    @ExplainAnnotation(explain = "货品ID")
    private String skuId;

    @Getter
    @Setter
    @ExplainAnnotation(explain = "货品名称")
    private String skuName;


    @Getter
    @Setter
    @ExplainAnnotation(explain = "货品描述")
    private String skuDesc;

    @Getter
    @Setter
    @ExplainAnnotation(explain = "货品图片")
    private String skuImg;
}
