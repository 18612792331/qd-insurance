package com.qding.api.imessage;

import java.io.Serializable;

/**
 * Created by jiawenzheng on 2015/8/14.
 */
public class IntegralMessageBean implements Serializable {


    private static final long serialVersionUID = 1142006459145107946L;


    /**
     * 会员ID
     */
    private String memberId;

    /**
     * 业务ID (feedId,订单Id .....)
     */
    private String businessId;

    /**
     * 当前操作社区ID
     */
    private Long projectId;

    /**
     * 操作时间
     */
    private Long optTime;

    /**
     * 业务类型
     */
    private String businessType;

    /**
     * 是否回退
     */
    private String isBack;

    /**
     * 积分兑换基数
     */
    private String cardinal;

    /**
     * 源业务ID (有可能和businessId相同)
     */
    private String sbusinessId;

    public String getCardinal() {
        return cardinal;
    }

    public void setCardinal(String cardinal) {
        this.cardinal = cardinal;
    }

    public String getIsBack() {
        return isBack;
    }

    public void setIsBack(String isBack) {
        this.isBack = isBack;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getOptTime() {
        return optTime;
    }

    public void setOptTime(Long optTime) {
        this.optTime = optTime;
    }

    public String getSbusinessId() {
        return sbusinessId;
    }

    public void setSbusinessId(String sbusinessId) {
        this.sbusinessId = sbusinessId;
    }
}
