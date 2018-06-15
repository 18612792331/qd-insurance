package com.qding.api.call.app.qding.v3_1_0.struct.entrancecard.response.data;

import java.util.List;

import com.qding.api.struct.ResponseData;

public class ActiveResponseData extends ResponseData {

    private static final long serialVersionUID = -5337441266330777488L;

    private List<String> activedList;

    public List<String> getActivedList() {
        return activedList;
    }

    public void setActivedList(List<String> activedList) {
        this.activedList = activedList;
    }

}
