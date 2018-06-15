package com.qding.api.call.app.qding.v3_3_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_5_0.struct.legou.order.response.data.GetOrderResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.SkuGrouponInfo;

/**
 * 
 * 带阶梯成团信息
 * @author Administrator
 *
 */
public class GetOrderWithGrouponResponseData extends GetOrderResponseData {

    private static final long serialVersionUID = -5384312937490257040L;

    @ExplainAnnotation(explain = "阶梯团购信息")
    private SkuGrouponInfo groupon;

	public SkuGrouponInfo getGroupon() {
		return groupon;
	}

	public void setGroupon(SkuGrouponInfo groupon) {
		this.groupon = groupon;
	}

	@Override
	public String toString() {
		return "GetOrderWithGrouponResponseData [groupon=" + groupon + "]";
	}

    
    
    
    
}
