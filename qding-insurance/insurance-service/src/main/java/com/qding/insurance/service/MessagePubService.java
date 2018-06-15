package com.qding.insurance.service;

import java.net.MalformedURLException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.caucho.hessian.client.HessianProxyFactory;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.imessage.common.sender.IRemoteJobService;
import com.qding.imessage.common.struct.MsginfoResponse;
import com.qding.imessage.common.struct.PubMsgInfoRequest;
import com.qding.insurance.domain.CompensateRecord;


/**
 * 消息发布
 */
@Service("messagePubService")
public class MessagePubService {
    
    private Logger logger = Logger.getLogger(MessagePubService.class);
    
    @Value("#{configproperties_disconf[messageID_picc_auth]}")
    private String messageTypeId;
    
    private IRemoteJobService messageClient;
    
    private  String imessageUrl = "";
    
    public void init() {

        HessianProxyFactory factory = new HessianProxyFactory();
        try {
            factory.setChunkedPost(false);
            messageClient = (IRemoteJobService) factory.create(IRemoteJobService.class, imessageUrl);
        } catch (MalformedURLException e) {
            logger.error("init IRemoteJobService error ",e);
        }
    }
    
    
    /**
     * PICC授权通过，发布广播
     */
    public void piccAuthMessage(CompensateRecord compensateRecord) throws Exception{
        
        PubMsgInfoRequest msgInfoRequest = new PubMsgInfoRequest();
        msgInfoRequest.setMessageTypeId(messageTypeId);
        msgInfoRequest.setMsgBody(JSONObject.toJSONString(compensateRecord));
        
        logger.info("pub picc-auth message["+compensateRecord.getId()+"] request : " + JSONObject.toJSONString(msgInfoRequest));
        MsginfoResponse response = messageClient.PubImessage(msgInfoRequest); 
        logger.info("pub picc-auth message["+compensateRecord.getId()+"] response : " + JSONObject.toJSONString(response));
        
        if(response==null || response.getReturnInfo().getCode()!=HttpStatus.OK.getStatusCode()){
            throw new Exception("pub picc-auth message error : "+response.getReturnInfo().getMessage());
        }
    }
    
    
    
    public String getImessageUrl() {
        return imessageUrl;
    }
    public void setImessageUrl(String imessageUrl) {
        this.imessageUrl = imessageUrl;
    }
    
}
  
