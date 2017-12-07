package cn.com.pansky.otp5.common;

import redis.clients.jedis.JedisCluster;

public class RedisUtil {
    
    public static JedisCluster getRedis(){
        return SpringContextHolder.getBean("redisCluster");
    }
    

};
