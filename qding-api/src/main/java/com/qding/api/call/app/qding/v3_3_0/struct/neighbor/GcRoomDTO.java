package com.qding.api.call.app.qding.v3_3_0.struct.neighbor;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;


public class GcRoomDTO implements Serializable {

    @ExplainAnnotation (explain = "群ID")
    private String gcRoomId;

    @ExplainAnnotation (explain = "群头像")
    private String headUrl;

    @ExplainAnnotation (explain = "群名称")
    private String name;

    @ExplainAnnotation (explain = "群聊人员状态(0未加入群，1待审核2拒绝3已加入4退群)")
    private String gcMemberStatus;

    @ExplainAnnotation (explain = "群聊人员类型(0未加入群用户1普通用户2管理员3群主)")
    private String gcMemberType;

    @ExplainAnnotation (explain = "当前群组人数")
    private String gcMemberCount;

    public String getGcRoomId() {
        return gcRoomId;
    }

    public void setGcRoomId(String gcRoomId) {
        this.gcRoomId = gcRoomId;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGcMemberStatus() {
        return gcMemberStatus;
    }

    public void setGcMemberStatus(String gcMemberStatus) {
        this.gcMemberStatus = gcMemberStatus;
    }

    public String getGcMemberType() {
        return gcMemberType;
    }

    public void setGcMemberType(String gcMemberType) {
        this.gcMemberType = gcMemberType;
    }

    public String getGcMemberCount() {
        return   gcMemberCount;
    }

    public void setGcMemberCount(String gcMemberCount) {
        this.gcMemberCount = gcMemberCount;
    }


    @Override
    public String toString() {
        return "GcRoomDTO{" +
                "gcRoomId='" + gcRoomId + '\'' +
                ", headUrl='" + headUrl + '\'' +
                ", name='" + name + '\'' +
                ", gcMemberStatus='" + gcMemberStatus + '\'' +
                ", gcMemberType='" + gcMemberType + '\'' +
                ", gcMemberCount='" + gcMemberCount + '\'' +
                '}';
    }
}
