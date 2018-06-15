package com.qding.api.call.app.qding.v3_0_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.user.CouponDTO;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/3/3.
 */
public class GetGiftDetailResponseData extends ResponseData {

    private static final long serialVersionUID = -2662200093208353971L;

    @ExplainAnnotation (explain = "超值优惠券列表")
    private List<CouponDTO> list;

    public List<CouponDTO> getList() {
        return list;
    }

    public void setList(List<CouponDTO> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetGiftDetailResponseData{" +
                "list=" + list +
                '}';
    }
}
