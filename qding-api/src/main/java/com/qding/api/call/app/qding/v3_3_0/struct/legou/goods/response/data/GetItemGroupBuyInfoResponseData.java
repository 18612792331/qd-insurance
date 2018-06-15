package com.qding.api.call.app.qding.v3_3_0.struct.legou.goods.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/3/3.
 */
public class GetItemGroupBuyInfoResponseData extends ResponseData {

	private static final long serialVersionUID = -3048088629820103021L;
	
	@ExplainAnnotation(explain="阶梯团购，团状态： 1.未开团   2.团购进行中   3.已成团   4.团购失败")
	private Integer status;
	
	@ExplainAnnotation(explain = "团购进行中商品详情页显示文案")
	private String tipInfo="阶梯团购 目前该商品正参加阶梯团购、查看活动";
	
	@ExplainAnnotation(explain = "参加阶梯团购少支付金额")
	private String lessPayMoney;

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

	public String getLessPayMoney() {
		return lessPayMoney;
	}

	public void setLessPayMoney(String lessPayMoney) {
		this.lessPayMoney = lessPayMoney;
	}

	@Override
	public String toString() {
		return "GetItemGroupBuyInfoResponseData "
				+ "[status=" + status
				+ ", tipInfo=" + tipInfo +
				", lessPayMoney=" + lessPayMoney
				+ "]";
	}
	
	
	
	
	
	
     
}
