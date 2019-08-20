package com.bmsoft.io.nio.buffer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @program: lds-space-master
 * @description: Buffer编码解码
 * @author: 李东升
 * @create: 2019-08-20 14:02
 **/
public class BufferDemo {

    /**
     * 编码
     *
     * @param str
     * @throws UnsupportedEncodingException
     */
    public static void deCode(String str) throws UnsupportedEncodingException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        byteBuffer.put(str.getBytes("UTF-8"));
        byteBuffer.flip();


        Charset utf8 = Charset.forName("UTF-8");
        CharBuffer charBuffer = utf8.decode(byteBuffer);
        char[] chars = Arrays.copyOf(charBuffer.array(), charBuffer.limit());
        System.out.println(chars);
    }

    /**
     * 解码
     *
     * @param str
     */
    public static void enCode(String str) {
        CharBuffer charBuffer = CharBuffer.allocate(128);
        charBuffer.append(str);
        charBuffer.flip();

        Charset utf8 = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = utf8.encode(charBuffer);
        byte[] bytes = Arrays.copyOf(byteBuffer.array(), byteBuffer.limit());
        System.out.println(Arrays.toString(bytes));


    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        deCode("北明软件");
        enCode("北明软件");
    }
}
