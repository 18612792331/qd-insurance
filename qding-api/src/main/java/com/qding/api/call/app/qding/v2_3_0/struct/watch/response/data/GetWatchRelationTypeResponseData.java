package com.qding.api.call.app.qding.v2_3_0.struct.watch.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_3_0.struct.watch.WatchRelationType;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetWatchRelationTypeResponseData extends ResponseData {

    @ExplainAnnotation(explain = "您与手表使用者的关系类型列表")
    private List<WatchRelationType> list;

    public List<WatchRelationType> getList() {
        return list;
    }

    public void setList(List<WatchRelationType> list) {
        this.list = list;
    }

    public GetWatchRelationTypeResponseData() {
    }

    @Override
    public String toString() {
        return "GetWatchRelationTypeResponseData{" +
                "list=" + list +
                '}';
    }
}

