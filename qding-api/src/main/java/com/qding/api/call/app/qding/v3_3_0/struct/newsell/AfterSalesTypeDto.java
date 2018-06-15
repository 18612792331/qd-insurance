package com.qding.api.call.app.qding.v3_3_0.struct.newsell;

import com.qding.api.annotation.ExplainAnnotation;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class AfterSalesTypeDto implements Serializable {

    private static final long serialVersionUID = 6246617206615223887L;


    @Setter
    @Getter
    @ExplainAnnotation(explain = "类型值")
    private Integer value;

    @Setter
    @Getter
    @ExplainAnnotation(explain = "类型描述")
    private String label;


}
