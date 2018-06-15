package com.qding.api.call.app.qding.v3_3_0.struct.user.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_3_0.struct.user.AutoPayProject;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/9/7.
 */
public class GetAutoPayPropertyFeeProjectsByMidResponseData extends ResponseData {

    private static final long serialVersionUID = 4249555711791674722L;

    @ExplainAnnotation (explain = "物业费代扣社区列表")
    private List<AutoPayProject> list;

    public List<AutoPayProject> getList() {
        return list;
    }

    public void setList(List<AutoPayProject> list) {
        this.list = list;
    }


    @Override
    public String toString() {
        return "GetAutoPayPropertyFeeProjectsByMidResponseData{" +
                "list=" + list +
                '}';
    }
}
