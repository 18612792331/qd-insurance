package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class NewSellColumn extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 6388594458816905138L;

    @ExplainAnnotation(explain = "栏目图片")
    @Getter
    @Setter
    private String columnImg;

    @ExplainAnnotation(explain = "栏目名称")
    @Getter
    @Setter
    private String columnName;


}
