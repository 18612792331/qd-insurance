package com.qding.api.call.app.qding.v1_3_0.struct.wallet.request;

import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

public class AccountWalletHomePageRequest extends BaseRequest{

	private static final long serialVersionUID = 8977976327891756061L;
	
	public AccountWalletHomePageRequest (){
		
	}
	
	/**
	 * 会员ID
	 */
	@NotNullValidate
	private String memberId;
	
	/**
	 * 社区ID
	 */
	@NotNullValidate
	private String projectId;


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
     * @return Returns the projectId.
     */
    public String getProjectId() {
        return projectId;
    }


    /**
     * @param projectId The projectId to set.
     */
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }
    
    
    
    @Override
    public String toString() {
        return "AccountWalletHomePageRequest [memberId=" + memberId
                + ", projectId=" + projectId + ", toString()=" + super.toString() + "]";
    }
    
	
}
