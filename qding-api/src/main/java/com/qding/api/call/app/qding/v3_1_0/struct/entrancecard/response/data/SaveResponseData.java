package com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.EntranceCardDto;
import com.qding.api.struct.ResponseData;

public class SaveResponseData extends ResponseData {

    private static final long serialVersionUID = 330762860313230709L;

    @ExplainAnnotation(explain = "门禁卡信息", desc = "")
    private EntranceCardDto card;

    
    public EntranceCardDto getCard() {
        return card;
    }

    public void setCard(EntranceCardDto card) {
        this.card = card;
    }
    
}
