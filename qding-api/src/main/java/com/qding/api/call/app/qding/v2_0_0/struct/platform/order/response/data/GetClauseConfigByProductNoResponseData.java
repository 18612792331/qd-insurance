package com.qding.api.call.app.qding.v2_0_0.struct.platform.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.ClauseConfig;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/8/22.
 */
public class GetClauseConfigByProductNoResponseData extends ResponseData {

    private static final long serialVersionUID = -8530662861278307863L;

    @ExplainAnnotation (explain = "条款列表")
    private List<ClauseConfig> list;

    public List<ClauseConfig> getList() {
        return list;
    }

    public void setList(List<ClauseConfig> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetClauseConfigByProductNoResponseData{" +
                "list=" + list +
                '}';
    }
}
