package com.qding.api.call.app.qding.v1_3_0.struct.coupon.request;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.oder.dto.SubOrderDto;

import java.util.List;

/**
 * 获取用户某个项目下某个业态的所有可用优惠券(不包含未生效的)		
 * @author lichao
 *
 */
@Validate
public class GetOrderCouponsRequest extends BaseRequest{


	private static final long serialVersionUID = -9162206036566619727L;
 
	@NotNullValidate
	@ExplainAnnotation (explain = "当前会员ID")
	private String memberId;
	
	@NotNullValidate
	@ExplainAnnotation (explain = "业态号")
	private String productNo;
	
	@NotNullValidate
	@ExplainAnnotation (explain = "社区ID")
	private Long projectId;

	@ExplainAnnotation (explain = "子订单列表")
	private List<SubOrderDto> subOrderlist;

	private String orderRealPrice;



	public GetOrderCouponsRequest() {

	}

	public List<SubOrderDto> getSubOrderlist() {
		return subOrderlist;
	}

	public void setSubOrderlist(List<SubOrderDto> subOrderlist) {
		this.subOrderlist = subOrderlist;
	}

	public String getOrderRealPrice() {
		return orderRealPrice;
	}

	public void setOrderRealPrice(String orderRealPrice) {
		this.orderRealPrice = orderRealPrice;
	}

	public Long getProjectId() {
		return projectId;
	}



	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}
}
