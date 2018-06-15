package com.qding.api.call.app.qding.v2_4_0.struct.sysconfig.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_4_0.struct.sysconfig.MallIndex;
import com.qding.api.struct.ResponseData;


public class GetMallIndexResponseData extends ResponseData {

    private static final long serialVersionUID = 8722040248346951635L;

    @ExplainAnnotation(explain = "商城首页数据")
    private MallIndex entity;

    public MallIndex getEntity() {
        return entity;
    }

    public void setEntity(MallIndex entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetMallIndexResponseData{" +
                "entity=" + entity +
                '}';
    }
}
