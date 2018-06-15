package com.qding.api.call.app.qding.v2_0_0.struct.project;


import com.google.common.collect.Lists;
import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/11/3.
 */
public class AuctionActivity implements Serializable {

    @ExplainAnnotation(explain = "夺宝活动ID")
    private String id = "";

    @ExplainAnnotation(explain = "夺宝活动名称")
    private String auctionName = "";

    @ExplainAnnotation(explain = "活动价")
    private Integer auctionPriceFen = 0;

    @ExplainAnnotation(explain = "开始时间")
    private Long startTime = 0L;

    @ExplainAnnotation(explain = "活动状态",desc = "0未发布 1已发布 3已结束 -1删除")
    private Integer auctionStatus = 0;

    @ExplainAnnotation(explain = "缩略图")
    private String imageUrl = "";

    @ExplainAnnotation(explain = "期数")
    private String  periods = "";

    @ExplainAnnotation(explain = "详情图")
    private List<String> detailImagUrl = Lists.newArrayList();

    @ExplainAnnotation(explain = "当前夺宝系统时间")
    private Long currentTime;

    @ExplainAnnotation(explain = "跳转URL")
    private String url;

    @ExplainAnnotation(explain = "最终成交价格")
    private Integer lastPriceFen;

    public Integer getLastPriceFen() {
        return lastPriceFen;
    }

    public void setLastPriceFen(Integer lastPriceFen) {
        this.lastPriceFen = lastPriceFen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuctionName() {
        return auctionName;
    }

    public void setAuctionName(String auctionName) {
        this.auctionName = auctionName;
    }

    public Integer getAuctionPriceFen() {
        return auctionPriceFen;
    }

    public void setAuctionPriceFen(Integer auctionPriceFen) {
        this.auctionPriceFen = auctionPriceFen;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Integer getAuctionStatus() {
        return auctionStatus;
    }

    public void setAuctionStatus(Integer auctionStatus) {
        this.auctionStatus = auctionStatus;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
    }

    public List<String> getDetailImagUrl() {
        return detailImagUrl;
    }

    public void setDetailImagUrl(List<String> detailImagUrl) {
        this.detailImagUrl = detailImagUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }

}
