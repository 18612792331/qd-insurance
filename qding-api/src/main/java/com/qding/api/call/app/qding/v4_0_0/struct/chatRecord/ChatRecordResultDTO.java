package com.qding.api.call.app.qding.v4_0_0.struct.chatRecord;

import java.io.Serializable;
import java.util.List;

import com.qding.api.annotation.ExplainAnnotation;

public class ChatRecordResultDTO implements Serializable{
	private static final long serialVersionUID = 1029962827093942616L;
	
	/**
	 * 查询到的当前会话的聊天记录列表
	 */
	@ExplainAnnotation (explain = "聊天记录列表")
	private List<ChatRecordDTO> chatRecordDtoList;
	
	/**
	 * 下一个会话ID（如果为空串则表示没有更多会话记录）
	 */
	@ExplainAnnotation (explain = "下一个会话ID")
	private String nextChatRecordGroupId;

	public List<ChatRecordDTO> getChatRecordDtoList() {
		return chatRecordDtoList;
	}

	public void setChatRecordDtoList(List<ChatRecordDTO> chatRecordDtoList) {
		this.chatRecordDtoList = chatRecordDtoList;
	}

	public String getNextChatRecordGroupId() {
		return nextChatRecordGroupId;
	}

	public void setNextChatRecordGroupId(String nextChatRecordGroupId) {
		this.nextChatRecordGroupId = nextChatRecordGroupId;
	}
	
	
}
