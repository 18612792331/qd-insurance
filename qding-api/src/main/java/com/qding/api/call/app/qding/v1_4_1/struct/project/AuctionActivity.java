package com.qding.api.call.app.qding.v1_4_1.struct.project;


import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/11/3.
 */
public class AuctionActivity implements Serializable {

    //id
    private String id = "";

    //名称
    private String auctionName = "";

    //活动价（分）
    private Integer auctionPriceFen = 0;

    //开始时间
    private Long startTime = 0L;

    //状态 0未发布 1已发布 3已结束 -1删除
    private Integer auctionStatus = 0;

    //缩略图
    private String imageUrl = "";

    //期数
    private String  periods = "";

    //详情图
    private List<String> detailImagUrl = Lists.newArrayList();

    //当前夺宝系统时间
    private Long currentTime;

    //跳转
    private String url;

    //最终成交价格
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
