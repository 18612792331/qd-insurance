package com.qding.api.call.app.qding.v3_1_1.struct.legou.order;


import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/6/22.
 */
public class EvaluateSourcesSaveBean implements Serializable {

    private static final long serialVersionUID = -5060176376632900623L;

    @ExplainAnnotation(explain = "商品ID")
    private String skuId;

    @ExplainAnnotation (explain = "用户上传的图片")
    private List<String> evaluateImgs;

    @ExplainAnnotation (explain = "评分")
    private int score;

    @ExplainAnnotation (explain = "标签列表")
    private List<String> flagItems;

    @ExplainAnnotation(explain = "评价内容")
    private String evaluateContent;

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public String getSkuId() {
        return skuId;
    }

    public void setSkuId(String skuId) {
        this.skuId = skuId;
    }

    public List<String> getEvaluateImgs() {
        return evaluateImgs;
    }

    public void setEvaluateImgs(List<String> evaluateImgs) {
        this.evaluateImgs = evaluateImgs;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<String> getFlagItems() {
        return flagItems;
    }

    public void setFlagItems(List<String> flagItems) {
        this.flagItems = flagItems;
    }
}
