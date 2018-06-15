package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/3/22.
 */
@Validate
public class ReplaceFeeCreateOrderRequest extends BaseRequest {

    private static final long serialVersionUID = 397703558305600611L;

    @ExplainAnnotation (explain = "房间ID")
    @NotNullValidate
    private String roomId ;

    @ExplainAnnotation (explain = "费用所属的人的第三方custID，也就是要给谁代缴")
    @NotNullValidate
    private String feeOwnersCustid;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getFeeOwnersCustid() {
        return feeOwnersCustid;
    }

    public void setFeeOwnersCustid(String feeOwnersCustid) {
        this.feeOwnersCustid = feeOwnersCustid;
    }
}
