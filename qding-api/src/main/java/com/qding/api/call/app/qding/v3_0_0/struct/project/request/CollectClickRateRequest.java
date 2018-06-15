package com.qding.api.call.app.qding.v3_0_0.struct.project.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;

import java.util.List;

/**
 * Created by qd on 2017/3/20.
 */
public class CollectClickRateRequest extends BaseRequest {

    private static final long serialVersionUID = -822546470951778349L;

    @ExplainAnnotation (explain = "日志集合")
    private List<String> logs;

    public List<String> getLogs() {
        return logs;
    }

    public void setLogs(List<String> logs) {
        this.logs = logs;
    }

    @Override
    public String toString() {
        return "CollectClickRateRequest{" +
                ", logs=" + logs +
                '}';
    }
}
