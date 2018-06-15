package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2017/2/24.
 */
public class BriefInteractionTagInfo extends SkipUrl implements Serializable {

    @ExplainAnnotation (explain = "常态标签ID")
    private String tagId;

    @ExplainAnnotation (explain = "常态标签名称")
    private String tagName;

    @ExplainAnnotation (explain = "标签背景图")
    private String tagImg;

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

    public String getTagImg() {
        return tagImg;
    }

    public void setTagImg(String tagImg) {
        this.tagImg = tagImg;
    }
}
