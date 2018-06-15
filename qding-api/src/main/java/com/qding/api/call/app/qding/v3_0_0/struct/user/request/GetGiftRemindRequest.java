package com.qding.api.call.app.qding.v3_0_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

/**
 * Created by qd on 2017/3/3.
 */
public class GetGiftRemindRequest extends BaseRequest {

    private static final long serialVersionUID = 1525047594351318767L;

    @ExplainAnnotation (explain = "页面来源",desc = "1:APP首页, 2:我的")
    private  Integer sourceType;

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public String toString() {
        return "GetGiftRemindRequest{" +
                "sourceType=" + sourceType +
                '}';
    }
}
