package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.response.data;

import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.GroupApply;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/10/29.
 */
public class JoinGroupApplyListResponseData extends ResponseData {

    private static final long serialVersionUID = 1730639500976578783L;

    private List<GroupApply> list;

    public List<GroupApply> getList() {
        return list;
    }

    public void setList(List<GroupApply> list) {
        this.list = list;
    }
}
