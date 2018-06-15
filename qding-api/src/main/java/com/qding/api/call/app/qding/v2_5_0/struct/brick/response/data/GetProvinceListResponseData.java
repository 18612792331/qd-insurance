package com.qding.api.call.app.qding.v2_5_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_5_0.struct.brick.Province;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/8/22.
 */
public class GetProvinceListResponseData extends ResponseData {

    private static final long serialVersionUID = 6437749313487237627L;

    @ExplainAnnotation (explain = "省份列表")
    private List<Province> list;

    public List<Province> getList() {
        return list;
    }

    public void setList(List<Province> list) {
        this.list = list;
    }
}
