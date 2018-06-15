package com.qding.api.call.app.qding.v2_1_0.struct.hotcycle;

import java.io.Serializable;


public class GcRoomDTO implements Serializable {
    /*编号*/
    private String gcRoomId;

    /*群头像*/
    private String headUrl;

    /*群名称*/
    private String name;

    /*群聊人员状态(1待审核2拒绝3已加入4退群)*/
    private String gcMemberStatus;

    /*群聊人员类型(1普通用户2管理员3群主)*/
    private String gcMemberType;

    /*当前群组人数*/
    private String remark;

    /*群组二维码*/
    private String qrcodeUrl;

    private Long cityId;

    private String cityName;

    private Long projectId;

    private String projectName;

    public String getGcRoomId() {
        return gcRoomId;
    }

    public void setGcRoomId(String gcRoomId) {
        this.gcRoomId = gcRoomId;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGcMemberStatus() {
        return gcMemberStatus;
    }

    public void setGcMemberStatus(String gcMemberStatus) {
        this.gcMemberStatus = gcMemberStatus;
    }

    public String getGcMemberType() {
        return gcMemberType;
    }

    public void setGcMemberType(String gcMemberType) {
        this.gcMemberType = gcMemberType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = "群人数：" + remark + "人";
    }

    public String getQrcodeUrl() {
        return qrcodeUrl;
    }

    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Override
    public String toString() {
        return "GcRoomInfo{" +
                "gcRoomId='" + gcRoomId + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", name='" + name + '\'' +
                ", gcMemberStatus='" + gcMemberStatus + '\'' +
                ", gcMemberType='" + gcMemberType + '\'' +
                ", remark='" + remark + '\'' +
                ", qrcodeUrl='" + qrcodeUrl + '\'' +
                ", cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
