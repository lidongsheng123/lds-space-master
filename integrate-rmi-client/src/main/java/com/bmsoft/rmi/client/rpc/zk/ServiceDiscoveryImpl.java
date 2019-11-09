package com.bmsoft.rmi.client.rpc.zk;

import com.bmsoft.rmi.client.rpc.loadbalance.LoadBanalce;
import com.bmsoft.rmi.client.rpc.loadbalance.RandomLoadBanalce;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: lds-space-master
 * @description: 服务发现
 * @author: 李东升
 * @create: 2019-04-10 22:21
 **/

public class ServiceDiscoveryImpl implements IServiceDiscovery {
    //服务的列表信息
    List<String> repos = new ArrayList<>();
    private String address;
    private CuratorFramework curatorFramework;

    public ServiceDiscoveryImpl(String address) {
        this.address = address;

        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(address).
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000,
                        10)).build();
        curatorFramework.start();
    }

    @Override
    public String discover(String serviceName) {
        String path = ZkConfig.ZK_REGISTER_PATH + "/" + serviceName;
        try {
            repos = curatorFramework.getChildren().forPath(path);
        } catch (Exception e) {
            throw new RuntimeException("获取子节点异常：" + e);
        }
        //动态发现服务节点的变化
        registerWatcher(path);
        //负载均衡机制
        LoadBanalce loadBanalce = new RandomLoadBanalce();
        //返回调用的服务地址
        return loadBanalce.selectHost(repos);
    }

    //动态发现服务节点的变化
    private void registerWatcher(String path) {
        PathChildrenCache childrenCache = new PathChildrenCache
                (curatorFramework, path, true);
        PathChildrenCacheListener pathChildrenCacheListener = new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                repos = curatorFramework.getChildren().forPath(path);
            }
        };
        childrenCache.getListenable().addListener(pathChildrenCacheListener);
        try {
            childrenCache.start();
        } catch (Exception e) {
            throw new RuntimeException("注册PatchChild Watcher 异常" + e);
        }
    }
}
