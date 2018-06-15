package com.qding.api.call.app.qding.v3_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2017/2/28.
 */
public class QualityLifeDTO extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 2137489066808764676L;

    @ExplainAnnotation (explain = "标签")
    private String tagName;

    @ExplainAnnotation (explain = "信息图片")
    private String img;

    @ExplainAnnotation (explain = "信息标题")
    private String title;

    @ExplainAnnotation (explain = "信息描述")
    private String desc;

    @ExplainAnnotation (explain = "截止时间")
    private Long endTime;

    public QualityLifeDTO(){

    }

    public QualityLifeDTO(String tagName, String img, String title, String desc, Long endTime) {
        this.tagName = tagName;
        this.img = img;
        this.title = title;
        this.desc = desc;
        this.endTime = endTime;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }
}
