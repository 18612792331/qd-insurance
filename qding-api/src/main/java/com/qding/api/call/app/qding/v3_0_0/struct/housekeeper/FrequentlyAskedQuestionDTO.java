package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2017/3/8.
 */
public class FrequentlyAskedQuestionDTO extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 8293010396384459800L;

    @ExplainAnnotation(explain = "内容描述")
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }
}
