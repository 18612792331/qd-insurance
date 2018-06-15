package com.qding.api.call.app.qding.v3_1_1.struct.legou.order;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2017/6/22.
 */
public class EvaluateFlagBean implements Serializable {

    private static final long serialVersionUID = -7065415777362042875L;

    @ExplainAnnotation (explain = "业态号")
    private String productNo;

    @ExplainAnnotation (explain = "业态名")
    private String productValue;

    @ExplainAnnotation (explain = "评分值")
    private Integer score;

    @ExplainAnnotation (explain = "标签项索引")
    private String flagItem;

    @ExplainAnnotation (explain = "标签项名称")
    private String flagName;


    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getProductValue() {
        return productValue;
    }

    public void setProductValue(String productValue) {
        this.productValue = productValue;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getFlagItem() {
        return flagItem;
    }

    public void setFlagItem(String flagItem) {
        this.flagItem = flagItem;
    }

    public String getFlagName() {
        return flagName;
    }

    public void setFlagName(String flagName) {
        this.flagName = flagName;
    }
}
