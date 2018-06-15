package com.qding.api.cache.redis;

import com.qding.framework.common.redis.ShardedJedisClient;

public class LongForWheelCache{

	private String redisPath;

	private static ShardedJedisClient client;

	public LongForWheelCache() {
	}

	/**
	 * spring 注入初始化
	 * @throws Exception
	 */
	public void springInit() throws Exception {
		client = new ShardedJedisClient(redisPath);
	}


	public Object get(String key) {
	    
		return client.get(key);
	}

	public void set(String key, Object value, long expireAt) {
		client.set(key, value.toString());
		int expireSeconds = (int) ((expireAt - System.currentTimeMillis()) / 1000);
		client.expire(key, expireSeconds);
	}


	public Long incr (String key,int expireAt) {
		Long result = client.incr(key);
		client.expire(key,expireAt);
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
		LongForWheelCache.client = client;
	}
}
