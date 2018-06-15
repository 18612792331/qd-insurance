package com.qding.api.call.app.qding.v2_4_0.struct.sysconfig;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;


public class WareInfo implements Serializable {

    private static final long serialVersionUID = -5152703956480504584L;

    public WareInfo() {
    }

    @ExplainAnnotation(explain = "id")
    private String id;

    @ExplainAnnotation(explain = "商城管理模块id")
    private String mallManagementModuleId;


    @ExplainAnnotation(explain = "货品id或者是秒杀.阶梯团购id")
    private String wareSkuId;

    @ExplainAnnotation(explain = "货品name")
    private String wareSkuName;

    @ExplainAnnotation(explain = "url链接地址.秒杀.阶梯团购货品的跳转地址")
    private String linkedUrl;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMallManagementModuleId() {
        return mallManagementModuleId;
    }

    public void setMallManagementModuleId(String mallManagementModuleId) {
        this.mallManagementModuleId = mallManagementModuleId;
    }

    public String getWareSkuId() {
        return wareSkuId;
    }

    public void setWareSkuId(String wareSkuId) {
        this.wareSkuId = wareSkuId;
    }

    public String getWareSkuName() {
        return wareSkuName;
    }

    public void setWareSkuName(String wareSkuName) {
        this.wareSkuName = wareSkuName;
    }

    public String getLinkedUrl() {
        return linkedUrl;
    }

    public void setLinkedUrl(String linkedUrl) {
        this.linkedUrl = linkedUrl;
    }

    @Override
    public String toString() {
        return "WareInfo{" +
                "id='" + id + '\'' +
                ", mallManagementModuleId='" + mallManagementModuleId + '\'' +
                ", wareSkuId='" + wareSkuId + '\'' +
                ", wareSkuName='" + wareSkuName + '\'' +
                ", linkedUrl='" + linkedUrl + '\'' +
                '}';
    }
}