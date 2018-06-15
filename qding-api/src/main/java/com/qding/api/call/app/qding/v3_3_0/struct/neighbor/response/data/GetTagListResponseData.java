package com.qding.api.call.app.qding.v3_3_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.neighbor.index.BriefInteractionTagInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/3/4.
 */
public class GetTagListResponseData extends ResponseData{

    private static final long serialVersionUID = -6726551281024575314L;

    @ExplainAnnotation (explain = "标签列表")
    private List<BriefInteractionTagInfo> list;

    public List<BriefInteractionTagInfo> getList() {
        return list;
    }

    public void setList(List<BriefInteractionTagInfo> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetTagListResponseData{" +
                "list=" + list +
                '}';
    }
}
