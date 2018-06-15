package com.qding.api.call.app.qding.v1_4_1.struct.hotcycle;

import com.qding.api.annotation.ExplainAnnotation;

/**
 * Created by qd on 2015/11/5.
 */
public class UserGroup extends Group {

    /**
     * 群聊人员类型(1普通用户2管理员3群主)
     */
    @ExplainAnnotation (explain = "群聊人员类型",desc = "1:普通用户,2:管理员(2.6活动群无此角色),3:群主")
    private Integer gcMemberType ;

    public Integer getGcMemberType() {
        return gcMemberType;
    }

    public void setGcMemberType(Integer gcMemberType) {
        this.gcMemberType = gcMemberType;
    }
}
