package com.qding.api.call.app.qding.v3_3_0.struct.legou.order.response.data;

import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.order.Cart;
import com.qding.api.call.app.qding.v3_1_0.struct.legou.order.response.data.GetCartResponseData;

/**
 * Created by qd on 2017/5/12.
 */
public class GetCartWithGrouponResponseData extends GetCartResponseData {

    private static final long serialVersionUID = 9197101907071029988L;

    @ExplainAnnotation(explain = "无效团购")
    private List<Cart> grouponList;

	public List<Cart> getGrouponList() {
		return grouponList;
	}

	public void setGrouponList(List<Cart> grouponList) {
		this.grouponList = grouponList;
	}

	@Override
	public String toString() {
		return "GetCartWithGrouponResponseData [grouponList=" + grouponList
				+ "]";
	}
    
    
    

     
}
