package com.qding.api.call.app.qding.v3_3_0.struct.newsell;

import com.qding.api.annotation.ExplainAnnotation;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class AfterSalesReasonDto implements Serializable {

    private static final long serialVersionUID = -7814355976849602487L;


    @Setter
    @Getter
    @ExplainAnnotation(explain = "原因ID")
    private Integer value;

    @Setter
    @Getter
    @ExplainAnnotation(explain = "原因描述")
    private String label;
}
