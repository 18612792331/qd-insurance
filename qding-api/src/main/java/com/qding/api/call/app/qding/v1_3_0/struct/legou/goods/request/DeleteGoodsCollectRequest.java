package com.qding.api.call.app.qding.v1_3_0.struct.legou.goods.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * 删除商品
 * @author jiawenzheng 
 *
 */
@Validate
public class DeleteGoodsCollectRequest  extends BaseRequest {

	private static final long serialVersionUID = 1431191661519585589L;
	
	/**
     * 会员ID
     */
	@NotNullValidate
	private String memberId;
	
	/**
     * 货品ID数组
     */
	@NotNullValidate
	private String[] skuIds;
	
	/**
     * 社区ID
     */
	@NotNullValidate
	private String projectId;
	
	public String getMemberId() {
		return memberId;
	}

	
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String[] getSkuIds() {
		return skuIds;
	}

	public void setSkuIds(String[] skuIds) {
		this.skuIds = skuIds;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	
	public DeleteGoodsCollectRequest(String memberId, String[] skuIds ,
			String projectId) {
		super();
		this.memberId = memberId;
		this.skuIds  = skuIds ;
		this.projectId = projectId;
	
	}
	
	
	public DeleteGoodsCollectRequest(){
		
	}
	
	
	@Override
    public String toString() {
        return "DeleteGoodsCollectRequest [memberId=" + memberId
                + ", projectId=" + projectId + ",skuIds="+skuIds+" ,toString()=" + super.toString() + "]";
    }

}
