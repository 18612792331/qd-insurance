package com.qding.api.call.app.qding.v2_0_0.struct.project;

import java.io.Serializable;

/**
 * Created by andy on 16-4-20.
 */
public class LgConfigBean implements Serializable {


    private static final long serialVersionUID = -7190602965938861942L;


    private Integer uiTempType;

    private String imgUrl;

    private String revealCategoryId;

    private String title;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getRevealCategoryId() {
        return revealCategoryId;
    }

    public void setRevealCategoryId(String revealCategoryId) {
        this.revealCategoryId = revealCategoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getUiTempType() {
        return uiTempType;
    }

    public void setUiTempType(Integer uiTempType) {
        this.uiTempType = uiTempType;
    }

    public LgConfigBean(Integer uiTempType, String imgUrl, String revealCategoryId, String title) {
        this.uiTempType = uiTempType;
        this.imgUrl = imgUrl;
        this.revealCategoryId = revealCategoryId;
        this.title = title;
    }

    public LgConfigBean() {
    }
}
