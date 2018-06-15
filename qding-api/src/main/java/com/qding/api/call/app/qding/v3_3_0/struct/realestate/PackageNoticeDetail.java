package com.qding.api.call.app.qding.v3_3_0.struct.realestate;


import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;
import java.util.List;

/**
 * Create by jinhaishan on 17/12/6
 **/
public class PackageNoticeDetail implements Serializable {

    private static final long serialVersionUID = -4343296472344156047L;

    @ExplainAnnotation(explain = "入住包裹通知基本信息")
    private PackageNotice packageNotice;

    /**
     * 收件人姓名
     */
    @ExplainAnnotation(explain = "收件人姓名")
    private String recipientsName;

    /**
     * 收件人手机号
     */
    @ExplainAnnotation(explain = "收件人手机号")
    private String recipientsMobile;

    /**
     * 会员ID
     */
    @ExplainAnnotation(explain = "会员ID")
    private String memberId;

    /**
     * 社区ID
     */
    @ExplainAnnotation(explain = "社区ID")
    private Long projectId;

    /**
     * 社区名称
     */
    @ExplainAnnotation(explain = "社区名称")
    private String projectName;

    /**
     * 城市名
     */
    @ExplainAnnotation(explain = "城市名")
    private String cityName;

    /**
     * 收件地址
     */
    @ExplainAnnotation(explain = "收件地址")
    private String address;

    /**
     * 房间名称
     */
    @ExplainAnnotation(explain = "收件人姓名")
    private List<String> roomNames;

    /**
     * 录入时间
     */
    @ExplainAnnotation(explain = "录入时间")
    private Long createTime;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
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

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public List<String> getRoomNames() {
        return roomNames;
    }

    public void setRoomNames(List<String> roomNames) {
        this.roomNames = roomNames;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public PackageNotice getPackageNotice() {
        return packageNotice;
    }

    public void setPackageNotice(PackageNotice packageNotice) {
        this.packageNotice = packageNotice;
    }

    public String getRecipientsName() {
        return recipientsName;
    }

    public void setRecipientsName(String recipientsName) {
        this.recipientsName = recipientsName;
    }

    public String getRecipientsMobile() {
        return recipientsMobile;
    }

    public void setRecipientsMobile(String recipientsMobile) {
        this.recipientsMobile = recipientsMobile;
    }


    @Override
    public String toString() {
        return "PackageNoticeDetail{" +
                "packageNotice=" + packageNotice +
                ", recipientsName='" + recipientsName + '\'' +
                ", recipientsMobile='" + recipientsMobile + '\'' +
                ", memberId='" + memberId + '\'' +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", cityName='" + cityName + '\'' +
                ", address='" + address + '\'' +
                ", roomNames=" + roomNames +
                ", createTime=" + createTime +
                '}';
    }
}
