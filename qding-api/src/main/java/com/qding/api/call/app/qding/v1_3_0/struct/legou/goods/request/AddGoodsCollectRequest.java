package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 收藏商品
 * @author jiawenzheng 
 *
 */
@Validate
public class AddGoodsCollectRequest  extends BaseRequest {


	private static final long serialVersionUID = 1398368185937513269L;
	
	/**
     * 会员ID
     */
	@NotNullValidate
	private String memberId; //mid
	
	/**
     *货品ID
     */
	@NotNullValidate
	private String skuId;
	
	/**
     * 社区ID
     */
	@NotNullValidate
	private String projectId;
	

	public String getSkuId() {
		return skuId;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}



	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public AddGoodsCollectRequest(){
		
	}

    @Override
    public String toString() {
        return "AddGoodsCollectRequest [memberId=" + memberId
                + ", projectId=" + projectId + ",skuId="+skuId+" ,toString()=" + super.toString() + "]";
    }

}
