package com.qding.api.call.app.qding.v2_8_0.struct.user.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2017/1/22.
 */
public class WeChatSPLoginRequest extends BaseRequest {

    private static final long serialVersionUID = -5056394594867330601L;

    @ExplainAnnotation (explain = "unionId")
    private String unionId;

    @ExplainAnnotation (explain = "来源类型")
    @NotNullValidate
    private Integer sourceType = Integer.valueOf(61);

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    @Override
    public String toString() {
        return "WeChatSPLoginRequest{" +
                "unionId='" + unionId + '\'' +
                ", sourceType=" + sourceType +
                '}';
    }
}
