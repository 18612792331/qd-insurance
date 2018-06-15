package com.qding.api.cache;

import com.alibaba.fastjson.JSON;

public class JSONSerial {
	
	public String serial(Object obj) {
		return JSON.toJSONString(obj);
	}
	
	public <T> T deserial(String str, Class<T> clazz) {
		if(str == null || str.length() == 0) {
			return null;
		}
		return JSON.parseObject(str, clazz);
	}
}
