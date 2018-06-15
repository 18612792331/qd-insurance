package com.qding.api.call.app.qding.v2_0_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by andy on 16-4-21.
 */
public class GetOnlineCustomerServiceStatusResponseData extends ResponseData {

    private static final long serialVersionUID = -3799364987216237296L;

    @ExplainAnnotation(explain = "在线客服状态",desc = "1:开启，0：关闭")
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "GetOnlineCustomerServiceStatusResponseData{" +
                "status=" + status +
                '}';
    }
}
