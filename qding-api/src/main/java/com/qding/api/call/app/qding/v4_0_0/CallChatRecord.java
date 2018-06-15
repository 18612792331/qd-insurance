package com.qding.api.call.app.qding.v4_0_0;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.annotation.ExplainAnnotation;
import com.qding.api.annotation.HTTP;
import com.qding.api.call.Callable;
import com.qding.api.call.app.qding.v4_0_0.struct.chatRecord.ChatRecordDTO;
import com.qding.api.call.app.qding.v4_0_0.struct.chatRecord.ChatRecordResultDTO;
import com.qding.api.call.app.qding.v4_0_0.struct.chatRecord.LinkDTO;
import com.qding.api.call.app.qding.v4_0_0.struct.chatRecord.request.ChatRecordRequest;
import com.qding.api.call.app.qding.v4_0_0.struct.chatRecord.response.data.ChatRecordResponseData;
import com.qding.api.process.security.UserToken;
import com.qding.api.struct.Response;
import com.qding.chat.record.dto.ChatRecordResDto;
import com.qding.chat.record.dto.ChatRecordResultResDto;
import com.qding.chat.record.dto.LinkResDto;
import com.qding.chat.record.rpc.IRpcChatRecordService;
import com.qding.framework.common.constants.HttpStatus;

/**
 * Created by zhangxiaojun on 2018/4/14.
 */
@ExplainAnnotation(explain = "AI聊天记录")
public class CallChatRecord extends Callable {

    private static final Logger logger = Logger.getLogger(CallChatRecord.class);

    @Autowired
    private IRpcChatRecordService rpcChatRecordService;

    @ExplainAnnotation(explain = "查询聊天记录")
    @HTTP(alias = "chatRecordList", isNeadToken = true, isRequireAuth = true)
    public Response<ChatRecordResponseData> chatRecord(ChatRecordRequest request, UserToken userToken) {
        Response<ChatRecordResponseData> response = new Response<ChatRecordResponseData>();
        ChatRecordResponseData data = new ChatRecordResponseData();
        try {
            //调用rpcChatRecordService查询聊天记录
            ChatRecordResultResDto crqrDto = rpcChatRecordService.getChatRecordListByMemberIdAndGroupId(userToken.getMemberId(), request.getChatGroupId());
            ChatRecordResultDTO rstDto = new ChatRecordResultDTO();    //response中需要返回的ChatRecordResultDto
            List<ChatRecordDTO> list = new ArrayList<ChatRecordDTO>();    //ChatRecordResultDto中的list

            //将ChatRecordResultResDto转为ChatRecordResultDTO
            List<ChatRecordResDto> chatRecordDtoList = crqrDto.getChatRecordDtoList();
            if (chatRecordDtoList != null && chatRecordDtoList.size() > 0) {
                for (ChatRecordResDto chatRecordDto : chatRecordDtoList) {
                    ChatRecordDTO crDto = new ChatRecordDTO();
                    crDto.setRecordId(chatRecordDto.getRecordId());
                    crDto.setRecordGroupId(chatRecordDto.getRecordGroupId());
                    crDto.setRecordType(chatRecordDto.getRecordType());
                    crDto.setRecordContent(chatRecordDto.getRecordContent());
                    crDto.setCreateTime(chatRecordDto.getCreateTime());
                    crDto.setKnowledgeId(chatRecordDto.getKnowledgeId());
                    crDto.setFeedbackResult(chatRecordDto.getFeedbackResult());
                    List<LinkDTO> linkDtoList = new ArrayList<LinkDTO>();
                    List<LinkResDto> lkDtoList = chatRecordDto.getLinkDtoList();
                    if (lkDtoList != null && lkDtoList.size() > 0) {
                        for (LinkResDto linkResDto : lkDtoList) {
                            LinkDTO linkDTO = new LinkDTO();
                            linkDTO.setTitle(linkResDto.getTitle());
                            linkDTO.setType(linkResDto.getType());
                            linkDTO.setValue(linkResDto.getValue());
                            linkDTO.setKnowledgeId(linkResDto.getKnowledgeId());
                            linkDtoList.add(linkDTO);
                            crDto.setLinkDtoList(linkDtoList);
                        }
                    }
                    list.add(crDto);
                }
                rstDto.setChatRecordDtoList(list);    //聊天记录的集合
                rstDto.setNextChatRecordGroupId(crqrDto.getNextChatRecordGroupId());    //下一个会话的ID
                data.setChatRecordQueryResultDTO(rstDto);
            }
            response.setCode(HttpStatus.OK.getStatusCode());
        } catch (Exception ex) {
            return handleException(ChatRecordResponseData.class, ex);
        }
        response.setData(data);
        logger.info("查询聊天记录成功");
        return response;

    }

}
