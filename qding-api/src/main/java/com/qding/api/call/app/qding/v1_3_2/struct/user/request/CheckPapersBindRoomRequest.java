package com.qding.api.call.app.qding.v1_3_2.struct.user.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/20.
 */
@Validate
public class CheckPapersBindRoomRequest extends BaseRequest {

    private static final long serialVersionUID = -6609276337385210074L;

    @NotNullValidate
    private String bindId;

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    @Override
    public String toString() {
        return "CheckPapersBindRoomStatusRequest [bindId=" + bindId
                + ", toString()=" + super.toString() + "]";
    }
}
