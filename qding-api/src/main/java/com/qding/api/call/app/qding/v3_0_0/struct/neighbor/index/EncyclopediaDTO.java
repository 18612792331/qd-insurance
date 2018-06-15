package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/2.
 */
public class EncyclopediaDTO extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 4201595417260969710L;

    @ExplainAnnotation(explain = "百科ID")
    private String id;

    @ExplainAnnotation (explain = "标题")
    private String title;

    @ExplainAnnotation (explain = "导语")
    private String introduction;

    @ExplainAnnotation (explain = "图片")
    private String img;

    @ExplainAnnotation (explain = "标签")
    private String tagName;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
