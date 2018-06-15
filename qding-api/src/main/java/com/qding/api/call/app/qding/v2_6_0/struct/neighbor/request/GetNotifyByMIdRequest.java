package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/9/22.
 */
@Validate
public class GetNotifyByMIdRequest extends BaseRequest {

    @ExplainAnnotation (explain = "是否需要重置未读消息,置会删除消息")
    private Integer reset = Integer.valueOf(1);


    @ExplainAnnotation (explain = "会员ID")
    @NotNullValidate
    private String memberId;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public Integer getReset() {
        return reset;
    }

    public void setReset(Integer reset) {
        this.reset = reset;
    }

    @Override
    public String toString() {
        return "GetNotifyByMIdRequest{" +
                "reset=" + reset +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
