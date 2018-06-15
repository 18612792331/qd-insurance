package com.qding.api.call.app.qding.v2_3_0.struct.watch.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.smart.validate.ValidateBean;

/**
 * Created by qd on 2016/5/16.
 */
@ValidateBean
public class GetWatchInfoListByMemberIdRequest extends BaseRequest {

    private static final long serialVersionUID = 4162413891394174146L;

    @NotNullValidate
    @ExplainAnnotation(explain = "memberId")
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "GetWatchInfoListByMemberIdRequest{" +
                "memberId='" + memberId + '\'' +
                '}';
    }
}
