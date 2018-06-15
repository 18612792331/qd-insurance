package com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2015/12/29.
 */
public class AssociateKeyWordResponseData extends ResponseData {

    private static final long serialVersionUID = 7446131998502701500L;

    @ExplainAnnotation(explain = "联系关键词列表")
    private List<String> list;

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "AssociateKeyWordResponseData{" +
                "list=" + list +
                '}';
    }
}
