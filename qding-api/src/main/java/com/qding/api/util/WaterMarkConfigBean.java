package com.qding.api.util;

/**
 * Created by qd on 2016/7/7.
 */
public class WaterMarkConfigBean  {


    /**
     * 水印图片模板地址
     */
    private String waterMarkImgUrl;

    /**
     * 是否是管道模式
     */
    private boolean isPipeline;

    /**
     * 水印图片缩放比例
     */
    private Integer scaling = 0;

    /**
     * 透明度，取值范围1-100，默认值为100（完全不透明）
     */
    private Integer dissolve = 100;

    /**
     * 九宫格水印位置
     */
    private String gravity;

    /**
     * 横轴边距，单位:像素(px)，默认值为10
     */
    private Integer dx;

    /**
     * 纵轴边距，单位:像素(px)，默认值为10
     */
    private Integer dy;

    /**
     * 水印图片自适应原图的短边比例，取值范围0-1
     */
    private Double ws;

    public String getWaterMarkImgUrl() {
        return waterMarkImgUrl;
    }

    public void setWaterMarkImgUrl(String waterMarkImgUrl) {
        this.waterMarkImgUrl = waterMarkImgUrl;
    }

    public Integer getDissolve() {
        return dissolve;
    }

    public void setDissolve(Integer dissolve) {
        this.dissolve = dissolve;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public Integer getDx() {
        return dx;
    }

    public void setDx(Integer dx) {
        this.dx = dx;
    }

    public Integer getDy() {
        return dy;
    }

    public void setDy(Integer dy) {
        this.dy = dy;
    }

    public Double getWs() {
        return ws;
    }

    public void setWs(Double ws) {
        this.ws = ws;
    }

    public boolean getIsPipeline() {
        return isPipeline;
    }

    public void setPipeline(boolean pipeline) {
        isPipeline = pipeline;
    }

    public Integer getScaling() {
        return scaling;
    }

    public void setScaling(Integer scaling) {
        this.scaling = scaling;
    }
}
