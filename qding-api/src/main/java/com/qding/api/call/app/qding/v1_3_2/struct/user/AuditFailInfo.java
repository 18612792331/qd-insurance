package com.qding.api.call.app.qding.v1_3_2.struct.user;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by qd on 2015/10/20.
 */
public class AuditFailInfo implements Serializable {

    private static final long serialVersionUID = 8498231613705363118L;

    private String roomPapersMsg ="ok";

    private String frontIdentityCardMsg ="ok";

    private String reverseIdentityCardMsg ="ok";

    public String getRoomPapersMsg() {
        return roomPapersMsg;
    }

    public void setRoomPapersMsg(String roomPapersMsg) {
        this.roomPapersMsg = roomPapersMsg;
    }

    public String getFrontIdentityCardMsg() {
        return frontIdentityCardMsg;
    }

    public void setFrontIdentityCardMsg(String frontIdentityCardMsg) {
        this.frontIdentityCardMsg = frontIdentityCardMsg;
    }

    public String getReverseIdentityCardMsg() {
        return reverseIdentityCardMsg;
    }

    public void setReverseIdentityCardMsg(String reverseIdentityCardMsg) {
        this.reverseIdentityCardMsg = reverseIdentityCardMsg;
    }
}
