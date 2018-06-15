package com.qding.api.process.security;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSON;
import com.qding.api.annotation.HTTP;
import com.qding.api.cache.memcache.MemberStatusCache;
import com.qding.api.constant.Constant;
import com.qding.api.process.GlobalInstance;
import com.qding.api.process.print.AbstractProtocolPrint;
import com.qding.api.util.APIPropertiesClient;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.framework.common.api.struct.request.BaseRequest;
import com.qding.framework.common.cache.MemCacheDataSource;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.util.QDStringUtil;
import com.qding.passport.service.IProfileService;
import com.qding.passport.struct.MemberInfo;
import com.qding.passport.struct.request.GetMemberRequest;
import com.qding.passport.struct.response.GetMemberResponse;
import com.qdingnet.auth.service.AuthService;
import com.qdingnet.auth.struct.AuthException;
import com.qdingnet.auth.struct.AuthRequest;
import com.qdingnet.auth.struct.AuthResponse;
import com.qdingnet.auth.struct.TokenRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTokenCallableSecurity extends CallableSecurity {

	private static Logger logger = Logger.getLogger(UserTokenCallableSecurity.class);

	public synchronized String generatorToken(UserToken tb,Integer version) {

		Map<String, String> map = new HashMap<>();
		map.put("data", JSON.toJSONString(tb));
		String tokenExpire = "";
		Integer addTime =20 * 24 * 60 * 60 * 1000;
		if(version < Constant.TOKEN_VERSION){
			tokenExpire = APIPropertiesClient.getValue("oldTokenExpire");
		} else {
			tokenExpire = APIPropertiesClient.getValue("tokenExpire");
		}
		try {
			if(QDStringUtil.isNotEmpty(tokenExpire)) {
				String[] tokenConfigArray = tokenExpire.split("-");
				if(tokenConfigArray.length>=2){
					String unit = tokenConfigArray[1];
					String etime = tokenConfigArray[0];
					if("d".equals(unit.replace(" ","").toLowerCase())){
						addTime = Integer.parseInt(etime) * 24 * 60 * 60 * 1000;
					} else if("h".equals(unit.replace(" ","").toLowerCase())){
						addTime = Integer.parseInt(etime) * 60 * 60 * 1000;
					}else if ("m".equals(unit.replace(" ","").toLowerCase())){
						addTime = Integer.parseInt(etime)  * 60 * 1000;
					}
				}
			}
		}catch (Exception e) {
			logger.error("获取token失效时间设置异常:",e);
		}

		TokenRequest tokenRequest = new TokenRequest(
				tb.getAccountId() == null ? "" : tb.getAccountId(), 
				tb.getName() == null ? "" : tb.getName(), 
				tb.getSourceType() == null ? "" : tb.getSourceType().toString(), 
				String.valueOf(System.currentTimeMillis() + addTime),
				map
		);
		
		try {
			return AuthService.generateToken(tokenRequest);
		} catch (AuthException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public UserToken checkCallableSecurity(AbstractProtocolPrint print,
			Method targetMethod, HttpServletRequest request,
			HttpServletResponse response, BaseRequest baseRequest) throws ServiceException {

		HTTP http = targetMethod.getAnnotation(HTTP.class);

		String token = (String) GlobalInstance.getTransportSecurity().getParameter(request, "userToken");
		Integer version = initVersion(baseRequest);

		UserToken tokenBean = null;

		if(QDStringUtil.isEmpty(token) && version< Constant.TOKEN_VERSION && http.isRequireAuth()) {
			throw new ServiceException(HttpStatus.UNAUTHORIZED);
		}

		if((version>= Constant.TOKEN_VERSION && ((QDStringUtil.isNotEmpty(token) && !token.equals("null")) || http.isRequireAuth()))
				|| (version< Constant.TOKEN_VERSION && http.isRequireAuth())) {

			AuthRequest authRequest = new AuthRequest(token);
			AuthResponse authResponse;
			try {
				authResponse = AuthService.auth(authRequest);
			} catch (AuthException e) {
				throw new ServiceException(HttpStatus.UNAUTHORIZED);
			}
			logger.info("当前用户口令状态:" +authResponse.getIs_expire() );

			if(authResponse.getIs_expire()) {
				//重新登录
				throw new ServiceException(HttpStatus.NOT_ACCEPTABLE);
			}

			Object data = authResponse.getAttribute("data");
			tokenBean = JSON.parseObject(data.toString(), UserToken.class);

		}

		return tokenBean;
	}

	public static Integer initVersion(BaseRequest baseRequest) {

		if(QDStringUtil.isNotNull(baseRequest) && QDStringUtil.isNotNull(baseRequest.getAppDevice())
				&&QDStringUtil.isNotEmpty(baseRequest.getAppDevice().getQdVersion())){
			String curVersion = baseRequest.getAppDevice().getQdVersion();
			String version = curVersion.replace(".", "");
			version = String.format("%-6s", version).replace(' ', '0');
			return Integer.parseInt(version);
		}
		return  0;

	}

	public static void checkMemberStatus( HttpServletRequest request,Boolean isNeadToken)  throws ServiceException {

		String token = (String) GlobalInstance.getTransportSecurity().getParameter(request, "userToken");
		if(QDStringUtil.isNotEmpty(token) && !token.equals("null")){
			AuthRequest authRequest = new AuthRequest(token);
			try {
				AuthResponse authResponse = AuthService.auth(authRequest);
				Object data = authResponse.getAttribute("data");
				UserToken tokenBean  = JSON.parseObject(data.toString(), UserToken.class);
				logger.info("验证用户是否冻结：[memberId:"+tokenBean.getMemberId()+"]" );
				if(isNeadToken){
					if(QDStringUtil.isNull(tokenBean)){
						logger.info("tokenBea为null");
						throw new ServiceException(HttpStatus.NOT_ACCEPTABLE.getStatusCode(),"当前用户状态异常，请退出重新登录");
					} else {
						if(QDStringUtil.isEmpty(tokenBean.getMemberId())){
							logger.info("memberId是必须项在当前userToken中无法获取-1");
							throw new ServiceException(HttpStatus.NOT_ACCEPTABLE);
						}
					}
				}

				if(QDStringUtil.isNotNull(tokenBean)){
					MemberStatusCache memberStatusCache =  ApplicationContextUtil.getBeansOfType(MemberStatusCache.class);
					boolean isHaltFlag = memberStatusCache.getMemberIsHalt(tokenBean.getMemberId());
					if(isHaltFlag){
						logger.info("当前用户已被停用!");
						GetMemberRequest re = new GetMemberRequest();
		                re.setMemberId(tokenBean.getMemberId());
		                GetMemberResponse memberResponse = memberStatusCache.getProfileAPI().getMemberById(re);
		                throw new ServiceException(HttpStatus.GONE.getStatusCode(),"您手机号码为“"+memberResponse.getMemberInfo().getMobile()+
	                			"”的账号不存在或已被注销，请使用其它账号登录！");
					}
				}
			} catch (AuthException e) {

			}
		} else {
			if(isNeadToken){
				logger.info("memberId是必须项在当前userToken中无法获取-2");
				throw new ServiceException(HttpStatus.NOT_ACCEPTABLE.getStatusCode(),"当前用户状态异常，请退出重新登录");
			}
		}

	}

}
