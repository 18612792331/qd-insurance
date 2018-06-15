package com.qding.api.call.app.qding.v1_4_0.struct.auction;

import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;

/**
 * 竞拍活动基本信息
 * Created by qd on 2015/8/20.
 */
public class AuctionInfo implements Serializable {


    private static final long serialVersionUID = -2263819544306131847L;
    //id
    private String id = "";

    //名称
    private String auctionName = "";

    //活动类型 1竞拍
    private Integer auctionType = 1;

    //原价（分）
    private Integer priceFen = 0;

    //活动价（分）
    private Integer auctionPriceFen = 0;

    //当前竞拍价(分)
    private Integer currentPriceFen = 0;

    //开始时间
    private Long startTime = 0L;

    //状态 0未发布 1已发布 3已结束 -1删除
    private Integer auctionStatus = 0;

    //是否兑奖 0否  1是
    private Integer isGet = 0;

    //创建时间
    private Long createTime = 0L;

    //修改时间
    private Long updateTime = 0L;

    //缩略图
    private String imageUrl = "";

    //期数
    private String  periods = "";

   //详情图
    private List<String> detailImagUrl = Lists.newArrayList();

    //是否已晒单
    private Integer isShare=0;

    //大图
    private String imageUrl2;

    public String getImageUrl2() {
        return imageUrl2;
    }

    public void setImageUrl2(String imageUrl2) {
        this.imageUrl2 = imageUrl2;
    }

    public Integer getIsShare() {
        return isShare;
    }

    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }


    public List<String> getDetailImagUrl() {
        return detailImagUrl;
    }

    public void setDetailImagUrl(List<String> detailImagUrl) {
        this.detailImagUrl = detailImagUrl;
    }

    public String getPeriods() {
        return periods;
    }

    public void setPeriods(String periods) {
        this.periods = periods;
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

    public Integer getAuctionType() {
        return auctionType;
    }

    public void setAuctionType(Integer auctionType) {
        this.auctionType = auctionType;
    }

    public Integer getPriceFen() {
        return priceFen;
    }

    public void setPriceFen(Integer priceFen) {
        this.priceFen = priceFen;
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

    public Integer getIsGet() {
        return isGet;
    }

    public void setIsGet(Integer isGet) {
        this.isGet = isGet;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getCurrentPriceFen() {
        return currentPriceFen;
    }

    public void setCurrentPriceFen(Integer currentPriceFen) {
        this.currentPriceFen = currentPriceFen;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
