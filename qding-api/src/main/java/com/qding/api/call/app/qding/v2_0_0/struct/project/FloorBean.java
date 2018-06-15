package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_1.struct.project.ActivitySale;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/12/16.
 */
public class FloorBean implements Serializable {

    private static final long serialVersionUID = 3656409854683695996L;

    @ExplainAnnotation(explain = "标题")
    private String title;

    @ExplainAnnotation(explain = "描述")
    private String desc;

    @ExplainAnnotation(explain = "活动列表")
    private List<ActivitySale> list;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ActivitySale> getList() {
        return list;
    }

    public void setList(List<ActivitySale> list) {
        this.list = list;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
