package com.qding.api.imessage;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.client.HessianProxyFactory;
import com.qding.imessage.common.sender.IRemoteJobService;
import com.qding.imessage.common.struct.MsginfoRequest;
import com.qding.imessage.common.struct.MsginfoResponse;
import org.apache.log4j.Logger;

import java.net.MalformedURLException;

/**
 * Created by qd on 2015/11/6.
 */
public class SearchKeyStatistics {

    private  String imessageUrl = "";

    private  String jobUrl ="";

    private  Integer type ;

    private   String toClass ="";

    private  int maxRetrycount;

    private  Long retryInterval;

    private  static  IRemoteJobService remoteJobService;

    private Logger logger = Logger.getLogger(SearchKeyStatistics.class);

    public void init() {

        HessianProxyFactory factory = new HessianProxyFactory();
        try {

            factory.setChunkedPost(false);
            remoteJobService = (IRemoteJobService) factory.create(IRemoteJobService.class, imessageUrl);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public int  sendKeyStatisticsInfo(KeywordNotify keywordNotify){

        try {

            String paramJsonStr = JSON.toJSONString(keywordNotify);
            MsginfoRequest msginfoRequest = new MsginfoRequest();
            msginfoRequest.setName("搜索关键词统计调度");
            msginfoRequest.setType(type);
            msginfoRequest.setIscallback(0);
            msginfoRequest.setTourl(jobUrl);
            msginfoRequest.setToclass(toClass);
            msginfoRequest.setMaxRetrycount(maxRetrycount);//重试次数
            msginfoRequest.setRetryInterval(retryInterval);//单位: 毫秒
            msginfoRequest.setMsgBody(paramJsonStr);

            MsginfoResponse msginfoResponse = remoteJobService.SendImessage(msginfoRequest);
            logger.info("SearchKeyStatistics Imessage Response code:"+msginfoResponse.getReturnInfo().getCode()+"," +
                    "message:"+msginfoResponse.getReturnInfo().getMessage());
            return msginfoResponse.getReturnInfo().getCode();
        } catch (Exception e) {
            e.printStackTrace();
            return 400;
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
