package com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

public class IsNewCardResponseData extends ResponseData {

    private static final long serialVersionUID = -5337441266330777488L;

    @ExplainAnnotation(explain = "是否新卡，1-是，0-否")
    private Integer isNew;

    public Integer getIsNew() {
        return isNew;
    }
    
    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

}
