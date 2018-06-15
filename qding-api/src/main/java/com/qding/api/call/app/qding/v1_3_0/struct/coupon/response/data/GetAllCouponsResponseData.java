package com.qding.api.call.app.qding.v1_3_0.struct.coupon.response.data;

import java.util.List;

import com.qding.api.call.app.qding.v1_3_0.struct.coupon.Coupon;
import com.qding.api.struct.ResponseData;

/**
 * 查询我的优惠券(全部)					
 * @author lichao
 *
 */
public class GetAllCouponsResponseData extends ResponseData{

	/**
	 * 
	 */
	private static final long serialVersionUID = -224401878133063838L;
 
	private List<Coupon> list;
	
	public GetAllCouponsResponseData() {

	}

	public void setList(List<Coupon> list) {
		this.list = list;
	}
	
	public List<Coupon> getList() {
		return list;
	}
}
