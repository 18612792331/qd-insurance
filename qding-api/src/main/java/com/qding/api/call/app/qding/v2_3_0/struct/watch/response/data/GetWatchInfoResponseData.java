package com.qding.api.call.app.qding.v2_3_0.struct.watch.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_3_0.struct.watch.WatchInfo;
import com.qding.api.call.app.qding.v2_3_0.struct.watch.WatchPhoneInfo;
import com.qding.api.struct.ResponseData;
import com.qding.passport.struct.watch.WatchPhone;

/**
 * Created by qd on 2016/5/16.
 */
public class GetWatchInfoResponseData extends ResponseData {

    private static final long serialVersionUID = 2360800144724984732L;

    @ExplainAnnotation(explain = "手表绑定信息汇总")
    private WatchPhoneInfo entity;

    public WatchPhoneInfo getEntity() {
        return entity;
    }

    public void setEntity(WatchPhoneInfo entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetWatchInfoResponseData{" +
                "entity=" + entity +
                '}';
    }
}
