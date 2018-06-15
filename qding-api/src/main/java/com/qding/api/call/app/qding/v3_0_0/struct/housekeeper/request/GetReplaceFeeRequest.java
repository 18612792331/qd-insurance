package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/3/22.
 */
@Validate
public class GetReplaceFeeRequest extends BaseRequest {

    private static final long serialVersionUID = -7351674480004861182L;

    @ExplainAnnotation(explain = "房间ID")
    @NotNullValidate
    private Long roomId;

    @ExplainAnnotation(explain = "龙湖方客户ID")
    @NotNullValidate
    private Long custId;


    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    @Override
    public String toString() {
        return "GetReplaceFeeRequest{" +
                "roomId=" + roomId +
                ", custId=" + custId +
                '}';
    }
}


