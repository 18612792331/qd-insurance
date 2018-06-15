package com.qding.api.call.app.qding.v1_3_2.struct.user.response.data;

import com.qding.api.call.app.qding.v1_3_2.struct.user.PapersBindRoom;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/10/20.
 */
public class CheckPapersBindRoomResponseData extends ResponseData {

    private static final long serialVersionUID = 188017486807463989L;

    private PapersBindRoom entity;

    public PapersBindRoom getEntity() {
        return entity;
    }

    public void setEntity(PapersBindRoom entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "CheckPapersBindRoomStatusResponseData [entity="+entity+",toString()=" + super.toString() + "]";
    }

}
