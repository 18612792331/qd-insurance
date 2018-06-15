package com.qding.api.call.app.qding.v3_0_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/3/3.
 */
@Validate
public class GetGiftListRequest extends BaseRequest {

    private static final long serialVersionUID = -8773576440186822525L;

    @ExplainAnnotation (explain = "页面来源",desc = "1:APP首页, 2:我的礼包页")
    @NotNullValidate
    private Integer sourceType;

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public String toString() {
        return "GetGiftListRequest{" +
                "sourceType=" + sourceType +
                '}';
    }
}
