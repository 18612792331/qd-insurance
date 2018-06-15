package com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.PropertyChargesOrder;
import com.qding.api.call.app.qding.v3_0_0.struct.housekeeper.PropertyFeeOrder;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/22.
 */
public class FeeCreateOrderResponseData extends ResponseData {

    private static final long serialVersionUID = -5303257238950908256L;

    @ExplainAnnotation (explain = "物业缴费账单")
    private PropertyChargesOrder entity;

    public PropertyChargesOrder getEntity() {
        return entity;
    }

    public void setEntity(PropertyChargesOrder entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "FeeCreateOrderResponseData{" +
                "entity=" + entity +
                '}';
    }
}
