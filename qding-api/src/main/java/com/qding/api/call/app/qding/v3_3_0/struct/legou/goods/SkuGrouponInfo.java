package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods;

import java.io.Serializable;

import com.qding.api.annotation.ExplainAnnotation;

/**
 * 阶梯成团信息
 * @author Administrator
 *
 */
@ExplainAnnotation(explain = "团购阶梯信息")
public class SkuGrouponInfo implements Serializable {

	private static final long serialVersionUID = -3048088629820103021L;
	
	@ExplainAnnotation(explain = "1.未开团   2.团购进行中   3.已成团   4.团购失败")
	private Integer status;
	
	@ExplainAnnotation(explain = "团购进行中商品详情页显示文案")
	private String tipInfo="目前该商品正参加阶梯团购、查看活动";
	
	@ExplainAnnotation(explain = "商品详情的团购点击跳转")
	private String skipModel;
	
	public String getTipInfo() {
		return tipInfo;
	}

	public void setTipInfo(String tipInfo) {
		this.tipInfo = tipInfo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


	public String getSkipModel() {
		return skipModel;
	}

	public void setSkipModel(String skipModel) {
		this.skipModel = skipModel;
	}

	
	
	
	
	
	
     
}
