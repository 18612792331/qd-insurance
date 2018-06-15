package com.qding.api.verifycode;

import com.alibaba.fastjson.JSON;
import com.qding.api.constant.Constant;
import com.qding.api.sms.SendSms;
import com.qding.api.sms.SmsAction;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.api.verifycode.send.SendChannel;
import com.qding.api.verifycode.store.RedisStoreDevice;
import com.qding.api.verifycode.store.StoreDevice;
import com.qding.dictionary.client.DictionaryClient;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.member.rpc.IMemberRPC;
import com.qding.passport.domain.Member;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.request.GetMemberByMobileRequest;
import com.qding.passport.struct.response.GetMemberByMobileResponse;
import org.apache.log4j.Logger;
import org.apache.thrift.TException;


public class VerifyCode {


	private static  Logger logger = Logger.getLogger(VerifyCode.class);

	public static void sendCode(SendCodeConfig config) throws ServiceException {

		//系统验证码进行验证
		StoreDevice store = config.getStore();

		ImgVerify imgVerify = config.getImgVerify();

		if (QDStringUtil.isNotNull(imgVerify) &&  !Constant.SEND_MSG_DEFAULT_FLAG.equals(config.getImgVerify().getSysCode())) {

			imageVerifyCode( new ImgCodeConfig(imgVerify.getSysCode(),imgVerify.getSysVerifyKey(),store,true));
		}


		//验证本次请求是否可以重写发送短信

		SendChannel sendChannel = config.getSendChannel();

		SmsAction action = config.getAction();

		String key = "verify:code:" + sendChannel.getIdentity() + ":" + action.getAction();

		StoreCode verifyCode = store.get(key);

		if (QDStringUtil.isNotNull(verifyCode)) {

			long left = (verifyCode.getExpireAt() - System.currentTimeMillis()) / 1000;

			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "请" + left + "秒后重新发送");
		}



		//验证短信发送频率是否满足要求，不满足则直接返回异常信息
		String checkFlag = checkCheat (config);

		switch (checkFlag) {

			case "maxDay" ://指定天数内达到最大值返回异常
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "当前手机号已达到验证码发送次数上限");

