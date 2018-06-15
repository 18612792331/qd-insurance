package com.qding.api.call.app.qding.v1_3_2.struct.user;

import com.qding.api.call.app.qding.v1_3_0.struct.brick.Room;

import java.io.Serializable;

/**
 * Created by qd on 2015/10/20.
 */
public class PapersBindRoom implements Serializable {

    private static final long serialVersionUID = -6977444224684300511L;

    /**
     * 绑定ID
     */
    private String bindId;

    /**
     * 房屋信息对象
     */
    private Room room;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 房屋证件
     */
    private String roomPapers;

    /**
     * 身份证正面
     */
    private String frontIdentityCard;

    /**
     * 身份证背面
     */
    private String reverseIdentityCard;

    /**
     * 审核状态 0:待审核  1：审核通过  2：审核不通过
     */
    private Integer auditStatus;

    /**
     * 申请身份
     */
    private Integer hkIndentity;

    /**
     * 审核失败信息
     */
    private AuditFailInfo auditFailInfo = null;

    public PapersBindRoom( String bindId,Room room, String mobile, String roomPapers, String frontIdentityCard, String reverseIdentityCard, Integer auditStatus, AuditFailInfo auditFailInfo, Integer hkIndentity) {
        this.bindId = bindId;
        this.room = room;
        this.mobile = mobile;
        this.roomPapers = roomPapers;
        this.frontIdentityCard = frontIdentityCard;
        this.reverseIdentityCard = reverseIdentityCard;
        this.auditStatus = auditStatus;
        this.auditFailInfo = auditFailInfo;
        this.hkIndentity = hkIndentity;
    }

    public AuditFailInfo getAuditFailInfo() {
        return auditFailInfo;
    }

    public void setAuditFailInfo(AuditFailInfo auditFailInfo) {
        this.auditFailInfo = auditFailInfo;
    }

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRoomPapers() {
        return roomPapers;
    }

    public void setRoomPapers(String roomPapers) {
        this.roomPapers = roomPapers;
    }

    public String getFrontIdentityCard() {
        return frontIdentityCard;
    }

    public void setFrontIdentityCard(String frontIdentityCard) {
        this.frontIdentityCard = frontIdentityCard;
    }

    public String getReverseIdentityCard() {
        return reverseIdentityCard;
    }

    public void setReverseIdentityCard(String reverseIdentityCard) {
        this.reverseIdentityCard = reverseIdentityCard;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getHkIndentity() {
        return hkIndentity;
    }

    public void setHkIndentity(Integer hkIndentity) {
        this.hkIndentity = hkIndentity;
    }
}
