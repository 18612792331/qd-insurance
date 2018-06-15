package com.qding.api.call.app.qding.v3_3_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

/**
 * Created by qd on 2017/9/11.
 */
public class MySkipDTO  {

    @ExplainAnnotation (explain = "标题",desc = "例如：我报名的")
    private  String title;

    @ExplainAnnotation (explain = "标签",desc = "例如：活动")
    private String  tagName;

    @ExplainAnnotation(explain = "跳转信息模型")
    private String skipModel = "";

    public MySkipDTO(String title, String tagName,String skipModel ) {
        this.title = title;
        this.tagName = tagName;
        this.skipModel = skipModel;
    }

    public MySkipDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public String getSkipModel() {
        return skipModel;
    }

    public void setSkipModel(String skipModel) {
        this.skipModel = skipModel;
    }
}
