package com.qding.api.call.app.qding.v2_8_0.struct.project;


import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BtnSkip;

import java.io.Serializable;

/**
 * Created by qd on 2016/11/30.
 */
public class IndexBanner extends BtnSkip implements Serializable {

    private static final long serialVersionUID = 5759703617233984531L;


    @ExplainAnnotation(explain = "bannerID")
    private String id;

    @ExplainAnnotation(explain = "banner标题")
    private String title;

    @ExplainAnnotation (explain = "banner图片")
    private String img;

    @ExplainAnnotation (explain = "排序位")
    private Integer showPosition;

    @ExplainAnnotation (explain = "是否可分享",desc = "1:可分享，0：不可分享")
    private Integer isShare = Integer.valueOf(0);

    @ExplainAnnotation (explain = "可分享信息")
    private BannerShareInfo shareInfo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Integer getIsShare() {
        return isShare;
    }

    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }

    public BannerShareInfo getShareInfo() {
        return shareInfo;
    }

    public void setShareInfo(BannerShareInfo shareInfo) {
        this.shareInfo = shareInfo;
    }

    public Integer getShowPosition() {
        return showPosition;
    }

    public void setShowPosition(Integer showPosition) {
        this.showPosition = showPosition;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
