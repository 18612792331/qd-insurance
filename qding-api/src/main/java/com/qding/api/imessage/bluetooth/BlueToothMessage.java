package com.qding.api.imessage.bluetooth;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.client.HessianProxyFactory;
import com.qding.hk.rpc.request.bluetooth.AddBlueToothLogRequest;
import com.qding.imessage.common.sender.IRemoteJobService;
import com.qding.imessage.common.struct.MsginfoRequest;
import com.qding.imessage.common.struct.MsginfoResponse;
import org.apache.log4j.Logger;

import java.net.MalformedURLException;

/**
 * Created by qd on 2017/5/4.
 */
public class BlueToothMessage {

    private  String imessageUrl = "";

    private  String jobUrl ="";

    private  Integer type ;

    private   String toClass ="";

    private  int maxRetrycount;

    private  Long retryInterval;

    private  static IRemoteJobService remoteJobService;


    private Logger logger = Logger.getLogger(BlueToothMessage.class);

    public void init() {

        HessianProxyFactory factory = new HessianProxyFactory();
        try {

            factory.setChunkedPost(false);
            remoteJobService = (IRemoteJobService) factory.create(IRemoteJobService.class, imessageUrl);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void addBlueToothLog(AddBlueToothLogRequest params){
        try {

            String paramJsonStr = JSON.toJSONString(params);
            logger.info("paramJsonStr:"+paramJsonStr);
            MsginfoRequest msginfoRequest = new MsginfoRequest();
            msginfoRequest.setName("addBlueToothLog");
            msginfoRequest.setType(type);
            msginfoRequest.setIscallback(0);
            msginfoRequest.setTourl(jobUrl);
            msginfoRequest.setToclass(toClass);
            msginfoRequest.setMaxRetrycount(maxRetrycount);//重试次数
            msginfoRequest.setRetryInterval(retryInterval);//单位: 毫秒
            msginfoRequest.setMsgBody(paramJsonStr);
            msginfoRequest.setTopicName("addBlueToothLog");
            MsginfoResponse msginfoResponse = remoteJobService.SendImessage(msginfoRequest);
            logger.info("addBlueToothLog Imessage Response code:"+msginfoResponse.getReturnInfo().getCode()+"," +
                    "message:"+msginfoResponse.getReturnInfo().getMessage());

        }catch (Exception e) {

            e.printStackTrace();
        }
    }

    public String getImessageUrl() {
        return imessageUrl;
    }

    public void setImessageUrl(String imessageUrl) {
        this.imessageUrl = imessageUrl;
    }

    public String getJobUrl() {
        return jobUrl;
    }

    public void setJobUrl(String jobUrl) {
        this.jobUrl = jobUrl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getToClass() {
        return toClass;
    }

    public void setToClass(String toClass) {
        this.toClass = toClass;
    }

    public int getMaxRetrycount() {
        return maxRetrycount;
    }

    public void setMaxRetrycount(int maxRetrycount) {
        this.maxRetrycount = maxRetrycount;
    }

    public Long getRetryInterval() {
        return retryInterval;
    }

    public void setRetryInterval(Long retryInterval) {
        this.retryInterval = retryInterval;
    }


}
