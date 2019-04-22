package com.bmsoft.breakpoint.download.thread;

import com.bmsoft.breakpoint.download.entity.MultiTheradDownLoad;

import java.io.*;
import java.net.HttpURLConnection;
import java.util.concurrent.CountDownLatch;

/**
 * @program: lds-space-master
 * @description: 多线程处理类
 * @author: 李东升
 * @create: 2019-04-18 11:11
 **/
public class DownLoadThread implements Runnable {

    private int id;
    private long startPos;
    private long endPos;
    private MultiTheradDownLoad task;
    private RandomAccessFile downloadfile;
    private RandomAccessFile rantmpfile;
    private File tmpfile;
    private CountDownLatch latch;

    public DownLoadThread(long startPos, long endPos,
                          MultiTheradDownLoad task, int id, File tmpfile,
                          CountDownLatch latch) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.task = task;
        this.tmpfile = tmpfile;
        try {
            //
            this.downloadfile = new RandomAccessFile(this.task.getFileName(), "rw");
            this.rantmpfile = new RandomAccessFile(this.tmpfile, "rw");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.id = id;
        this.latch = latch;
    }

    @Override
    public void run() {
        HttpURLConnection httpcon = null;
        InputStream is = null;
        int length = 0;

        System.out.println("线程" + id + " 开始下载!!");

        while (true) {
            try {
                httpcon = (HttpURLConnection) task.getUrl().openConnection();
                httpcon.setRequestMethod("GET");

                //防止网络阻塞，设置指定的超时时间；单位都是ms。超过指定时间，就会抛出异常
                httpcon.setReadTimeout(20000);//读取数据的超时设置
                httpcon.setConnectTimeout(20000);//连接的超时设置

                if (startPos < endPos) {

                    //向服务器请求指定区间段的数据，这是实现断点续传的根本。
                    httpcon.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);

                    System.out.println("线程 " + id + " 长度:---- " + (endPos - startPos));

                    downloadfile.seek(startPos);

                    if (httpcon.getResponseCode() != HttpURLConnection.HTTP_OK
                            && httpcon.getResponseCode() != HttpURLConnection.HTTP_PARTIAL) {
                        this.task.setBool(true);
                        httpcon.disconnect();
                        downloadfile.close();
                        System.out.println("线程 ---" + id + " 下载完成!!");
                        latch.countDown();//计数器自减
                        break;
                    }
                    //获取服务器返回的资源流
                    is = httpcon.getInputStream();
                    long count = 0L;
                    byte[] buf = new byte[1024];

                    while (!this.task.isBool() && (length = is.read(buf)) != -1) {
                        count += length;
                        downloadfile.write(buf, 0, length);

                        //不断更新每个线程下载资源的起始位置，并写入临时文件；为断点续传做准备
                        startPos += length;
                        rantmpfile.seek(8 * id + 8);
                        rantmpfile.writeLong(startPos);
                    }
                    System.out.println("线程 " + id
                            + " 总下载大小: " + count);

                    //关闭流
                    is.close();
                    httpcon.disconnect();
                    downloadfile.close();
                    rantmpfile.close();
                }
                latch.countDown();//计数器自减
                System.out.println("线程 " + id + " 下载完成!!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null) {
                        is.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

