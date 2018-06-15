package com.qding.api.call.app.qding.v3_1_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.Cart;
import com.qding.api.struct.ResponseData;

import java.util.List;

/**
 * Created by qd on 2017/5/12.
 */
public class GetCartResponseData extends ResponseData {

    private static final long serialVersionUID = 9197101907071029988L;

    @ExplainAnnotation(explain = "每日鲜购物车列表")
    private List<Cart> mrxList;

    @ExplainAnnotation(explain = "购物有效车列表")
    private List<Cart> effectiveList;

    @ExplainAnnotation(explain = "购物无效车列表")
    private List<Cart> invalidList;
    
    public List<Cart> getMrxList() {
        return mrxList;
    }

    public void setMrxList(List<Cart> mrxList) {
        this.mrxList = mrxList;
    }

    public List<Cart> getEffectiveList() {
        return effectiveList;
    }

    public void setEffectiveList(List<Cart> effectiveList) {
        this.effectiveList = effectiveList;
    }

    public List<Cart> getInvalidList() {
        return invalidList;
    }

    public void setInvalidList(List<Cart> invalidList) {
        this.invalidList = invalidList;
    }
    

	@Override
    public String toString() {
        return "GetCartResponseData{" +
                "mrxList=" + mrxList +
                ", effectiveList=" + effectiveList +
                ", invalidList=" + invalidList +
                '}';
    }
}
