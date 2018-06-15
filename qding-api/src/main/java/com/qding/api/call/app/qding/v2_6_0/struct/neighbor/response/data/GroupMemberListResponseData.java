package com.qding.api.call.app.qding.v2_6_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.GroupUser;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.GroupUserList;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.JoinGroupApplyUserList;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/10/27.
 */
public class GroupMemberListResponseData extends ResponseData {

    private static final long serialVersionUID = -1899547498703298246L;

    private Integer totalCount;

    private List<GroupUser> list;


    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<GroupUser> getList() {
        return list;
    }

    public void setList(List<GroupUser> list) {
        this.list = list;
    }


    @Override
    public String toString() {
        return "GroupMemberListResponseData{" +
                "totalCount=" + totalCount +
                ", list=" + list +
                '}';
    }
}
