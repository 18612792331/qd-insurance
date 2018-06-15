package com.qding.api.call.app.qding.v2_3_0.struct.watch.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_3_0.struct.watch.WatchLocation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/5/17.
 */
public class GetWatchLocationByIMEIResponseData extends ResponseData {

    private static final long serialVersionUID = 5096132307111845306L;

    @ExplainAnnotation(explain = "地图信息")
    private WatchLocation entity;


    public WatchLocation getEntity() {
        return entity;
    }

    public void setEntity(WatchLocation entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GeolocationResponseData{" +
                "entity=" + entity +
                '}';
    }
}
