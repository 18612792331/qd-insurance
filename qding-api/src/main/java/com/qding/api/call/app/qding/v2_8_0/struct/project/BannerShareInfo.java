package com.qding.api.call.app.qding.v2_8_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/11/30.
 */
public class BannerShareInfo implements Serializable {

    private static final long serialVersionUID = 423318513360117752L;

    @ExplainAnnotation (explain = "分享图片")
    private String shareImg;

    @ExplainAnnotation (explain = "分享标题")
    private String shareTitle;

    @ExplainAnnotation (explain = "分享描述")
    private String shareDesc;

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareDesc() {
        return shareDesc;
    }

    public void setShareDesc(String shareDesc) {
        this.shareDesc = shareDesc;
    }
}
