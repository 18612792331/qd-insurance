package com.qding.api.call.app.qding.v2_8_0.struct.neighbor.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;

/**
 * Created by qd on 2016/12/16.
 */
@Validate
public class GetThemeContainMultiProjectRequest extends BaseRequest {

    private static final long serialVersionUID = 1323597134539051740L;

    @ExplainAnnotation (explain = "主题ID")
    private String themeId;

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    @Override
    public String toString() {
        return "GetThemeContainMultiProjectRequest{" +
                "themeId='" + themeId + '\'' +
                '}';
    }
}
