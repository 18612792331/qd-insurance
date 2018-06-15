package com.qding.api.call.app.qding.v2_0_0.struct.housekeeper.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2016/1/16.
 */
@Validate
public class GetMyReportByIdRequest extends BaseRequest {

    private static final long serialVersionUID = -2215206013507214955L;

    @NotNullValidate
    @ExplainAnnotation(explain = "报事评价ID")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
