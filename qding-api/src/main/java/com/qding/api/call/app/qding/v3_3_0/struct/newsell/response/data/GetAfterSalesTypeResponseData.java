package com.qding.api.call.app.qding.v3_3_0.struct.newsell.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.newsell.AfterSalesTypeDto;
import com.qding.api.struct.ResponseData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GetAfterSalesTypeResponseData  extends ResponseData {

    private static final long serialVersionUID = 8743942351511084987L;

    @Getter
    @Setter
    @ExplainAnnotation(explain = "售后类型列表")
    private List<AfterSalesTypeDto> list;


    @Override
    public String toString() {
        return "GetAfterSalesTypeResponseData{" +
                "list=" + list +
                '}';
    }
}
