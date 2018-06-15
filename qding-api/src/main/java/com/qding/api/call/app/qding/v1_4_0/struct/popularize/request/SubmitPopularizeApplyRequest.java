package com.qding.api.call.app.qding.v1_4_0.struct.popularize.request;

import com.qding.api.smart.validate.rule.FixLengthValidate;
import com.qding.api.smart.validate.rule.FullLengthValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

/**
 * Created by Administrator on 2015/7/28.
 */
@Validate
public class SubmitPopularizeApplyRequest extends BaseRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7773260293941629626L;
	
	/**
	 * 用户ID
	 */
	@NotNullValidate
	private String memberId;
	
	/**
	 * 用户名称
	 */
	@NotNullValidate
	private String userName;
	
	/**
	 * 身份证号
	 */
	@NotNullValidate
	@FullLengthValidate(length=18,name="身份证")
	private String identityCard;
	
	/**
	 * 开卡银行
	 */
	@NotNullValidate
	private String bankId;
	
	/**
	 * 银行卡号
	 */
	@NotNullValidate
	@FixLengthValidate(min=16, max=19, name="银行卡号")
	private String bankCardNum;

	/**
	 * 社区ID
	 */
	@NotNullValidate
	private String projectId;

	/**
	 * 开户行
	 */
	@NotNullValidate
	private String bankBranch;

	public String getBankBranch() {
		return bankBranch;
	}

	public void setBankBranch(String bankBranch) {
		this.bankBranch = bankBranch;
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

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankCardNum() {
		return bankCardNum;
	}

	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}

	public SubmitPopularizeApplyRequest(String memberId, String userName,
										String identityCard, String bankId, String bankCardNum,String bankBranch) {
		super();
		this.memberId = memberId;
		this.userName = userName;
		this.identityCard = identityCard;
		this.bankId = bankId;
		this.bankCardNum = bankCardNum;
		this.bankBranch =bankBranch;
	}
	
	
	
	public String getUserName() {
		return userName;
	}

	public SubmitPopularizeApplyRequest(){
		
	}
	
}
