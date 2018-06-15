package com.qding.api.call.app.qding.v2_1_0.struct.sysconfig.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_1_0.struct.sysconfig.AccessControAnimationBean;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/7/22.
 */
public class GetAccessControAnimationResponseData extends ResponseData {

    private static final long serialVersionUID = 1191703304949003209L;

    @ExplainAnnotation (explain = "开门用动画")
    private AccessControAnimationBean entity;

    public AccessControAnimationBean getEntity() {
        return entity;
    }

    public void setEntity(AccessControAnimationBean entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetAccessControAnimationResponseData{" +
                "entity=" + entity +
                '}';
    }
}
