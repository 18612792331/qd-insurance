package com.qding.api.call.app.qding.v1_3_1.struct.brick.response.data;

import com.qding.api.call.app.qding.v1_3_1.struct.brick.ClearAppData;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/9/25.
 */
public class ClearAppDataResponseData extends ResponseData {


    private static final long serialVersionUID = -2250556589292178472L;

    private ClearAppData entity;

    public ClearAppData getEntity() {
        return entity;
    }

    public void setEntity(ClearAppData entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "ClearAppDataResponseData [entity=" + entity
                + ", toString()=" + super.toString() + "]";
    }
}
