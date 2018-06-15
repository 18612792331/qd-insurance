package com.qding.api.call.app.qding.v1_3_2.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by andy on 16-4-20.
 */
public class SetPassFreeBarrierStatusResponseData extends ResponseData {

    private static final long serialVersionUID = 7161947882024291872L;


    @ExplainAnnotation(explain = "权限状态",desc = "1：有权限，0：无权限")
    private Integer permissionStatus;

    @ExplainAnnotation (explain = "自身开关",desc = "1：开启，0：关闭")
    private Integer selfStatus;

    public Integer getPermissionStatus() {
        return permissionStatus;
    }

    public void setPermissionStatus(Integer permissionStatus) {
        this.permissionStatus = permissionStatus;
    }

    public Integer getSelfStatus() {
        return selfStatus;
    }

    public void setSelfStatus(Integer selfStatus) {
        this.selfStatus = selfStatus;
    }

    @Override
    public String toString() {
        return "SetPassFreeBarrierStatusResponseData{" +
                "permissionStatus=" + permissionStatus +
                ", selfStatus=" + selfStatus +
                '}';
    }
}
