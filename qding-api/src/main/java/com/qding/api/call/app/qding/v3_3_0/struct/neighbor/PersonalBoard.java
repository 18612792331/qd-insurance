package com.qding.api.call.app.qding.v3_3_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.project.SkipUrl;

import java.util.List;

/**
 * Created by qd on 2017/9/11.
 */
public class PersonalBoard extends SkipUrl {

    @ExplainAnnotation (explain = "我的相关导航")
    private List<MySkipDTO> list;

    @ExplainAnnotation (explain = "用户头像")
    private String imgUrl;

    public List<MySkipDTO> getList() {
        return list;
    }

    public void setList(List<MySkipDTO> list) {
        this.list = list;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
