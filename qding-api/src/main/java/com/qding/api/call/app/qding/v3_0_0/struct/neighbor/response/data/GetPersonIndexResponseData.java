package com.qding.api.call.app.qding.v3_0_0.struct.neighbor.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v1_3_0.struct.user.AccountMember;
import com.qding.api.call.app.qding.v2_4_0.struct.legou.goods.Supplier;
import com.qding.api.struct.ResponseData;

/**
 * Created by qd on 2017/2/24.
 */
public class GetPersonIndexResponseData extends ResponseData {

	private static final long serialVersionUID = 5707334558376930938L;


	@ExplainAnnotation (explain = "动态数")
	private Integer dynamicCount;

	@ExplainAnnotation (explain = "用户信息")
	private AccountMember entity;

	@ExplainAnnotation (explain = "1可以发言  0禁言")
	private Integer isSay = 1;

	@ExplainAnnotation (explain = "会员邻居状态：是否冻结 1是 0否")
	private Integer isFreeze = 0;

	public Integer getDynamicCount() {
		return dynamicCount;
	}

	public void setDynamicCount(Integer dynamicCount) {
		this.dynamicCount = dynamicCount;
	}

	public AccountMember getEntity() {
		return entity;
	}

	public void setEntity(AccountMember entity) {
		this.entity = entity;
	}

	public Integer getIsSay() {
		return isSay;
	}

	public void setIsSay(Integer isSay) {
		this.isSay = isSay;
	}

	public Integer getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(Integer isFreeze) {
		this.isFreeze = isFreeze;
	}

	@Override
	public String toString() {
		return "GetPersonIndexResponseData{" +
				"dynamicCount=" + dynamicCount +
				", entity=" + entity +
				", isSay=" + isSay +
				", isFreeze=" + isFreeze +
				'}';
	}
}
