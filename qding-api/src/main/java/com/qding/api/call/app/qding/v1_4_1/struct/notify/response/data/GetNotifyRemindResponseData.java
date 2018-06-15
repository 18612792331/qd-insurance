package com.qding.api.call.app.qding.v1_4_1.struct.notify.response.data;

import com.qding.api.call.app.qding.v1_4_1.struct.notify.NotifyRemind;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/11/2.
 */
public class GetNotifyRemindResponseData  extends ResponseData {

    private static final long serialVersionUID = -7692427959431122988L;

    private List<NotifyRemind> list;

    public List<NotifyRemind> getList() {
        return list;
    }

    public void setList(List<NotifyRemind> list) {
        this.list = list;
    }


    @Override
    public String toString() {
        return "GetNotifyRemindResponseData [list="+list+",toString()="+super.toString()+" ]";
    }
}
