package com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_5_0.struct.housekeeper.PurposeDic;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/11/18.
 */
public class GetPurposeOfVisitDicsByProjectIdResponseData  extends ResponseData {

    private static final long serialVersionUID = -3360021753800176813L;

    @ExplainAnnotation (explain = "来访目的字典信息")
    private List<PurposeDic> list;

    public List<PurposeDic> getList() {
        return list;
    }

    public void setList(List<PurposeDic> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetPurposeOfVisitDicsByProjectIdResponseData{" +
                "list=" + list +
                '}';
    }
}
