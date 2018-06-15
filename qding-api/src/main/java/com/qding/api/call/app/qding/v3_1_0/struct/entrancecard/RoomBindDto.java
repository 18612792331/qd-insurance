package com.qding.api.call.app.qding.v3_1_0.struct.entrancecard;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;

@ExplainAnnotation(explain = "房屋绑定信息")
public class RoomBindDto implements Serializable {

    private static final long serialVersionUID = -7770487005752101081L;

    @ExplainAnnotation(explain = "房屋ID", desc = "")
    private String roomId;

    @ExplainAnnotation(explain = "房屋名称", desc = "")
    private String roomName;

    @ExplainAnnotation(explain = "是否已与该卡绑定", desc = "0-否，1-是")
    private Integer isBind;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Integer getIsBind() {
        return isBind;
    }

    public void setIsBind(Integer isBind) {
        this.isBind = isBind;
    }

}
