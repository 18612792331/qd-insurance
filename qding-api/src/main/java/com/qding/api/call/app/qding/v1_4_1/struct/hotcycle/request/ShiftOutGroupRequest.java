package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/27.
 */
@Validate
public class ShiftOutGroupRequest extends BaseRequest {

    private static final long serialVersionUID = -6561095301167659023L;

    /**
     * 群组ID
     */
    @NotNullValidate
    private  String gcRoomId;


    /**
     * 被操作者的账户ID
     */
    @NotNullValidate
    private String userId;

    /**
     * 操作者ID (这里是member名称)
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

    @Override
    public String toString() {
        return "ShifOutGroupRequest [gcRoomId=" + gcRoomId +",userId="+userId+
                " super.toString() ]";
    }
}
