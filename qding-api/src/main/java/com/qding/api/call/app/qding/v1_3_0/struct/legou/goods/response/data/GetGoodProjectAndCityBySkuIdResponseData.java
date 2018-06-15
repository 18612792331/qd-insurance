package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.response.data;

import com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.GoodProjectAndCity;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/9/1.
 */
public class GetGoodProjectAndCityBySkuIdResponseData   extends ResponseData {


    private static final long serialVersionUID = 8982369638444157825L;

    /**
     * 获取所属社区城市列表
     */
    private GoodProjectAndCity  entity;

    public GoodProjectAndCity getEntity() {
        return entity;
    }

    public void setEntity(GoodProjectAndCity entity) {
        this.entity = entity;
    }

    @Override
    public String toString() {
        return "GetGoodProjectAndCityBySkuIdResponseData [entity=" + entity
                + ", toString()="+ super.toString() + "]";
    }
}
