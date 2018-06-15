package com.qding.api.call.app.qding.v1_3_2.struct.user.request;

import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.api.smart.validate.rule.RoomValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/20.
 */
@Validate
public class PapersBindRoomRequest  extends BaseRequest {

    private static final long serialVersionUID = 1642375736533781142L;

    /**
     * 绑定ID 用于修改操作时候
     */
    private String bindId;

    /**
     * 会员ID
     */
    @MemberValidate
    private String memberId;

    /**
     * 房间号ID
     */
    @RoomValidate
    private String roomId;

    /**
     * 身份
     */
    @NotNullValidate
    private Integer hkIndentity;

    /**
     * 房屋证件
     */
    @NotNullValidate
    private String roomPapers;

    /**
     * 身份证正面
     */
    @NotNullValidate
    private String frontIdentityCard;

    /**
     * 身份证背面
     */
    @NotNullValidate
    private String reverseIdentityCard;

    public String getBindId() {
        return bindId;
    }

    public void setBindId(String bindId) {
        this.bindId = bindId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Integer getHkIndentity() {
        return hkIndentity;
    }

    public void setHkIndentity(Integer hkIndentity) {
        this.hkIndentity = hkIndentity;
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


    @Override
    public String toString() {
        return "PapersBindRoomRequest [bindId="+bindId+",memberId=" + memberId
                + ", roomId="+roomId+",hkIndentity="+hkIndentity+",roomPapers="+roomPapers+"," +
                "frontIdentityCard="+frontIdentityCard+",reverseIdentityCard="+reverseIdentityCard+",toString()=" + super.toString() + "]";
    }
}
