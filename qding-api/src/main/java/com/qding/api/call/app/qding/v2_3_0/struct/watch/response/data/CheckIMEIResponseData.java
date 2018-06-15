package com.qding.api.call.app.qding.v2_3_0.struct.watch.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_3_0.struct.watch.WatchAccountInfo;
import com.qding.api.struct.ResponseData;


public class CheckIMEIResponseData extends ResponseData {

    private static final long serialVersionUID = 4919716192615021724L;

    private WatchAccountInfo watchAccountInfo;

    public WatchAccountInfo getWatchAccountInfo() {
        return watchAccountInfo;
    }

    public void setWatchAccountInfo(WatchAccountInfo watchAccountInfo) {
        this.watchAccountInfo = watchAccountInfo;
    }

    @Override
    public String toString() {
        return "CheckIMEIResponseData{" +
                "watchAccountInfo=" + watchAccountInfo +
                '}';
    }
}
