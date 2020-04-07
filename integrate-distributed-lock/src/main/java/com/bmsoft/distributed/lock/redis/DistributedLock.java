package com.bmsoft.distributed.lock.redis;

import redis.clients.jedis.BitOP;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author 李东升
 * @version 1.0.0
 * @ClassName DistributedLock.java
 * @Description TODO
 * @createTime 2018年10月26日 12:30:00
 */
public class DistributedLock {

    //获得锁

    /**
     * @param lockName       锁的名词
     * @param acquireTimeout 获得锁的超时时间
     * @param lockTimeout    锁本身的过期时间
     * @return
     */
    public String acquireLock(String lockName, long acquireTimeout, long lockTimeout) {
        String identifier = UUID.randomUUID().toString();//保证释放锁的时候是同一个持有锁的人
        String lockKey = "lock:" + lockName;
        int lockExpire = (int) (lockTimeout / 1000);
        Jedis jedis = null;
        try {
            try {
                jedis = JedisClient.getJedis();
                jedis.setbit("20200401", 1, true);
                jedis.setbit("20200401", 2, true);


            } catch (Exception e) {
                e.printStackTrace();
            }
            long end = System.currentTimeMillis() + acquireTimeout;
            //获取锁的限定时间
            while (System.currentTimeMillis() < end) {
                if (jedis.setnx(lockKey, identifier) == 1) { //设置值成功
                    jedis.expire(lockKey, lockExpire); //设置超时时间
                    return identifier; //获得锁成功
                }
                //
                if (jedis.ttl(lockKey) == -1) {
                    jedis.expire(lockKey, lockExpire); //设置超时时间
                }
                try {
                    //等待片刻后进行获取锁的重试
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            jedis.close(); //回收
        }
        return null;
    }

    public boolean releaseLockWithLua(String lockName, String identifier) {
        System.out.println(lockName + "开始释放锁：" + identifier);

        Jedis jedis = null;
        try {
            jedis = JedisClient.getJedis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String lockKey = "lock:" + lockName;

        String lua = "if redis.call(\"get\",KEYS[1])==ARGV[1] then " +
                "return redis.call(\"del\",KEYS[1]) " +
                "else return 0 end";
        Long rs = (Long) jedis.eval(lua, 1, new String[]{lockKey, identifier});
        if (rs.intValue() > 0) {
            return true;
        }
        return false;

    }

    //释放锁
    public boolean releaseLock(String lockName, String identifier) {
        System.out.println(lockName + "开始释放锁：" + identifier);

        String lockKey = "lock:" + lockName;
        Jedis jedis = null;
        boolean isRelease = false;
        try {
            jedis = JedisClient.getJedis();
            while (true) {
                jedis.watch(lockKey);
                //判断是否为同一把锁
                if (identifier.equals(jedis.get(lockKey))) {
                    Transaction transaction = jedis.multi();
                    transaction.del(lockKey);
                    if (transaction.exec().isEmpty()) {
                        continue;
                    }
                    isRelease = true;
                }
                //TODO 异常
                jedis.unwatch();
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jedis.close();
        }
        return isRelease;
    }

    private void updateUser(long userId, String time) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        time = sdf.format(new Date());
        JedisClient jedis = new JedisClient();


    }

    public static void main(String[] args) throws Exception {
        Jedis jedis = JedisClient.getJedis();
        jedis.setbit("20200401", 1, true);
        jedis.setbit("20200401", 2, true);
        jedis.setbit("20200402", 1, true);
        jedis.setbit("20200403", 2, true);

        //Long count = jedis.bitcount("20200401");
        jedis.bitop(BitOP.OR,"result","20200401","20200403");



    }


}
