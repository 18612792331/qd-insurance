package com.qding.api.call.app.qding.v3_3_0.struct.user.request;


import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Create by jinhaishan on 18/5/30
 **/
public class GetRelNameStatusRequest extends BaseRequest {

    private static final long serialVersionUID = 6500584933813103856L;

    @ExplainAnnotation(explain = "会员ID")
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "GetRelNameStatusRequest{" +
                "memberId='" + memberId + '\'' +
                '}';
    }
}
