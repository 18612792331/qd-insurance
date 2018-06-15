package com.qding.api.util;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.client.HessianProxyFactory;
import com.qding.framework.common.constants.Constants.QDResponse;
import com.qding.framework.common.util.QDNumberUtil;
import com.qding.framework.common.util.QDStringUtil;


public class QDMemberRemote {


	private static Logger log = LoggerFactory.getLogger(QDMemberRemote.class);

	@SuppressWarnings("unchecked")
	public static <T> T getService(Class<T> clazz, String serviceUrl) {
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			Object object = factory.create(clazz, serviceUrl);
			if (object != null) {
				return (T) object;
			}
		} catch (MalformedURLException e) {
			log.error("occurs MalformedURLException", e);
		} catch (Exception e) {
			log.error("occurs Exception", e);
		}
		return null;
	}

	public static int responseCode(Map<String, Object> map) {
		return map == null ? null : QDNumberUtil.toInt(map.get("code"));
	}

	public static <T> T entityResult(Map<String, Object> map) {
		return parseResult(map, "entity");
	}

	public static <T> T listResult(Map<String, Object> map) {
		
		T parseResult = parseResult(map, "list");
		
		if(parseResult == null) {
			
			return (T) Collections.emptyList();
		}
		
		return parseResult;
	}
	
	public static <T> T getTotal(Map<String, Object> map) {
		T total = parseResult(map, "total");
		return total;
	}

	@SuppressWarnings("unchecked")
    private static <T> T parseResult(Map<String, Object> reslut, String resultKey) {
		T t = null;
		if (reslut != null) {
			String code = QDStringUtil.toString(reslut.get("code"), "0");
			if (QDStringUtil.equals(QDResponse.CODE_200, code)) {
                Map<String, Object> data = reslut.get("data") == null ? null : (Map<String, Object>) reslut.get("data");
				if (data != null) {
					Object obj = data.get(resultKey);
					if (obj != null) {
						t = (T) obj;
					}
				}
			} else {
				log.error("remote protocol error");
				return null;
			}
		}
		return t;

	}


}
