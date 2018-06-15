package com.qding.api.call.app.qding.v1_4_1.struct.notify.response.data;

import com.qding.api.call.app.qding.v1_4_1.struct.notify.BusinessNotify;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/10/29.
 */
public class GetBusinessNotifiesResponseData extends ResponseData {

    private static final long serialVersionUID = -6924659553020365527L;

    private List<BusinessNotify> list;

    private Integer totalCount;

    private Integer recordCount;

    public List<BusinessNotify> getList() {
        return list;
    }

    public void setList(List<BusinessNotify> list) {
        this.list = list;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(Integer recordCount) {
        this.recordCount = recordCount;
    }


    @Override
    public String toString() {
        return "GetBusinessNotifiesResponseData [list="+list+",totalCount="+totalCount+",recordCount="+recordCount+",toString()="+super.toString()+" ]";
    }

}
