package com.qding.api.call.app.qding.v3_3_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

/**
 * Created by qd on 2017/9/11.
 */
public class NewsAndActivityBar  {

    @ExplainAnnotation (explain = "图标")
    private String imgUrl;

    @ExplainAnnotation (explain = "标题")
    private String title;

    @ExplainAnnotation (explain = "描述")
    private String desc;

    @ExplainAnnotation (explain = "是否显示最新图标",desc = "1:是，0：否")
    private Integer isNew;

    @ExplainAnnotation(explain = "跳转信息模型")
    private String skipModel = "";

    public NewsAndActivityBar(String imgUrl, String title, String desc, Integer isNew, String skipModel) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.desc = desc;
        this.isNew = isNew;
        this.skipModel = skipModel;
    }

    public NewsAndActivityBar() {
    }

    public String getSkipModel() {
        return skipModel;
    }

    public void setSkipModel(String skipModel) {
        this.skipModel = skipModel;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }
}
