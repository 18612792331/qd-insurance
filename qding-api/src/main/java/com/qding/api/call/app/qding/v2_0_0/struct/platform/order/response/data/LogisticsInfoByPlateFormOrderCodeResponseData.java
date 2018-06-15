package com.qding.api.call.app.qding.v2_0_0.struct.platform.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.platform.order.LogisticsForSubCode;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/9/15.
 */
public class LogisticsInfoByPlateFormOrderCodeResponseData extends ResponseData {


    private static final long serialVersionUID = 8056863282854556286L;


    @ExplainAnnotation(explain = "子订单分组显示物流信息")
    private List<LogisticsForSubCode> list;

    public List<LogisticsForSubCode> getList() {
        return list;
    }

    public void setList(List<LogisticsForSubCode> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "LogisticsInfoByPlateFormOrderCodeResponseData{" +
                "list=" + list +
                '}';
    }
}
