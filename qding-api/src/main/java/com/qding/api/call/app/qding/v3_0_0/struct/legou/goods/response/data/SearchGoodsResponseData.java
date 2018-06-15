package com.qding.api.call.app.qding.v3_0_0.struct.legou.goods.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_0_0.struct.legou.goods.SearchGoodBean;
import com.qding.api.call.app.qding.v3_0_0.struct.legou.goods.ServiceGoodBean;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2015/11/4.
 */
public class SearchGoodsResponseData extends ResponseData {

    private static final long serialVersionUID = 5924774001093082799L;

    @ExplainAnnotation(explain = "匹配的商品列表")
    private SearchGoodBean goods;

    @ExplainAnnotation(explain = "匹配的业态列表")
    private ServiceGoodBean services;

    public SearchGoodBean getGoods() {
        return goods;
    }

    public void setGoods(SearchGoodBean goods) {
        this.goods = goods;
    }

    public ServiceGoodBean getServices() {
        return services;
    }

    public void setServices(ServiceGoodBean services) {
        this.services = services;
    }

    public SearchGoodsResponseData(){

    }

    @Override
    public String toString() {
        return "SearchGoodsResponseData [order=" + goods
                + ", services=" + services
                + ", toString()="+ super.toString() + "]";
    }

}