			case "maxMinute"://指定分钟内达到最大值返回异常
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "当前手机号已达到验证码发送次数上限,请稍后再试");

			case "sysValidate"://指定时间内提示前端需要弹出系统验证码
				throw new ServiceException(1305, "");
		}



		//如果重发时间具备了可以重发短信
		if(verifyCode == null) {

			long expireAt = config.getExpireAt();

			String codeKey = "store:verify:code:" + sendChannel.getIdentity() + ":" + action.getAction();

			String code = store.getCode(codeKey);	//获取要发送的短信验证码

			if ( code == null ){

				code = config.getGeneratorCode().getCode();
				store.setCode(codeKey,code,config.getCodeExpireAt());
			}

			if(code == null || expireAt < System.currentTimeMillis() || store == null || sendChannel == null || action == null) {

				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "参数错误");
			}

			if (config.getCodeType() == 2) {
				logger.info(sendChannel.getIdentity() + " 语音验证码:"+code);
				sendChannel.sendVoicVerfyCode(sendChannel.getIdentity(),code);
			} else {
				logger.info(sendChannel.getIdentity() + " 短信验证码:"+code);
				sendChannel.send(code);
			}
			store.set(key, new StoreCode(code, expireAt), expireAt);

			if (QDStringUtil.isNotNull(imgVerify) &&  !Constant.SEND_MSG_DEFAULT_FLAG.equals(config.getImgVerify().getSysCode())){
				String sysCodeKey = "store:imgverify:code:" + imgVerify.getSysVerifyKey() ;
				store.delKey(sysCodeKey);
			}

			throw new ServiceException(HttpStatus.OK.getStatusCode(), "验证码发送成功");
		}

	}


	/**
	 * 短信频率校验和系统验证码弹出条件验证 （==========频率参数可在字典库管理中维护============）
	 * @param config
	 * @return
     */
	private  static String checkCheat (SendCodeConfig config ) {


		SendChannel sendChannel = config.getSendChannel();
		String mobile = sendChannel.getIdentity();

		StringBuffer mKey = new StringBuffer("store_cheat_m_code_" + mobile);
		StringBuffer dKey = new StringBuffer("store_cheat_d_code_" + mobile);
		String ruleKey = "store_cheat_rule_code_" + mobile;

		StoreDevice store = config.getStore();

		try {

			//是否采用短信类别分组进行频率控制，true:采用 false：否
			String useAction = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_MSG_CHEAT_1.getGroupName(),Constant.Dict_K_V_Enum.DICT_MSG_CHEAT_1.getDictKey());

			if ("true".equals(useAction.trim())){

				SmsAction action = config.getAction();
				mKey.append("_"+action.getAction());
				dKey.append("_"+action.getAction());
			}

			//验证指定分钟内第几次
			int checkMFlag = countStatistics(mKey.toString(), store);
			//验证指定天内第几次
			int checkDFlag = countStatistics(dKey.toString(), store);

			String day_rule = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_MSG_CHEAT_2.getGroupName(),Constant.Dict_K_V_Enum.DICT_MSG_CHEAT_2.getDictKey());
			String[] day_rules = day_rule.split("\\|\\|");
			Integer checkDayRuleExpire = Integer.parseInt(String.valueOf(day_rules[0]));
			Integer  checkDayRuleCheatCount = Integer.parseInt(String.valueOf(day_rules[1]));

			//如果达到指定天内最大值
			if (checkDFlag >= checkDayRuleCheatCount) {
				return "maxDay";
			}

			String minute_rule = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_MSG_CHEAT_3.getGroupName(),Constant.Dict_K_V_Enum.DICT_MSG_CHEAT_3.getDictKey());
			String[] minute_rules = minute_rule.split("\\|\\|");
			Integer checkMinuteRuleExpire = Integer.parseInt(String.valueOf(minute_rules[0]));
			Integer  checkMinuteRuleCheatCount = Integer.parseInt(String.valueOf(minute_rules[1]));


			//如果达到指定分钟内最大值
			if ( checkMFlag >= checkMinuteRuleCheatCount ) {
				return "maxMinute";
			}

			//弹出系统验证码条件验证
			String rule = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_MSG_CHEAT_4.getGroupName(),Constant.Dict_K_V_Enum.DICT_MSG_CHEAT_4.getDictKey());
			String[] rules = rule.split("\\|\\|");
			Integer  checkRuleExpire = Integer.parseInt(String.valueOf(rules[0]));
			Integer  checkRuleCheatCount = Integer.parseInt(String.valueOf(rules[1]));
			int checkRuleFlag  = countStatistics(ruleKey,store);

			if (QDStringUtil.isNotNull(config.getImgVerify()) && Constant.SEND_MSG_DEFAULT_FLAG.equals(config.getImgVerify().getSysCode())
					&& checkRuleFlag >= checkRuleCheatCount){

				return "sysValidate";
			}

			//记录当前短信验证码发送次数（用于验证是否需要弹出系统验证码框）
			if (checkRuleFlag == 0) {
				store.incr(ruleKey,checkRuleExpire * 60);
			} else {
				store.incr(ruleKey,Integer.parseInt(String.valueOf(store.getExpireTime(ruleKey))));
			}

			//如果当天没有次数记录，则可以发放短信，并重置指定天数内次数为第一次
			if (checkDFlag == 0) {
				store.incr(dKey.toString(),checkDayRuleExpire * 24  * 60 * 60 );
				store.incr(mKey.toString(),checkMinuteRuleExpire * 60 );
				return "ok";
			}

			//如果指定分钟内没有次数记录，则可以发放短信，并重置次数为第一次，指定天记录自增一次
			if (checkMFlag == 0) {
				store.incr(mKey.toString(),checkMinuteRuleExpire * 60);
				store.incr(dKey.toString(),Integer.parseInt(String.valueOf(store.getExpireTime(dKey.toString()))));
				return  "ok";
			}

			//数量自增
			store.incr(dKey.toString(),Integer.parseInt(String.valueOf(store.getExpireTime(dKey.toString()))));
			store.incr(mKey.toString(),Integer.parseInt(String.valueOf(store.getExpireTime(mKey.toString()))));


		} catch (Exception e) {
			logger.error("短信验证码，验证是否弹出系统验证码规则报错：",e);
			e.printStackTrace();
		}


		return "ok";

	}



	/**
	 * 获取指定KEY累计数
	 * @param Key
	 * @param store
     * @return
     */
	private static int countStatistics (String Key,StoreDevice store) {

		String countTr = store.getCode(Key);
		if (QDStringUtil.isNotNull(countTr)){
			Integer count = Integer.parseInt(countTr);
			return count;
		}
		return 0;

	}



	public static boolean verifyCode(VerifyCodeConfig config) throws ServiceException {
		
		StoreDevice store = config.getStore();
		
		String identity = config.getIdentity();
		
		String code = config.getCode();
		
		SmsAction action = config.getAction();
		
		if(identity == null || code == null || store == null || action == null) {
		
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "参数错误");
		}
		
		String key = "verify:code:" + identity + ":" + action.getAction();
		
		StoreCode verifyCode = store.get(key);

		boolean checkFlag = checkMsgVerificationFrequency(config);

		if (checkFlag) {

			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "验证请求频次达到上限");
		}

		if(verifyCode == null) {
			
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "验证码已过期，请重新发送");
		}
		else {
			
			if(!verifyCode.getCode().equals(code)) {
				
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "验证码错误");
			}
		}

		String codeKey = "store:verify:code:" +identity + ":" + action.getAction();
		String checkMsgVerificationFrequencyKey = "verify:num:" + config.getIdentity();
		store.delKey(checkMsgVerificationFrequencyKey);
		store.delKey(key);
		store.delKey(codeKey);

		return true;
	}


	/**
	 * 验证短信验证请求次数上限
	 * @param config
	 * @return
	 * @throws Exception
     */
	private static  boolean checkMsgVerificationFrequency (VerifyCodeConfig config ) throws ServiceException {

		try {
			StoreDevice store = config.getStore();
			String rule = DictionaryClient.findDictValueByGroupNameAndDictKey(Constant.Dict_K_V_Enum.DICT_MSG_CHEAT_5.getGroupName(),Constant.Dict_K_V_Enum.DICT_MSG_CHEAT_5.getDictKey());
			String checkMsgVerifFrequencyKey = String.format(Constant.CHECK_MSG_VERIFY_FREQUENCY_KEY, config.getIdentity());//"verify:num:" + config.getIdentity();
			int count = countStatistics (checkMsgVerifFrequencyKey,store);
			if (count >= Integer.parseInt(rule)) {
				if (config.getAction().getAction() != 1) {
					logger.info("短信验证请求超频:" + JSON.toJSONString(config));
					try {
						// 冻结用户
						IProfileService profileAPI = ApplicationContextUtil.getBeansOfType(IProfileService.class);
						GetMemberByMobileRequest memberByMobileRequest = new GetMemberByMobileRequest();
						memberByMobileRequest.setMobile(config.getIdentity());
						GetMemberByMobileResponse memberByMobileResponse = profileAPI.getMemberByMobile(memberByMobileRequest);
						if (memberByMobileResponse.getReturnInfo().getCode() == HttpStatus.OK.getStatusCode() ) {
							Member member = memberByMobileResponse.getMember();
							if( 0 == member.getStatus().intValue() ){
								return true;
							}
							store.delKey(checkMsgVerifFrequencyKey);
							store.sadd(Constant.FREEZ_EMEMBER_READ_KEY.toString(),member.getId());
							IMemberRPC memberServiceAPI = ApplicationContextUtil.getBeansOfType(IMemberRPC.class);
							memberServiceAPI.toggle(member.getId(),0,"短信验证请求频率超限冻结","系统");
						}
					} catch (Exception ex) {
						 logger.error("超出短信频率，用户冻结失败：",ex);
						 return true;
					}
				}
			   return  true;

			} else {
				store.incr(checkMsgVerifFrequencyKey);//数量自增
			}

		} catch (TException e) {
			e.printStackTrace();
			return  false;
		}
		return  false;

	}


	/**
	 * 重置短信验证码
	 * @param config
	 * @throws ServiceException
	 */
	public static void resetCode(RestCodeConfig config)  throws ServiceException{

		StoreDevice store = config.getStore();

		long expireAt = config.getExpireAt();

		SendChannel sendChannel = config.getSendChannel();

		SmsAction action = config.getAction();

		String codeKey = "store:verify:code:" + sendChannel.getIdentity() + ":" + action.getAction();

		String code = config.getCode();

		if(code == null || expireAt < System.currentTimeMillis() || store == null || sendChannel == null || action == null) {

			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "参数错误");
		}

		store.setCode(codeKey,code,config.getCodeExpireAt());

		String key = "verify:code:" + sendChannel.getIdentity() + ":" + action.getAction();

		store.set(key, new StoreCode(code, expireAt), expireAt);

	}


	public static boolean checkVerifyCodeIsReset(VerifyCodeConfig config) throws ServiceException {

		StoreDevice store = config.getStore();

		String identity = config.getIdentity();

		String code = config.getCode();

		SmsAction action = config.getAction();

		if(identity == null || code == null || store == null || action == null) {

			return false;
		}

		String key = "verify:code:" + identity + ":" + action.getAction();

		StoreCode verifyCode = store.get(key);

		if(verifyCode == null) {

			return false;
		}
		else {

			if(!verifyCode.getCode().equals(code)) {

				return false;
			}
		}

		return true;
	}


	/**
	 * 设置图片系统验证码
	 * @param config
	 * @throws ServiceException
	 */
	public static void setImgCode(ImgCodeConfig config) throws ServiceException {

		StoreDevice store = config.getStore();

		String identity = config.getIdentity();

		String codeKey = "store:imgverify:code:" + identity ;

		String code = store.getCode(codeKey);

		code = config.getCode();

		logger.info("系统验证码=============key:"+codeKey+"======== code:"+code);

		if(config.isExpireAt()){

			store.setCode(codeKey,code,60);
		}
	}

	/**
	 * 验证系统图片验证码
	 * @param config
	 * @return
	 * @throws ServiceException
	 */
	public static boolean imageVerifyCode(ImgCodeConfig config) throws ServiceException {



		StoreDevice store = config.getStore();

		String identity = config.getIdentity();

		String code = config.getCode();


		if(identity == null || code == null || store == null ) {

			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "参数错误");
		}

		String key = "store:imgverify:code:" + identity ;

		String verifyCode = store.getCode(key);

		if(verifyCode == null) {

			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "验证码已过期，请重新发送");
		}
		else {


			if(!verifyCode.toLowerCase().equals(code)) {

				logger.info("获取输入验证码为："+code +" ===========  缓存验证码为："+verifyCode);
				throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "验证码错误");
			}
		}

		return true;
	}


	public static void delVerifyMsgCache(String mobile,String type) {

		StringBuffer dKey = new StringBuffer("store_cheat_d_code_" + mobile);
		dKey.append("_"+type);
		StoreDevice store = new RedisStoreDevice();
		store.delKey(dKey.toString());
	}

}
