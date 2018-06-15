package com.qding.api.call.app.qding.v2_0_0.struct.familypayment.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.useraccount.UserFamilyRelationType;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetFamilyRelationTypeResponseData extends ResponseData {

    @ExplainAnnotation(explain = "亲情支付关系类型列表")
    private List<UserFamilyRelationType> list;

    public List<UserFamilyRelationType> getList() {
        return list;
    }

    public void setList(List<UserFamilyRelationType> list) {
        this.list = list;
    }

    public GetFamilyRelationTypeResponseData() {
    }

    public GetFamilyRelationTypeResponseData(String message) {

    }

    public GetFamilyRelationTypeResponseData(List<UserFamilyRelationType> list) {
        this.list = list;
    }
}

