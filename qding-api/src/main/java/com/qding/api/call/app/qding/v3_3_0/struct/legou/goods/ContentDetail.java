package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class ContentDetail extends SkipUrl implements Serializable {


    @ExplainAnnotation(explain = "内容图片")
    @Getter
    @Setter
    private String contentImg;

    @ExplainAnnotation(explain = "sku")
    @Getter
    @Setter
    private SkuContent sku;
}
