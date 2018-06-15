package com.qding.insurance.rpc.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class IsInsuranceProductRequest extends BaseRequest {

    private static final long serialVersionUID = -3864718395809030684L;

    // 房屋ID
    private String roomID;

    // 商品ID
    private String productId;

    public String getRoomID() {
        return roomID;
    }

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

}
