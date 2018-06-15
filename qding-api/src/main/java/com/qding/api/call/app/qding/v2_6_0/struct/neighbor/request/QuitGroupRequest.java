package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/9/22.
 */
@Validate
public class QuitGroupRequest extends BaseRequest {

    private static final long serialVersionUID = -5702212895227092495L;

    @ExplainAnnotation(explain = "群id")
    @NotNullValidate
    private String gcRoomId;

    @ExplainAnnotation(explain = "会员ID")
    @NotNullValidate
    private String memberId;

    public String getGcRoomId() {
        return gcRoomId;
    }

    public void setGcRoomId(String gcRoomId) {
        this.gcRoomId = gcRoomId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "OptGroupMemberRequest{" +
                "gcRoomId='" + gcRoomId + '\'' +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
