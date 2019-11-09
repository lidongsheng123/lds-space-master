package com.bmsoft.test.rmi;

import lombok.extern.slf4j.Slf4j;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @program: lds-space-master
 * @description: 协议层
 * @author: 李东升
 * @create: 2019-03-28 17:13
 **/
@Slf4j
public class TcpTransport {

    private String host;
    private int port;

    public TcpTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }

    private Socket newSocket() {
        Socket socket = null;
        try {
            socket = new Socket(host, port);
        } catch (Exception e) {
            throw new RuntimeException("链接失败");
        }
        return socket;
    }

    public Object send(RpcRequest rpcRequest) {
        Socket socket = null;
        try {
            socket = newSocket();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object result = objectInputStream.readObject();

            objectOutputStream.close();
            objectInputStream.close();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            log.info("关闭连接");
        }
        return null;
    }
}
