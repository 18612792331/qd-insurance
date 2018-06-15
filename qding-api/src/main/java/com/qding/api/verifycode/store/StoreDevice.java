package com.qding.api.verifycode.store;

import com.qding.api.verifycode.StoreCode;


public abstract class StoreDevice {
	
	public abstract StoreCode get(String key);
	
	public abstract void set(String key, StoreCode value, long expireAt);

	public abstract String getCode(String key);

	public abstract  void setCode (String key,String code,int expireAt);

	public abstract Long incr (String key,int expireAt);

	public abstract Long incr (String key);

	public abstract long getExpireTime (String key);

	public abstract void delKey (String key);

	public abstract Long sadd (String key,String memberId);

	public abstract Long srem (String key,String memberId);
	
}
