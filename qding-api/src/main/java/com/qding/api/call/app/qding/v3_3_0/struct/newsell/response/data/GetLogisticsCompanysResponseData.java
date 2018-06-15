package com.qding.api.call.app.qding.v3_3_0.struct.newsell.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.newsell.CourierCompanyBean;
import com.qding.api.struct.ResponseData;
import com.qding.framework.common.api.struct.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class GetLogisticsCompanysResponseData extends ResponseData {

    private static final long serialVersionUID = -7750701584166518532L;


    @Getter
    @Setter
    @ExplainAnnotation(explain = "物流公司列表")
    private List<CourierCompanyBean> courierCompanyList;

    @Override
    public String toString() {
        return "GetLogisticsCompanysResponseData{" +
                "courierCompanyList=" + courierCompanyList +
                '}';
    }
}
