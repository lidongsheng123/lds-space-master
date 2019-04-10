package com.bmsoft.test.rmi;

import com.bmsoft.test.rmi.handler.ProcessHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @program: lds-space-master
 * @description: 发布服务类
 * @author: 李东升
 * @create: 2019-03-28 16:50
 **/
public class RpcServer {

    private static final Executor executor = Executors.newCachedThreadPool();

    public void publisher(final Object service, int port) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket socket = serverSocket.accept();
                executor.execute(new ProcessHandler(service, socket));
            }
        } catch (Exception e) {
            throw new RuntimeException("链接出现异常");
        } finally {
            try {
                if (serverSocket != null) {
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}