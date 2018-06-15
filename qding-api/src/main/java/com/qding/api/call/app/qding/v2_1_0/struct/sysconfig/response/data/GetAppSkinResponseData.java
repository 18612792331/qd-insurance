package com.qding.api.call.app.qding.v2_1_0.struct.sysconfig.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_1_0.struct.sysconfig.AppSkinDto;
import com.qding.api.call.app.qding.v2_1_0.struct.sysconfig.AppSkinEntity;
import com.qding.api.struct.ResponseData;

import java.util.List;


public class GetAppSkinResponseData extends ResponseData {

    private static final long serialVersionUID = 8722040248346951635L;

    @ExplainAnnotation(explain = "皮肤数据")
    private AppSkinEntity appSkinEntity;

    public AppSkinEntity getAppSkinEntity() {
        return appSkinEntity;
    }

    public void setAppSkinEntity(AppSkinEntity appSkinEntity) {
        this.appSkinEntity = appSkinEntity;
    }

    @Override
    public String toString() {
        return "GetAppSkinResponseData{" +
                "appSkinEntity=" + appSkinEntity +
                '}';
    }
}
