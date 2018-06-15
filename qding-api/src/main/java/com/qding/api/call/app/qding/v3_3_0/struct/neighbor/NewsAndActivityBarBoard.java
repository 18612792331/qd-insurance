package com.qding.api.call.app.qding.v3_3_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

import java.util.List;

/**
 * Created by qd on 2017/9/11.
 */
public class NewsAndActivityBarBoard {

    @ExplainAnnotation (explain = "周边新闻和活动报名条")
    private List<NewsAndActivityBar> list;

    public List<NewsAndActivityBar> getList() {
        return list;
    }

    public void setList(List<NewsAndActivityBar> list) {
        this.list = list;
    }


}
