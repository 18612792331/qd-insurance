package com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_4_0.struct.housekeeper.PropertyChargesOrder;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/9/18.
 */
public class PropertyChargesOrderResponseData extends ResponseData {


    private static final long serialVersionUID = 6600581732611054543L;

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
        return "PropertyChargesOrderResponseData [entity="+entity+",toString()=" + super.toString()
                + "]";
    }
}
