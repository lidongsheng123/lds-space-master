package com.bmsoft.test.rmi.handler;

import com.bmsoft.test.rmi.RpcRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @program: lds-space-master
 * @description: 任务处理类
 * @author: 李东升
 * @create: 2019-03-28 16:56
 **/
public class ProcessHandler implements Runnable {

    private Socket socket;
    private Object service;

    public ProcessHandler(Object service, Socket socket) {
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            Object result = invoke(rpcRequest);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(result);
            objectOutputStream.flush();
        } catch (Exception e) {
            throw new RuntimeException("出现异常");
        } finally {
            try {
                objectInputStream.close();
                objectOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Object invoke(RpcRequest rpcRequest) throws Exception {
        Object[] args = rpcRequest.getParamters();
        Class[] types = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            types[i] = args[i].getClass();
        }
        Method method = service.getClass().getMethod(rpcRequest.getMethodName(), types);
        return method.invoke(service, args);
    }
}
