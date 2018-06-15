package com.qding.api.call.app.qding.v1_3_1.struct.project.response.data;

import com.qding.api.struct.ResponseData;

/**
 * Created by Administrator on 2015/8/11.
 */
public class GetServiceIsOpenByProjectIdResponseData  extends ResponseData {

    private static final long serialVersionUID = 1196415477752416352L;

    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GetServiceIsOpenByProjectIdResponseData [status=" + status
                + ", toString()=" + super.toString() + "]";
    }
}
