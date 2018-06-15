package com.qding.api.call.app.qding.v4_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2016/12/1.
 */
public class IndexBanner extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -494100789218868319L;

    @ExplainAnnotation(explain = "banner图片")
    private String bannerImg;

    @ExplainAnnotation(explain = "bannerId")
    private String bannerId;

    @ExplainAnnotation(explain = "banner名称")
    private String bannerName;

    public String getBannerImg() {
        return bannerImg;
    }

    public void setBannerImg(String bannerImg) {
        this.bannerImg = bannerImg;
    }

    public String getBannerId() {
        return bannerId;
    }

    public void setBannerId(String bannerId) {
        this.bannerId = bannerId;
    }

    public String getBannerName() {
        return bannerName;
    }

    public void setBannerName(String bannerName) {
        this.bannerName = bannerName;
    }
}
