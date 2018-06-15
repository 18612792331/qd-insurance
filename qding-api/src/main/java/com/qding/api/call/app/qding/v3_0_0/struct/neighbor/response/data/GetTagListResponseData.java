package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.Tag;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefInteractionTagInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/3/4.
 */
public class GetTagListResponseData extends ResponseData{

    private static final long serialVersionUID = -6726551281024575314L;

    @ExplainAnnotation (explain = "标签列表")
    private List<Tag> list;

    public List<Tag> getList() {
        return list;
    }

    public void setList(List<Tag> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetTagListResponseData{" +
                "list=" + list +
                '}';
    }
}
