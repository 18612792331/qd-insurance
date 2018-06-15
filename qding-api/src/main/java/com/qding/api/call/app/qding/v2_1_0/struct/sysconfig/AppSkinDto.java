package com.qding.api.call.app.qding.v2_1_0.struct.sysconfig;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public class AppSkinDto implements Serializable {

    @ExplainAnnotation(explain = "id")
    private String id;

    @ExplainAnnotation(explain = "名称")
    private String name;

    @ExplainAnnotation(explain = "顶部图片", desc = "type: 1.头部区域背景图 2.切换社区箭头图标 3.消息中心图标 4.头部4个推荐位背景图")
    private Map<Integer, String> topImageList;

    @ExplainAnnotation(explain = "顶部色值", desc = "type: 1.社区名称色值 2.消息数字颜色")
    private Map<Integer, String> topColorList;

    @ExplainAnnotation(explain = "底部色值", desc = "type: 1.底部导航栏背景色")
    private Map<Integer, String> bottomColorList;

    @ExplainAnnotation(explain = "底部图片", desc = "type:1.首页图标 2.管家图标 3.邻聚图标 4.我的图标 5.扫码/报事/发照片图标")
    private Map<Integer, String> bottomImageList;

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

    public Map<Integer, String> getTopImageList() {
        return topImageList;
    }

    public void setTopImageList(Map<Integer, String> topImageList) {
        this.topImageList = topImageList;
    }

    public Map<Integer, String> getTopColorList() {
        return topColorList;
    }

    public void setTopColorList(Map<Integer, String> topColorList) {
        this.topColorList = topColorList;
    }

    public Map<Integer, String> getBottomColorList() {
        return bottomColorList;
    }

    public void setBottomColorList(Map<Integer, String> bottomColorList) {
        this.bottomColorList = bottomColorList;
    }

    public Map<Integer, String> getBottomImageList() {
        return bottomImageList;
    }

    public void setBottomImageList(Map<Integer, String> bottomImageList) {
        this.bottomImageList = bottomImageList;
    }

    @Override
    public String toString() {
        return "AppSkinDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", topImageList=" + topImageList +
                ", topColorList=" + topColorList +
                ", bottomColorList=" + bottomColorList +
                ", bottomImageList=" + bottomImageList +
                '}';
    }
}
