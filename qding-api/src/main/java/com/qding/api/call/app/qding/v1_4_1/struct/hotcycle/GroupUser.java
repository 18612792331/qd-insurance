package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle;


import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_2_0.struct.hotcycle.UserInfo;

import java.io.Serializable;

/**
 * Created by qd on 2015/10/27.
 */
public class GroupUser extends UserInfo implements Serializable {

    private static final long serialVersionUID = 1440093779936971613L;

    @ExplainAnnotation(explain = "群聊人员类型",desc = "1:普通用户,2:管理员(2.6活动群无此角色),3:群主")
    private Integer gcMemberType ;

    public Integer getGcMemberType() {
        return gcMemberType;
    }

    public void setGcMemberType(Integer gcMemberType) {
        this.gcMemberType = gcMemberType;
    }
}
