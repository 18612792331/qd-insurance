package com.qding.api.call.app.qding.v1_3_1.struct.wallet;

import java.io.Serializable;

import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;

@Validate
public class CheckSecurityQuestion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4553407685096843956L;

	@NotNullValidate
	private String id;
	
	@NotNullValidate
	private String answer;
	
	public CheckSecurityQuestion() {

	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

}
