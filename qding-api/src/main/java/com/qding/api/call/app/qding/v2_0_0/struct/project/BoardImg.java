package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2015/12/16.
 */
public class BoardImg extends SkipUrl implements Serializable {

    private static final long serialVersionUID = 465955495750281714L;


    @ExplainAnnotation(explain = "服务id")
    private String id;

    @ExplainAnnotation(explain = "图片url")
    private String imageUrl;

    @ExplainAnnotation(explain = "按钮名称")
    private String btnName;

    @ExplainAnnotation(explain = "图片标题")
    private String imgTitle;

    @ExplainAnnotation(explain = "图片描述")
    private String imgDesc;

    @ExplainAnnotation(explain = "标签名称")
    private String tagName;

    public String getImgTitle() {
        return imgTitle;
    }

    public void setImgTitle(String imgTitle) {
        this.imgTitle = imgTitle;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getBtnName() {
        return btnName;
    }

    public void setBtnName(String btnName) {
        this.btnName = btnName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
