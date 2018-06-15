package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import lombok.Getter;
import lombok.Setter;

public class NewSellBanner extends SkipUrl {

    @ExplainAnnotation(explain = "banner图片地址")
    @Getter
    @Setter
    private String  bannerImg;

    @ExplainAnnotation(explain = "货品ID",desc = " 仅当跳转类型为商品时有值")
    @Getter
    @Setter
    private String skuId;

}
