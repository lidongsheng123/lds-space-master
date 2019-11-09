package com.bmsoft.distributed.lock.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName RedissonClient.java
 * @Description TODO
 * @createTime 2018年10月26日 14:10:00
 */
public class RedissonClient {

    public static org.redisson.api.RedissonClient getRedissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        return Redisson.create(config);
    }


    public static void main(String[] args) {
        org.redisson.api.RedissonClient redissonClient = RedissonClient.getRedissonClient();
        RLock lock = redissonClient.getLock("");
        lock.lock();
        boolean locked = lock.isLocked();
        lock.unlock();
    }

}
