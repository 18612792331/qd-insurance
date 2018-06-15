package com.qding.api.call.app.qding.v3_0_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/24.
 */
public class CheckSuperiorLimitByMidResponseData extends ResponseData {

    private static final long serialVersionUID = 5615095905715347588L;

    @ExplainAnnotation (explain = "是否到达上限",desc = "1:上限，0:正常")
    private Integer superiorLimitFlag;

    public Integer getSuperiorLimitFlag() {
        return superiorLimitFlag;
    }

    public void setSuperiorLimitFlag(Integer superiorLimitFlag) {
        this.superiorLimitFlag = superiorLimitFlag;
    }

    @Override
    public String toString() {
        return "CheckSuperiorLimitByMidResponseData{" +
                "superiorLimitFlag=" + superiorLimitFlag +
                '}';
    }
}
