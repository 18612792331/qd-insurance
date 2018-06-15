package com.qding.api.call.app.qding.v1_4_1.struct.notify.response.data;

import com.qding.api.call.app.qding.v1_4_1.struct.notify.NotifySettings;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/11/2.
 */
public class GetNotifySettingsResponseData  extends ResponseData {

    private static final long serialVersionUID = -1320165463545591419L;

    private NotifySettings entity;

    public NotifySettings getEntity() {
        return entity;
    }

    public void setEntity(NotifySettings entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetNotifySettingsResponseData [entity="+entity+",toString()="+super.toString()+" ]";
    }

}
