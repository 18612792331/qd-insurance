package com.qding.api.call.app.qding.v2_0_0.struct.platform.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

public class PromotionLabel implements Serializable {

//    private Long id;
    @ExplainAnnotation(explain = "标签码")
    private String labelCode;
    @ExplainAnnotation(explain = "标签文字")
    private String labelChar;
    @ExplainAnnotation(explain = "标签颜色")
    private String labelColor;

  /*  @ExplainAnnotation(explain = "适用业态")
    private String productNo;
    @ExplainAnnotation(explain = "是否删除")
    private Integer isDel;
    @ExplainAnnotation(explain = "规则id")
    private Integer ruleId;
    @ExplainAnnotation(explain = "标签码")
    private Integer counterId;
    @ExplainAnnotation(explain = "标签码")
    private Long createAt;
    @ExplainAnnotation(explain = "标签码")
    private Long updateAt;*/

    public PromotionLabel() {
    }

    public String getLabelCode() {
        return labelCode;
    }

    public void setLabelCode(String labelCode) {
        this.labelCode = labelCode;
    }

    public String getLabelChar() {
        return labelChar;
    }

    public void setLabelChar(String labelChar) {
        this.labelChar = labelChar;
    }

    public String getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(String labelColor) {
        this.labelColor = labelColor;
    }
}
