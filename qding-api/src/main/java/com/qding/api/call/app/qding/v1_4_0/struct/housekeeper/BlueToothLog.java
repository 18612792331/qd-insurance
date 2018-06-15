package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

/**
 * Created by qd on 2015/10/10.
 */
public class BlueToothLog implements Serializable {


    private static final long serialVersionUID = 4728146952727993582L;

    private String id = "";
    private String projectId = "";
    private String projectName = "";
    private String accountId = "";
    private String memberId = "";
    private String mobile = "";
    private String name = "";
    private String gateLocation = "";

    /**
     * OPEN_BY_USER = 100, //用户手动点击开门
     OPEN_BY_IBEACON = 101, //ibeacon无障碍通行
     OPEN_BY_SHAKE = 102, //摇一摇
     OPEN_BY_Button = 103, //按钮
     OPEN_BY_SPLASH = 104, //启动页
     OPEN_BY_SHORTCUT = 105, //桌面快捷方式
     OPEN_BY_3DTOUCH = 106, //3DTouch
     */
    private String passMode = "";
    private Long createTime = Long.valueOf(0L);
    private String code = "";
    private String message = "";

    @ExplainAnnotation(explain = "步骤")
    private String step;

    @ExplainAnnotation(explain = "关联ID",desc = "生成算法：projectId_memberId_mobile_createTime")
    private String relationId;

    @ExplainAnnotation(explain = "开门用时")
    private String callTime;

    /**
     * BYDeviceHelper_NetMode_UnReachable = 0, //无服务
     BYDeviceHelper_NetMode_Unknow      = 1, //未知
     BYDeviceHelper_NetMode_2G          = 2, //2G
     BYDeviceHelper_NetMode_3G          = 3, //3G
     BYDeviceHelper_NetMode_LTE         = 4, //LTE(准4G：TD-LTE、FDD-LTE)
     BYDeviceHelper_NetMode_LTEA        = 5, //标准4G，暂无运营商支持
     BYDeviceHelper_NetMode_WIFI        = 6, //wifi
     */
    @ExplainAnnotation(explain = "网络模式")
    private String netWork;

    public String getCallTime() {
        return callTime;
    }

    public void setCallTime(String callTime) {
        this.callTime = callTime;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGateLocation() {
        return gateLocation;
    }

    public void setGateLocation(String gateLocation) {
        this.gateLocation = gateLocation;
    }

    public String getPassMode() {
        return passMode;
    }

    public void setPassMode(String passMode) {
        this.passMode = passMode;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getNetWork() {
        return netWork;
    }

    public void setNetWork(String netWork) {
        this.netWork = netWork;
    }
}
