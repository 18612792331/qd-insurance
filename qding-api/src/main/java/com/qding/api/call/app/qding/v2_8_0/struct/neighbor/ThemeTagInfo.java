package com.qding.api.call.app.qding.v2_8_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/11/22.
 */
public class ThemeTagInfo implements Serializable {

    private static final long serialVersionUID = 8715638614750860884L;

    @ExplainAnnotation(explain = "标签ID")
    private String tagId;

    @ExplainAnnotation (explain = "标签名称")
    private String tagName;

    @ExplainAnnotation (explain = "标签图片列表")
    private List<String> tagImg;

    @ExplainAnnotation (explain = "标签描述")
    private String tagDesc;

    @ExplainAnnotation (explain = "标签状态",desc = "1:上线")
    private Integer tagStatus;

    public String getTagId() {
        return tagId;
    }

    public void setTagId(String tagId) {
        this.tagId = tagId;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public ThemeTagInfo() {
    }

    public ThemeTagInfo(String tagId, String tagName,Integer tagStatus) {
        this.tagId = tagId;
        this.tagName = tagName;
        this.tagStatus = tagStatus;
    }

    public List<String> getTagImg() {
        return tagImg;
    }

    public void setTagImg(List<String> tagImg) {
        this.tagImg = tagImg;
    }

    public String getTagDesc() {
        return tagDesc;
    }

    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc;
    }

    public Integer getTagStatus() {
        return tagStatus;
    }

    public void setTagStatus(Integer tagStatus) {
        this.tagStatus = tagStatus;
    }
}
