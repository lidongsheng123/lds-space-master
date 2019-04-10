package com.bmsoft.rmi.service.rpc;

import com.bmsoft.rmi.service.rpc.anno.RpcAnnotation;
import com.bmsoft.rmi.service.rpc.zk.IRegisterCenter;
import org.apache.commons.lang.StringUtils;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: lds-space-master
 * @description: 服务发布核心代码
 * @author: 李东升
 * @create: 2019-04-10 21:15
 **/
public class RpcServer {
    //创建一个线程池
    private static final ExecutorService executorService= Executors.newCachedThreadPool();
    //注册中心
    private IRegisterCenter registerCenter;

    //服务发布地址
    private String serviceAddress;

    // 存放服务名称和服务对象之间的关系
    Map<String, Object> handlerMap = new ConcurrentHashMap<>();

    public RpcServer(IRegisterCenter registerCenter, String serviceAddress) {
        this.registerCenter = registerCenter;
        this.serviceAddress = serviceAddress;
    }

    /**
     * 绑定服务名称和服务对象
     *
     * @param services
     */
    public void bind(Object... services) {
        for (Object service : services) {
            RpcAnnotation rpcAnnotation = service.getClass().getAnnotation(RpcAnnotation.class);
            //获取发布的服务名称
            String serviceName = rpcAnnotation.value().getName();
            //获取版本号
            String version = rpcAnnotation.version();

            if (StringUtils.isNotBlank(version)) {
                serviceName = serviceName + "-" + version;
            }
            //绑定服务接口名称对应的服务
            handlerMap.put(serviceName, service);
        }
    }

    /**
     * 功能描述:发布服务
     *
     * @auther: 李东升
     * @date: 2019/4/10 21:16
     */
    public void publisher() {
        ServerSocket serverSocket = null;
        try {
            //分割注册地址  127.0.0.1:8080
            String[] addrs = serviceAddress.split(":");
            //启动一个serverSocket 监听一个端口
            serverSocket=new ServerSocket(Integer.parseInt(addrs[1]));
            //将服务注册到注册中心
            for (String serviceName: handlerMap.keySet()){
                 registerCenter.register(serviceName,serviceAddress);
                System.out.println("注册服务成功："+serviceName+"->"+serviceAddress);
            }
            while (true){
                Socket socket = serverSocket.accept();
                executorService.execute( new ProcessorHandler(socket, handlerMap));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
