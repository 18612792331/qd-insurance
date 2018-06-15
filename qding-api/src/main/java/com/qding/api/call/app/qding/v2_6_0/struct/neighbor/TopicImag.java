package com.qding.api.call.app.qding.v2_6_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/9/19.
 */
public class TopicImag implements Serializable {

    private static final long serialVersionUID = -2140368650723633008L;

    @ExplainAnnotation(explain = "位置索引")
    private Integer index;

    @ExplainAnnotation(explain = "图片url")
    private String url;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
