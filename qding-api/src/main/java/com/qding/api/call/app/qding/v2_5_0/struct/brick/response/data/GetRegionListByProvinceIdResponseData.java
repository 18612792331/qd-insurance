package com.qding.api.call.app.qding.v2_5_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.brick.City;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/8/22.
 */
public class GetRegionListByProvinceIdResponseData extends ResponseData {

    private static final long serialVersionUID = 2101530699824642409L;

    @ExplainAnnotation (explain = "城市列表")
    private List<City> list;

    public List<City> getList() {
        return list;
    }

    public void setList(List<City> list) {
        this.list = list;
    }
}
