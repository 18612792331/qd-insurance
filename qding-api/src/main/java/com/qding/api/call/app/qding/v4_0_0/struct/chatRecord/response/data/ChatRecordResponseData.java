package com.qding.api.call.app.qding.v4_0_0.struct.chatRecord.response.data;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.call.app.qding.v4_0_0.struct.chatRecord.ChatRecordResultDTO;
import com.qding.api.struct.ResponseData;

/**
 * Created by zhangxiaojun on 2018/4/14.
 */
public class ChatRecordResponseData extends ResponseData {
	private static final long serialVersionUID = 9153545829841806312L;
	
	@ExplainAnnotation(explain = "聊天记录")
	private ChatRecordResultDTO chatRecordQueryResultDTO;

	public ChatRecordResultDTO getChatRecordQueryResultDTO() {
		return chatRecordQueryResultDTO;
	}

	public void setChatRecordQueryResultDTO(
			ChatRecordResultDTO chatRecordQueryResultDTO) {
		this.chatRecordQueryResultDTO = chatRecordQueryResultDTO;
	}

}
