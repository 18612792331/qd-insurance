package com.qding.api.call.app.qding.v2_0_0.struct.familypayment.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.useraccount.PaymentInfo;
import com.qding.api.call.app.qding.v2_0_0.struct.useraccount.TicketInfo;
import com.qding.api.call.app.qding.v2_0_0.struct.useraccount.UserFamilyRelationInfo;
import com.qding.api.struct.ResponseData;

import java.util.List;

public class GetFamilyRelationListResponseData extends ResponseData {

    private static final long serialVersionUID = 8505693784159846717L;

    @ExplainAnnotation(explain = "亲情支付-身份类型",desc = "1:开通者; 2:使用者")
    private String type;

    @ExplainAnnotation(explain = "亲情支付关系列表")
    private List<UserFamilyRelationInfo> relationList;

    public List<UserFamilyRelationInfo> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<UserFamilyRelationInfo> relationList) {
        this.relationList = relationList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public GetFamilyRelationListResponseData() {
    }

    public GetFamilyRelationListResponseData(String type, List<UserFamilyRelationInfo> relationList) {
        this.type = type;
        this.relationList = relationList;
    }

    @Override
    public String toString() {
        return "GetFamilyRelationListResponseData{" +
                "type='" + type + '\'' +
                ", relationList=" + relationList +
                '}';
    }
}
