package com.qding.api.call.app.qding.v3_3_0.struct.legou.order.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v3_2_0.struct.legou.order.response.data.ConfirmOrderResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.SkuGrouponInfo;

/**
 * Created by qd on 2017/5/12.
 */
public class ConfirmOrderWithGrouponResponseData extends ConfirmOrderResponseData {

    private static final long serialVersionUID = 8322369230818571755L;

    
    @ExplainAnnotation(explain = "团购信息")
    private SkuGrouponInfo groupon;
    

    public ConfirmOrderWithGrouponResponseData() {

    }


	public SkuGrouponInfo getGroupon() {
		return groupon;
	}


	public void setGroupon(SkuGrouponInfo groupon) {
		this.groupon = groupon;
	}


	@Override
	public String toString() {
		return "ConfirmOrderWithGrouponResponseData [groupon=" + groupon + "]";
	}
    
    
    
    
    
}
