package com.qding.api.call.app.qding.v2_3_0.struct.watch.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_3_0.struct.watch.WatchDetailInfo;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/5/16.
 */
public class GetWatchDetailResponseData extends ResponseData {

    private static final long serialVersionUID = 4613826836833952546L;

    @ExplainAnnotation(explain = "电话详情")
    private WatchDetailInfo watchDetailInfo;

    public WatchDetailInfo getWatchDetailInfo() {
        return watchDetailInfo;
    }

    public void setWatchDetailInfo(WatchDetailInfo watchDetailInfo) {
        this.watchDetailInfo = watchDetailInfo;
    }

    @Override
    public String toString() {
        return "GetWatchDetailResponseData{" +
                "watchDetailInfo=" + watchDetailInfo +
                '}';
    }
}
