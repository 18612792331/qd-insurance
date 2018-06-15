package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.GroupUserList;
import com.qding.api.call.app.qding.v1_4_1.struct.hotcycle.JoinGroupApplyUserList;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/10/27.
 */
public class GroupManagerListResponseData extends ResponseData {

    private static final long serialVersionUID = -1899547498703298246L;

    private Integer gcRoomType;

    private JoinGroupApplyUserList applyList;

    private GroupUserList memberList;

    @ExplainAnnotation(explain = "是否可以退出讨论组",desc = "0：不可以 | 1:可以")
    private Integer  canQuitGroup = 1;

    public Integer getCanQuitGroup() {
        return canQuitGroup;
    }

    public void setCanQuitGroup(Integer canQuitGroup) {
        this.canQuitGroup = canQuitGroup;
    }

    public JoinGroupApplyUserList getApplyList() {
        return applyList;
    }

    public void setApplyList(JoinGroupApplyUserList applyList) {
        this.applyList = applyList;
    }

    public GroupUserList getMemberList() {
        return memberList;
    }

    public void setMemberList(GroupUserList memberList) {
        this.memberList = memberList;
    }

    public Integer getGcRoomType() {
        return gcRoomType;
    }

    public void setGcRoomType(Integer gcRoomType) {
        this.gcRoomType = gcRoomType;
    }

    @Override
    public String toString() {
        return "GroupManagerListResponseData{" +
                "gcRoomType=" + gcRoomType +
                ", applyList=" + applyList +
                ", memberList=" + memberList +
                ", canQuitGroup=" + canQuitGroup +
                '}';
    }
}
