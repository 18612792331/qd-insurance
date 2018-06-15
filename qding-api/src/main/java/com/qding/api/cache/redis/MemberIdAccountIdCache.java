package com.qding.api.cache.redis;

import com.qding.api.util.APIPropertiesClient;
import com.qding.framework.common.redis.ShardedJedisClient;

public class MemberIdAccountIdCache {

    private String redisPath;

    private static ShardedJedisClient client;

    private int expireMonth;

    public MemberIdAccountIdCache() {
    }

    /**
     * spring 注入初始化
     *
     * @throws Exception
     */
    public void springInit() throws Exception {
        client = new ShardedJedisClient(redisPath);
        expireMonth = Integer.parseInt(APIPropertiesClient.getValue("mid.accountId.expire.month"));
    }


    public String get(String key) {
        String value = client.get(key);
        if (value == null) {
            return null;
        }
        return value;
    }

    public void set(String key, String value) {
        client.set(key, value);
        client.expire(key, expireMonth * 30 * 24 * 60 * 60);//失效时间暂定3个月
    }


    public void setRedisPath(String redisPath) {
        this.redisPath = redisPath;
    }

    public static ShardedJedisClient getClient() {
        return client;
    }

    public static void setClient(ShardedJedisClient client) {
        MemberIdAccountIdCache.client = client;
    }
}
