package com.qding.api.call.app.qding.v1_4_1.struct.notify.response.data;

import com.qding.api.call.app.qding.v1_4_1.struct.notify.SysNotify;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/10/29.
 */
public class GeSysNotifiesResponseData extends ResponseData {

    private static final long serialVersionUID = -958399812872592336L;

    private List<SysNotify> list;

    private int recordCount;

    private int totalCount;

    public List<SysNotify> getList() {
        return list;
    }

    public void setList(List<SysNotify> list) {
        this.list = list;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }


    @Override
    public String toString() {
        return "GeSysNotifiesResponseData [list="+list+",recordCount="+recordCount+",totalCount="+totalCount+",toString()="+super.toString()+" ]";
    }
}
