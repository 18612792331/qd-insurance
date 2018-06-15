package com.qding.api.imessage;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.client.HessianProxyFactory;
import com.qding.imessage.common.sender.IRemoteJobService;
import com.qding.imessage.common.struct.MsginfoRequest;
import com.qding.imessage.common.struct.MsginfoResponse;

import org.apache.log4j.Logger;
import java.net.MalformedURLException;
import java.util.Map;

/**
 * 抽奖消息
 * @author Administrator
 */
public class ActivityMessage {

	private  String imessageUrl = "";

	private  String jobUrl ="";

	private  Integer type ;

	private   String toClass ="";

	private  int maxRetrycount;

	private  Long retryInterval;
	
	private String toClass_LWM = "ActivityHandleMessage";
	
	private String toClass_CQLH = "ActivityHandleMessageLH";

	private  static  IRemoteJobService remoteJobService;
	
	//0 启用，1停用
	private String status="0";


	private Logger logger = Logger.getLogger(ActivityMessage.class);

	public void init() {
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			factory.setChunkedPost(false);
			remoteJobService = (IRemoteJobService) factory.create(IRemoteJobService.class, imessageUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 通知sysConfig系统处理消息
	 * @param param
	 * type: 1-龙纹脉，2-重庆龙湖
	 */
	public void noticeSysConfig(Map<String,Object> param,int type){
		try {
			String paramJsonStr = JSON.toJSONString(param);
			logger.info("龙湖报事评论抽奖消息 参数:"+paramJsonStr);
			MsginfoRequest msginfoRequest = new MsginfoRequest();
			msginfoRequest.setName("报事评论抽奖");
			msginfoRequest.setType(type);
			msginfoRequest.setIscallback(0);
			msginfoRequest.setTourl(jobUrl);
			
			switch (type) {
            case 1:
                msginfoRequest.setToclass(toClass_LWM);
                break;
            case 2:
                msginfoRequest.setToclass(toClass_CQLH);
                break;
            default:
                msginfoRequest.setToclass(toClass_LWM);
                break;
            }
//			msginfoRequest.setToclass(toClass);
			msginfoRequest.setMaxRetrycount(maxRetrycount);//重试次数
			msginfoRequest.setRetryInterval(retryInterval);//单位: 毫秒
			msginfoRequest.setMsgBody(paramJsonStr);
			MsginfoResponse msginfoResponse = remoteJobService.SendImessage(msginfoRequest);
			logger.info("报事评论抽奖 结果:"+msginfoResponse.getReturnInfo().getCode()+"," +
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

	public  String getJobUrl() {
		return jobUrl;
	}

	public  void setJobUrl(String jobUrl) {
		this.jobUrl = jobUrl;
	}

	public  Integer getType() {
		return type;
	}

	public  void setType(Integer type) {
		this.type = type;
	}

	public  String getToClass() {
		return toClass;
	}

	public  void setToClass(String toClass) {
		this.toClass = toClass;
	}

	public  int getMaxRetrycount() {
		return maxRetrycount;
	}

	public  void setMaxRetrycount(int maxRetrycount) {
		this.maxRetrycount = maxRetrycount;
	}

	public  Long getRetryInterval() {
		return retryInterval;
	}

	public  void setRetryInterval(Long retryInterval) {
		this.retryInterval = retryInterval;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


}
