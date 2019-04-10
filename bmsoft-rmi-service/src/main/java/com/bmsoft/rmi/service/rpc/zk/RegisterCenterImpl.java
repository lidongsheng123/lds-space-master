package com.bmsoft.rmi.service.rpc.zk;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * 腾讯课堂搜索 咕泡学院
 * 加群获取视频：608583947
 * 风骚的Michael 老师
 */
public class RegisterCenterImpl implements IRegisterCenter {

    private CuratorFramework curatorFramework;

    /**
     * 构造代码块实现curator初始化
     */ {
        curatorFramework = CuratorFrameworkFactory.builder().
                connectString(ZkConfig.CONNNECTION_STR).
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000,
                        10)).build();
        curatorFramework.start();
    }

    /**
     * 功能描述:
     *
     * @param:
     * @param: serviceName
     * @param: serviceAddress
     * @return: void
     * @auther: 李东升
     * @date: 2019/4/10 21:13
     */
    @Override
    public void register(String serviceName, String serviceAddress) {

        String servicePath = ZkConfig.ZK_REGISTER_PATH + "/" + serviceName;

        try {
            //判断 /registrys/product-service是否存在，不存在则创建
            if (curatorFramework.checkExists().forPath(servicePath) == null) {
                curatorFramework.create().creatingParentsIfNeeded().
                        withMode(CreateMode.PERSISTENT).forPath(servicePath, "0".getBytes());
            }
            // /registrys/serviceName/127.0.0.1:8080
            // /registrys/serviceName/127.0.0.1:8081
            String addressPath = servicePath + "/" + serviceAddress;
            String rsNode = curatorFramework.create().withMode(CreateMode.EPHEMERAL).
                    forPath(addressPath, "0".getBytes());
            System.out.println("服务注册成功：" + rsNode);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
