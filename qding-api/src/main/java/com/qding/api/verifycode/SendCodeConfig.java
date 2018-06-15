package com.qding.api.verifycode;

import com.qding.api.sms.CodeAction;
import com.qding.api.sms.SmsAction;
import com.qding.api.verifycode.generator.GeneratorCode;
import com.qding.api.verifycode.send.SendChannel;
import com.qding.api.verifycode.store.StoreDevice;


public class SendCodeConfig {

	//短信验证码内容失效时间
	private int codeExpireAt;

	//距离下次可发送短信的失效时间
	private long expireAt;

	//短信类型
	private SmsAction action;

	//验证码方式 (默认是短信验证码)
	private Integer codeType = 1;
	
	private GeneratorCode generatorCode;
	
	private StoreDevice store;
	
	private SendChannel sendChannel;

	private String activityKey;

	private String activityName;

	private ImgVerify imgVerify;



	public SendCodeConfig(long expireAt, SmsAction action, GeneratorCode generatorCode,
						  StoreDevice store, SendChannel sendChannel,int codeExpireAt,int codeType) {
		super();
		this.expireAt = expireAt;
		this.action = action;
		this.generatorCode = generatorCode;
		this.store = store;
		this.sendChannel = sendChannel;
		this.codeExpireAt = codeExpireAt;
		this.codeType = codeType;
	}

	public SendCodeConfig(long expireAt, SmsAction action, GeneratorCode generatorCode,
						  StoreDevice store, SendChannel sendChannel,int codeExpireAt,ImgVerify imgVerify,int codeType) {
		super();
		this.expireAt = expireAt;
		this.action = action;
		this.generatorCode = generatorCode;
		this.store = store;
		this.sendChannel = sendChannel;
		this.codeExpireAt = codeExpireAt;
		this.imgVerify = imgVerify;
		this.codeType = codeType;
	}

	public SendCodeConfig( long expireAt, SmsAction action, GeneratorCode generatorCode, StoreDevice store, SendChannel sendChannel,int codeExpireAt,String activityKey, String activityName,int codeType) {
		this.expireAt = expireAt;
		this.action = action;
		this.generatorCode = generatorCode;
		this.store = store;
		this.sendChannel = sendChannel;
		this.activityKey = activityKey;
		this.codeExpireAt = codeExpireAt;
		this.activityName = activityName;
		this.codeType = codeType;
	}

	public ImgVerify getImgVerify() {
		return imgVerify;
	}

	public void setImgVerify(ImgVerify imgVerify) {
		this.imgVerify = imgVerify;
	}

	public void setAction(SmsAction action) {
		this.action = action;
	}
	
	public SmsAction getAction() {
		return action;
	}
	
	public long getExpireAt() {
		return expireAt;
	}

	public void setExpireAt(long expireAt) {
		this.expireAt = expireAt;
	}

	public GeneratorCode getGeneratorCode() {
		return generatorCode;
	}

	public void setGeneratorCode(GeneratorCode generatorCode) {
		this.generatorCode = generatorCode;
	}

	public StoreDevice getStore() {
		return store;
	}

	public void setStore(StoreDevice store) {
		this.store = store;
	}

	public SendChannel getSendChannel() {
		return sendChannel;
	}

	public void setSendChannel(SendChannel sendChannel) {
		this.sendChannel = sendChannel;
	}

	public int getCodeExpireAt() {
		return codeExpireAt;
	}

	public void setCodeExpireAt(int codeExpireAt) {
		this.codeExpireAt = codeExpireAt;
	}

	public String getActivityKey() {
		return activityKey;
	}

	public void setActivityKey(String activityKey) {
		this.activityKey = activityKey;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public Integer getCodeType() {
		return codeType;
	}

	public void setCodeType(Integer codeType) {
		this.codeType = codeType;
	}
}
