package com.qding.api.call.app.qding.v2_8_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/11/22.
 */
public class GetGlobalThemeTitleResponseData extends ResponseData {

    private static final long serialVersionUID = -7724182381537902549L;

    @ExplainAnnotation(explain = "全局话题发布标题")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "GetGlobalThemeTitleResponseData{" +
                "title='" + title + '\'' +
                '}';
    }
}
