package com.qding.api.call.app.qding.v1_4_0.struct.auction;

import java.io.Serializable;

/**
 * 竞拍活动详细
 * Created by qd on 2015/8/20.
 */
public class AuctionDetail implements Serializable {


    private static final long serialVersionUID = -6509562603933035381L;
    //id
    private String id = "";

    //竞拍活动id
    private String auctionId = "";

    //竞拍活动图片集合
    private String auctionImages = "";

    //竞拍活动介绍
    private String auctionDesc = "";

    private Long createTime = 0L;

    private Long updateTime = 0L;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public String getAuctionImages() {
        return auctionImages;
    }

    public void setAuctionImages(String auctionImages) {
        this.auctionImages = auctionImages;
    }

    public String getAuctionDesc() {
        return auctionDesc;
    }

    public void setAuctionDesc(String auctionDesc) {
        this.auctionDesc = auctionDesc;
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


}



