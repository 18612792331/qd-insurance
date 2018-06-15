package com.qding.api.call.app.qding.v2_4_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2016/6/14.
 */
public class ReportTaskBean implements Serializable {

    private static final long serialVersionUID = -3640233591623467909L;

    @ExplainAnnotation (explain = "报事ID")
    private  String  reportId;

    @ExplainAnnotation (explain = "工单ID")
    private  String  taskId;

    @ExplainAnnotation (explain = "报事类型")
    private  String  reportType;

    @ExplainAnnotation (explain = "报事类型名称")
    private String reportTypeName;

    @ExplainAnnotation (explain = "工单内容")
    private  String  content;

    @ExplainAnnotation (explain = "头像")
    private String  headImage;

    @ExplainAnnotation (explain = "管家名称")
    private  String userName;

    @ExplainAnnotation (explain = "手机号")
    private  String mobile;

    @ExplainAnnotation (explain = "费用")
    private String totalPrice;

    @ExplainAnnotation (explain = "费用标签描述")
    private String priceLable;

    @ExplainAnnotation (explain = "处理时间")
    private Long handleAt;

    @ExplainAnnotation(explain = "角色")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getReportTypeName() {
        return reportTypeName;
    }

    public void setReportTypeName(String reportTypeName) {
        this.reportTypeName = reportTypeName;
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Long getHandleAt() {
        return handleAt;
    }

    public void setHandleAt(Long handleAt) {
        this.handleAt = handleAt;
    }

    public String getPriceLable() {
        return priceLable;
    }

    public void setPriceLable(String priceLable) {
        this.priceLable = priceLable;
    }

    @Override
    public String toString() {
        return "ReportTaskBean{" +
                "reportId='" + reportId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", reportType='" + reportType + '\'' +
                ", reportTypeName='" + reportTypeName + '\'' +
                ", content='" + content + '\'' +
                ", headImage='" + headImage + '\'' +
                ", userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", priceLable='" + priceLable + '\'' +
                ", handleAt=" + handleAt +
                ", role='" + role + '\'' +
                '}';
    }
}
