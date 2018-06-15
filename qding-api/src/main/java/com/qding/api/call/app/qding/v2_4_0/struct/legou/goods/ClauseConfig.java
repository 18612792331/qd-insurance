package com.qding.api.call.app.qding.v2_4_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/8/16.
 */
public class ClauseConfig implements Serializable {

    private static final long serialVersionUID = -5788153470144477L;

    @ExplainAnnotation (explain = "条款图片")
    private String imgUrl;

    @ExplainAnnotation (explain = "条款描述")
    private String content;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
