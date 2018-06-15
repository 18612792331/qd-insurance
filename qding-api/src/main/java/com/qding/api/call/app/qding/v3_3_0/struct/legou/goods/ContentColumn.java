package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

public class ContentColumn implements Serializable {

    private static final long serialVersionUID = 7084451877975755522L;

    @ExplainAnnotation(explain = "标签")
    @Getter
    @Setter
    private String title;

    @ExplainAnnotation(explain = "布局类型",desc = "1:单个 2:双个 3:三个")
    @Getter
    @Setter
    private Integer columnType;


    @ExplainAnnotation(explain = "内容类型" ,desc = "1：url,2:商品")
    @Getter
    @Setter
    private Integer contentType;


    @ExplainAnnotation(explain = "品类图片")
    @Getter
    @Setter
    private List<ContentDetail> contents;


}
