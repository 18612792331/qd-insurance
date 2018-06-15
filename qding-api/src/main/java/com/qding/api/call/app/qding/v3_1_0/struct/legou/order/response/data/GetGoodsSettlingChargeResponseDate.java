package com.qding.api.call.app.qding.v3_1_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_1_0.struct.legou.order.SettlingChargeBean;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/5/16.
 */
public class GetGoodsSettlingChargeResponseDate extends ResponseData {

    private static final long serialVersionUID = -8240534052340222042L;

    @ExplainAnnotation(explain = "结算列表")
    private  List<SettlingChargeBean> list;

    public List<SettlingChargeBean> getList() {
        return list;
    }

    public void setList(List<SettlingChargeBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetGoodsSettlingChargeResponseDate{" +
                "list=" + list +
                '}';
    }
}
