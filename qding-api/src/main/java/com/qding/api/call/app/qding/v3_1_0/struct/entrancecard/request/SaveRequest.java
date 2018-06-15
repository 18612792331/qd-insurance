package com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.request;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.RoomDto;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class SaveRequest extends BaseRequest {

    private static final long serialVersionUID = 5311022661356402582L;

    @ExplainAnnotation(explain = "门禁卡号")
    @NotNullValidate
    private String cardNo;
    
    @ExplainAnnotation(explain = "绑定房间列表")
    @NotNullValidate
    private List<RoomDto> roomList;

    
    public String getCardNo() {
        return cardNo;
    }

    
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    
    public List<RoomDto> getRoomList() {
        return roomList;
    }

    
    public void setRoomList(List<RoomDto> roomList) {
        this.roomList = roomList;
    }

    

}
