package com.qding.api.call.app.qding.v2_8_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/11/30.
 */
public class BannerBoard implements Serializable {

    private static final long serialVersionUID = 8131689965002492285L;

    @ExplainAnnotation(explain = "社区首页banner列表")
    private List<IndexBanner> bannerList;

    public List<IndexBanner> getBannerList() {
        return bannerList;
    }

    public void setBannerList(List<IndexBanner> bannerList) {
        this.bannerList = bannerList;
    }
}
