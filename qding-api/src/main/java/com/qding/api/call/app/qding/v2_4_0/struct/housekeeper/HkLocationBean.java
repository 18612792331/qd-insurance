package com.qding.api.call.app.qding.v2_4_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.passport.struct.response.location.Address;

import java.io.Serializable;

/**
 * Created by qd on 2016/6/8.
 */
public class HkLocationBean implements Serializable {

    private static final long serialVersionUID = -7291320830094429616L;


    @ExplainAnnotation (explain = "是否支持地图模式",desc = "1:支持，0:不支持")
    private Integer isLocation;

    @ExplainAnnotation (explain = "报事ID")
    private String reportId;

    @ExplainAnnotation (explain = "PUSER ID")
    private String pUserId;

    @ExplainAnnotation (explain = "会员ID")
    private String memberId;

    @ExplainAnnotation (explain = "姓名")
    private String userName;

    @ExplainAnnotation(explain = "虚拟电话")
    private String virtualMobile;

    @ExplainAnnotation(explain = "经度")
    private String longitude;

    @ExplainAnnotation(explain = "纬度")
    private String latitude;

    @ExplainAnnotation(explain = "社区描述")
    private String projectDesc;

    @ExplainAnnotation(explain = "头像")
    private String headImage;

    @ExplainAnnotation(explain = "角色")
    private String role;

    @ExplainAnnotation(explain = "用户ID")
    private String userId;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getIsLocation() {
        return isLocation;
    }

    public void setIsLocation(Integer isLocation) {
        this.isLocation = isLocation;
    }

    public String getpUserId() {
        return pUserId;
    }

    public void setpUserId(String pUserId) {
        this.pUserId = pUserId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getVirtualMobile() {
        return virtualMobile;
    }

    public void setVirtualMobile(String virtualMobile) {
        this.virtualMobile = virtualMobile;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getProjectDesc() {
        return projectDesc;
    }

    public void setProjectDesc(String projectDesc) {
        this.projectDesc = projectDesc;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
