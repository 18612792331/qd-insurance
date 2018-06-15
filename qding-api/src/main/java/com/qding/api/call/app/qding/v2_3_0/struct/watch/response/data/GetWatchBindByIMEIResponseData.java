package com.qding.api.call.app.qding.v2_3_0.struct.watch.response.data;

import com.qding.api.call.app.qding.v2_3_0.struct.watch.WatchBindInfo;
import com.qding.api.call.app.qding.v2_3_0.struct.watch.WatchInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetWatchBindByIMEIResponseData extends ResponseData {

    private static final long serialVersionUID = 4613826836833952546L;

    private WatchInfo watchInfo;

    private List<WatchBindInfo> watchBindInfoList;

    public WatchInfo getWatchInfo() {
        return watchInfo;
    }

    public void setWatchInfo(WatchInfo watchInfo) {
        this.watchInfo = watchInfo;
    }

    public List<WatchBindInfo> getWatchBindInfoList() {
        return watchBindInfoList;
    }

    public void setWatchBindInfoList(List<WatchBindInfo> watchBindInfoList) {
        this.watchBindInfoList = watchBindInfoList;
    }

    @Override
    public String toString() {
        return "GetWatchBindByIMEIResponseData{" +
                "watchInfo=" + watchInfo +
                ", watchBindInfoList=" + watchBindInfoList +
                '}';
    }
}
