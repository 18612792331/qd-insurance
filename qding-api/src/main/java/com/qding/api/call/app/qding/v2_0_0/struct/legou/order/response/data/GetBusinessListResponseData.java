package com.qding.api.call.app.qding.v2_0_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.order.BusinessBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/1/4.
 */
public class GetBusinessListResponseData extends ResponseData {

    private static final long serialVersionUID = 4824598585457477136L;

    @ExplainAnnotation(explain = "业态列表")
    private List<BusinessBean> list;

    public List<BusinessBean> getList() {
        return list;
    }

    public void setList(List<BusinessBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetBusinessListResponseData{" +
                "list=" + list +
                '}';
    }
}
