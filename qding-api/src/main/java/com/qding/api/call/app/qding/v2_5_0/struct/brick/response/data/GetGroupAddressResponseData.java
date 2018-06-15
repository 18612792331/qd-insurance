package com.qding.api.call.app.qding.v2_5_0.struct.brick.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_5_0.struct.brick.GroupAddress;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2016/11/22.
 */
public class GetGroupAddressResponseData extends ResponseData {

    private static final long serialVersionUID = -598202159884900510L;

    @ExplainAnnotation (explain = "组团地址列表")
    private List<GroupAddress> list;

    public List<GroupAddress> getList() {
        return list;
    }

    public void setList(List<GroupAddress> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "GetGroupAddressResponseData{" +
                "list=" + list +
                '}';
    }
}
