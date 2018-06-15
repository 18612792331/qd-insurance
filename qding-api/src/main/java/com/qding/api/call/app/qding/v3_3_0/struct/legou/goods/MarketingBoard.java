package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class MarketingBoard extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 4528520294453995532L;

    @ExplainAnnotation(explain = "背景图")
    @Getter
    @Setter
    private String backgroundImg;

    @ExplainAnnotation(explain = "标题")
    @Getter
    @Setter
    private String title;

    @ExplainAnnotation(explain = "描述")
    @Getter
    @Setter
    private String desc;

    @ExplainAnnotation(explain = "货品ID",desc = "仅在配置为商品时有值")
    @Getter
    @Setter
    private String skuId;

}
