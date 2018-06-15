package com.qding.api.call.app.qding.v3_0_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/23.
 */
public class GetRobotHeartbeatNumResponseData extends ResponseData {

    private static final long serialVersionUID = 8748263577289420703L;

    @ExplainAnnotation (explain = "心跳检测数")
    private Integer heartBeatNum;


    public Integer getHeartBeatNum() {
        return heartBeatNum;
    }

    public void setHeartBeatNum(Integer heartBeatNum) {
        this.heartBeatNum = heartBeatNum;
    }


    @Override
    public String toString() {
        return "GetRobotHeartbeatNumResponseData{" +
                "heartBeatNum=" + heartBeatNum +
                '}';
    }
}
