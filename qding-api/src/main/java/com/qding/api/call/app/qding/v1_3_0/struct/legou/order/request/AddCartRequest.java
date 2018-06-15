package com.qding.api.call.app.qding.v1_3_0.struct.legou.order.request;

import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.api.smart.validate.rule.ProjectValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.MinValueValidate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 添加到购物车					
 * @author lichao
 *
 */
@Validate
public class AddCartRequest extends BaseRequest{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3603255876585844128L;

	@MemberValidate
	private String memberId;
	
	@ProjectValidate
	private String projectId;
	
	@NotNullValidate
	private String skuId;
	
	@MinValueValidate(value="1")
	private Integer count;
	
	private String hkMid;
	
	public AddCartRequest() {

	}

	public AddCartRequest(String memberId, String projectId, String skuId,
			Integer count) {
		super();
		this.memberId = memberId;
		this.projectId = projectId;
		this.skuId = skuId;
		this.count = count;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getHkMid() {
		return hkMid;
	}

	public void setHkMid(String hkMid) {
		this.hkMid = hkMid;
	}

	@Override
	public String toString() {
		return "AddCartRequest [memberId=" + memberId + ", projectId="
				+ projectId + ", skuId=" + skuId + ", count=" + count
				+ ", toString()=" + super.toString() + "]";
	}
	
}
