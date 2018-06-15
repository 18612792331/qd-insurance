package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/10.
 */
@Validate
public class AddBlueToothLogRequest  extends BaseRequest {

    @NotNullValidate
    private String projectId = "";

    @NotNullValidate
    private String projectName = "";

    @NotNullValidate
    private String accountId = "";

    @NotNullValidate
    private String memberId = "";

    @NotNullValidate
    private String mobile = "";

    @NotNullValidate
    private String name = "";

    @NotNullValidate
    private String gateLocation = "";

    @NotNullValidate
    private String passMode = "";

    private Long createTime = Long.valueOf(0L);

    @NotNullValidate
    private String code = "";

    @NotNullValidate
    private String message = "";

    @ExplainAnnotation (explain = "步骤")
    private String step;

    @ExplainAnnotation (explain = "关联ID",desc = "生成算法：projectId_memberId_mobile_createTime")
    private String relationId;

    @ExplainAnnotation (explain = "调用时长")
    private String callTime;

    @ExplainAnnotation (explain = "网络制式")
    private String netWork;

    public String getNetWork() {
        return netWork;
    }

    public void setNetWork(String netWork) {
        this.netWork = netWork;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "AddBlueToothLogRequest{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", accountId='" + accountId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", mobile='" + mobile + '\'' +
                ", name='" + name + '\'' +
                ", gateLocation='" + gateLocation + '\'' +
                ", passMode='" + passMode + '\'' +
                ", createTime=" + createTime +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", step='" + step + '\'' +
                ", relationId='" + relationId + '\'' +
                ", callTime='" + callTime + '\'' +
                ", netWork='" + netWork + '\'' +
                '}';
    }
}
