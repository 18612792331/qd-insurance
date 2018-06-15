package com.qding.api.call.app.qding.v3_0_0.struct.project.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;

/**
 * Created by qd on 2017/2/23.
 */
@Validate
public class GetPushNoticRemindRequest extends BaseRequest {

    private static final long serialVersionUID = -1852163380517469328L;

    @ExplainAnnotation(explain = "上次刷新时间戳")
    private Long lastFreshTime;

    public Long getLastFreshTime() {
        return lastFreshTime;
    }

    public void setLastFreshTime(Long lastFreshTime) {
        this.lastFreshTime = lastFreshTime;
    }
}
