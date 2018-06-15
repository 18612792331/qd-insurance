package com.qding.api.call.app.qding.v1_3_2.struct.points;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/8/13.
 */
public class PointsDetail implements Serializable {

    private static final long serialVersionUID = 8452855719175279692L;


    /**
     *  订单号
     */
    private String detailId;

    /**
     *  会员id
     */
    private String memberId;

    /**
     *  操作积分数
     */
    private String optPoints;

    /**
     * 剩余积分数
     */
    private String surplusPoints;


    /**
     *  业务名称
     */
    private String productName;

    /**
     *  操作时间
     */
    private Long optTime;


    /**
     * 业物编号
     */
    private String productNo;

    /**
     * 操作类型
     */
    private String optType;



    public String getMemberId() {
        return memberId;
    }


    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }




    public String getProductName() {
        return productName;
    }


    public void setProductName(String productName) {
        this.productName = productName;
    }


    public Long getOptTime() {
        return optTime;
    }


    public void setOptTime(Long optTime) {
        this.optTime = optTime;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
    }

    public String getOptPoints() {
        return optPoints;
    }

    public void setOptPoints(String optPoints) {
        this.optPoints = optPoints;
    }

    public String getSurplusPoints() {
        return surplusPoints;
    }

    public void setSurplusPoints(String surplusPoints) {
        this.surplusPoints = surplusPoints;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getOptType() {
        return optType;
    }

    public void setOptType(String optType) {
        this.optType = optType;
    }
}
