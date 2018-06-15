package com.qding.api.call.app.qding.v2_5_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_5_0.struct.brick.District;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/8/22.
 */
public class GetDistrictListByRegionIdResponseData extends ResponseData {

    private static final long serialVersionUID = -2816420795588973014L;

    @ExplainAnnotation(explain = "区县列表")
    private List<District> list;

    public List<District> getList() {
        return list;
    }

    public void setList(List<District> list) {
        this.list = list;
    }
}
