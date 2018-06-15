package com.qding.api.call.app.qding.v1_3_0.struct.user.request;

import com.qding.api.smart.validate.rule.MemberValidate;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class AddSuggestRequest  extends BaseRequest  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5476555858687946927L;

	public AddSuggestRequest() {
	}

	/**
	 * 会员ID
	 */
	@MemberValidate
	private String memberId;
	
	/**
	 * 建议信息文本
	 */
	private String suggestion;

	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public AddSuggestRequest(String memberId, String suggestion) {
		super();
		this.memberId = memberId;
		this.suggestion = suggestion;
	}
	
	@Override
    public String toString() {
        return "AddSuggestRequest [memberId="+memberId+",suggestion=" + suggestion
                + ", toString()=" + super.toString() + "]";
    }
}
