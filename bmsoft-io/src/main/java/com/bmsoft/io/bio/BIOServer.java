package com.bmsoft.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: lds-space-master
 * @description: BIO服务端
 * 同步跟异步最大的区别就是:数据访问进程的时候是否阻塞。
 * @author: 李东升
 * @create: 2019-08-19 14:03
 **/
public class BIOServer {

    //默认的端口号
    private static int DEFAULT_PORT = 7777;
    //单例的ServerSocket
    private static ServerSocket serverSocket;

    //根据传入参数设置监听端口，如果没有参数调用以下方法并使用默认值
    public static void start() throws IOException {
        //使用默认值
        start(DEFAULT_PORT);
    }

    //这个方法不会被大量并发访问，不太需要考虑效率，直接进行方法同步就行了
    public synchronized static void start(int port) throws IOException {
        if (serverSocket != null) return;
        try {
            //通过构造函数创建ServerSocket
            //如果端口合法且空闲，服务端就监听成功
            serverSocket = new ServerSocket(port);
            System.out.println("服务端已启动，端口号:" + port);

            //通过无线循环监听客户端连接
            //如果没有客户端接入，将阻塞在accept操作上。
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        } finally {
            if (serverSocket != null) {
                System.out.println("服务端已关闭。");
                serverSocket.close();
                serverSocket = null;
            }
        }

    }


}
