package com.qding.api.call.app.qding.v3_3_0.struct.newsell.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import lombok.Getter;
import lombok.Setter;

@Validate
public class GetLogisticsCompanysRequest extends BaseRequest {

    private static final long serialVersionUID = -3743333272178010054L;


    @Getter
    @Setter
    @NotNullValidate
    @ExplainAnnotation(explain = "渠道类型 1：点滴")
    private Integer sourceType;


}
