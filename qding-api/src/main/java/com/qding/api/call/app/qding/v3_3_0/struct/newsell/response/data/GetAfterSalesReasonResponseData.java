package com.qding.api.call.app.qding.v3_3_0.struct.newsell.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.newsell.AfterSalesReasonDto;
import com.qding.api.struct.ResponseData;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GetAfterSalesReasonResponseData extends ResponseData {

    private static final long serialVersionUID = 8050876352297124792L;


    @Getter
    @Setter
    @ExplainAnnotation(explain = "原因列表")
    private List<AfterSalesReasonDto> list;


    @Override
    public String toString() {
        return "GetAfterSalesReasonResponseData{" +
                "list=" + list +
                '}';
    }
}
