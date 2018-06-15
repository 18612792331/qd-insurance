package com.qding.api.call.app.qding.v1_3_0.struct.user.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

public class DelReceiveMessageRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4457126576488944323L;
	
	
	public DelReceiveMessageRequest (){
		
	}
	
	/**
	 * 地址ID 主键 
	 */
	@NotNullValidate
	private String id;
	
	/**
	 * 用户ID
	 */
	@NotNullValidate
	private String memberId;
	
	


	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
    @Override
    public String toString() {
        return "DelReceiveMessageRequest [id="+id+",memberId=" + memberId+", toString()=" + super.toString() + "]";
    }
	


}
