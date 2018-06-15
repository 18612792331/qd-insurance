package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/12/16.
 */
public class BoardBean  implements Serializable {

    private static final long serialVersionUID = 2469679930813973567L;

    @ExplainAnnotation(explain = "板块列表")
    private List<BoardTmplBean> list;

    public List<BoardTmplBean> getList() {
        return list;
    }

    public void setList(List<BoardTmplBean> list) {
        this.list = list;
    }
}
