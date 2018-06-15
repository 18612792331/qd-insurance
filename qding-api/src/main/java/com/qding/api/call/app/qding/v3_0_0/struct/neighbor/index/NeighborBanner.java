package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.io.Serializable;

/**
 * Created by qd on 2016/12/1.
 */
public class NeighborBanner extends SkipUrl implements Serializable {

    private static final long serialVersionUID = -494100789218868319L;

    @ExplainAnnotation(explain = "banner图片")
    private String img;

    @ExplainAnnotation(explain = "bannerId")
    private String id;

    @ExplainAnnotation(explain = "banner名称")
    private String name;


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
