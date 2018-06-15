package com.qding.api.call.app.qding.v1_4_1.struct.housekeeper.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by qd on 2015/10/27.
 */
@Validate
public class HousekeeperBindRoomQuickmarkRequest extends BaseRequest {

    private static final long serialVersionUID = -845362133028300479L;

    /**
     * 房间ID
     */
    @NotNullValidate
    private String roomId;

    /**
     * 身份
     */
    @NotNullValidate
    private Integer hkIndentity;

    /**
     * 创建者Id(管家登陆者ID)
     */
    @NotNullValidate
    private String createAt;


    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
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

    @Override
    public String toString() {
        return "HousekeeperBindRoomQuickmarkRequest [createAt="+createAt+",roomId=" + roomId +",hkIndentity="+hkIndentity+"," +
                "super.toString() ]";
    }

}
