package com.qding.api.call.app.qding.v1_3_1.struct.wallet;

import java.io.Serializable;

public class GetSecurityQuestion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4553407685096843956L;

	private String id;
	
	private String question;
	
	public GetSecurityQuestion() {

	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
	
}
