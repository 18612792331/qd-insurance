package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/27.
 */
@Validate
public class PassGroupApplyRequest  extends BaseRequest {

    private static final long serialVersionUID = -2496641919845942427L;

    @NotNullValidate
    private  String gcRoomId;

    @NotNullValidate
    private String userId;

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

    @Override
    public String toString() {
        return "PassGroupApplyRequest [optAt="+optAt+",gcRoomId=" + gcRoomId +",userId="+userId+"," +
                " super.toString() ]";
    }
}
