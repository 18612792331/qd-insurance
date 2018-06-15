package com.qding.api.call.app.qding.v4_0_0.struct.chatRecord.request;

import com.qding.framework.common.api.struct.request.BaseRequest;

public class ChatRecordRequest extends BaseRequest{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String chatGroupId;

	public String getChatGroupId() {
		return chatGroupId;
	}

	public void setChatGroupId(String chatGroupId) {
		this.chatGroupId = chatGroupId;
	}

	
}
