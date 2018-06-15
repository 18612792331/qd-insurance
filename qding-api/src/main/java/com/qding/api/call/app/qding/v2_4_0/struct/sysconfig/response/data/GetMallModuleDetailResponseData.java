package com.qding.api.call.app.qding.v2_4_0.struct.sysconfig.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_4_0.struct.sysconfig.ModuleInfo;
import com.qding.api.struct.ResponseData;


public class GetMallModuleDetailResponseData extends ResponseData {

    private static final long serialVersionUID = 8722040248346951635L;

    @ExplainAnnotation(explain = "模块详情信息")
    private ModuleInfo entity;

    public ModuleInfo getEntity() {
        return entity;
    }

    public void setEntity(ModuleInfo entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetMallModuleDetailResponseData{" +
                "entity=" + entity +
                '}';
    }
}
