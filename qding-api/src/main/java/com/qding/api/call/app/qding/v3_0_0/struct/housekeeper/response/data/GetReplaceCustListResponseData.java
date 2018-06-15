package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.CustModel;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/3/22.
 */
public class GetReplaceCustListResponseData extends ResponseData {

    private static final long serialVersionUID = -2467210801516056611L;

    @ExplainAnnotation (explain = "代缴的客户列表")
    private List<CustModel> custModelList;

    public List<CustModel> getCustModelList() {
        return custModelList;
    }

    public void setCustModelList(List<CustModel> custModelList) {
        this.custModelList = custModelList;
    }

    @Override
    public String toString() {
        return "GetReplaceCustListResponseData{" +
                "custModelList=" + custModelList +
                '}';
    }
}
