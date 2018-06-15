package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.request;

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
                '}';
    }
}
