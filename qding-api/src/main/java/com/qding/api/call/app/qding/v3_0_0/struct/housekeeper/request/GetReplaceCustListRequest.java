package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/3/22.
 */
@Validate
public class GetReplaceCustListRequest extends BaseRequest {

    private static final long serialVersionUID = -4214139320285392730L;

    @ExplainAnnotation (explain = "房间ID")
    @NotNullValidate
    private Long roomId;


    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }


    @Override
    public String toString() {
        return "GetReplaceCustListRequest{" +
                "roomId=" + roomId +
                '}';
    }
}
