package com.qding.api.imessage;

import java.io.Serializable;

/**
 * Created by jiawenzheng on 2015/8/14.
 */
public class IntegralMessageBeanT implements Serializable {


    private static final long serialVersionUID = 1142006459145107946L;

    private String abstractId;
    private String businessType;
    private String tbusinessId;
    private Long porjectId;
    private Long optTime;
    private String isBack;
    private String cardinal;

    private String sbusinessId;

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getTbusinessId() {
        return tbusinessId;
    }

    public void setTbusinessId(String tbusinessId) {
        this.tbusinessId = tbusinessId;
    }

    public Long getPorjectId() {
        return porjectId;
    }

    public void setPorjectId(Long porjectId) {
        this.porjectId = porjectId;
    }

    public Long getOptTime() {
        return optTime;
    }

    public void setOptTime(Long optTime) {
        this.optTime = optTime;
    }

    public String getIsBack() {
        return isBack;
    }

    public void setIsBack(String isBack) {
        this.isBack = isBack;
    }

    public String getCardinal() {
        return cardinal;
    }

    public void setCardinal(String cardinal) {
        this.cardinal = cardinal;
    }

    public String getSbusinessId() {
        return sbusinessId;
    }

    public void setSbusinessId(String sbusinessId) {
        this.sbusinessId = sbusinessId;
    }

    public String getAbstractId() {
        return abstractId;
    }

    public void setAbstractId(String abstractId) {
        this.abstractId = abstractId;
    }

    public IntegralMessageBeanT(String abstractId, String businessType, String tbusinessId, Long porjectId, Long optTime, String isBack, String cardinal, String sbusinessId) {
        this.abstractId = abstractId;
        this.businessType = businessType;
        this.tbusinessId = tbusinessId;
        this.porjectId = porjectId;
        this.optTime = optTime;
        this.isBack = isBack;
        this.cardinal = cardinal;
        this.sbusinessId = sbusinessId;
    }
}
