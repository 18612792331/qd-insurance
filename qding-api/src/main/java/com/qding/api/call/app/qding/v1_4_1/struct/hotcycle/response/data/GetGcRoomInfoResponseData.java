package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.response.data;

import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.Group;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/1/9.
 */
public class GetGcRoomInfoResponseData extends ResponseData {

    private static final long serialVersionUID = 4853024573072921506L;

    private Group entity;

    public Group getEntity() {
        return entity;
    }

    public void setEntity(Group entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetGcRoomInfoResponseData{" +
                "entity=" + entity +
                '}';
    }
}
