package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle;

import java.io.Serializable;
import java.util.List;

/**
 * Created by qd on 2015/10/29.
 */
public class GroupApply  implements Serializable {

    private static final long serialVersionUID = -8330843456525109489L;

    private String gcRoomId;

    private String name;

    private String headUrl;

    private String remark;

    private List<JoinGroupApplyUser>  userList;

    public String getGcRoomId() {
        return gcRoomId;
    }

    public void setGcRoomId(String gcRoomId) {
        this.gcRoomId = gcRoomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public List<JoinGroupApplyUser> getUserList() {
        return userList;
    }

    public void setUserList(List<JoinGroupApplyUser> userList) {
        this.userList = userList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
