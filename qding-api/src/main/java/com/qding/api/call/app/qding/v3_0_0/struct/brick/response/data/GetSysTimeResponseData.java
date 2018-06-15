package com.qding.api.call.app.qding.v3_0_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/23.
 */
public class GetSysTimeResponseData extends ResponseData {

    private static final long serialVersionUID = 2301647217732375307L;

    @ExplainAnnotation (explain = "系统时间")
    private Long sysTime;

    public Long getSysTime() {
        return sysTime;
    }

    public void setSysTime(Long sysTime) {
        this.sysTime = sysTime;
    }


    @Override
    public String toString() {
        return "GetSysTimeResponseData{" +
                "sysTime=" + sysTime +
                '}';
    }
}
