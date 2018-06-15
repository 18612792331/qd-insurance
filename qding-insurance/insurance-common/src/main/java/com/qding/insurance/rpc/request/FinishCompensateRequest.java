package com.qding.insurance.rpc.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class FinishCompensateRequest extends BaseRequest {

    private static final long serialVersionUID = -3864718395809030684L;

    // 房屋ID
    private String roomID;

    // 订单号
    private String orderCode;

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

}
