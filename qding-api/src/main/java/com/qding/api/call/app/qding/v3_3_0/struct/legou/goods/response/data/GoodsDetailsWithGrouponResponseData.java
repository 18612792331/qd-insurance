package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.response.data.GetGoodsDetailsResponseData;
import com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.SkuGrouponInfo;

/**
 * Created by qd on 2016/6/16.
 */
public class GoodsDetailsWithGrouponResponseData extends GetGoodsDetailsResponseData {

    private static final long serialVersionUID = 279943727424149463L;


    @ExplainAnnotation(explain = "团购信息 3.3新增")
    private SkuGrouponInfo groupon;
    
    @ExplainAnnotation(explain = "商品缩略图")
    private String skuMinPic;

    public GoodsDetailsWithGrouponResponseData(){

    }

	public SkuGrouponInfo getGroupon() {
		return groupon;
	}

	public void setGroupon(SkuGrouponInfo groupon) {
		this.groupon = groupon;
	}
	

	public String getSkuMinPic() {
		return skuMinPic;
	}

	public void setSkuMinPic(String skuMinPic) {
		this.skuMinPic = skuMinPic;
	}

	@Override
	public String toString() {
		return "GoodsDetailsWithGrouponResponseData [groupon=" + groupon
				+ ", skuMinPic=" + skuMinPic + "]";
	}

	
    
	
    
     

     
}
