package com.qding.api.call.app.qding.v2_0_0.struct.project;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by andy on 16-4-21.
 */
public class LgRevealCategoryBoard implements Serializable {

    private static final long serialVersionUID = 7899670008436325986L;

    public LgRevealCategoryBoard() {
    }

    public LgRevealCategoryBoard(List<BoardImg> imgList) {
        this.imgList = imgList;
    }

    @ExplainAnnotation(explain = "板块图片信息列表")
    private List<BoardImg> imgList;


    public List<BoardImg> getImgList() {
        return imgList;
    }

    public void setImgList(List<BoardImg> imgList) {
        this.imgList = imgList;
    }
}
