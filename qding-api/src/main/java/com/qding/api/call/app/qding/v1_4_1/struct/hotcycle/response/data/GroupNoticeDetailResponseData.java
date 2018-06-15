package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.response.data;

import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.Notice;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/10/27.
 */
public class GroupNoticeDetailResponseData extends ResponseData {

    private static final long serialVersionUID = -537530285901755819L;

    private Notice entity;

    public Notice getEntity() {
        return entity;
    }

    public void setEntity(Notice entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GroupNoticeDetailResponseData [entity=" + entity  +
                " ,toString ="+ super.toString() +"]";
    }
}
