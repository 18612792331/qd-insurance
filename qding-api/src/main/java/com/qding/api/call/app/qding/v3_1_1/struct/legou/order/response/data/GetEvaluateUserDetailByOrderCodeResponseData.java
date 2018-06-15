package com.qding.api.call.app.qding.v3_1_1.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_1_1.struct.legou.order.EvaluateSourcesBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/6/22.
 */
public class GetEvaluateUserDetailByOrderCodeResponseData extends ResponseData {

    private static final long serialVersionUID = 5502743723758199763L;

    @ExplainAnnotation (explain = "指定订单评价详情")
    private List<EvaluateSourcesBean> list;

    public List<EvaluateSourcesBean> getList() {
        return list;
    }

    public void setList(List<EvaluateSourcesBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetEvaluateUserDetailByOrderCodeResponseData{" +
                "list=" + list +
                '}';
    }
}
