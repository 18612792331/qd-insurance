package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2015/12/16.
 */
public class SkipUrl implements Serializable {

    private static final long serialVersionUID = -7483525946675731168L;

    @ExplainAnnotation(explain = "跳转信息模型")
    private String skipModel = "";

    public String getSkipModel() {
        return skipModel;
    }

    public void setSkipModel(String skipModel) {
        this.skipModel = skipModel;
    }

}
