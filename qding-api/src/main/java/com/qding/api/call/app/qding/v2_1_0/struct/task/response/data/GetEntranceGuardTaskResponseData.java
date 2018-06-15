package com.qding.api.call.app.qding.v2_1_0.struct.task.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_1_0.struct.task.EntranceGuardBean;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/3/24.
 */
public class GetEntranceGuardTaskResponseData extends ResponseData {

    private static final long serialVersionUID = -2419835841594744402L;

    @ExplainAnnotation (explain = "门禁营销任务")
    private EntranceGuardBean entity;

    public EntranceGuardBean getEntity() {
        return entity;
    }

    public void setEntity(EntranceGuardBean entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetEntranceGuardTaskResponseData{" +
                "entity=" + entity +
                '}';
    }
}
