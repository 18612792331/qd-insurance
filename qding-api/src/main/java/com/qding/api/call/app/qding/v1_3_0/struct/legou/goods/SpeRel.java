package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods;

import com.qding.api.annotation.ExplainAnnotation;

import java.io.Serializable;

public class SpeRel implements Serializable{


	private static final long serialVersionUID = -7527420471632193550L;
	
	@ExplainAnnotation(explain = "货品ID")
	private String skuId;
	
	@ExplainAnnotation(explain = "规格属性名")
	private String[] spe;
	
	@ExplainAnnotation(explain = "规格属性值")
	private String[] speInfo;

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String[] getSpe() {
		return spe;
	}

	public void setSpe(String[] spe) {
		this.spe = spe;
	}

	public String[] getSpeInfo() {
		return speInfo;
	}

	public void setSpeInfo(String[] speInfo) {
		this.speInfo = speInfo;
	}

	

	
	

}
