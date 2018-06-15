package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;


@Validate
public class GetOrderDetailRequest extends BaseRequest {

    /**
     *
     */
    private static final long serialVersionUID = -7722980109485018092L;

    @NotNullValidate
    @ExplainAnnotation(explain = "订单号")
    private String orderCode;

    @NotNullValidate
    @ExplainAnnotation(explain = "房间ID")
    private String roomId;

    @ExplainAnnotation(explain = "memberId")
    private String memberId;

    public GetOrderDetailRequest() {

    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "GetOrderDetailRequest{" +
                "orderCode='" + orderCode + '\'' +
                ", roomId='" + roomId + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
