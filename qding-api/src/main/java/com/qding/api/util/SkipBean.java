package com.qding.api.util;

import java.io.Serializable;

/**
 * Created by qd on 2016/1/4.
 */
public class SkipBean implements Serializable {

    private static final long serialVersionUID = -8304026985307364910L;

    //url
    private String skipUrl;

    //id  多个用逗号隔开
    private String ids;

    //编号
    private Integer skipNo;

    //模板
    private String skipTemplate;

    //支持版本
    private Integer version;

    //权限代码
    private  Integer pcode =0;

    //分享 针对url
    private Integer share = 0;

    //分享 shareTitle
    private String shareTitle;

    //分享 shareText
    private String shareText;

    //分享 shareImageUrl
    private String shareImageUrl;

    //分享 shareSkipUrl
    private String shareSkipUrl;

    private String projectId;

    private String projectName;

    //身份
    private String identity;

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Integer getShare() {
        return share;
    }

    public void setShare(Integer share) {
        this.share = share;
    }

    public Integer getPcode() {
        return pcode;
    }

    public void setPcode(Integer pcode) {
        this.pcode = pcode;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getSkipNo() {
        return skipNo;
    }

    public void setSkipNo(Integer skipNo) {
        this.skipNo = skipNo;
    }

    public String getSkipTemplate() {
        return skipTemplate;
    }

    public void setSkipTemplate(String skipTemplate) {
        this.skipTemplate = skipTemplate;
    }

    public String getSkipUrl() {
        return skipUrl;
    }

    public void setSkipUrl(String skipUrl) {
        this.skipUrl = skipUrl;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    public String getShareImageUrl() {
        return shareImageUrl;
    }

    public void setShareImageUrl(String shareImageUrl) {
        this.shareImageUrl = shareImageUrl;
    }

    public String getShareSkipUrl() {
        return shareSkipUrl;
    }

    public void setShareSkipUrl(String shareSkipUrl) {
        this.shareSkipUrl = shareSkipUrl;
    }
}
