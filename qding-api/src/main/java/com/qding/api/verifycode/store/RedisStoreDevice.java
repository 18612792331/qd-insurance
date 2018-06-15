package com.qding.api.verifycode.store;

import com.qding.api.cache.redis.VerifyCodeCache;
import com.qding.api.util.ApplicationContextUtil;
import com.qding.api.verifycode.StoreCode;

public class RedisStoreDevice extends StoreDevice{

	private VerifyCodeCache cache = ApplicationContextUtil.getBeansOfType(VerifyCodeCache.class);
	
	@Override
	public StoreCode get(String key) {
		
		return cache.get(key);
		
	}

	@Override
	public void set(String key, StoreCode value, long expireAt) {
		cache.set(key, value, expireAt);
	}

	@Override
	public String getCode(String key) {

		return cache.getCode(key);
	}

	@Override
	public void setCode(String key, String code, int expireAt) {
		 cache.setCode(key,code,expireAt);
	}


	@Override
	public Long  incr (String key,int expireAt) {
		Long result = cache.incr(key,expireAt);
		return result ;
	}

	@Override
	public Long incr(String key) {
		Long result = cache.incr(key);
		return result ;
	}

	@Override
	public Long sadd (String key,String memberId){
		Long result = cache.sadd(key,memberId);
		return  result;
	}

	@Override
	public Long srem (String key,String memberId) {
		Long result = cache.srem(key,memberId);
		return result;
	}

	@Override
	public long getExpireTime(String key) {
		return cache.getExpireTime(key);
	}

	@Override
	public void delKey(String key) {
		cache.delKey(key);
	}
}
