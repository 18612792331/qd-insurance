package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class Seckill extends BtnSkip implements Serializable {


    @ExplainAnnotation(explain = "秒杀开始时间")
    @Getter
    @Setter
    private Long seckillStartTime;

    @ExplainAnnotation(explain = "秒杀结束时间")
    @Getter
    @Setter
    private Long seckillEndTime;

    @ExplainAnnotation(explain = "货品ID")
    @Getter
    @Setter
    private String skuId;

    @ExplainAnnotation(explain = "商品名称")
    @Getter
    @Setter
    private String goodsName;

    @ExplainAnnotation(explain = "商品图片")
    @Getter
    @Setter
    private String goodsImg;

    @ExplainAnnotation(explain = "市场价格")
    @Getter
    @Setter
    private String originalPrice;

    @ExplainAnnotation(explain = "当前价格")
    @Getter
    @Setter
    private String price;

    @ExplainAnnotation(explain = "数量")
    @Getter
    @Setter
    private Integer sellCount;

    @ExplainAnnotation (explain = "剩余数量")
    @Getter
    @Setter
    private Integer surplusCount;

    @ExplainAnnotation (explain = "秒杀状态",desc = "-1:未开始，0:进行，1：已结束,2：无库存")
    @Getter
    @Setter
    private Integer status;

    @ExplainAnnotation(explain = "限购数量")
    @Getter
    @Setter
    private int limitationCount;

    @ExplainAnnotation(explain = "秒杀链接")
    @Getter
    @Setter
    private String seckillLink;

}
