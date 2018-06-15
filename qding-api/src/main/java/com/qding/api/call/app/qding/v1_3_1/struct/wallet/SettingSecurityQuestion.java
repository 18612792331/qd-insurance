package com.qding.api.call.app.qding.v1_3_1.struct.wallet;

import java.io.Serializable;

import com.qding.framework.common.smart.validate.Validate;
import com.qding.framework.common.smart.validate.rule.NotNullValidate;
import com.qding.framework.common.smart.validate.rule.RangeLengthValidate;

@Validate
public class SettingSecurityQuestion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4553407685096843956L;

	@NotNullValidate
	private String id;
	
	@RangeLengthValidate(min=1, max=20, message="答案的长度必须在1和20之间")
	private String answer;


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
