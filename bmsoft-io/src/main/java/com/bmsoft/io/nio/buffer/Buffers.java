package com.bmsoft.io.nio.buffer;

import java.nio.ByteBuffer;

/**
 * @program: lds-space-master
 * @description: 自定义Buffer类中包含读缓冲区和写缓冲区，用于注册通道时的附加对象
 * @author: 李东升
 * @create: 2019-08-20 16:57
 **/
public class Buffers {
    ByteBuffer readBuffer;
    ByteBuffer writeBuffer;

    public Buffers(int readCapacity, int writeCapacity) {
        readBuffer = ByteBuffer.allocate(readCapacity);
        writeBuffer = ByteBuffer.allocate(writeCapacity);
    }

    public ByteBuffer getReadBuffer() {
        return readBuffer;
    }

    public ByteBuffer gerWriteBuffer() {
        return writeBuffer;
    }

}
