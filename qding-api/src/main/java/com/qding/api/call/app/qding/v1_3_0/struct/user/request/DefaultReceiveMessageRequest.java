package com.qding.api.call.app.qding.v1_3_0.struct.user.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class DefaultReceiveMessageRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8323073598993097906L;

	public DefaultReceiveMessageRequest(){
		
	}
	
	/**
	 * 会员ID
	 */
	private String memberId;
	
	/**
	 * 地址ID
	 */
	private String id;

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
        return "CheckMobileIsRegisterRequest [id="+id+",memberId=" + memberId+", toString()=" + super.toString() + "]";
    }
    
}
