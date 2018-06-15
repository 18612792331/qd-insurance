package com.qding.api.call.app.qding.v3_3_0.struct.newsell.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.newsell.AfterSalesDetailDto;
import com.qding.api.struct.ResponseData;
import com.qding.framework.common.api.struct.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GetAfterSalesDetailsByOrderCodeResponseData extends ResponseData {

    private static final long serialVersionUID = -589713936078249169L;


    @Getter
    @Setter
    @ExplainAnnotation(explain = "售后申请列表")
    private List<AfterSalesDetailDto> afterSalesDetailList;


    @Override
    public String toString() {
        return "GetAfterSalesDetailsByOrderCodeResponseData{" +
                "afterSalesDetailList=" + afterSalesDetailList +
                '}';
    }
}
