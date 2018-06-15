package com.qding.api.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;

import com.qding.api.cache.JSONSerial;
import com.qding.api.verifycode.StoreCode;
import com.qding.framework.common.redis.ShardedJedisClient;

public class VerifyCodeCache extends JSONSerial{

	private String redisPath;

	private static ShardedJedisClient client;

	public VerifyCodeCache() {
	}

	/**
	 * spring 注入初始化
	 * @throws Exception
	 */
	public void springInit() throws Exception {
		client = new ShardedJedisClient(redisPath);
	}


	public StoreCode get(String key) {
		String value = client.get(key);
		if(value == null) {
			return null;
		}
		return deserial(value, StoreCode.class);
	}

	public void set(String key, StoreCode value, long expireAt) {
		client.set(key, serial(value));
		int expireSeconds = (int) ((expireAt - System.currentTimeMillis()) / 1000);
		client.expire(key, expireSeconds);
	}

	public String getCode(String key) {
		String value = client.get(key);
		if(value == null) {
			return null;
		}
		return deserial(value,String.class);
	}

	public void setCode(String key, String value, int expireAt) {
		client.set(key, serial(value));
		client.expire(key, expireAt);

	}

	public Long incr (String key,int expireAt) {
		Long result = client.incr(key);
		client.expire(key,expireAt);
		return  result;
	}

	public Long sadd (String key,String memberId){
		Long result = client.sadd(key,memberId);
		return  result;
	}

	public Long srem (String key,String memberId) {
		Long result = client.zrem(key,memberId);
		return result;
	}


	public Long incr (String key) {
		Long result = client.incr(key);
		return  result;
	}

	public Long delKey (String key) {
		Long result =  client.del(key);
		return  result;
	}

	public long getExpireTime (String key) {
		return client.ttl(key);
	}

	public String getRedisPath() {
		return redisPath;
	}

	public void setRedisPath(String redisPath) {
		this.redisPath = redisPath;
	}

	public static ShardedJedisClient getClient() {
		return client;
	}

	public static void setClient(ShardedJedisClient client) {
		VerifyCodeCache.client = client;
	}
}
