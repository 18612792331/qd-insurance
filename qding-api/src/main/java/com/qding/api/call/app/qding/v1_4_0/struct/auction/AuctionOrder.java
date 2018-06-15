package com.qding.api.call.app.qding.v1_4_0.struct.auction;

import java.io.Serializable;

/**
 * 竞拍活动订单
 * Created by qd on 2015/8/20.
 */
public class AuctionOrder implements Serializable {


    private static final long serialVersionUID = 8527030476872164892L;

    //id
    private String id = "";

    //会员id
    private String memberId = "";

    //账户id
    private String accountId = "";

    //会员姓名
    private String memberName = "";

    //会员手机号
    private String mobile = "";

    //出价金额（分）
    private Integer priceFen = 0;

    //出价时间
    private Long biddenTime = 0L;

    //是否中奖
    private Integer isGet = 0;

    //头像地址
    private String headImage = "";

    //创建时间
    private Long createTime = 0L;

    //当前商品竞拍价格
    private Integer currentPriceFen = 0;

    public Integer getCurrentPriceFen() {
        return currentPriceFen;
    }

    public void setCurrentPriceFen(Integer currentPriceFen) {
        this.currentPriceFen = currentPriceFen;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public Integer getPriceFen() {
        return priceFen;
    }

    public void setPriceFen(Integer priceFen) {
        this.priceFen = priceFen;
    }

    public Long getBiddenTime() {
        return biddenTime;
    }

    public void setBiddenTime(Long biddenTime) {
        this.biddenTime = biddenTime;
    }

    public Integer getIsGet() {
        return isGet;
    }

    public void setIsGet(Integer isGet) {
        this.isGet = isGet;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
