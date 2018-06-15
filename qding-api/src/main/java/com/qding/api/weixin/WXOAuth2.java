package com.qding.api.weixin;

import com.qding.api.call.app.qding.v1_3_0.struct.user.WXUser;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qding.api.weixin.json.WXOAuthAccessToken;
import com.qding.framework.common.constants.HttpStatus;
import com.qding.framework.common.exception.ServiceException;
import com.qding.framework.common.http.QDHttpClientService;

/**
 * 微信公众账号
 * @author lichao
 *
 */
public class WXOAuth2 {

	private String getWxUserUrl;

	private String accessTokenUrl;
	
	private String appId;
	
	private String secret;
	
	private static final Logger logger = Logger.getLogger("WXOAuth2");
	
	public WXOAuthAccessToken getWXOAuthAccessToken(String code) throws ServiceException {
		
		String url = String.format(accessTokenUrl, appId, secret, code);
		
		logger.info("request : " + url);
		
		String response = QDHttpClientService.sendGetSSLRequest(url, "utf-8");
		
		logger.info("response : " + response);
		
		JSONObject jsonObject = JSON.parseObject(response);
		
		if(jsonObject.containsKey("errcode")) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), jsonObject.getString("errmsg"));
		}
		
		WXOAuthAccessToken authJSON = JSON.parseObject(response, WXOAuthAccessToken.class);
		
		if(authJSON == null) {

			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "微信授权失败");
		}
		return authJSON;
	}


	public WXUser getWXUser(String accessToken, String openId) throws ServiceException {

		String url = String.format(getWxUserUrl, accessToken, openId);

		logger.info("request : " + url);

		String response = QDHttpClientService.sendGetSSLRequest(url, "utf-8");

		logger.info("response : " + response);

		WXUser wxUser = get(response, WXUser.class);

		if(wxUser == null) {

			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), "获取用户信息失败");
		}

		return wxUser;
	}

	private <T> T get(String response, Class<T> target) throws ServiceException {

		JSONObject jsonObject = JSON.parseObject(response);

		if(jsonObject.containsKey("errcode")) {
			throw new ServiceException(HttpStatus.BAD_REQUEST.getStatusCode(), jsonObject.getString("errmsg"));
		}

		T parseObject = JSON.parseObject(response, target);

		return parseObject;
	}

	public String getAccessTokenUrl() {
		return accessTokenUrl;
	}

	public void setAccessTokenUrl(String accessTokenUrl) {
		this.accessTokenUrl = accessTokenUrl;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getGetWxUserUrl() {
		return getWxUserUrl;
	}

	public void setGetWxUserUrl(String getWxUserUrl) {
		this.getWxUserUrl = getWxUserUrl;
	}
}
