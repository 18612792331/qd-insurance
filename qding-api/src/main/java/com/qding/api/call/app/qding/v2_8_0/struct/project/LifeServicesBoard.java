package com.qding.api.call.app.qding.v2_8_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.BoardImg;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/11/30.
 */
public class LifeServicesBoard implements Serializable {

    private static final long serialVersionUID = 2004429887560993300L;

    @ExplainAnnotation(explain = "UI模板类型")
    private Integer uiTempType;

    @ExplainAnnotation(explain = "服务板块名称")
    private String name = "生活服务";

    @ExplainAnnotation(explain = "生活服务列表")
    private List<BoardImg> lifeServicesList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BoardImg> getLifeServicesList() {
        return lifeServicesList;
    }

    public void setLifeServicesList(List<BoardImg> lifeServicesList) {
        this.lifeServicesList = lifeServicesList;
    }

    public Integer getUiTempType() {
        return uiTempType;
    }

    public void setUiTempType(Integer uiTempType) {
        this.uiTempType = uiTempType;
    }
}
