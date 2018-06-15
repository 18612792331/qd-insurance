package com.qding.api.call.app.qding.v2_4_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

import java.io.Serializable;

/**
 * Created by qd on 2016/6/14.
 */
public class ReportEvaluateBean implements Serializable {

    private static final long serialVersionUID = 8736749837296752949L;

    @ExplainAnnotation (explain = "评价内容")
    private String content;

    @NotNullValidate
    @ExplainAnnotation (explain = "评分")
    private Integer score = 0;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
