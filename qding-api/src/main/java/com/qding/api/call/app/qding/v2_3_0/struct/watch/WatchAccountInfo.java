package com.qding.api.call.app.qding.v2_3_0.struct.watch;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

public class WatchAccountInfo implements Serializable {

    private static final long serialVersionUID = -5815089942755047044L;

    @ExplainAnnotation(explain = "id")
    private String id;


    @ExplainAnnotation(explain = "昵称")
    private String nickName;


    @ExplainAnnotation(explain = "头像")
    private String headImg;


    public WatchAccountInfo() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }


    @Override
    public String toString() {
        return "WatchAccountInfo{" +
                "id='" + id + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headImg='" + headImg + '\'' +
                '}';
    }
}
