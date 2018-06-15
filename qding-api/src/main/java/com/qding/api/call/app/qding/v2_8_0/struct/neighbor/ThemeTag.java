package com.qding.api.call.app.qding.v2_8_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2016/11/22.
 */
public class ThemeTag extends ThemeTagInfo implements Serializable {

    private static final long serialVersionUID = 1911087656742581664L;


    @ExplainAnnotation (explain = "标签描述")
    private String tagDesc;

    @ExplainAnnotation (explain = "标签图片")
    private List<String> imgList;

    public String getTagDesc() {
        return tagDesc;
    }

    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc;
    }

    public List<String> getImgList() {
        return imgList;
    }

    public void setImgList(List<String> imgList) {
        this.imgList = imgList;
    }
}
