package com.qding.api.call.app.qding.v1_3_2.struct.poster;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/8/13.
 */
public class HomePagePoster implements Serializable {


    private static final long serialVersionUID = -7837048843776132253L;

    /**
     * 广告图片
     */
    private String posterImgUrl;

    public String getPosterImgUrl() {
        return posterImgUrl;
    }

    public void setPosterImgUrl(String posterImgUrl) {
        this.posterImgUrl = posterImgUrl;
    }



}
