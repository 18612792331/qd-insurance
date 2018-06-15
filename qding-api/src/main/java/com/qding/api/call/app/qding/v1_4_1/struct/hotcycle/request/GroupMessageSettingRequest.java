package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/27.
 */
@Validate
public class GroupMessageSettingRequest  extends BaseRequest {

    private static final long serialVersionUID = 3321877723234075710L;

    /**
     * 群组ID
     */
    @NotNullValidate
    private String gcRoomId;

    /**
     * 账户ID
     */
    @NotNullValidate
    private String userId;

    /**
     * 屏蔽状态 1接收群信息 2屏蔽群信息
     */
    @NotNullValidate
    private Integer receiveType;

    /**
     * 操作者名称
     */
    @NotNullValidate
    private String optAt;

    public String getOptAt() {
        return optAt;
    }

    public void setOptAt(String optAt) {
        this.optAt = optAt;
    }

    public String getGcRoomId() {
        return gcRoomId;
    }

    public void setGcRoomId(String gcRoomId) {
        this.gcRoomId = gcRoomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getReceiveType() {
        return receiveType;
    }

    public void setReceiveType(Integer receiveType) {
        this.receiveType = receiveType;
    }

    @Override
    public String toString() {
        return "GroupMessageSettingRequest [optAt="+optAt+",gcRoomId=" + gcRoomId +",userId="+userId+",receiveType="+receiveType +
                " ,super.toString() ]";
    }
}
