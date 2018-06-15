package com.qding.api.call.app.qding.v3_0_0.struct.brick;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/3.
 */
public class HomeSituationDicDTO implements Serializable {

    private static final long serialVersionUID = -4177843305262636625L;

    @ExplainAnnotation (explain = "家庭状况索引值")
    private Integer type;

    @ExplainAnnotation (explain = "家庭状况名称值")
    private String name;

    public HomeSituationDicDTO(Integer type, String name) {
        this.type = type;
        this.name = name;
    }

    public HomeSituationDicDTO(){

    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "HomeSituationDicDTO{" +
                "type=" + type +
                ", name='" + name + '\'' +
                '}';
    }
}
