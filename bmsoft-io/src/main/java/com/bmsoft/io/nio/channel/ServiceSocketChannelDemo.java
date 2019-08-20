package com.bmsoft.io.nio.channel;

import com.bmsoft.io.nio.buffer.Buffers;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @program: lds-space-master
 * @description: NIO服务端
 * @author: 李东升
 * @create: 2019-08-20 16:21
 **/
public class ServiceSocketChannelDemo {
    public static class TCPEchoServer implements Runnable {
        //服务器地址
        private InetSocketAddress localAddress;

        public TCPEchoServer(int port) {
            localAddress = new InetSocketAddress(port);
        }

        @Override
        public void run() {
            Charset utf8 = Charset.forName("UTF-8");
            Selector selector = null;
            ServerSocketChannel serverSocketChannel = null;
            try {
                //创建选择器
                selector = Selector.open();
                /*创建服务器通道*/
                serverSocketChannel = ServerSocketChannel.open();
                /*设置非阻塞*/
                serverSocketChannel.configureBlocking(false);
                /*绑定IP+Port*/ /*设置最大链接缓冲区为100*/
                serverSocketChannel.bind(localAddress, 100);
               /* 将chanel注册到选择器上  TCP 事件*/
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            } catch (IOException e) {
                System.out.println("Server start failed");
                return;
            }
            System.out.println("Server start with address :" + localAddress.toString());

            try {
                while (!Thread.currentThread().isInterrupted()) {
                    int n = selector.select();
                    if (n == 0) continue;
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> it = selectionKeys.iterator();
                    SelectionKey key = null;
                    try {
                        while (it.hasNext()) {
                            key = it.next();
                       /* 将处理过的key删除*/
                            it.remove();
                            if (key.isAcceptable()) {
                                SocketChannel sc = serverSocketChannel.accept();
                                sc.configureBlocking(false);
                                int read = SelectionKey.OP_READ;
                                SelectionKey register = sc.register(selector, read, new Buffers(256, 256));
                                System.out.println("accept from " + sc.getRemoteAddress());
                            }
                            /*通道是读事件*/
                            if (key.isReadable()) {
                            /*通过SelectionKey获取通道对应的缓冲区*/
                                Buffers buffers = (Buffers) key.attachment();
                                ByteBuffer readBuffer = buffers.getReadBuffer();
                                ByteBuffer writeBuffer = buffers.gerWriteBuffer();

                            /*通过SelectionKey获取对应的通道*/
                                SocketChannel sc = (SocketChannel) key.channel();

                            /*从底层socket读缓冲区中读入数据*/
                                sc.read(readBuffer);
                                readBuffer.flip();

                            /*解码显示，客户端发送来的信息*/
                                CharBuffer cb = utf8.decode(readBuffer);
                                System.out.println(cb.array());
                                readBuffer.rewind();

                                //向客户端写的操作
                                writeBuffer.put("echo from service:".getBytes("UTF-8"));
                                writeBuffer.put(readBuffer);
                                readBuffer.clear();
                              /*设置通道写事件*/
                                key.interestOps(key.interestOps() | SelectionKey.OP_WRITE);
                            }

                         /*通道是写事件*/
                            if (key.isWritable()) {
                                Buffers buffers = (Buffers) key.attachment();
                                ByteBuffer writeBuffer = buffers.gerWriteBuffer();
                                writeBuffer.flip();
                                SocketChannel sc = (SocketChannel) key.channel();
                                int len = 0;
                                while (writeBuffer.hasRemaining()) {
                                    len = sc.write(writeBuffer);
                                    /*说明底层的socket写缓冲已满*/
                                    if (len == 0) {
                                        break;
                                    }
                                }
                                writeBuffer.compact();
                                /*说明数据全部写入到底层的socket写缓冲区*/
                                if (len != 0) {
                                    /*取消通道的写事件*/
                                    key.interestOps(key.interestOps() & (~SelectionKey.OP_WRITE));
                                }
                            }
                        }
                    } catch (IOException e) {
                        key.cancel();
                        key.channel().close();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    selector.close();
                } catch (IOException e) {
                    System.out.println("selector close failed");
                } finally {
                    System.out.println("server close");
                }
            }

        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        Thread thread = new Thread(new TCPEchoServer(8080));
        thread.start();
        Thread.sleep(100000);
        /*结束服务器线程*/
        thread.interrupt();
    }

}
