package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.response.data;

import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.UserGroup;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/10/27.
 */
public class InterestGroupListResponseData extends ResponseData {

    private static final long serialVersionUID = -4941628047737990811L;

    private List<UserGroup> list;

    private int totalCount;

    public List<UserGroup> getList() {
        return list;
    }

    public void setList(List<UserGroup> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return "InterestGroupListResponseData [list=" + list +",totalCount="+totalCount+"," +
                " super.toString() ]";
    }
}
