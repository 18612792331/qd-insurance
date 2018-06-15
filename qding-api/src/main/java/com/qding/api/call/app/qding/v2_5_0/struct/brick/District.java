package com.qding.api.call.app.qding.v2_5_0.struct.brick;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/8/22.
 */
public class District implements Serializable {

    private static final long serialVersionUID = -2373573059510280898L;

    @ExplainAnnotation (explain = "区县ID")
    private String id;

    @ExplainAnnotation (explain = "区县名称")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
