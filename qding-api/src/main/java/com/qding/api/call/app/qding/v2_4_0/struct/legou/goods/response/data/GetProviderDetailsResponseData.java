package com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.Supplier;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/6/16.
 */
public class GetProviderDetailsResponseData extends ResponseData {

    private static final long serialVersionUID = 279943727424149463L;

    @ExplainAnnotation(explain = "供方信息")
    private Supplier entity;

    public Supplier getEntity() {
        return entity;
    }

    public void setEntity(Supplier entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetProviderDetailsResponseData{" +
                "entity=" + entity +
                '}';
    }
}
