package com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.GoodsDetail;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2016/6/16.
 */
public class GetGoodsDetailsResponseData extends ResponseData {

    private static final long serialVersionUID = 279943727424149463L;


    @ExplainAnnotation(explain = "商品详情信息")
    private GoodsDetail entity;

    @ExplainAnnotation(explain = "购物车数量")
    private Integer cartCount = 0;


    public GoodsDetail getEntity() {
        return entity;
    }


    public void setEntity(GoodsDetail entity) {
        this.entity = entity;
    }


    public GetGoodsDetailsResponseData(){

    }

    public Integer getCartCount() {
        return cartCount;
    }

    public void setCartCount(Integer cartCount) {
        this.cartCount = cartCount;
    }

    @Override
    public String toString() {
        return "GetGoodsDetailsResponseData{" +
                "entity=" + entity +
                ", cartCount=" + cartCount +
                '}';
    }
}
