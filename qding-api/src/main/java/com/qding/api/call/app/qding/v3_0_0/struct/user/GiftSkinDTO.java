package com.qding.api.call.app.qding.v3_0_0.struct.user;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2017/3/3.
 */
public class GiftSkinDTO extends SkipUrl implements Serializable {

    @ExplainAnnotation (explain = "礼包图")
    private String img;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }


    public GiftSkinDTO(String img) {
        this.img = img;
    }

    public GiftSkinDTO() {
    }
}
