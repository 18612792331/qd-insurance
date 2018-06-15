package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/9/18.
 */
@Validate
public class PropertyChargesOrderRequest extends BaseRequest {

    private static final long serialVersionUID = 7463410954225625570L;

    /**
     * 房间号
     */
    @NotNullValidate
    private String roomId;

    /**
     * 会员ID
     */
    @NotNullValidate
    private String memberId;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "PropertyChargesOrderRequest [memberId="+memberId+",roomId=" + roomId + ", toString()="
                + super.toString() + "]";
    }
}
