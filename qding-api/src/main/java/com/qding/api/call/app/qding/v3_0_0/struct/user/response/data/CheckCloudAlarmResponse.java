package com.qding.api.call.app.qding.v3_0_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/14.
 */
public class CheckCloudAlarmResponse  extends ResponseData{

    private static final long serialVersionUID = 5920159036508725324L;

    @ExplainAnnotation (explain = "是否设置了云报警")
    private Boolean  alarmFlag = false ;

    public Boolean getAlarmFlag() {
        return alarmFlag;
    }

    public void setAlarmFlag(Boolean alarmFlag) {
        this.alarmFlag = alarmFlag;
    }

    @Override
    public String toString() {
        return "CheckCloudAlarmResponse{" +
                "alarmFlag=" + alarmFlag +
                '}';
    }
}
