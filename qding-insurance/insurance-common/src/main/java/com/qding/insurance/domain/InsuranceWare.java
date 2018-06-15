package com.qding.insurance.domain;

import java.io.Serializable;
import java.util.Date;

public class InsuranceWare implements Serializable {

    private static final long serialVersionUID = 5754817770746367720L;

    private String id;

    private Long brickWareId;

    private String title; // 标题

    private String subTitle; // 副标题

    private String image;// 主图地址

    private String detailImage;// 详情图地址，多图用英文逗号分隔

    private Long categoryId1;// 一级品类

    private Long categoryId2;// 二级品类

    private Long categoryId3;// 三级品类

    private Integer minEffectDays;// 生效日期最小天数

    private Integer maxEffectDays;// 生效日期最大天数

    private Integer objectType;// 投保对象 1：房屋

    private Integer policyType;// 保单生成方式，1：电子保单，2：纸质保单

    private Date onlineTime; // 上架时间 yyyy-MM-dd HH:mm:ss

    private Date offlineTime; // 下架时间 yyyy-MM-dd HH:mm:ss

    private Integer status;

    private String contractId; // 合同ID

    private String contractName;

    private String providerId;

    private String providerName;

    private String createBy;

    private Date createAt;

    private String toAuditBy;

    private Date toAuditAt;

    private String auditBy;

    private Date auditAt;

    private String updateBy;

    private Date updateTime;

    public Long getBrickWareId() {
        return brickWareId;
    }

    public void setBrickWareId(Long brickWareId) {
        this.brickWareId = brickWareId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle == null ? null : subTitle.trim();
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image == null ? null : image.trim();
    }

    public String getDetailImage() {
        return detailImage;
    }

    public void setDetailImage(String detailImage) {
        this.detailImage = detailImage == null ? null : detailImage.trim();
    }

    public Long getCategoryId1() {
        return categoryId1;
    }

    public void setCategoryId1(Long categoryId1) {
        this.categoryId1 = categoryId1;
    }

    public Long getCategoryId2() {
        return categoryId2;
    }

    public void setCategoryId2(Long categoryId2) {
        this.categoryId2 = categoryId2;
    }

    public Long getCategoryId3() {
        return categoryId3;
    }

    public void setCategoryId3(Long categoryId3) {
        this.categoryId3 = categoryId3;
    }

    public Integer getMinEffectDays() {
        return minEffectDays;
    }

    public void setMinEffectDays(Integer minEffectDays) {
        this.minEffectDays = minEffectDays;
    }

    public Integer getMaxEffectDays() {
        return maxEffectDays;
    }

    public void setMaxEffectDays(Integer maxEffectDays) {
        this.maxEffectDays = maxEffectDays;
    }

    public Integer getObjectType() {
        return objectType;
    }

    public void setObjectType(Integer objectType) {
        this.objectType = objectType;
    }

    public Integer getPolicyType() {
        return policyType;
    }

    public void setPolicyType(Integer policyType) {
        this.policyType = policyType;
    }

    public Date getOnlineTime() {
        return onlineTime;
    }

    public void setOnlineTime(Date onlineTime) {
        this.onlineTime = onlineTime;
    }

    public Date getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Date offlineTime) {
        this.offlineTime = offlineTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId == null ? null : contractId.trim();
    }

    public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName == null ? null : contractName.trim();
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId == null ? null : providerId.trim();
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName == null ? null : providerName.trim();
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getToAuditBy() {
        return toAuditBy;
    }

    public void setToAuditBy(String toAuditBy) {
        this.toAuditBy = toAuditBy == null ? null : toAuditBy.trim();
    }

    public Date getToAuditAt() {
        return toAuditAt;
    }

    public void setToAuditAt(Date toAuditAt) {
        this.toAuditAt = toAuditAt;
    }

    public String getAuditBy() {
        return auditBy;
    }

    public void setAuditBy(String auditBy) {
        this.auditBy = auditBy == null ? null : auditBy.trim();
    }

    public Date getAuditAt() {
        return auditAt;
    }

    public void setAuditAt(Date auditAt) {
        this.auditAt = auditAt;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy == null ? null : updateBy.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}