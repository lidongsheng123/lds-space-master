package com.bmsoft.distributed.lock.zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @program: lds-space-master
 * @description: curator客户端
 * @author: 李东升
 * @create: 2019-04-10 10:30
 **/
public class CuratorClient {

    public static CuratorFramework getCuratorClient() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("10.21.41.181:2181,10.21.42.47:2181,10.21.49.252:2181", retryPolicy);
        client.start();
        return client;
    }
}
